package Persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weapon_description")
public class WeaponDescription {

    @Id
    @Column(name = "weapon_name")
    private String weaponName;

    @Column(name = "range_name")
    private String rangeName;

    @Column(name = "ammo")
    private int ammo;

    @Column(name = "reload_time")
    private int reloadTime;

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    @Override
    public String toString() {
        return "WeaponDescription{" +
                "weaponName='" + weaponName + '\'' +
                ", rangeName='" + rangeName + '\'' +
                ", ammo=" + ammo +
                ", reloadTime=" + reloadTime +
                '}';
    }
}
