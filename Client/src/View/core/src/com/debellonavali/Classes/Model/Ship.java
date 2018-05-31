package com.debellonavali.Classes.Model;

import com.debellonavali.Classes.Model.RangeStrategy.RangeStrategyW1;

import java.util.HashMap;

/**
 * Ship object
 */
public class Ship {

    private int shipID;
    private String shipName;
    private int dimension;
    private int hitsReceived;
    private int shipWeight;
    private HashMap<Integer, Weapon> weapons;

    /**
     * Default constructor
     */
    public Ship() {
        this.shipID = 0;
        this.shipName = "";
        this.dimension = 0;
        this.hitsReceived = 0;
        this.shipWeight = 0;
        this.weapons = new HashMap<>();
    }

    /**
     * Returns the ship's unique identifier
     * @return The ship's ID
     */
    public int getShipID() {
        return this.shipID;
    }

    /**
     * Sets the ship's identifier to the given parameter
     * @param shipID The ship's ID
     */
    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    /**
     * Returns the ship's dimension
     * @return The ship's dimension
     */
    public int getDimension() {
        return this.dimension;
    }

    /**
     * Sets the ship's dimension to the given parameter
     * @param dimension The ship's dimension
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    /**
     * Returns the ship's number of hits received
     * @return The ship's number of hits received
     */
    public int getHitsReceived() {
        return this.hitsReceived;
    }

    /**
     * Sets ship's number of hits received to the given parameter
     * @param hitsReceived
     */
    public void setHitsReceived(int hitsReceived) {
        this.hitsReceived = hitsReceived;
    }

    /**
     * Returns the Map containing the ship's weapons
     * @return The Map containing the ship's weapons
     */
    public HashMap<Integer, Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Sets the Map containing the ship's weapons
     * @param weapons The map containing the ship's weapons
     */
    public void setWeapons(HashMap<Integer, Weapon> weapons) {
        this.weapons = weapons;
    }

    /**
     * Checks if the ship can fire the weapon with the given ID
     * @param weaponID The ID of the weapon that has to fire
     * @return True if the ship can fire the selected weapon, false otherwise
     */
    public boolean isFireable(int weaponID) {

        if (this.isAlive()) {
            // The ship is still alive and kicking. Let's check if it can fire
            Weapon weapon = weapons.get(weaponID);
            if (!(weapon.getRangeStrategy().getClass().getSimpleName().equalsIgnoreCase(RangeStrategyW1.class.getSimpleName()))) {
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

    /**
     * Checks if the ship is still alive
     * @return True if the number of hits received by the ship is less than its dimension, false otherwise
     */
    public boolean isAlive() {
        return (this.hitsReceived < this.dimension);
    }

    /**
     * Sets the ship's name to the given string parameter
     * @param name The ship's name
     */
    public void setShipName(String name) {
        this.shipName = name;
    }

    /**
     * Returns the ship's name
     * @return The ship's name
     */
    public String getShipName() {
        return shipName;
    }

    /**
     * Returns the ship's weight
     * @return The ship's weight
     */
    public int getShipWeight() {
        return shipWeight;
    }

    /**
     * Sets the ship's weight to the given integer parameter
     * @param shipWeight The ship's weight
     */
    public void setShipWeight(int shipWeight) {
        this.shipWeight = shipWeight;
    }

}
