package com.debellonavali.Classes.Communicator;

import com.debellonavali.Classes.Communicator.DTO.IDTO;

public interface IConnectionManager {

    void sendMessage(IDTO sendingDTO);
}
