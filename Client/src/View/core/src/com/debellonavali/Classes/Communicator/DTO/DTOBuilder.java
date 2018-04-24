package com.debellonavali.Classes.Communicator.DTO;

public class DTOBuilder {

    private String functionString;
    // Aggiungere qui altri attributi nel caso fossero richiesti

    public DTOBuilder() {}

    public DTOBuilder function(String functionString) {
        this.functionString = functionString;
        return this;
    }

    public String getFunctionString() {
        return this.functionString;
    }

    public DTO build() {
        return new DTO(this);
    }
}
