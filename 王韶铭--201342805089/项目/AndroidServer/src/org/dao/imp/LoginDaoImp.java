package org.dao.imp;

import javax.persistence.Entity;
import org.dao.LoginDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.TableUser;

@Entity
public class LoginDaoImp implements LoginDao {
	String username;
	String password;

	public LoginDaoImp(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public TableUser validate() {
		try{
//			System.out.println(username);
			Session session = org.util.HibernateSessionFactory.getSession();
//			Transaction ts = session.beginTransaction();
//			System.out.println(username+password);
			Query query = session.createQuery("from TableUser where userName=? and password=?");
			query.setParameter(0, username);
			query.setParameter(1, password);
			query.setMaxResults(1);
			TableUser tableUser = (TableUser) query.uniqueResult();
//			if(tableUser!=null){
			if(tableUser.getPassword() == password){
				return tableUser;
			}else{
				System.out.println("用户名或密码错误，请重试");
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
