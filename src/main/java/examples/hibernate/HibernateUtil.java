package examples.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory() {
		
		sessionFactory = new  Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		
		return sessionFactory;
		
		// A SessionFactory is set up once for an application!
//		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//				.configure() // configures settings from hibernate.cfg.xml
//				.build();
//		
//		try {
//			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
//			
//			return sessionFactory;
//		}
//		catch (Exception e) {
//			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
//			// so destroy it manually.
//			StandardServiceRegistryBuilder.destroy( registry );
//		}
//		return sessionFactory;
	}
	
	public static void shutdown() {
		
		if (sessionFactory != null) sessionFactory.close();
	}
	
}
