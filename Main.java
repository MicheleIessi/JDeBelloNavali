import Model.Battlefield;
import Model.Factories.RangeStrategyFactory;
import Model.RangeStrategy.IRangeStrategy;
import Model.Ship;
import Persistence.ShipCatalog;
import Persistence.ShipDescription;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;

public class Main {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {


        RangeStrategyFactory rsf = RangeStrategyFactory.getInstance();

        IRangeStrategy irs = RangeStrategyFactory.getInstance().createStrategy("W2");

        System.out.println(irs.getClass().getName());

        Battlefield battlefield = new Battlefield();
        battlefield.disegnaCampo();


        ShipCatalog shipCatalog = ShipCatalog.getInstance();

        ShipDescription shipDescription = shipCatalog.getShipDescriptionByCivDim("Galli",4);

        System.out.println(shipDescription.toString());

        HibernateUtil.getSessionFactory().close();
    }

}
