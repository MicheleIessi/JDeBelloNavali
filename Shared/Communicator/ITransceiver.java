package Communicator;

import DTO.IMessageDTO;

import java.io.IOException;

public interface ITransceiver {

    void initializeTransceiver(String host, int port) throws IOException, ClassNotFoundException;
    void closeConnection() throws IOException;
    IMessageDTO sendDTO(IMessageDTO dto) throws IOException, ClassNotFoundException;

}
