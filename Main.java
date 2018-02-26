import Model.Factories.FleetFactory.BretoniFleetFactory;
import Model.Factories.FleetFactory.FleetFactory;
import Model.Factories.FleetFactory.GalliFleetFactory;
import Model.Factories.RangeStrategyFactory;
import Model.RangeStrategy.IRangeStrategy;
import Model.Ship;
import Util.HibernateUtil;

public class Main {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {


        RangeStrategyFactory rsf = RangeStrategyFactory.getInstance();

        IRangeStrategy irs = RangeStrategyFactory.getInstance().createStrategy("W2");

        System.out.println(irs.getClass().getName());

        FleetFactory galli = GalliFleetFactory.getInstance();
        Ship ship = galli.getShipDimensionThree();

        FleetFactory bretoni = BretoniFleetFactory.getInstance();
        Ship ship2 = bretoni.getShipDimensionThree();

        System.out.println(ship.toString());
        System.out.println(ship2.toString());

//        Battlefield battlefield = new Battlefield();
//        battlefield.addShipToField(2,2,1,1,Battlefield.HORIZONTAL);
//        battlefield.addShipToField(3,6,5,5,Battlefield.VERTICAL);
//        battlefield.drawField();
//
//        Weapon weapon = WeaponFactory.getInstance().createWeapon("Quadrato");
//        System.out.println(weapon.toString());


        HibernateUtil.getSessionFactory().close();
    }

}
