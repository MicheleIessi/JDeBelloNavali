package src.Model;

public class Square {

    private boolean hit;
    private boolean empty;   // serve davvero?
    private int shipReference;

    public Square() {
        this.hit = false;
        this.empty = true;
        this.shipReference = 0;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public int getShipReference() {
        return shipReference;
    }

    public void setShipReference(int shipReference) {
        this.shipReference = shipReference;
    }
}
