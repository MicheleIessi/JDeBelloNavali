package com.debellonavali.Classes.Model;

public class Square {

    private boolean hit;
    private int shipReference;

    public Square() {
        this.hit = false;
        this.shipReference = 0;
    }

    public boolean isHit() {
        return hit;
    }

    /**
     * Method attack returns true if the attack has hit a ship
     * @return
     */
    public boolean attack() {
        boolean isHit = false;
        if(this.hit == false && this.shipReference!= 0){
            isHit = true;
            this.hit = true;
            // Bisogna dare alla square la responsabilità di modificare gli attributi di integrità della nave?
        }
        return isHit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public int getShipReference() {
        return shipReference;
    }

    public void setShipReference(int shipReference) {
        this.shipReference = shipReference;
    }
}
