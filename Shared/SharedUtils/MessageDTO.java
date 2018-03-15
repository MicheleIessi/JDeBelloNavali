package SharedUtils;

import java.io.Serializable;

public class MessageDTO implements Serializable {

    private String functionString;

    public MessageDTO() { }

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
        return "MessageDTO{" +
                "functionString='" + functionString + '\'' +
                '}';
    }
}
