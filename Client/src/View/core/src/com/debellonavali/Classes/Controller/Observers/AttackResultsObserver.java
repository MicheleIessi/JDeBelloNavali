package com.debellonavali.Classes.Controller.Observers;

import com.debellonavali.Classes.Communicator.DTO.DTOMessages;
import com.debellonavali.Classes.Controller.IClientController;

import java.util.Map;
import java.util.Map.Entry;
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
        if(controller.getFunction().equalsIgnoreCase(DTOMessages.ATTACK_RESULT_MESSAGE)) {
            Logger.getLogger(this.getClass().getCanonicalName()).info(String.format("Received message %s", controller.getFunction()));

            Map<int[], Boolean> attackResults = (Map<int[], Boolean>) controller.getDTO().getObjectFromMap("AttackResults");
            for(Entry<int[], Boolean> attackedSquare : attackResults.entrySet()) {
                int x = attackedSquare.getKey()[0];
                int y = attackedSquare.getKey()[1];
                boolean outcome = attackedSquare.getValue();

                System.out.println(String.format("Square (%d,%d) : %b",x, y, outcome));
            }
        }
    }
}
