package Model;

import java.util.ArrayList;

public class Ship {

    private int shipID;

    private int integrity;

    private ArrayList<Weapon> weapons;

    public int getShipID() {
        return shipID;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    public int getIntegrity() {
        return integrity;
    }

    public void setIntegrity(int integrity) {
        this.integrity = integrity;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipID=" + shipID +
                ", integrity=" + integrity +
                ", weapons=" + weapons +
                '}';
    }
}
