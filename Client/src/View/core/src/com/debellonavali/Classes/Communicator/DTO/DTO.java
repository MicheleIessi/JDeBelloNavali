package com.debellonavali.Classes.Communicator.DTO;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Data Transfer Object to transfer information between socket connected peers
 */
public class DTO implements IDTO, Serializable {

    private String functionString;
    private HashMap<String, Object> hashMap;

    /**
     * Creates a new DTO. Observer controllers use the functionString information
     * to understand whether they are responsible of handling the response or not
     * @param functionString The function of the DTO
     */
    public DTO(String functionString) {
        this.hashMap = new HashMap<>();
        this.functionString = functionString;
    }

    /**
     * Returns the function of the DTO
     * @return The function of the DTO
     */
    @Override
    public String getFunctionString() {
        return this.functionString;
    }

    /**
     * Gets the Object with the given key from the Map of the DTO
     * @param key The key of the Object to be retrieved
     * @return The Object with the given key
     */
    @Override
    public Object getObjectFromMap(String key) {
        return hashMap.get(key);
    }

    /**
     * Adds an Object with a given key to the Map of the DTO
     * @param key The key of the Object to be added
     * @param obj The Object to be added
     */
    @Override
    public void addObjectToMap(String key, Object obj) {
        if(!hashMap.containsKey(key)) {
            hashMap.put(key, obj);
        }
    }

}
