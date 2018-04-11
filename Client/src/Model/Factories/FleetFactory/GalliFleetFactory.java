package src.Model.Factories.FleetFactory;

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
