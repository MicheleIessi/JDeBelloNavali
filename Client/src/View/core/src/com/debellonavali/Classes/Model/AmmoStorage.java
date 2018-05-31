package com.debellonavali.Classes.Model;

import java.util.HashMap;

/**
 * AmmoStorage is used to keep track of remaning ammo of the weapons of all ships on the battlefield
 */
public class AmmoStorage {

    private HashMap<String, Integer> ammoByWeaponName;
    private static AmmoStorage instance;

    /**
     * Default constructor initializes an empty HashMap
     */
    private AmmoStorage() {
        ammoByWeaponName = new HashMap<>();
    }

    /**
     * Adds a weapon to the Map, if it wasn't already in there
     * @param weaponName The name of the weapon to be added
     * @param ammo The ammo of the weapon
     */
    public void addWeaponToStorage(String weaponName, int ammo) {
        if (!ammoByWeaponName.containsKey(weaponName)) {
            ammoByWeaponName.put(weaponName, ammo);
        }
    }

    /**
     * Returns the Map containing all ammo information
     * @return The Map containing all ammo information
     */
    public HashMap<String, Integer> getAmmoByWeaponName() {
        return ammoByWeaponName;
    }

    /**
     * Returns the ammo of the selected weapon
     * @param weaponName The name of the weapon
     * @return The remaining ammo of the weapon
     */
    public int getWeaponAmmo(String weaponName) {
        return ammoByWeaponName.get(weaponName);
    }

    /**
     * Decreases the ammo of the given weapon
     * @param weaponName The name of the weapon
     */
    public void decreaseAmmo(String weaponName) {
        System.out.println("Prima era " + ammoByWeaponName.get(weaponName));
        ammoByWeaponName.replace(weaponName, ammoByWeaponName.get(weaponName) - 1);
    }

    /**
     * Singleton: only one instance of this class is allowed to be instantiated
     * @return The unique instance of AmmoStorage
     */
    public static AmmoStorage getInstance() {
        if (instance == null) {
            instance = new AmmoStorage();
        }
        return instance;
    }
}
