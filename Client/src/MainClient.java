import SharedUtils.MessageDTO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import View.Browser;

import java.io.*;
import java.net.Socket;


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

    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int portNumber = 1234;
        Socket connectionSocket = null;

        try {
            connectionSocket = new Socket(host, portNumber);
            ObjectOutputStream objectOutputStream =  new ObjectOutputStream(connectionSocket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(connectionSocket.getInputStream());

            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setFunctionString("Ciao");

            System.out.println(messageDTO.toString());

            objectOutputStream.writeObject(messageDTO);
            objectOutputStream.flush();
        } catch (IOException e) {
            System.out.printf("Connection refused on host %s/%d.%n", host, portNumber);
            System.exit(1);
        }

        //launch(args);

    }

}
