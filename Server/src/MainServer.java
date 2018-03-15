import Controller.FacadeController;
import Persistence.ShipCatalog;
import Persistence.ShipDescription;
import Util.HibernateUtil;

import java.io.IOException;

public class MainServer {


    public static void main(String[] args) throws IOException {

        ConnectionListener connectionListener = new ConnectionListener();
        FacadeController facadeController = new FacadeController();

        connectionListener.setFacadeController(facadeController);

        connectionListener.startServer();

    }

}
