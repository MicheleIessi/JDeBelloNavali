package com.debellonavali.Classes.Controller.Observers;

import com.debellonavali.Classes.Communicator.DTO.DTOMessages;
import com.debellonavali.Classes.Controller.IClientController;
import com.debellonavali.Classes.Model.DeBelloGame;

import java.util.Map;
import java.util.logging.Logger;

/**
 * AttackResultsObserver is the controller responsible of handling the response send by the enemy
 * after a player's attack. It modifies the player's battlefield and triggers ship integrity updates.
 */
public class AttackResultsObserver implements IObserverController {

    /**
     * This controller receives a Map<int[2], boolean> after an attack.
     * The int[2] array represents the coordinate of a square, and the boolean
     * is true if it has hit a Ship, false otherwise.
     * @param controller
     */
    @Override
    public void update(IClientController controller) {
        if(controller.getFunction().equalsIgnoreCase(DTOMessages.ATTACK_RESULT_MESSAGE_ATTACKER) ||
                controller.getFunction().equalsIgnoreCase(DTOMessages.ATTACKER_WIN)) {
            Logger.getLogger(this.getClass().getCanonicalName()).info(String.format("Received message %s", controller.getFunction()));
            //notify the screen observer in order to update the view
            DeBelloGame.getInstance().notifyScreen(controller.getDTO());
            }
        }
    }

