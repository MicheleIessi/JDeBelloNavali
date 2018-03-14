package Model.Factories.FleetFactory;

import Model.Factories.WeaponFactory;
import Model.Ship;
import Model.Weapon;
import Persistence.ShipCatalog;
import Persistence.ShipDescription;

import java.util.ArrayList;

public class GalliFleetFactory extends FleetFactory {

    private static final String civilization = "Galli";
    private static GalliFleetFactory instance;

    private GalliFleetFactory() {
        super(civilization);
    }

    public static GalliFleetFactory getInstance() {
        if(instance == null) {
            instance = new GalliFleetFactory();
        }
        return instance;
    }
}
