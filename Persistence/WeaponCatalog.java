package Persistence;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.HashMap;

public class WeaponCatalog {

    //TODO: aggiungere una hashmap id-description per evitare di fare accessi multipli al db se si ha già recuperato il descrittore in precedenza
    private static WeaponCatalog instance;

    private WeaponCatalog() {}

    public WeaponDescription getWeaponDescriptionByKey(String weaponID) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        WeaponDescription weaponDescription;

        try {
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<WeaponDescription> criteriaQuery = criteriaBuilder.createQuery(WeaponDescription.class);
            Root<WeaponDescription> root = criteriaQuery.from(WeaponDescription.class);
            ParameterExpression<String> weaponNameParameter = criteriaBuilder.parameter(String.class);

            criteriaQuery.select(root).where(
                    criteriaBuilder.equal(root.get("weaponName"), weaponNameParameter));

            TypedQuery<WeaponDescription> query = session.createQuery(criteriaQuery).
                setParameter(weaponNameParameter, weaponID);

            weaponDescription = query.getSingleResult();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return weaponDescription;
    }

    public static WeaponCatalog getInstance() {
        if(instance == null) {
            instance = new WeaponCatalog();
        }
        return instance;
    }

}
