package com.debellonavali.Classes.Controller.Observers;

import com.debellonavali.Classes.Communicator.AnswerContainer;
import com.debellonavali.Classes.Communicator.DTO.DTOBuilder.DTOBuilder;
import com.debellonavali.Classes.Communicator.DTO.DTOMessages;
import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Controller.IClientController;
import com.debellonavali.Classes.Model.DeBelloGame;

import java.util.List;
import java.util.Map;

/**
 * ReceiveAttackObserver is responsible to reply to a received attack. It is its duty also to recognize if a game
 * has ended, and communicates attack results storing this information into the AnswerContainer unique object.
 */
public class ReceiveAttackObserver implements IObserverController {

    /**
     * This controller receives a List<int[2]> of attacked coordinates, and proceeds to
     * compute the attack result. Then it communicates the result of the attack, asking to DeBelloGame
     * if the game has eventually ended
     * @param controller The IClientController class that contains information about the function to achieve
     */
    @Override
    public void update(IClientController controller) {
        if (controller.getFunction().equalsIgnoreCase(DTOMessages.ATTACK_MESSAGE)) {
            List<int[]> attackedSquares = (List<int[]>) controller.getDTO().getObjectFromMap("AttackedSquares");
            Map<int[], Boolean> attackResult = DeBelloGame.getInstance().getPlayerBattlefield().receiveAttack(attackedSquares);

            IDTO attackResultDTO;
            IDTO defenceResultDTO;
            //Check if the game is ended

            if(DeBelloGame.getInstance().getGameEnded()) {
                System.out.println("LA PARTITA È FINITA");
                attackResultDTO = DTOBuilder.getInstance().createAttackWinDTO(attackResult);
                AnswerContainer.getInstance().putDTOMessage(attackResultDTO);
                defenceResultDTO = DTOBuilder.getInstance().createDefenceLoseDTO(attackResult);
            } else {
                attackResultDTO = DTOBuilder.getInstance().createAttackResultsDTO(attackResult);
                AnswerContainer.getInstance().putDTOMessage(attackResultDTO);
                defenceResultDTO = DTOBuilder.getInstance().createDefenceResultsDTO(attackResult);
            }

            //TODO: run it using a thread to improve user experience and latency
            DeBelloGame.getInstance().notifyScreen(defenceResultDTO);
            DeBelloGame.getInstance().setPlayerTurn(true);

        }
    }
}
