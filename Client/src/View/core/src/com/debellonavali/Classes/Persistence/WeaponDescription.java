package com.debellonavali.Classes.Persistence;

public class WeaponDescription {

    private String weaponName;

    private String rangeName;

    private int ammo;

    private int reloadTime;

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    @Override
    public String toString() {
        return "WeaponDescription{" +
                "weaponName='" + weaponName + '\'' +
                ", rangeName='" + rangeName + '\'' +
                ", ammo=" + ammo +
                ", reloadTime=" + reloadTime +
                '}';
    }
}
