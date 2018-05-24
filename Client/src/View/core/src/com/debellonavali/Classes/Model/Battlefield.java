package com.debellonavali.Classes.Model;


import com.debellonavali.Classes.Model.Factories.FleetFactory.FleetFactory;
import com.debellonavali.Screens.PlaceShip.GridChecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Battlefield {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private Map<Integer, Ship> fleet;
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
                        if (square.getShipReference() != 0) {
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
                        if (square.getShipReference() != 0) {
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
                square.setShipReference(shipID);
            }

        } else {
            // The ship is not placeable!
            System.err.println("Ship not placeable: weight=" + weight + ",remaining=" + (this.fleetWeight - this.shipWeight));
            return false;
        }
        return true;
    }

    public boolean addShip(Ship ship, int[] position, int orientation) {
        int posX = position[0];
        int posY = position[1];

        ArrayList<Square> squaresToBePlaced = new ArrayList<>();

        if (orientation == Battlefield.HORIZONTAL) {
            if ((posX + ship.getDimension()) > 8) {
                System.err.println("The ships doesn't fit the battlefield");
                return false;
            } else {
                for (int i = 0; i < ship.getDimension(); i++) {
                    Square square = this.field[i + posX][posY];
                    if (square.getShipReference() != 0) {
                        System.err.println("Square already occupied");
                        return false;
                    } else {
                        squaresToBePlaced.add(square);
                    }
                }
            }
        } else if (orientation == Battlefield.VERTICAL) {
            if ((posY + ship.getDimension()) > 8) {
                System.err.println("The ship doesn't fit the battlefield");
                return false;
            } else {
                for (int i = 0; i < ship.getDimension(); i++) {
                    Square square = this.field[posX][i + posY];
                    if (square.getShipReference() != 0) {
                        System.err.println("Square already occupied");
                        return false;
                    } else {
                        squaresToBePlaced.add(square);
                    }
                }
            }
        }
        for (Square square : squaresToBePlaced) {
            square.setShipReference(ship.getShipID());
        }

        return true;
    }

    private boolean isFireable(int shipID, int weaponID) {
        Ship firingShip = fleet.get(shipID);
        return firingShip.isFireable(weaponID);
    }

    public List<int[]> attack(int shipID, int weaponID, int posX, int posY) {

        ArrayList<int[]> hitSquares = new ArrayList<>();

        if (isFireable(shipID, weaponID)) {
            Ship attackingShip = fleet.get(shipID);
            Weapon attackingWeapon = attackingShip.getWeapons().get(weaponID);
            hitSquares = attackingWeapon.attack(posX, posY);

            for (int[] hitSquare : hitSquares) {
                int hitX = hitSquare[0];
                int hitY = hitSquare[1];

                //field[hitX][hitY].setHit(true);
            }
            attackingWeapon.setReloadTime(attackingWeapon.getMaxReloadTime());
            System.out.println("NEW RELOAD: " + attackingWeapon.getReloadTime());
            attackingWeapon.decreaseAmmo();
        } else {
            System.out.println("Attack not possible");
        }
        return hitSquares;
    }

    public Map<int[], Boolean> receiveAttack(List<int[]> attackedSquares) {
        Map<int[], Boolean> attackResult = new HashMap<>();
        for (int[] attackedCoordinate : attackedSquares) {
            int coordX = attackedCoordinate[0];
            int coordY = attackedCoordinate[1];
            attackResult.put(attackedCoordinate, field[coordX][coordY].attack());
        }
        return attackResult;
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


    public HashMap<String,String> getShipPosition(int shipID){

        int x=0;
        int y=0;
        HashMap<String,String> shipInfo= new HashMap<>();
        for (Square[] squares: field
             ) {
            for (Square square: squares
                 ) {
                if (square.getShipReference()==shipID){
                    //TODO:Come faccio a capire l'orientamento della nave??
                    //shipInfo.put("orientation",orientation);
                    shipInfo.put("position",""+x+"-"+y);
                    return shipInfo;
                }

                x++;
            }
            x=0;
            y++;

        }
        return null;

    }


}