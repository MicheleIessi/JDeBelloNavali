package src.Model.Factories.FleetFactory;

public class BretoniFleetFactory extends FleetFactory {

    private static final String civilization = "Bretoni";
    private static BretoniFleetFactory instance;

    private BretoniFleetFactory() {
        super(civilization);
    }

    public static BretoniFleetFactory getInstance() {
        if(instance == null) {
            instance = new BretoniFleetFactory();
        }
        return instance;
    }
}
