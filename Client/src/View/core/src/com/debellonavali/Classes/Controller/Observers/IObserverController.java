package com.debellonavali.Classes.Controller.Observers;

import com.debellonavali.Classes.Controller.IClientController;

/**
 *
 */
public interface IObserverController {

    /**
     * Each Observer class receives the controller as a parameter and acts only if
     * it is its duty to do so.
     * @param controller The IClientController class that contains information about the function to achieve
     */
    void update(IClientController controller);
}
