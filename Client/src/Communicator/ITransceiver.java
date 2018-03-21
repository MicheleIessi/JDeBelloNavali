package Communicator;

import Controller.FacadeClientController;
import Controller.IClientController;
import DTO.IMessageDTO;

import java.io.IOException;

public interface ITransceiver {

    void initializeTransceiver(String host, int port) throws IOException, ClassNotFoundException;
    void attachFacadeController(FacadeClientController controller);
    void closeConnection() throws IOException;
    IMessageDTO sendDTO(IMessageDTO dto) throws IOException, ClassNotFoundException;

}
