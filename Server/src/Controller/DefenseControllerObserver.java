package Controller;

import Communicator.AnswerContainer;
import Communicator.DTO.IMessageDTO;
import Communicator.DTO.DTO;

public class DefenseControllerObserver implements IObserver {
    @Override
    public void update(IController controller) {
        if("Difesa".equalsIgnoreCase(controller.getFunction())) {
            System.out.println("Difesa ricevuta");
            IMessageDTO answerDTO = new DTO("Attack");
            AnswerContainer.getInstance().putDTOMessage(answerDTO);
        }
    }
}
