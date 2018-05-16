package com.debellonavali.Classes.Communicator;

import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Model.DeBelloGame;
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
    private ServerSocket serverSocket;
    private Socket socket;
    private String enemyHost;
    private int enemyPort;
    private Logger logger;

    private ConnectionManagerImpl() {
        logger = Logger.getLogger(this.getClass().getSimpleName());
    }

    public void initialize(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
            logger.info(String.format(Locale.ITALIAN,
                    "Starting Client serversocket at address %s:%d...",
                    serverSocket.getInetAddress().getHostName(),
                    serverSocket.getLocalPort()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setEnemyInformation(String host, int port) {
        this.enemyHost = host;
        this.enemyPort = port;
    }

    public void startMonitoringThread() {
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
                                IDTO incomingDTO = (IDTO) incomingDTOStream.readObject();
                                logger.info(String.format(Locale.getDefault(), "Incoming DTO message with function %s", incomingDTO.getFunctionString()));
                                IDTO outgoingDTO = DeBelloGame.getInstance().incomingMessage(incomingDTO);
                                outgoingDTOStream.writeObject(outgoingDTO);
                                outgoingDTOStream.flush();
                                logger.info(String.format(Locale.getDefault(), "Outgoing DTO message with function %s", outgoingDTO.getFunctionString()));
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        listeningThread.start();
    }

    public void sendMessage(IDTO dto) {
        try {
            Socket sendingSocket = new Socket(enemyHost, enemyPort);
            ObjectOutputStream outputStream = new ObjectOutputStream(sendingSocket.getOutputStream());
            outputStream.writeObject(dto);
            outputStream.flush();
            logger.info(String.format(Locale.getDefault(), "Outgoing DTO message with function %s", dto.getFunctionString()));
        } catch (IOException ex) {
            logger.warning(Constants.CONNECTION_ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }


    public static ConnectionManagerImpl getInstance() {
        if (instance == null) {
            instance = new ConnectionManagerImpl();
        }
        return instance;
    }
}
