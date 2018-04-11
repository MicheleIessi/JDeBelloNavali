package src.Persistence;


public class ShipCatalog {

    private static ShipCatalog instance;

    private ShipCatalog() {}

    public ShipDescription getShipDescriptionByKey(int shipID) {

        return new ShipDescription();
    }

    public ShipDescription getShipDescriptionByCivDim(String civilization, int dimension) {

        return new ShipDescription();
    }

    public static ShipCatalog getInstance() {
        if(instance == null) {
            instance = new ShipCatalog();
        }
        return instance;
    }
}
