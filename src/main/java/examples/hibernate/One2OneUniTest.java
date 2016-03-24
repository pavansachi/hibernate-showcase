package examples.hibernate;

import java.util.List;

import org.hibernate.Session;

import examples.hibernate.model.Stock;
import examples.hibernate.model.StockDescription;

public class One2OneUniTest 
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

				Stock s1 = new Stock();
				s1.setStockName("APPL");
				
				StockDescription sd1 = new StockDescription();
				sd1.setDescription("This is Apple stock");
				sd1.setStock(s1);
				
				Stock s2 = new Stock();
				s2.setStockName("GOOG");
				StockDescription sd2 = new StockDescription();
				sd2.setDescription("This is google stock");
				sd2.setStock(s2);
				
				session.save(sd1);
				session.save(sd2);
				
				query();
				
				tx.commit();
				
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

		org.hibernate.Query query = session.createQuery("from StockDescription");
		
//		query.setResultTransformer(Transformers.aliasToBean(Location.class));
		
		@SuppressWarnings("unchecked")
		List<StockDescription> list = query.list();

		if (list.size() == 0) {
			System.out.println("No records!!");
		}
		System.out.println("Stock        Stock Name           Stock Description");
		System.out.println("----------------------------------------------");
		for (StockDescription l: list) {

			System.out.print(l.getStockId());
			System.out.print("               ");
			System.out.print(l.getStock().getStockName());
			System.out.print("               ");
			System.out.print(l.getDescription());
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

