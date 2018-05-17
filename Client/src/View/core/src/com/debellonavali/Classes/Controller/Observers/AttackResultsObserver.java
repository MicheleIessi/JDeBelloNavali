package com.debellonavali.Classes.Controller.Observers;

import com.debellonavali.Classes.Communicator.DTO.DTOMessages;
import com.debellonavali.Classes.Controller.IClientController;

/**
 * AttackResultsObserver is the controller responsible of handling the response send by the enemy
 * after a player's attack. It modifies the player's battlefield and triggers ship integrity updates.
 */
public class AttackResultsObserver implements IObserverController {

    @Override
    public void update(IClientController controller) {
        if(controller.getFunction().equalsIgnoreCase(DTOMessages.ATTACK_RESULT_MESSAGE)) {
            System.out.println("HO FINALMENTE RICEVUTO LA RISPOSTA ALL'ATTACCO");
        }
    }
}
