package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateConnector {

    private SessionFactory factory;

    public HibernateConnector() {
	   buildSessionFactory();
    }

    private void buildSessionFactory() {
	   try {
		  Configuration config = new Configuration().configure("/hibernate.cfg.xml");
		  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
		            config.getProperties()).build();
		  factory = config.buildSessionFactory(serviceRegistry);
	   } catch (Throwable ex) {
		  System.err.println("Failed to create sessionFactory object." + ex);
		  throw new ExceptionInInitializerError(ex);
	   }

    }

    public SessionFactory getSessionFactory() {
	   return factory;
    }
}