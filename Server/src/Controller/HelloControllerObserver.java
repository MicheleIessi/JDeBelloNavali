package Controller;

import DTO.BasicMessageDTO;
import DTO.IMessageDTO;
import Util.AnswerContainer;

public class HelloControllerObserver implements IObserver {


    @Override
    public void update(IController controller) {
        if("Hello".equalsIgnoreCase(controller.getFunction())) {

            IMessageDTO dto = new BasicMessageDTO();
            dto.setFunctionString("HelloReply");
            AnswerContainer.getInstance().putDTOMessage(dto);

        }
    }
}
