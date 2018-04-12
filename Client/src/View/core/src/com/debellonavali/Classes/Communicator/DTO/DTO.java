package com.debellonavali.Classes.Communicator.DTO;

import java.io.Serializable;
import java.util.HashMap;

public class DTO implements IMessageDTO, Serializable {

    private String functionString;
    private HashMap<String, Object> hashMap;

    protected DTO(DTOBuilder builder) {
        this.functionString = builder.getFunctionString();
    }

    @Override
    public String getFunctionString() {
        return this.functionString;
    }

}
