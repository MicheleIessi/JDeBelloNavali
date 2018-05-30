package com.debellonavali.Classes.Controller;

import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Controller.Observers.IObserverController;

import java.util.ArrayList;
import java.util.logging.Logger;

public class FacadeClientController implements IClientController {

    private String function;
    private IDTO dto;
    private ArrayList<IObserverController> clientObservers;

    public FacadeClientController() {
        this.clientObservers = new ArrayList<>();
    }

    @Override
    public String getFunction() {
        return this.function;
    }

    @Override
    public IDTO getDTO() {
        return this.dto;
    }

    @Override
    public void attachObserver(IObserverController observer) {
        this.clientObservers.add(observer);
    }

    @Override
    public void detachObserver(IObserverController observer) {
        this.clientObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserverController observer : clientObservers) {
            observer.update(this);
        }
    }

    @Override
    public void incomingRequest(IDTO incomingDTO) {
        this.function = incomingDTO.getFunctionString();
        this.dto = incomingDTO;
        Logger.getAnonymousLogger().info("DTO received");
        notifyObservers();
    }

    @Override
    public String toString() {
        return "FacadeClientController{" +
                "function='" + function + '\'' +
                ", dto=" + dto +
                ", clientObservers=" + clientObservers +
                '}';
    }
}
