import Persistence.ShipCatalog;
import Persistence.ShipDescription;
import Util.HibernateUtil;

public class MainServer {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        ShipCatalog shipCatalog = ShipCatalog.getInstance();
        ShipDescription shipDescription = shipCatalog.getShipDescriptionByCivDim("Bretoni", 2);

        System.out.println(shipCatalog.toString());

        HibernateUtil.getSessionFactory().close();

    }






}
