package com.debellonavali.Classes.Model;


import com.debellonavali.Classes.Model.Factories.FleetFactory.FleetFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Battlefield models the player's battlefield
 */
public class Battlefield {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private Map<Integer, Ship> fleet;
    private Square[][] field;
    private int fleetWeight;
    private int shipWeight;
    private int deadShips;

    /**
     * Default constructor
     */
    public Battlefield() {
        deadShips = 0;
        this.fleetWeight = 70;
        this.shipWeight = 0;
        this.fleet = new HashMap<>();
        this.field = buildField();
    }

    /**
     * Checks if it is possible to add a ship with the given weight to the battlefield
     * @param addedWeight The weight to check for
     * @return True if it is possible to add a ship with the given weight, false otherwise
     */
    public boolean isPlaceable(int addedWeight) {
        return (addedWeight <= (this.fleetWeight - this.shipWeight));
    }

    /**
     * Returns a Map containing the player's fleet
     * @return A Map containing the player's fleet
     */
    public Map<Integer, Ship> getFleet() {
        return fleet;
    }

    /**
     * Sets the player's fleet to the given Map
     * @param fleet The player's fleet
     */
    public void setFleet(Map<Integer, Ship> fleet) {
        this.fleet = fleet;
    }

    /**
     * Returns the player's field, that is a matrix of Square objects
     * @return The player's field
     */
    public Square[][] getField() {
        return field;
    }

    /**
     * Sets the player's field
     * @param field The player's field
     */
    public void setField(Square[][] field) {
        this.field = field;
    }

    /**
     * Returns the current fleet weight of the battlefield
     * @return The current fleet weight
     */
    public int getFleetWeight() {
        return fleetWeight;
    }

    /**
     * Sets the fleet weight of the battlefield
     * @param fleetWeight the fleet weight of the battlefield
     */
    public void setFleetWeight(int fleetWeight) {
        this.fleetWeight = fleetWeight;
    }

    /**
     * Returns the ship weight of the battlefield
     * @return The ship weight of the battlefield
     */
    public int getShipWeight() {
        return shipWeight;
    }

    /**
     * Sets the ship weight of the battlefield
     * @param shipWeight The ship weight of the battlefield
     */
    public void setShipWeight(int shipWeight) {
        this.shipWeight = shipWeight;
    }

    /**
     * TODO: add weight check to this method
     * Adds a ship object to the field
     * @param ship The ship to be added
     * @param position The position of the ship, as a bidimensional integer array
     * @param orientation The orientation of the Ship
     * @return True if the addition of the ship has been successful, false otherwise
     */
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

    /**
     * Checks if the ship with the given identifier can fire the weapon with the given identifier
     * @param shipID The ID of the ship that has to fire
     * @param weaponID The ID of the weapon that has to be fired
     * @return True if the selected ship can fire the selected weapon, false otherwise
     */
    private boolean isFireable(int shipID, int weaponID) {
        Ship firingShip = fleet.get(shipID);
        return firingShip.isFireable(weaponID);
    }

    /**
     * Performs a simulation of the attack, in order to return a list of hit coordinates.
     * It uses the RangeStrategy of the weapon in order to give this method flexibility.
     * @param shipID The identifier of the ship that has to attack
     * @param weaponID The identifier of the weapon that has to fire
     * @param posX The target on the X-Axis
     * @param posY The target on the Y-Axis
     * @return a List of hit coordinates
     */
    public List<int[]> attack(int shipID, int weaponID, int posX, int posY) {

        ArrayList<int[]> hitSquares = new ArrayList<>();

        if (isFireable(shipID, weaponID)) {
            Ship attackingShip = fleet.get(shipID);
            Weapon attackingWeapon = attackingShip.getWeapons().get(weaponID);
            hitSquares = attackingWeapon.attack(posX, posY);
            attackingWeapon.setReloadTime(attackingWeapon.getMaxReloadTime());
            System.out.println("NEW RELOAD: " + attackingWeapon.getReloadTime());
            attackingWeapon.decreaseAmmo();
        } else {
            System.out.println("Attack not possible");
        }
        return hitSquares;
    }

    /**
     * Receives an attack on this battlefield. This method is called only if the player is defending.
     * The purpose is to update ship's integrity, and in case the game ends it must communicate it to DeBelloGame.
     * @param attackedSquares A List of attacked coordinates
     * @return A Map that represents the outcome of the attack. The keys are the coordinates, guaranteed
     * to be unique due to the way RangeStrategy objects work, and the values are True if it has hit a ship
     * for the first time (multiple hits return false), and false if it was a hole in the water
     */
    public Map<int[], Boolean> receiveAttack(List<int[]> attackedSquares) {
        Map<int[], Boolean> attackResult = new HashMap<>();
        for (int[] attackedCoordinate : attackedSquares) {
            int coordX = attackedCoordinate[0];
            int coordY = attackedCoordinate[1];
            int shipID = this.field[coordX][coordY].getShipReference();
            if (shipID != 0) {
                Ship hitShip = fleet.get(shipID);
                if (hitShip.getHitsReceived() < hitShip.getDimension()) {
                    hitShip.setHitsReceived(hitShip.getHitsReceived() + 1);
                    if (!hitShip.isAlive()) {
                        deadShips++;
                    }
                }
                if (deadShips == getFleet().size()) {
                    DeBelloGame.getInstance().setGameEnded(true);
                }
            }
            attackResult.put(attackedCoordinate, field[coordX][coordY].attack());
        }
        return attackResult;
    }

    /**
     * Builds the field, instantiating 64 Square objects in a matrix
     * @return The Square matrix representing the field
     */
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

    /**
     * Gets the ship position given its identifier
     * @param shipID The ship's ID
     * @return A map that contains the first position of the ship
     */
    public HashMap<String, String> getShipPosition(int shipID) {

        int x = 0;
        int y = 0;
        HashMap<String, String> shipInfo = new HashMap<>();
        for (Square[] squares : field) {
            for (Square square : squares) {
                if (square.getShipReference() == shipID) {
                    //TODO:Come faccio a capire l'orientamento della nave??
                    //shipInfo.put("orientation",orientation);
                    shipInfo.put("position", "" + x + "-" + y);
                    return shipInfo;
                }
                x++;
            }
            x = 0;
            y++;
        }
        return null;
    }

}