package Controller;

import DTO.DTOBuilder;
import DTO.IMessageDTO;
import Util.AnswerContainer;

public class DefenseControllerObserver implements IObserver {
    @Override
    public void update(IController controller) {
        if("Difesa".equalsIgnoreCase(controller.getFunction())) {
            System.out.println("Difesa ricevuta");
            IMessageDTO answerDTO = new DTOBuilder()
                    .function("Attack")
                    .build();
            AnswerContainer.getInstance().putDTOMessage(answerDTO);
        }
    }
}
