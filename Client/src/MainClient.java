import Communicator.DTOTransceiver;
import Communicator.ITransceiver;
import DTO.AttackMessageDTO;
import DTO.BasicMessageDTO;
import DTO.IMessageDTO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import View.Browser;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;


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
            if(transceiver.initializeTransceiver(host, port)) {
                System.out.printf("Connection estabilished with Server at %s:%d%n", host, port);
                int i = 1;
                while (true) {
                    transceiver.initializeTransceiver(host, port);
                    System.out.printf("Sending message number %d...%n", i);
                    IMessageDTO messageDTO = new BasicMessageDTO();
                    messageDTO.setFunctionString("Message number " + i);
                    transceiver.sendDTO(messageDTO);
                    transceiver.closeConnection();
                    System.out.println("Ciao");
                    i++;
                    Thread.sleep(1000);
                }
                //launch(args);
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
