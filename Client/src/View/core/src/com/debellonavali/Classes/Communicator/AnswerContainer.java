package com.debellonavali.Classes.Communicator;

import com.debellonavali.Classes.Communicator.DTO.IMessageDTO;

public class AnswerContainer {

    private static AnswerContainer instance = null;
    private IMessageDTO storedDTO = null;

    private AnswerContainer() {}

    public void putDTOMessage(IMessageDTO dtoMessage) {
        this.storedDTO = dtoMessage;
    }

    public IMessageDTO getStoredDTO() {
        IMessageDTO dtoAnswer = this.storedDTO;
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