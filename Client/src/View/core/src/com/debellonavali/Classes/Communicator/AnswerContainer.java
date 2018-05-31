package com.debellonavali.Classes.Communicator;

import com.debellonavali.Classes.Communicator.DTO.IDTO;

/**
 * AnswerContainer is used to retrieve the response of an Observer Controller
 */
public class AnswerContainer {

    private static AnswerContainer instance = null;
    private IDTO storedDTO = null;

    /**
     * Puts a IDTO object into the container
     * @param dtoMessage The IDTO object to be inserted
     */
    public void putDTOMessage(IDTO dtoMessage) {
        this.storedDTO = dtoMessage;
    }

    /**
     * Gets the stored IDTO object
     * @return The stored IDTO object
     */
    public IDTO getStoredDTO() {
        IDTO dtoAnswer = this.storedDTO;
        this.storedDTO = null;
        return dtoAnswer;
    }

    /**
     * Singleton class: only one instance of this class is allowed to be instantiated
     * @return The single instance of AnswerContainer
     */
    public static AnswerContainer getInstance() {
        if(instance == null) {
            instance = new AnswerContainer();
        }
        return instance;
    }

}