package com.debellonavali.Classes.Controller.Observers;

import com.debellonavali.Classes.Communicator.AnswerContainer;
import com.debellonavali.Classes.Communicator.DTO.DTOBuilder.DTOBuilder;
import com.debellonavali.Classes.Communicator.DTO.DTOMessages;
import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Controller.IClientController;
import com.debellonavali.Classes.Model.DeBelloGame;

import java.util.List;
import java.util.Map;

public class ReceiveAttackObserver implements IObserverController {

    @Override
    public void update(IClientController controller) {
        if(controller.getFunction().equalsIgnoreCase(DTOMessages.ATTACK_MESSAGE)) {
            List<int[]> attackedSquares = (List<int[]>) controller.getDTO().getObjectFromMap("AttackedSquares");
            Map<int[], Boolean> attackResult = DeBelloGame.getInstance().getPlayerBattlefield().receiveAttack(attackedSquares);

            IDTO attackResultDTO;
            IDTO defenceResultDTO;
            //Check if the game is ended
            if (attackedSquares.get(0)[0]==717){
                //The game is ended
                 attackResultDTO = DTOBuilder.getInstance().createAttackWinDTO(attackResult);
                AnswerContainer.getInstance().putDTOMessage(attackResultDTO);
                 defenceResultDTO = DTOBuilder.getInstance().createDefenceLoseDTO(attackResult);


            }
            else
            {
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
