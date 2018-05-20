package DTO;

import java.io.Serializable;
import java.util.HashMap;

public class DTO implements IMessageDTO, Serializable {

    private String functionString;
    private HashMap<String, Object> hashMap;

    public DTO(String functionString) {
        this.functionString = functionString;
    }

    @Override
    public String getFunctionString() {
        return this.functionString;
    }

}
