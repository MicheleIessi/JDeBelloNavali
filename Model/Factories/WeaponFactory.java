package Model.Factories;

import Model.Weapon;

public class WeaponFactory {


    private static int weaponID;
    private static WeaponFactory instance;

    private WeaponFactory(){}

    public Weapon createWeapon(String weaponName) {
        // Connettersi all'orm, prendere il descrittore e creare la weapon di conseguenza
        return new Weapon();
    }

    public static WeaponFactory getInstance() {
        if(instance == null) {
            instance = new WeaponFactory();
        }
        return instance;
    }
}
