package View;
import javafx.scene.web.WebEngine;
import jdk.nashorn.internal.runtime.JSONFunctions;
import netscape.javascript.JSObject;


public class JavaBridge {

    /** for communication to the Javascript engine. */
    private JSObject jsBridge;

    private WebEngine webEngine;

    public JavaBridge(WebEngine engine){

        this.webEngine=engine;
        // get the Javascript connector object.
        jsBridge = (JSObject) webEngine.executeScript("getJsBridge()");
    }

    //--------------------------PUBLIC METHOD ACCESSIBLE FROM JAVASCRIPT------------------------------------------//

    public void receive(JSObject data){
        if (data.getMember("task")!=null)
        {
            JSObject ship= (JSObject) data.getMember("ship");
            System.out.print(ship.getMember("dim"));
            System.out.println(ship.getMember("position"));

            this.sendToJs("receiveFromJava",data);
        }
    }


    public void sendToJs(String method,JSObject data){
        this.jsBridge.call(method,data);
    }



}
