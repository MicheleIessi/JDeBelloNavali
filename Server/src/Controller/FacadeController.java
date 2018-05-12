package Controller;

import Communicator.DTO.IMessageDTO;

import java.util.ArrayList;

/**
 * Class FacadeController represents the main controller for the server application.
 * Its purpose is to redirect any incoming message to the specific controller.
 */
public class FacadeController implements IController {

    private String function;
    private ArrayList<IObserver> observerList;

    public FacadeController() {
        this.observerList = new ArrayList<>();
    }

    public String getFunction() {
        return this.function;
    }

    public void attachObserver(IObserver observer) {
        observerList.add(observer);
    }

    public void detachObserver(IObserver observer) {
        observerList.remove(observer);
    }

    public void notifyObservers() {
        for (IObserver observer : observerList) {
            observer.update(this);
        }
    }

    @Override
    public void sendAnswerDTO(IMessageDTO dto) {

    }

    public void incomingRequest(IMessageDTO incomingDTO) {
        this.function = incomingDTO.getFunctionString();
        System.out.printf("Incoming request...%n");
        notifyObservers();
    }

}
