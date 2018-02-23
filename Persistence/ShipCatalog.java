package Persistence;

import Model.Ship;
import Util.HibernateUtil;
import com.mysql.fabric.xmlrpc.base.Param;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ShipCatalog {

    private static ShipCatalog instance;

    private ShipCatalog() {}

//    public ShipDescription getShipDescriptionByKey() {
//
//    }

    public ShipDescription getShipDescriptionByCivDim(String civilization, int dimension) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        List<ShipDescription> shipDescriptions= new ArrayList<>();
        try {
            session.beginTransaction();

            // create criteria
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            // create query
            CriteriaQuery<ShipDescription> criteriaQuery = criteriaBuilder.createQuery(ShipDescription.class);
            Root<ShipDescription> root = criteriaQuery.from(ShipDescription.class);

            // setting parameters
            ParameterExpression<String> civilizationParameter = criteriaBuilder.parameter(String.class);
            ParameterExpression<Integer> dimensionParameter = criteriaBuilder.parameter(Integer.class);

            criteriaQuery.select(root).where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("civilization"), civilizationParameter),
                            criteriaBuilder.equal(root.get("dimension"), dimensionParameter)));

            TypedQuery<ShipDescription> query = session.createQuery(criteriaQuery)
                    .setParameter(civilizationParameter, civilization)
                    .setParameter(dimensionParameter, dimension);

            shipDescriptions = query.getResultList();


            // commit a transaction
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
