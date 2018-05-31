package com.debellonavali.Classes.Communicator.DTO;

/**
 * Interface that a DTO class must implement
 */
public interface IDTO {

    String getFunctionString();
    Object getObjectFromMap(String key);
    void addObjectToMap(String key, Object obj);

}
