package com.debellonavali.Classes.Communicator.DTO;

public interface IDTO {

    String getFunctionString();

    Object getObjectFromMap(String key);

    void addObjectToMap(String key, Object obj);
}
