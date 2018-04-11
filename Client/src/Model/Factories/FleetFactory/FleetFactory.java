package src.Model.Factories.FleetFactory;

import src.Model.Factories.WeaponFactory;
import Persistence.ShipCatalog;
import Persistence.ShipDescription;
import src.Model.Ship;
import src.Model.Weapon;

import java.util.HashMap;

public abstract class FleetFactory {

    private String civilization;
    private static GalliFleetFactory instance;
    private static int shipID;

    FleetFactory(String civilization) {
        this.civilization = civilization;
        shipID = 0;
    }

    public String getCivilization() {
        return civilization;
    }

    public Ship createShip(int dimension) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Ship ship = null;
        switch (dimension) {
            case 2: ship = createShipDimensionTwo(); break;
            case 3: ship = createShipDimensionThree(); break;
            case 4: ship =  createShipDimensionFour(); break;
            default: break;
        }
        return ship;
    }

    private Ship createShipDimensionTwo() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ShipDescription shipDescription = ShipCatalog.getInstance().getShipDescriptionByCivDim(civilization,2);
        return createShip(shipDescription);
    }

    private Ship createShipDimensionThree() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ShipDescription shipDescription = ShipCatalog.getInstance().getShipDescriptionByCivDim(civilization,3);
        return createShip(shipDescription);
    }

    private Ship createShipDimensionFour() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ShipDescription shipDescription = ShipCatalog.getInstance().getShipDescriptionByCivDim(civilization,4);
        return createShip(shipDescription);
    }

    private Ship createShip(ShipDescription shipDescription) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Ship ship = new Ship();
        HashMap<Integer,Weapon> weaponList = new HashMap<>();

        incrementShipID();
        ship.setShipID(shipID);
        ship.setIntegrity(100);

        Weapon firstWeapon = WeaponFactory.getInstance().createWeapon(shipDescription.getFirstWeapon());
        weaponList.put(firstWeapon.getWeaponID(), firstWeapon);

        if(shipDescription.getSecondWeapon() != null) {
            Weapon secondWeapon = WeaponFactory.getInstance().createWeapon(shipDescription.getSecondWeapon());
            weaponList.put(secondWeapon.getWeaponID(), secondWeapon);
        }
        if(shipDescription.getThirdWeapon() != null) {
            Weapon thirdWeapon = WeaponFactory.getInstance().createWeapon(shipDescription.getThirdWeapon());
            weaponList.put(thirdWeapon.getWeaponID(), thirdWeapon);
        }

        ship.setWeapons(weaponList);
        return ship;
    }

    private void incrementShipID() {
        shipID++;
    }


}
