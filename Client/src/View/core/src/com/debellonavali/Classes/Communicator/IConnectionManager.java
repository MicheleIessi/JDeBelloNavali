package com.debellonavali.Classes.Communicator;

import com.debellonavali.Classes.Communicator.DTO.IMessageDTO;

public interface IConnectionManager {

    void sendMessage(IMessageDTO sendingDTO, String enemyHost, int enemyPort);
}
