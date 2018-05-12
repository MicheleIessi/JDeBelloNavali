package com.debellonavali.Classes.Model.Factories;

import com.debellonavali.Classes.Model.Battlefield;
import com.debellonavali.Classes.Model.DeBelloGame;

public class GameFactory {

    public DeBelloGame createDeBelloGame() {
        return new DeBelloGame();
    }

    public Battlefield createBattlefield() {
        return new Battlefield();
    }

}
