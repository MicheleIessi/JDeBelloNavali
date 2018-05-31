package com.debellonavali.Classes.Model;

/**
 * Square represent the single square on the battlefield
 */
public class Square {

    private boolean hit;
    private int shipReference;

    /**
     * Default constructor, sets the hit and shipReference attributes to their initial values
     */
    public Square() {
        this.hit = false;
        this.shipReference = 0;
    }

    /**
     * Returns the hit attribute.
     * @return True if the square has been hit, false otherwise
     */
    public boolean isHit() {
        return hit;
    }

    /**
     * Returns true if the attack has hit a ship for the first time
     * @return True if the attack has hit a ship for the first time, false otherwise
     */
    public boolean attack() {
        boolean isHit = false;
        if(!isHit() && this.shipReference!= 0){
            isHit = true;
            this.hit = true;
        }
        return isHit;
    }

    /**
     * Returns the ID of the ship on the square, if any
     * @return The ID of the ship on the square, if there is one, 0 otherwise
     */
    public int getShipReference() {
        return shipReference;
    }

    /**
     * Sets the ship reference of this square to the given ID
     * @param shipReference The ID of the ship to be set on this square
     */
    public void setShipReference(int shipReference) {
        this.shipReference = shipReference;
    }
}
