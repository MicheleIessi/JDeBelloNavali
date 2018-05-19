package com.debellonavali.Classes.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Ship {

    private int shipID;
    private int dimension;
    private int hitsReceived;
    private HashMap<Integer, Weapon> weapons;


    public int getShipID() {
        return this.shipID;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    public int getDimension() {
        return this.dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getHitsReceived() {
        return this.hitsReceived;
    }

    public void setHitsReceived(int hitsReceived) {
        this.hitsReceived = hitsReceived;
    }

    public HashMap<Integer, Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(HashMap<Integer, Weapon> weapons) {
        this.weapons = weapons;
    }

    public boolean isFireable(int weaponID) {
        Weapon weapon = weapons.get(weaponID);
        return (weapon.isFireable() && weapon.hasAmmo());
    }

}
