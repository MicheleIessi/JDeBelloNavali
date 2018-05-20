package com.debellonavali.Classes.Communicator.DTO;

import java.io.Serializable;
import java.util.HashMap;

public class DTO implements IDTO, Serializable {

    private String functionString;
    private HashMap<String, Object> hashMap;

    public DTO(String functionString) {
        this.hashMap = new HashMap<>();
        this.functionString = functionString;
    }

    @Override
    public String getFunctionString() {
        return this.functionString;
    }

    @Override
    public Object getObjectFromMap(String key) {
        return hashMap.get(key);
    }

    @Override
    public void addObjectToMap(String key, Object obj) {
        if(!hashMap.containsKey(key)) {
            hashMap.put(key, obj);
        }
    }

}
