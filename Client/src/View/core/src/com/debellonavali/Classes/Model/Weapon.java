package com.debellonavali.Classes.Model;

import com.debellonavali.Classes.Model.RangeStrategy.IRangeStrategy;

import java.util.ArrayList;

/**
 * Weapon models a weapon mounted on a ship
 */
public class Weapon {

    private String weaponName;
    private int weaponID;
    private int reloadTime;
    private int maxReloadTime;
    private IRangeStrategy rangeStrategy;

    /**
     * Returns true if the weapon is ready to fire
     * @return True if the weapon is ready to fire, false otherwise
     */
    public boolean isFireable() {
        return (this.reloadTime == 0);
    }

    /**
     * Returns information about remaining ammo of the weapon
     * @return True if the weapon has ammo left, false otherwise
     */
    public boolean hasAmmo() {
        return (AmmoStorage.getInstance().getWeaponAmmo(this.weaponName) > 0);
    }

    /**
     * Decreases the ammo of the weapon by 1
     */
    public void decreaseAmmo() {
        AmmoStorage.getInstance().decreaseAmmo(this.weaponName);
    }

    /**
     * Performs an attack with the weapon's IRangeStrategy
     * @param posX The X-Axis target position
     * @param posY The Y-Axis target position
     * @return A List of involved coordinates
     */
    public ArrayList<int[]> attack(int posX, int posY) {
        return rangeStrategy.getInvolvedSquares(posX, posY);
    }

    /**
     * Sets the current reload time to the maximum
     */
    public void setMaxReloadTime() {
        setReloadTime(this.maxReloadTime);
    }

    /**
     * Returns the weapon's name
     * @return The weapon's name
     */
    public String getWeaponName() {
        return this.weaponName;
    }

    /**
     * Sets the weapon's name to the given parameter
     * @param weaponName The weapon's name
     */
    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    /**
     * Returns the weapon's identifier
     * @return The weapon's ID
     */
    public int getWeaponID() {
        return this.weaponID;
    }

    /**
     * Sets the weapon's identifier to the given parameter
     * @param weaponID The weapon's ID
     */
    public void setWeaponID(int weaponID) {
        this.weaponID = weaponID;
    }

    /**
     * Returns current reload time
     * @return The weapon's current reload time
     */
    public int getReloadTime() {
        return this.reloadTime;
    }

    /**
     * Sets the current reload time to the given parameter
     * @param reloadTime The reload time
     */
    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    /**
     * Get the weapon's maximum reload time
     * @return The weapon's maximum reload time
     */
    public int getMaxReloadTime() {
        return this.maxReloadTime;
    }

    /**
     * Sets the weapon's maximum reload time to the given parameter
     * @param maxReloadTime The maximum reload time
     */
    public void setMaxReloadTime(int maxReloadTime) {
        this.maxReloadTime = maxReloadTime;
    }

    /**
     * Returns the IRangeStrategy of the weapon
     * @return The IRangeStrategy of the weapon
     */
    public IRangeStrategy getRangeStrategy() {
        return this.rangeStrategy;
    }

    /**
     * Sets the IRangeStrategy of the weapon to the given parameter
     * @param rangeStrategy The IRangeStrategy to be set
     */
    public void setRangeStrategy(IRangeStrategy rangeStrategy) {
        this.rangeStrategy = rangeStrategy;
    }

}
