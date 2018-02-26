package Persistence;

import javax.persistence.*;

@Entity
@Table(name = "ship_description")
public class ShipDescription {

    @Id
    @Column(name = "ship_id")
    private int shipID;

    @Column(name = "civilization")
    private String civilization;

    @Column(name = "dimension")
    private int dimension;

    @Column(name = "ship_name")
    private String shipName;

    @Column(name = "ship_weight")
    private int shipWeight;

    @Column(name = "first_weapon")
    private String firstWeapon;

    @Column(name = "second_weapon")
    private String secondWeapon;

    @Column(name = "third_weapon")
    private String thirdWeapon;

    /**
     * This empty constructor method is required in order to perform Hibernate queries.
     */
    protected ShipDescription() {}

    public int getShipID() {
        return shipID;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    public String getCivilization() {
        return civilization;
    }

    public void setCivilization(String civilization) {
        this.civilization = civilization;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getShipWeight() {
        return shipWeight;
    }

    public void setShipWeight(int shipWeight) {
        this.shipWeight = shipWeight;
    }

    public String getFirstWeapon() {
        return firstWeapon;
    }

    public void setFirstWeapon(String firstWeapon) {
        this.firstWeapon = firstWeapon;
    }

    public String getSecondWeapon() {
        return secondWeapon;
    }

    public void setSecondWeapon(String secondWeapon) {
        this.secondWeapon = secondWeapon;
    }

    public String getThirdWeapon() {
        return thirdWeapon;
    }

    public void setThirdWeapon(String thirdWeapon) {
        this.thirdWeapon = thirdWeapon;
    }

    /**
     * Full constructor for class ShipDescriptor
     * @param shipID        The ID of the ship descriptor
     * @param civilization  The civilization of the ship
     * @param dimension     The dimension of the ship
     * @param shipName      The name of the ship
     * @param shipWeight    The weight of the ship
     * @param firstWeapon   The name of the first weapon of the ship
     * @param secondWeapon  The name of the second weapon of the ship
     * @param thirdWeapon   The name of the third weapon of the ship
     */
    public ShipDescription(
            int shipID,
            String civilization,
            int dimension,
            String shipName,
            int shipWeight,
            String firstWeapon,
            String secondWeapon,
            String thirdWeapon) {

        this.shipID = shipID;
        this.civilization = civilization;
        this.dimension = dimension;
        this.shipName = shipName;
        this.shipWeight = shipWeight;
        this.firstWeapon = firstWeapon;
        this.secondWeapon = secondWeapon;
        this.thirdWeapon = thirdWeapon;
    }

    @Override
    public String toString() {
        return "ShipDescription{" +
                "shipID=" + shipID +
                ", civilization='" + civilization + '\'' +
                ", dimension=" + dimension +
                ", shipName='" + shipName + '\'' +
                ", shipWeight=" + shipWeight +
                ", firstWeapon='" + firstWeapon + '\'' +
                ", secondWeapon='" + secondWeapon + '\'' +
                ", thirdWeapon='" + thirdWeapon + '\'' +
                '}';
    }
}
