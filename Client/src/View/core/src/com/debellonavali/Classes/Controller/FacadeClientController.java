package com.debellonavali.Classes.Controller;

import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Controller.Observers.IObserverController;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * FacadeClientController is the unique access point of a socket communication to the system
 */
public class FacadeClientController implements IClientController {

    private String function;
    private IDTO dto;
    private ArrayList<IObserverController> clientObservers;

    /**
     * Default constructor. It instantiates an empty ArrayList of IObserverController objects.
     */
    public FacadeClientController() {
        this.clientObservers = new ArrayList<>();
    }

    /**
     * Returns the function of the last received communication
     * @return The function of the last received communication
     */
    @Override
    public String getFunction() {
        return this.function;
    }

    /**
     * Returns the DTO contained in the last received communication
     * @return The DTO contained in the last received communication
     */
    @Override
    public IDTO getDTO() {
        return this.dto;
    }

    /**
     * Attaches an IObserverController object to this Facade
     * @param observer The IObserverController to be attached
     */
    @Override
    public void attachObserver(IObserverController observer) {
        this.clientObservers.add(observer);
    }

    /**
     * Detaches an IObserverController object form this Facade
     * @param observer The IObserverController to be detached
     */
    @Override
    public void detachObserver(IObserverController observer) {
        this.clientObservers.remove(observer);
    }

    /**
     * Notifies all IObserverController attached to this Facade
     */
    @Override
    public void notifyObservers() {
        for (IObserverController observer : clientObservers) {
            observer.update(this);
        }
    }

    /**
     * Processes an incoming DTO object and notifies the IObserverController attached to this Facade
     * @param incomingDTO
     */
    @Override
    public void incomingRequest(IDTO incomingDTO) {
        this.function = incomingDTO.getFunctionString();
        this.dto = incomingDTO;
        Logger.getAnonymousLogger().info("DTO received");
        notifyObservers();
    }
}
