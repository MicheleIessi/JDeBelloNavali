package com.debellonavali.Classes.Controller;

import com.debellonavali.Classes.Communicator.DTO.IMessageDTO;

import java.util.ArrayList;
import java.util.logging.Logger;

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
        Logger.getAnonymousLogger().info("DTO received");
        notifyObservers();
    }
}
