package Controller;

import Communicator.AnswerContainer;
import Communicator.DTO.IMessageDTO;
import Communicator.DTO.DTO;

public class HelloControllerObserver implements IObserver {

    private String controllerFunctionName = "Hello";

    @Override
    public void update(IController controller) {
        if(controllerFunctionName.equalsIgnoreCase(controller.getFunction())) {

            IMessageDTO dto = new DTO("HelloReply");

            System.out.printf("%s", dto.getFunctionString());
            AnswerContainer.getInstance().putDTOMessage(dto);

        }
    }
}
