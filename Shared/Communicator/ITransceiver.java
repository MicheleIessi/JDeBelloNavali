package Communicator;

import DTO.IMessageDTO;

import java.io.IOException;

public interface ITransceiver {

    public boolean initializeTransceiver(String host, int port) throws IOException, ClassNotFoundException;
    public void closeConnection() throws IOException;
    public IMessageDTO sendDTO(IMessageDTO dto) throws IOException, ClassNotFoundException;

}
