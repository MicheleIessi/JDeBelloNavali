package com.debellonavali.Screens.BattlePhase.Observers;

import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Screens.BattlePhase.INotifiableScreen;

public class ChatObserver implements IScreenObserver {
    @Override
    public void update(IDTO dto, INotifiableScreen notifiableScreen) {
        if(dto.getFunctionString().equalsIgnoreCase("chat")) {

        }
    }
}
