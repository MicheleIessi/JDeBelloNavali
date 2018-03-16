package Model;

import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.Objects;

public class DeBelloGame {

    private int gameID;
    private ArrayList<Battlefield> battlefieldList;

    private static DeBelloGame instance;

    public DeBelloGame() {
    }

    ;


    public static DeBelloGame getInstance() {
        if (instance == null) {
            instance = new DeBelloGame();
        }
        return instance;
    }


    public JSObject placeShips(JSObject packet) {
        System.out.println("placeShips.");
        packet.setMember("task", "shipsPlaced");
        return packet;
    }


}
