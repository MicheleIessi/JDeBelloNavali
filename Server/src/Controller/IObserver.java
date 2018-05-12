package Controller;

import Communicator.DTO.IMessageDTO;

public interface IObserver {

    void update(IController controller);

}
