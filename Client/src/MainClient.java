import Communicator.DTOTransceiver;
import Communicator.ITransceiver;
import DTO.DTOBuilder;
import DTO.IMessageDTO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import View.Browser;

import java.io.*;


public class MainClient extends Application{

    private Scene scene;
    private Browser browser;

    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("De Bello Navali");
        this.browser= new Browser();

        scene = new Scene(this.browser, Browser.PrefWidth, Browser.PrefHeight);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        ITransceiver transceiver = new DTOTransceiver();
        try {
            String host = "localhost";
            int port = 1234;
            transceiver.initializeTransceiver(host,port);
            IMessageDTO messageDTO = new DTOBuilder()
                    .function("Hello")
                    .build();
            transceiver.sendDTO(messageDTO);
            transceiver.closeConnection();
            launch(args);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}