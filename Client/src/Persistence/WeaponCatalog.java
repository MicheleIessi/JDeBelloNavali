package src.Persistence;


public class WeaponCatalog {

    //TODO: aggiungere una hashmap id-description per evitare di fare accessi multipli al db se si ha già recuperato il descrittore in precedenza
    private static WeaponCatalog instance;

    private WeaponCatalog() {}

    public WeaponDescription getWeaponDescriptionByKey(String weaponID) {
        return new WeaponDescription();
    }

    public static WeaponCatalog getInstance() {
        if(instance == null) {
            instance = new WeaponCatalog();
        }
        return instance;
    }

}
