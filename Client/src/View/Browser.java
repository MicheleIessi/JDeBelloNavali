package View;

import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;


import java.net.URL;




public class Browser extends javafx.scene.layout.Region {



    /** for communication from the Javascript engine. */
    private JavaBridge javaBridge;

    public static double PrefWidth=800;
    public static double PrefHeight=600;

    private String homePage="placeShip";
    private String homePackage="placeShipUI";

    final WebView browser;
    final WebEngine webEngine;


    public Browser() {
        this.browser= new WebView();
        this.webEngine= this.browser.getEngine();

        // set up the listener
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                // set an interface object named 'javaBridge' in the web engine's page
                JSObject window = (JSObject) webEngine.executeScript("window");

                // init the bridge
                this.javaBridge= new JavaBridge(this.webEngine);

                //set the bridge for js
                window.setMember("javaBridge", this.javaBridge);



            }
        });





        browser.setPrefSize(PrefWidth,PrefHeight);

        URL urlHomePage=this.getPathHtmlResource(homePackage,homePage);
        webEngine.load(urlHomePage.toExternalForm());



        getChildren().add(browser);
    }


    /**
     * Return the URL of a local html file inside a package on the same level of Browser class.
     *
     * @param subPackage a string that indicates the package name where the file is suppose to be
     * @param page  the name of the html file
     * @return URL
     */
    private URL getPathHtmlResource(String subPackage, String page){

        URL urlTemp= getClass().getResource(subPackage+"/"+page+".html");
        System.out.println(subPackage+"/"+page+".html");
        if (urlTemp==null){
            throw new NullPointerException("resource not found or path not valid");
        }
        else
            return urlTemp;



    }




}