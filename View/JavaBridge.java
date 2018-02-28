package View;
import javafx.scene.web.WebEngine;
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

    public void provaJava(JSObject data){
            System.out.print(data.getMember("msg"));
            this.jsBridge.call("provaJS",new Object[]{"ciao da java!!"});
    }
}
