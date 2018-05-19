package com.debellonavali.Screens.BattlePhase.Observers;

import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Screens.BattlePhase.INotifiableScreen;

public class BattleObserver implements IScreenObserver {

    @Override
    public void update(IDTO dto, INotifiableScreen notifiableScreen) {
        if(dto.getFunctionString().equalsIgnoreCase("attack")) {
            // accedi alla table di notifiableScreen
            // modifica le square della table in base al dto
        }
    }
}
