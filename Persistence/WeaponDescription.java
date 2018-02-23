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

}
