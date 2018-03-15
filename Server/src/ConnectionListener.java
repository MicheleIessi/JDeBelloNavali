import Controller.FacadeController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import SharedUtils.MessageDTO;

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
                System.out.printf("Client accepted: %d%n", accessSocket.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectInputStream objectInputStream = new ObjectInputStream(accessSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(accessSocket.getOutputStream());
            MessageDTO messageDTO = null;

            try {

                messageDTO = (MessageDTO) objectInputStream.readObject();
                System.out.println(messageDTO);
                String functionString = messageDTO.getFunctionString();

                System.out.printf("String received from Client %d: %s%n", accessSocket.getPort(), functionString);


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }



        }
    }
}
