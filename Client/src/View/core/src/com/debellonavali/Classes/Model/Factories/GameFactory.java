package com.debellonavali.Classes.Model.Factories;

import com.debellonavali.Classes.Model.Battlefield;
import com.debellonavali.Classes.Model.DeBelloGame;

public class GameFactory {

    private static GameFactory instance = null;

    public DeBelloGame createDeBelloGame() {
        return new DeBelloGame();
    }

    public Battlefield createBattlefield() {
        return new Battlefield();
    }

    public static GameFactory getInstance() {
        if (instance == null) {
            instance = new GameFactory();
        }
        return instance;
    }
}
