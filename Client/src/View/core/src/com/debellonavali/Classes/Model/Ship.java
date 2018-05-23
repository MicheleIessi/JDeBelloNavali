package com.debellonavali.Classes.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Ship {

    private int shipID;
    private String shipName;
    private int dimension;
    private int hitsReceived;



    private int shipWeight;
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

    public void setShipName(String name) {
        this.shipName=name;
    }

    public String getShipName() {
        return shipName;
    }

    public int getShipWeight() {
        return shipWeight;
    }

    public void setShipWeight(int shipWeight) {
        this.shipWeight = shipWeight;
    }

}
