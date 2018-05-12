package com.debellonavali.Classes.Controller;

import com.debellonavali.Classes.Communicator.AnswerContainer;
import com.debellonavali.Classes.Communicator.DTO.DTO;
import com.debellonavali.Classes.Communicator.DTO.IMessageDTO;

public class AttackControllerObserver implements IClientObserver {

    @Override
    public void update(IClientController controller) {
        if("Attack".equalsIgnoreCase(controller.getFunction())) {
            System.out.println("Attacco ricevuto");
            IMessageDTO answerDTO = new DTO("Difesa");
            AnswerContainer.getInstance().putDTOMessage(answerDTO);
        }
    }
}
