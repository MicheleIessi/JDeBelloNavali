package Util;

import Persistence.ShipDescription;
import Persistence.WeaponDescription;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {

            Configuration configuration = new Configuration();
            configuration.configure("Util/hibernate.cfg.xml");
            configuration = addAnnotatedClasses(configuration);
            System.out.println("Hibernate Configuration Loaded");

            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Configuration addAnnotatedClasses(Configuration configuration) {

        configuration.addAnnotatedClass(ShipDescription.class);
        configuration.addAnnotatedClass(WeaponDescription.class);

        return configuration;
    }


    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}
