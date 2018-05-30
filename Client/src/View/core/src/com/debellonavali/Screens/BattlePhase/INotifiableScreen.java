package com.debellonavali.Screens.BattlePhase;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.debellonavali.Classes.Communicator.DTO.IDTO;

public interface INotifiableScreen extends Screen {

    void notifyScreen(IDTO dto);
    void showEndGameDialog(boolean winner);

}
