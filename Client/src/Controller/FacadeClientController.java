package src.Controller;

import DTO.IMessageDTO;

import java.util.ArrayList;

public class FacadeClientController implements IClientController {

    private String function;
    private ArrayList<IClientObserver> clientObservers;

    public FacadeClientController() {
        this.clientObservers = new ArrayList<>();
    }

    @Override
    public String getFunction() {
        return this.function;
    }

    @Override
    public void attachObserver(IClientObserver observer) {
        this.clientObservers.add(observer);
    }

    @Override
    public void detachObserver(IClientObserver observer) {
        this.clientObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IClientObserver observer : clientObservers) {
            observer.update(this);
        }
    }

    public void incomingRequest(IMessageDTO incomingDTO) {
        this.function = incomingDTO.getFunctionString();
        System.out.printf("Server sent message %s%n", this.function);
        notifyObservers();
    }
}
