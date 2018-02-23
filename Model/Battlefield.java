package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Battlefield {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private Map<Integer, Ship> fleet;
    private Map<Integer, String> supportFleet;
    private Square[][] field;
    private int fleetWeight;
    private int shipWeight;



    public Battlefield() {
        this.fleetWeight = 70;
        this.shipWeight = 0;
        this.fleet = new HashMap<>();
        this.supportFleet = new HashMap<>();
        this.field = buildField();
    }

    public boolean isPlaceable(int addedWeight) {
        return (addedWeight <= (this.fleetWeight - this.shipWeight));
    }

    public boolean addShipToField(int dimension, int posX, int posY, int orientation) {


        return true;
    }


    public void disegnaCampo() {
        Square[][] campo = this.field;

        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                System.out.print(campo[i][j].getShipReference());
            }
            System.out.println(" ");
        }
    }

    private Square[][] buildField() {
        int length = 8, height = 8;
        Square[][] tempField = new Square[length][height];
        for(int i=0; i<length; i++) {
            for(int j=0; j<height; j++) {
                tempField[i][j] = new Square();
            }
        }
        return tempField;
    }

}
