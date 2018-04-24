package com.debellonavali.Classes.Model.Factories;

import com.debellonavali.Classes.Persistence.WeaponCatalog;
import com.debellonavali.Classes.Persistence.WeaponDescription;
import com.debellonavali.Classes.Model.AmmoStorage;
import com.debellonavali.Classes.Model.RangeStrategy.IRangeStrategy;
import com.debellonavali.Classes.Model.Weapon;

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
