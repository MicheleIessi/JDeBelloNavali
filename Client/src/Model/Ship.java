package src.Model;

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
        if (integrity > 50) {
            for (Entry<Integer, Weapon> entry : weapons.entrySet()) {
                Weapon weapon = entry.getValue();
                if (weaponID == weapon.getWeaponID()) {
                    return (weapon.isFireable() && weapon.hasAmmo());
                }
            }
        }
        return false;
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
