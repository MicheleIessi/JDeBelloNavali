package com.debellonavali.Screens.BattlePhase.Observers;

import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Screens.BattlePhase.INotifiableScreen;

public interface IScreenObserver {

    void update(IDTO dto, INotifiableScreen notifiableScreen);

}
