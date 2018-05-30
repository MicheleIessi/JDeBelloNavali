package com.debellonavali.Classes.Model;

import com.debellonavali.Classes.Model.RangeStrategy.RangeStrategyW1;

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

    public Ship() {
        this.shipID = 0;
        this.shipName = "";
        this.dimension = 0;
        this.hitsReceived = 0;
        this.shipWeight = 0;
        this.weapons = new HashMap<>();
    }

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

        if (this.isAlive()) {

            Weapon weapon = weapons.get(weaponID);
            if (!(weapon.getWeaponName().equalsIgnoreCase("W1"))) {
                float maximumDamage = (float) this.dimension / 2;
                if (this.getHitsReceived() >= maximumDamage) {
                    return false;
                }
            }
            return (weapon.isFireable() && weapon.hasAmmo());
        } else {
            // The ship has been destroyed!
            return false;
        }
    }

    public boolean isAlive() {
        return (this.hitsReceived < this.dimension);
    }

    public void setShipName(String name) {
        this.shipName = name;
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
