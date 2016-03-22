package examples.hibernate;

import java.util.List;

import org.hibernate.Session;

import examples.hibernate.model.Stock;
import examples.hibernate.model.StockDescription;

public class StockOne2OneBiTest 
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
				s1.setStockDescription(sd1);
				session.save(s1);
				
				Stock s2 = new Stock();
				s2.setStockName("GOOG");
				StockDescription sd2 = new StockDescription();
				sd2.setDescription("This is Google stock");
				sd2.setStock(s2);
				s2.setStockDescription(sd2);
				session.save(s2);
				
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

		org.hibernate.Query query = session.createQuery("from Stock");
		
//		query.setResultTransformer(Transformers.aliasToBean(Location.class));
		
		@SuppressWarnings("unchecked")
		List<Stock> list = query.list();

		if (list.size() == 0) {
			System.out.println("No records!!");
		}
		System.out.println("Stock        Stock Name           Stock Description");
		System.out.println("----------------------------------------------");
		for (Stock l: list) {

			System.out.print(l.getStockId());
			System.out.print("               ");
			System.out.print(l.getStockName());
			System.out.print("               ");
			System.out.print(l.getStockDescription().getDescription());
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

