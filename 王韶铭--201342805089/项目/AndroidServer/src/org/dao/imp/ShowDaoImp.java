package org.dao.imp;


import java.util.List;

import javax.persistence.Entity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dao.ShowDao;
import org.util.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.model.TableCommodity;

@Entity
public class ShowDaoImp implements ShowDao {
	String s,username;

	protected ShowDaoImp() {
	}

	public ShowDaoImp(String s, String username) {
		this.s = s;
		this.username = username;
	}

	public List getAllCommodity(){//int commodityId) {
		try{
			Session session = org.util.HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			List queryList;
			switch(s){
			case "main":
				queryList = session.createQuery("from TableCommodity").list();break;
			case "recommend":
				queryList = session.createQuery("from TableCommodity where commodityId in (select commodityId from TableRecommend)").list();break;
			case "shoppingcar":
				queryList = session.createQuery("from TableCommodity where commodityId in (select commodityId from TableShoppingcar where userName='"+username+"')").list();break;
			case "fabu":
				queryList = session.createQuery("from TableCommodity where commodityId in (select commodityId from TableFabu where userName='"+username+"')").list();
				break;
			case "sale":
				queryList = session.createQuery("from TableCommodity where commodityId in (select commodityId from TableSale where userName='"+username+"')").list();break;
			case "bought":
				queryList = session.createQuery("from TableCommodity where commodityId in (select commodityId from TableBought where userName='"+username+"')").list();break;
			default:
				queryList = session.createQuery("from TableCommodity").list();break;
			}

			//			TableCommodity queryList = (TableCommodity) query.uniqueResult();

			ts.commit();

			System.out.println(queryList);
			return queryList;


		}catch(Exception e){
			e.printStackTrace();

			return null;}
	}

}
