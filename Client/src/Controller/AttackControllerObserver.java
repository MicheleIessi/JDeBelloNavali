package Controller;

import Communicator.AnswerContainer;
import DTO.DTOBuilder;
import DTO.IMessageDTO;

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
