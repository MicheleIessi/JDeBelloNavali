import Model.AmmoStorage;
import Model.Battlefield;
import Model.Factories.FleetFactory.FleetFactory;
import Model.Factories.FleetFactory.GalliFleetFactory;
import Model.Factories.RangeStrategyFactory;
import Model.Factories.WeaponFactory;
import Model.RangeStrategy.IRangeStrategy;
import Model.Weapon;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import View.Browser;

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

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        launch(args);

    }

}
