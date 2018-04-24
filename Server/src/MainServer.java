import Controller.DefenseControllerObserver;
import Controller.FacadeController;
import Controller.HelloControllerObserver;

import java.io.IOException;

public class MainServer {


    public static void main(String[] args) throws IOException {

        HelloControllerObserver helloControllerObserver = new HelloControllerObserver();
        DefenseControllerObserver defenseControllerObserver = new DefenseControllerObserver();

        FacadeController facadeController = new FacadeController();

        facadeController.attachObserver(helloControllerObserver);
        facadeController.attachObserver(defenseControllerObserver);

        ConnectionListener connectionListener = new ConnectionListener();
        connectionListener.setFacadeController(facadeController);

        // Server can be started
        connectionListener.startServer();

    }

}
