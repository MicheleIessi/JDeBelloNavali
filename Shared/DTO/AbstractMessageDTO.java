package DTO;

import java.io.Serializable;

public abstract class AbstractMessageDTO implements Serializable, IMessageDTO {

    private String functionString;

    public AbstractMessageDTO() { }

    public void setFunctionString(String functionString) {
        this.functionString = functionString;
    }

    public String getFunctionString() {
        return this.functionString;
    }

    // Debug purposes only
    // TODO: Delete after finishing
    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{" +
                "functionString='" + functionString + '\'' +
                '}';
    }
}
