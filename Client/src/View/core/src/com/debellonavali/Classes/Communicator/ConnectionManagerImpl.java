package com.debellonavali.Classes.Communicator;

import com.debellonavali.Classes.Communicator.DTO.DTO;
import com.debellonavali.Classes.Communicator.DTO.IMessageDTO;
import com.debellonavali.Classes.Controller.FacadeClientController;
import com.debellonavali.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;
import java.util.logging.Logger;


public class ConnectionManagerImpl implements IConnectionManager{

    private static ConnectionManagerImpl instance = null;
    private FacadeClientController facadeClientController;
    private ServerSocket serverSocket;
    private Socket socket;
    private Logger logger;

    private ConnectionManagerImpl() {
        logger = Logger.getLogger(this.getClass().getSimpleName());
    }

    public void initialize(int portNumber) {
        try {
            this.serverSocket = new ServerSocket(portNumber);
            logger.info(String.format(Locale.ITALIAN,
                    "Starting Client serversocket at address %s:%d...",
                    serverSocket.getInetAddress().getHostName(),
                    serverSocket.getLocalPort()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void attachFacadeController(FacadeClientController facadeClientController) {
        this.facadeClientController = facadeClientController;
    }

    public synchronized void startMonitoringThread() {
        Thread listeningThread = new Thread(
                () -> {
                    while (true) {
                        try {
                            socket = serverSocket.accept();
                            logger.info(String.format(Locale.getDefault(),
                                    "Accepting client socket from address %s:%d",
                                    socket.getInetAddress().getHostName(),
                                    socket.getPort()));

                            ObjectInputStream incomingDTOStream = new ObjectInputStream(socket.getInputStream());
                            ObjectOutputStream outgoingDTOStream = new ObjectOutputStream(socket.getOutputStream());
                            try {
                                IMessageDTO incomingDTO;
                                IMessageDTO outgoingDTO;

                                incomingDTO = (IMessageDTO) incomingDTOStream.readObject();
                                logger.info(String.format(Locale.getDefault(),
                                        "Incoming DTO message with function %s",
                                        incomingDTO.getFunctionString()));
                                facadeClientController.incomingRequest(incomingDTO);

                                outgoingDTO = AnswerContainer.getInstance().getStoredDTO();
                                outgoingDTOStream.writeObject(outgoingDTO);
                                outgoingDTOStream.flush();

                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } finally {
                                incomingDTOStream.close();
                                outgoingDTOStream.close();
                                socket.close();
                                logger.info(String.format(Locale.getDefault(),
                                        "Closing connection with client %s:%d",
                                        socket.getInetAddress().getHostName(),
                                        socket.getPort()));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        listeningThread.start();
    }

    public void sendMessage(IMessageDTO dto, String enemyHost, int enemyPort) {
        IMessageDTO sendingDTO = new DTO("Attack");
        try {
            this.socket = new Socket(enemyHost, enemyPort);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(sendingDTO);
            outputStream.flush();
        } catch (IOException ex) {
            logger.warning(Constants.CONNECTION_ERROR_MESSAGE);
        }

    }


    public static ConnectionManagerImpl getInstance() {
        if (instance == null) {
            // Still needs to be initialized for the desider server port number
            instance = new ConnectionManagerImpl();
        }
        return instance;
    }
}
