package Model;

import Model.RangeStrategy.IRangeStrategy;

public class Weapon {

    private String weaponName;
    private int weaponID;
    private int maxReloadTime;
    private IRangeStrategy rangeStrategy;


    public Weapon() {}



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
}
