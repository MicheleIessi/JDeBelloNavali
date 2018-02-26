package Model;

import Model.Factories.FleetFactory.FleetFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Battlefield {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private Map<Integer, Ship> fleet;
    private Map<Integer, String> supportFleet;
    private Square[][] field;
    private FleetFactory fleetFactory;
    private int fleetWeight;
    private int shipWeight;
    private int shipCumulativeID;


    public Battlefield() {
        this.shipCumulativeID = 0;
        this.fleetWeight = 70;
        this.shipWeight = 0;
        this.fleet = new HashMap<>();
        this.supportFleet = new HashMap<>();
        this.field = buildField();
    }

    public boolean isPlaceable(int addedWeight) {
        return (addedWeight <= (this.fleetWeight - this.shipWeight));
    }

    public boolean addShipToField(int dimension, int weight, int posX, int posY, int orientation) {

        if(isPlaceable(weight)) {

            ArrayList<Square> squaresToBePlaced = new ArrayList<>();

            if(orientation == Battlefield.HORIZONTAL) {
                if((posX + dimension) > 8) {
                    System.err.println("The ships doesn't fit the battlefield");
                    return false;
                }
                else {
                    for(int i=0; i<dimension; i++) {
                        Square square = this.field[i+posX][posY];
                        if(!square.isEmpty()) {
                            System.err.println("Square already occupied");
                            return false;
                        }
                        else {
                            squaresToBePlaced.add(square);
                        }
                    }
                }
            }
            else if(orientation == Battlefield.VERTICAL) {
                if((posY + dimension) > 8) {
                    System.err.println("The ship doesn't fit the battlefield");
                    return false;
                }
                else {
                    for(int i=0; i<dimension; i++) {
                        Square square = this.field[posX][i+posY];
                        if(!square.isEmpty()) {
                            System.err.println("Square already occupied");
                            return false;
                        }
                        else {
                            squaresToBePlaced.add(square);
                        }
                    }
                }
            }
            // Problema: come posso recuperare le informazioni della nave da passare in questo caso?
            // Il Battlefield deve conoscere shipCatalog e la civiltà scelta dal giocatore?
            shipCumulativeID++;
            this.shipWeight += weight;
            for (Square square : squaresToBePlaced ) {
                square.setEmpty(false);
                square.setShipReference(shipCumulativeID);
            }

        } else {
            // The ship is not placeable!
            System.err.println("Ship not placeable: weight="+weight+",remaining="+(this.fleetWeight-this.shipWeight));
            return false;
        }
        return true;
    }

    public void attack(int shipID, int weaponID, int posX, int posY) {


    }
    public void drawField() {
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                System.out.print(field[j][i].getShipReference());
            }
            System.out.println("");
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
