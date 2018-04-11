package src.Model.Factories.FleetFactory;

public class RomaniFleetFactory extends FleetFactory {

    private static final String civilization = "Romani";
    private static RomaniFleetFactory instance;

    private RomaniFleetFactory() {
        super(civilization);
    }

    public static RomaniFleetFactory getInstance() {
        if(instance == null) {
            instance = new RomaniFleetFactory();
        }
        return instance;
    }
}
