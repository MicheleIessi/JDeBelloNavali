package com.debellonavali.Classes.Model;


import com.debellonavali.Classes.Model.Factories.FleetFactory.FleetFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Battlefield {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private Map<Integer, Ship> fleet;
    private Map<Integer, Integer> supportFleet;
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

    public void setFleetFactory(FleetFactory fleetFactory) {
        this.fleetFactory = fleetFactory;
    }

    public boolean isPlaceable(int addedWeight) {
        return (addedWeight <= (this.fleetWeight - this.shipWeight));
    }

    public Map<Integer, Ship> getFleet() {
        return fleet;
    }

    public void setFleet(Map<Integer, Ship> fleet) {
        this.fleet = fleet;
    }

    public Map<Integer, Integer> getSupportFleet() {
        return supportFleet;
    }

    public void setSupportFleet(Map<Integer, Integer> supportFleet) {
        this.supportFleet = supportFleet;
    }

    public Square[][] getField() {
        return field;
    }

    public void setField(Square[][] field) {
        this.field = field;
    }

    public FleetFactory getFleetFactory() {
        return fleetFactory;
    }

    public int getFleetWeight() {
        return fleetWeight;
    }

    public void setFleetWeight(int fleetWeight) {
        this.fleetWeight = fleetWeight;
    }

    public int getShipWeight() {
        return shipWeight;
    }

    public void setShipWeight(int shipWeight) {
        this.shipWeight = shipWeight;
    }

    public boolean addShipToField(int dimension, int weight, int posX, int posY, int orientation) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        if (isPlaceable(weight)) {

            ArrayList<Square> squaresToBePlaced = new ArrayList<>();

            if (orientation == Battlefield.HORIZONTAL) {
                if ((posX + dimension) > 8) {
                    System.err.println("The ships doesn't fit the battlefield");
                    return false;
                } else {
                    for (int i = 0; i < dimension; i++) {
                        Square square = this.field[i + posX][posY];
                        if (!square.isEmpty()) {
                            System.err.println("Square already occupied");
                            return false;
                        } else {
                            squaresToBePlaced.add(square);
                        }
                    }
                }
            } else if (orientation == Battlefield.VERTICAL) {
                if ((posY + dimension) > 8) {
                    System.err.println("The ship doesn't fit the battlefield");
                    return false;
                } else {
                    for (int i = 0; i < dimension; i++) {
                        Square square = this.field[posX][i + posY];
                        if (!square.isEmpty()) {
                            System.err.println("Square already occupied");
                            return false;
                        } else {
                            squaresToBePlaced.add(square);
                        }
                    }
                }
            }
            // Problema: come posso recuperare le informazioni della nave da passare in questo caso?
            // Il Battlefield deve conoscere shipCatalog e la civiltà scelta dal giocatore?
            // La civiltà la conosce perché conosce la fleetFactory
            Ship ship = fleetFactory.createShip(dimension);
            int shipID = ship.getShipID();

            fleet.put(shipID, ship);


            System.out.println("SHIP ID = " + shipID);


            this.shipWeight += weight;
            for (Square square : squaresToBePlaced) {
                square.setEmpty(false);
                square.setShipReference(shipID);
            }

        } else {
            // The ship is not placeable!
            System.err.println("Ship not placeable: weight=" + weight + ",remaining=" + (this.fleetWeight - this.shipWeight));
            return false;
        }
        return true;
    }

//    public boolean placeShips() {
//        for(Map.Entry<Integer,Integer> entry : supportFleet.entrySet()) {
//
//
//
//        }
//    }

    private boolean isFireable(int shipID, int weaponID) {
        Ship firingShip = fleet.get(shipID);
        return firingShip.isFireable(weaponID);
    }

    public void attack(int shipID, int weaponID, int posX, int posY) {

        if (isFireable(shipID, weaponID)) {
            Ship attackingShip = fleet.get(shipID);
            Weapon attackingWeapon = attackingShip.getWeapons().get(weaponID);
            ArrayList<int[]> hitSquares = attackingWeapon.attack(posX, posY);

            for (int[] hitSquare : hitSquares) {
                int hitX = hitSquare[0];
                int hitY = hitSquare[1];
                field[hitX][hitY].setHit(true);
            }

            attackingWeapon.setReloadTime(attackingWeapon.getMaxReloadTime());
            System.out.println("NEW RELOAD: " + attackingWeapon.getReloadTime());
            attackingWeapon.decreaseAmmo();
        } else {
            System.out.println("Attack not possible");
        }
    }

    public void drawField() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[j][i].isHit()) {
                    System.out.print("x");
                } else {
                    System.out.print(field[j][i].getShipReference());
                }
            }
            System.out.println("");
        }
    }

    private Square[][] buildField() {
        int length = 8, height = 8;
        Square[][] tempField = new Square[length][height];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                tempField[i][j] = new Square();
            }
        }
        return tempField;
    }

}