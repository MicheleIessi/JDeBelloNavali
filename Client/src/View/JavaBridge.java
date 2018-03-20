package View;
import Model.DeBelloGame;
import javafx.scene.web.WebEngine;

import netscape.javascript.JSObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;


public class JavaBridge {

    private DeBelloGame dbg;

    /** for communication to the Javascript engine. */
    private JSObject jsBridge;

    private WebEngine webEngine;

    private Method method;

    public JavaBridge(WebEngine engine){

        this.dbg=DeBelloGame.getInstance();

        this.webEngine=engine;
        // get the Javascript connector object.
        jsBridge = (JSObject) webEngine.executeScript("getJsBridge()");
    }

    //--------------------------PUBLIC METHOD ACCESSIBLE FROM JAVASCRIPT------------------------------------------//

    public void receive(JSObject packet){

        String task = packet.getMember("task").toString();
        JSObject result;

        if (task!=null)  {

            try {
                method = dbg.getClass().getDeclaredMethod(task,netscape.javascript.JSObject.class);
            } catch (SecurityException e) {
                    System.out.println(e.toString());
            } catch (NoSuchMethodException e) {
                System.out.println(e.toString());
            }

            try {
                result=(JSObject)method.invoke(dbg,packet);
                this.send("receive",result);
            } catch (IllegalArgumentException e) {
                System.out.println(e.toString());
            } catch (IllegalAccessException e) {
                System.out.println(e.toString());
            } catch (InvocationTargetException e) {
                System.out.println(e.toString());
            }

        }

    }


    public void send(String method,JSObject packet){
        this.jsBridge.call(method,packet);
    }



}
