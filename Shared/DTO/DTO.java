package DTO;

import java.io.Serializable;

public class DTO implements IMessageDTO, Serializable {

    private String functionString;

    protected DTO(DTOBuilder builder) {
        this.functionString = builder.getFunctionString();
    }

    @Override
    public String getFunctionString() {
        return this.functionString;
    }

}
