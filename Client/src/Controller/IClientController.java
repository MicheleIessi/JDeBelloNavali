package src.Controller;

public interface IClientController {

    String getFunction();
    // Observer methods
    void attachObserver(IClientObserver observer);
    void detachObserver(IClientObserver observer);
    void notifyObservers();



}
