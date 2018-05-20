package Controller;

import Communicator.DTO.IMessageDTO;

public interface IController {

    String getFunction();
    // Observer methods
    void attachObserver(IObserver observer);
    void detachObserver(IObserver observer);
    void notifyObservers();

    // Answer method?
    void sendAnswerDTO(IMessageDTO dto);
}
