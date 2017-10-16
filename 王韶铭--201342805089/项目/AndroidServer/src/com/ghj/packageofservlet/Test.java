package com.ghj.packageofservlet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.Entity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.mapping.Table;
import org.model.TableCommodity;
import org.model.TableUser;
import org.util.HibernateSessionFactory;
import org.dao.*;
import org.dao.imp.ShowDaoImp;


@Entity
public class Test {
	
	public static String username;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		ShowDao sd = new ShowDaoImp();
//		sd.getAllCommodity();
	}
		
//		Session session = HibernateSessionFactory.getSession();
//		
//				
//		Transaction ts = session.beginTransaction();
//		TableUser tu = new TableUser();
////		
//		try{
//			
//		tu.setPassword("123456");
//
//		System.out.println("a");
//		tu.setUserName("test2");
//
//		}catch(Exception e){
//			System.out.println("aa");
//
////			PrintWriter printWriter = response.getWriter();
////			printWriter.print("用户号已被使用");
//		}finally{
//			System.out.println("aaaa");
//		session.save(tu);
//		ts.commit();
//		HibernateSessionFactory.closeSession();
//		}
//		
////		
////		Query query = session.createQuery("from TableUser where user_id=4");
////		Query query1 = session.createQuery("from TableCommodity where user_id=2");
////		List list = query.list();
////		List list1 = query1.list();
////		TableUser tu1 = (TableUser) list.get(0);
////		TableCommodity tc1 = (TableCommodity) list1.get(0);
////		username = tu1.getUserName();
////		String commodityname = tc1.getCommodityName();
////		System.out.println(tu1.getUserName());
////		System.out.println(tc1.getCommodityName());
////		HibernateSessionFactory.closeSession();
//	}

}
