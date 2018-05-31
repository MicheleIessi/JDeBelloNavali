package com.debellonavali.Classes.Communicator;

import com.debellonavali.Classes.Communicator.DTO.IDTO;

/**
 * Interface that a ConnectionManager must implement
 */
public interface IConnectionManager {

    void sendMessage(IDTO sendingDTO);

}
