package Persistence;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class ShipCatalog {

    private static ShipCatalog instance;

    private ShipCatalog() {}

    public ShipDescription getShipDescriptionByKey(int shipID) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        ShipDescription shipDescription;

        try {
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ShipDescription> criteriaQuery = criteriaBuilder.createQuery(ShipDescription.class);
            Root<ShipDescription> root = criteriaQuery.from(ShipDescription.class);
            ParameterExpression<Integer> idParameter = criteriaBuilder.parameter(Integer.class);

            criteriaQuery.select(root).where(
                    criteriaBuilder.equal(root.get("shipID"), idParameter));

            TypedQuery<ShipDescription> query = session.createQuery(criteriaQuery)
                    .setParameter(idParameter, shipID);

            shipDescription = query.getSingleResult();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return shipDescription;
    }

    public ShipDescription getShipDescriptionByCivDim(String civilization, int dimension) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        List<ShipDescription> shipDescriptions;
        try {
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ShipDescription> criteriaQuery = criteriaBuilder.createQuery(ShipDescription.class);
            Root<ShipDescription> root = criteriaQuery.from(ShipDescription.class);
            ParameterExpression<String> civilizationParameter = criteriaBuilder.parameter(String.class);    // civilization = string
            ParameterExpression<Integer> dimensionParameter = criteriaBuilder.parameter(Integer.class);     // dimension = integer

            criteriaQuery.select(root).where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("civilization"), civilizationParameter),
                            criteriaBuilder.equal(root.get("dimension"), dimensionParameter)));

            TypedQuery<ShipDescription> query = session.createQuery(criteriaQuery)
                    .setParameter(civilizationParameter, civilization)
                    .setParameter(dimensionParameter, dimension);

            shipDescriptions = query.getResultList();
            session.getTransaction().commit();

        } finally {
            session.close();
        }

        return shipDescriptions.get(0);
    }

    public static ShipCatalog getInstance() {
        if(instance == null) {
            instance = new ShipCatalog();
        }
        return instance;
    }
}
