package com.debellonavali.Classes.Controller;

import com.debellonavali.Classes.Communicator.AnswerContainer;
import com.debellonavali.Classes.Communicator.DTO.DTOBuilder;
import com.debellonavali.Classes.Communicator.DTO.IMessageDTO;

public class AttackControllerObserver implements IClientObserver {

    @Override
    public void update(IClientController controller) {
        if("Attack".equalsIgnoreCase(controller.getFunction())) {
            System.out.println("Attacco ricevuto");
            IMessageDTO answerDTO = new DTOBuilder()
                    .function("Difesa")
                    .build();
            AnswerContainer.getInstance().putDTOMessage(answerDTO);
        }
    }
}
