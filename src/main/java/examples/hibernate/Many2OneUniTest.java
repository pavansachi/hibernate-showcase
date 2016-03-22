package examples.hibernate;

import java.util.List;

import org.hibernate.Session;

import examples.hibernate.model.City;
import examples.hibernate.model.Country;

public class Many2OneUniTest 
{
	static Session session = null;
	static org.hibernate.Transaction tx =  null;

	public static void main( String[] args )
	{

		try {

			/*
			 * gets the session from the session factory
			 */
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();

			try {

				Country country1 = new Country();
				country1.setCountryName("India");
				
				City city1 = new City();
				city1.setCityName("Bangalore");
				city1.setCountry(country1);
				
				City city2 = new City();
				city2.setCityName("Delhi");
				city2.setCountry(country1);
				
				session.save(city1);
				session.save(city2);
				
				tx.commit();
				
				query();
				
			}
			catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			}

		}
		catch (Exception e1) {
			e1.printStackTrace();
			System.exit(1);
		}

		finally {
			if (session != null) session.close();
			HibernateUtil.shutdown();
		}

	}

	public static void query () {

		org.hibernate.Query query = session.createQuery("from City");
		
//		query.setResultTransformer(Transformers.aliasToBean(Location.class));
		
		@SuppressWarnings("unchecked")
		List<City> list = query.list();

		if (list.size() == 0) {
			System.out.println("No records!!");
		}
		System.out.println("City Name     Country");
		System.out.println("----------------------------------------------");
		for (City l: list) {

			System.out.print(l.getCityName());
			System.out.print("               ");
			System.out.print(l.getCountry().getCountryName());
			System.out.print("               ");
			System.out.println();
		}
	}

//	public static void insert(Location loc) {
//
//		/*
//		 * begin the transaction
//		 */
//		tx = session.beginTransaction();
//
//		session.save(loc);
//
//		tx.commit();
//
//		System.out.println("Inserted record");
//	}
//
//	public static void update(Location loc) {
//
//		/*
//		 * begin the transaction
//		 */
//		tx = session.beginTransaction();
//
//		session.save(loc);
//
//		tx.commit();
//
//		System.out.println("Updated record");
//	}
//	
//	public static void delete(Location loc) {
//
//		/*
//		 * begin the transaction
//		 */
//		tx = session.beginTransaction();
//
//		session.delete(loc);
//
//		tx.commit();
//
//		System.out.println("Deleted record");
//	}

}

