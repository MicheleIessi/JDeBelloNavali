package com.debellonavali.Classes.Model;

import com.debellonavali.Classes.Controller.FacadeClientController;

import java.util.ArrayList;

public class DeBelloGame {

    private Battlefield playerBattlefield;
    private FacadeClientController facadeController;
    private static DeBelloGame instance;

    public DeBelloGame() {
        playerBattlefield = new Battlefield();
    }

    public Battlefield getPlayerBattlefield() {
        return playerBattlefield;
    }

    public static DeBelloGame getInstance() {
        if (instance == null) {
            instance = new DeBelloGame();
        }
        return instance;
    }
}