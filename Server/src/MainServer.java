import Controller.FacadeController;
import Controller.HelloControllerObserver;

import java.io.IOException;

public class MainServer {


    public static void main(String[] args) throws IOException {

        HelloControllerObserver helloControllerObserver = new HelloControllerObserver();

        FacadeController facadeController = new FacadeController();

        facadeController.attachObserver(helloControllerObserver);

        ConnectionListener connectionListener = new ConnectionListener();
        connectionListener.setFacadeController(facadeController);

        // Server can be started
        connectionListener.startServer();

    }

}
