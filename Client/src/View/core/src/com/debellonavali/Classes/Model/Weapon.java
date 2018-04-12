package com.debellonavali.Classes.Model;

import com.debellonavali.Classes.Model.RangeStrategy.IRangeStrategy;

import java.util.ArrayList;

public class Weapon {

    private String weaponName;
    private int weaponID;
    private int reloadTime;
    private int maxReloadTime;
    private IRangeStrategy rangeStrategy;


    public Weapon() {
    }

    public boolean isFireable() {
        System.out.println("RELOAD TIME: " + this.reloadTime);
        return (this.reloadTime == 0);
    }

    public boolean hasAmmo() {
        return (AmmoStorage.getInstance().getWeaponAmmo(this.weaponName) > 0);
    }

    public void decreaseAmmo() {
        AmmoStorage.getInstance().decreaseAmmo(this.weaponName);
    }

    public ArrayList<int[]> attack(int posX, int posY) {
        return rangeStrategy.getInvolvedSquares(posX, posY);
    }

    public void setMaxReloadTime() {
        setReloadTime(this.maxReloadTime);
    }

    /* -- Getter and Setter Methods -- */

    public String getWeaponName() {
        return this.weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getWeaponID() {
        return this.weaponID;
    }

    public void setWeaponID(int weaponID) {
        this.weaponID = weaponID;
    }

    public int getReloadTime() {
        return this.reloadTime;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    public int getMaxReloadTime() {
        return this.maxReloadTime;
    }

    public void setMaxReloadTime(int maxReloadTime) {
        this.maxReloadTime = maxReloadTime;
    }

    public IRangeStrategy getRangeStrategy() {
        return this.rangeStrategy;
    }

    public void setRangeStrategy(IRangeStrategy rangeStrategy) {
        this.rangeStrategy = rangeStrategy;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "weaponName='" + weaponName + '\'' +
                ", weaponID=" + weaponID +
                ", maxReloadTime=" + maxReloadTime +
                ", rangeStrategy=" + rangeStrategy +
                '}';
    }
}
