package Model.Factories;

import Model.AmmoStorage;
import Model.RangeStrategy.IRangeStrategy;
import Model.Weapon;
import Persistence.WeaponCatalog;
import Persistence.WeaponDescription;

public class WeaponFactory {

    private static int weaponID;
    private static WeaponFactory instance;

    private WeaponFactory(){
        weaponID = 0;
    }

    public Weapon createWeapon(String weaponName) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        // Get description from catalog
        WeaponDescription weaponDescription = WeaponCatalog.getInstance().getWeaponDescriptionByKey(weaponName);
        Weapon weapon = new Weapon();
        // ID
        incrementWeaponID();
        weapon.setWeaponID(weaponID);
        // Name
        weapon.setWeaponName(weaponDescription.getWeaponName());
        // Reload time
        weapon.setMaxReloadTime(weaponDescription.getReloadTime());
        // Range
        IRangeStrategy rangeStrategy = RangeStrategyFactory.getInstance().createStrategy(weaponDescription.getRangeName());
        weapon.setRangeStrategy(rangeStrategy);
        // Update ammo storage
        AmmoStorage.getInstance().addWeaponToStorage(weaponDescription.getWeaponName(), weaponDescription.getAmmo());

        return weapon;
    }

    private void incrementWeaponID() {
        weaponID++;
    }

    public static WeaponFactory getInstance() {
        if(instance == null) {
            instance = new WeaponFactory();
        }
        return instance;
    }
}
