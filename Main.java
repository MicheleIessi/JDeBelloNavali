import Model.AmmoStorage;
import Model.Battlefield;
import Model.Factories.FleetFactory.BretoniFleetFactory;
import Model.Factories.FleetFactory.FleetFactory;
import Model.Factories.FleetFactory.GalliFleetFactory;
import Model.Factories.RangeStrategyFactory;
import Model.Factories.WeaponFactory;
import Model.RangeStrategy.IRangeStrategy;
import Model.Ship;
import Model.Weapon;
import Util.HibernateUtil;

public class Main {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {


        RangeStrategyFactory rsf = RangeStrategyFactory.getInstance();

        IRangeStrategy irs = RangeStrategyFactory.getInstance().createStrategy("W2");

        System.out.println(irs.getClass().getName());

        FleetFactory galli = GalliFleetFactory.getInstance();

        Battlefield battlefield = new Battlefield();
        battlefield.setFleetFactory(galli);
        battlefield.addShipToField(3,2,1,1,Battlefield.HORIZONTAL);
        battlefield.addShipToField(4,3,2,2,Battlefield.VERTICAL);

        System.out.println(AmmoStorage.getInstance().getAmmoByWeaponName());

        battlefield.attack(1,1,1,1);
        battlefield.attack(1,1,1,1);
        battlefield.attack(1,1,1,1);
        battlefield.drawField();


        Weapon weapon = WeaponFactory.getInstance().createWeapon("Quadrato");
        System.out.println(weapon.getReloadTime());
        weapon.setMaxReloadTime();
        System.out.println(weapon.getReloadTime());

        System.out.println(AmmoStorage.getInstance().getAmmoByWeaponName());
        System.out.println(battlefield.getFleet());

        HibernateUtil.getSessionFactory().close();
    }

}
