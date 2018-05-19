package com.debellonavali.Screens.BattlePhase;

import com.badlogic.gdx.Screen;
import com.debellonavali.Classes.Communicator.DTO.IDTO;

public interface INotifiableScreen extends Screen {

    void notifyScreen(IDTO dto);

}
