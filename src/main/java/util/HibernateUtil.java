package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    
    static {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = 
				new MetadataSources(registry)
					.buildMetadata()
					.buildSessionFactory();
		} catch (Throwable ex) {
			// The registry would be destroyed by the SessionFactory, but if we had trouble building 
			// the SessionFactory, we destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
			throw new ExceptionInInitializerError(ex);
		}
	}

    public static SessionFactory getSessionFactory() {
            return sessionFactory;
    }

}
