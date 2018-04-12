package com.debellonavali.Classes.Model;

import com.debellonavali.Classes.Communicator.DTOTransceiver;
import com.debellonavali.Classes.Controller.FacadeClientController;

import java.util.ArrayList;

public class DeBelloGame {

    private int gameID;
    private ArrayList<Battlefield> battlefieldList;
    private DTOTransceiver transceiver;
    private FacadeClientController facadeController;
    private static DeBelloGame instance;

    public DeBelloGame() {
//        facadeController = new FacadeClientController();
//        try {
//            DTOTransceiver transceiver = new DTOTransceiver();
//            transceiver.initializeTransceiver("localhost", 1234);
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public static DeBelloGame getInstance() {
        if (instance == null) {
            instance = new DeBelloGame();
        }
        return instance;
    }
}