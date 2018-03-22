package Communicator;

import Controller.FacadeClientController;
import Controller.IClientController;
import DTO.DTOBuilder;
import DTO.IMessageDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DTOTransceiver implements ITransceiver {

    private String destinationHost = null;
    private int destinationPort;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Socket connectionSocket;
    private FacadeClientController facadeClientController;

    public DTOTransceiver() {}

    @Override
    public void attachFacadeController(FacadeClientController facadeController) {
        this.facadeClientController = facadeController;
    }

    @Override
    public void initializeTransceiver(String host, int port) throws IOException, ClassNotFoundException {
        System.out.printf("Trying to connect to %s:%d...%n", host, port);
        this.destinationHost = host;
        this.destinationPort = port;
        try {
            connectionSocket = new Socket(destinationHost, destinationPort);
            objectOutputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(connectionSocket.getInputStream());

        } catch (IOException e) {
            System.out.printf("Connection impossible to destination %s:%d%n", destinationHost, destinationPort);
        }
    }

    public void closeConnection() throws IOException {
        this.objectInputStream.close();
        this.objectOutputStream.close();
        this.connectionSocket.close();
    }

    @Override
    public IMessageDTO sendDTO(IMessageDTO dto) throws IOException, ClassNotFoundException {

        objectOutputStream.writeObject(dto);
        objectOutputStream.flush();
        System.out.println("Message sent");
        IMessageDTO replyDTO = (IMessageDTO) objectInputStream.readObject();
        facadeClientController.incomingRequest(replyDTO);
        return replyDTO;
    }

}