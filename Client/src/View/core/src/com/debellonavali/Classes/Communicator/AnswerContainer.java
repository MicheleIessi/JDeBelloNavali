package com.debellonavali.Classes.Communicator;

import com.debellonavali.Classes.Communicator.DTO.IDTO;

public class AnswerContainer {

    private static AnswerContainer instance = null;
    private IDTO storedDTO = null;

    private AnswerContainer() {}

    public void putDTOMessage(IDTO dtoMessage) {
        this.storedDTO = dtoMessage;
    }

    public IDTO getStoredDTO() {
        IDTO dtoAnswer = this.storedDTO;
        this.storedDTO = null;
        return dtoAnswer;

    }

    public static AnswerContainer getInstance() {
        if(instance == null) {
            instance = new AnswerContainer();
        }
        return instance;
    }

}