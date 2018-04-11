package src.Communicator;

import DTO.IMessageDTO;
import src.Controller.FacadeClientController;

import java.io.IOException;

public interface ITransceiver {

    void initializeTransceiver(String host, int port) throws IOException, ClassNotFoundException;
    void attachFacadeController(FacadeClientController controller);
    void closeConnection() throws IOException;
    IMessageDTO sendDTO(IMessageDTO dto) throws IOException, ClassNotFoundException;

}
