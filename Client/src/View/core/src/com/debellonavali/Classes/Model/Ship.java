package com.debellonavali.Classes.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Ship {

    private int shipID;

    private int integrity;

    private HashMap<Integer, Weapon> weapons;

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

    @Override
    public String toString() {
        return "Ship{" +
                "shipID=" + shipID +
                ", integrity=" + integrity +
                ", weapons=" + weapons +
                '}';
    }
}
