import Communicator.AnswerContainer;
import Controller.FacadeController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import Communicator.DTO.IMessageDTO;

public class ConnectionListener {

    private ServerSocket serverSocket = null;
    private int socketPort = 1234;
    private FacadeController facadeController;

    public void setFacadeController(FacadeController facadeController) {
        this.facadeController = facadeController;
    }

    public void startServer() throws IOException {

        try {
            System.out.printf("Starting server on port %d...%n", socketPort);
            serverSocket = new ServerSocket(socketPort);
            System.out.printf("Server started on port %d.%n", socketPort);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket accessSocket = null;

        while (true) {
            try {
                accessSocket = serverSocket.accept();
                System.out.printf("Client accepted from: %s:%d%n",
                        accessSocket.getInetAddress().getHostName(),
                        accessSocket.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectInputStream objectInputStream = new ObjectInputStream(accessSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(accessSocket.getOutputStream());

            IMessageDTO messageDTO;
            IMessageDTO answerDTO;
            try {

                messageDTO = (IMessageDTO) objectInputStream.readObject();
                System.out.println("Messaggio ricevuto:" + messageDTO.getFunctionString());
                facadeController.incomingRequest(messageDTO);
                answerDTO = AnswerContainer.getInstance().getStoredDTO();
                System.out.printf("Answering with DTO message '%s'...%n", answerDTO.getFunctionString());
                objectOutputStream.writeObject(answerDTO);
                objectOutputStream.flush();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                accessSocket.close();
            }
        }
    }
}
