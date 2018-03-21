package Controller;

import DTO.DTOBuilder;
import DTO.IMessageDTO;
import Util.AnswerContainer;

public class HelloControllerObserver implements IObserver {

    private String controllerFunctionName = "Hello";

    @Override
    public void update(IController controller) {
        if(controllerFunctionName.equalsIgnoreCase(controller.getFunction())) {

            IMessageDTO dto = new DTOBuilder()
                    .function("Attack")
                    .build();

            System.out.printf("%s", dto.getFunctionString());
            AnswerContainer.getInstance().putDTOMessage(dto);

        }
    }
}
