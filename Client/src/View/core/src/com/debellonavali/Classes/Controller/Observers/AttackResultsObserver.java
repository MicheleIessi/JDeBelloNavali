package com.debellonavali.Classes.Controller.Observers;

import com.debellonavali.Classes.Communicator.DTO.DTOMessages;
import com.debellonavali.Classes.Controller.IClientController;

public class AttackResultsObserver implements IObserverController {

    @Override
    public void update(IClientController controller) {
        if(controller.getFunction().equalsIgnoreCase(DTOMessages.ATTACK_RESULT_MESSAGE)) {
            System.out.println("HO FINALMENTE RICEVUTO LA RISPOSTA ALL'ATTACCO");
        }
    }
}
