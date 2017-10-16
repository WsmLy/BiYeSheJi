package org.dao.imp;

import java.util.List;

import javax.persistence.Entity;
import org.dao.SearchDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Entity
public class SearchDaoImp implements SearchDao {

	public List getSearchResult(String commodityTitle) {
		try{
			Session session = org.util.HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			List queryList = session.createQuery("from TableCommodity where commodityTitle like '"+commodityTitle+"'").list();

			System.out.println(commodityTitle);
			ts.commit();

			return queryList;


		}catch(Exception e){
			e.printStackTrace();

			return null;
		}
	}


}
