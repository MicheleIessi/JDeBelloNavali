package src.Model;

import java.util.HashMap;

public class AmmoStorage {

    private HashMap<String, Integer> ammoByWeaponName;
    private static AmmoStorage instance;

    private AmmoStorage() {
        ammoByWeaponName = new HashMap<>();
    }

    public void addWeaponToStorage(String weaponName, int ammo) {
        if (!ammoByWeaponName.containsKey(weaponName)) {
            ammoByWeaponName.put(weaponName, ammo);
        }
    }

    public HashMap<String, Integer> getAmmoByWeaponName() {
        return ammoByWeaponName;
    }

    public int getWeaponAmmo(String weaponName) {
        return ammoByWeaponName.get(weaponName);
    }

    public void decreaseAmmo(String weaponName) {
        System.out.println("Prima era " + ammoByWeaponName.get(weaponName));
        ammoByWeaponName.replace(weaponName, ammoByWeaponName.get(weaponName) - 1);
    }

    public static AmmoStorage getInstance() {
        if (instance == null) {
            instance = new AmmoStorage();
        }
        return instance;
    }
}
