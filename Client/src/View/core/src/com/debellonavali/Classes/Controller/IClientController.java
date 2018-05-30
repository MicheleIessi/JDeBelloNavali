package com.debellonavali.Classes.Controller;

import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Controller.Observers.IObserverController;

public interface IClientController {

    String getFunction();
    IDTO getDTO();
    // Observer methods
    void attachObserver(IObserverController observer);
    void detachObserver(IObserverController observer);
    void notifyObservers();
    void incomingRequest(IDTO dto);


}
