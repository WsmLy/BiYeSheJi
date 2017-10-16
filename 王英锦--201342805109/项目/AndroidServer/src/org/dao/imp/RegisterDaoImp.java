package org.dao.imp;

import java.util.List;

import javax.persistence.Entity;
import org.dao.RegisterDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.TableUser;
import org.util.HibernateSessionFactory;

@Entity
public class RegisterDaoImp implements RegisterDao{

	public TableUser register(String userName, String password,String phone,String userImg) {

		Session session = HibernateSessionFactory.getSession();

		Transaction ts = session.beginTransaction();
		TableUser tableUser = new TableUser();

		if(userName.equals("")){
			System.out.println("用户名不能为空！");
			return null;
		}else if(password.equals("")){
			System.out.println("密码不能为空！");
			return null;
		}else{
			Query query = session.createQuery("from TableUser where userName=?");
			query.setString(0, userName);

			List list = query.list();
			if(list.size()==0){
				tableUser.setUserName(userName);
				tableUser.setPassword(password);
				tableUser.setPhone(phone);
				tableUser.setUserImg(userImg);
				session.save(tableUser);
				ts.commit();
				return tableUser;
			}else{
				System.out.println("用户名已被使用");
				return null;
			}
		}
	}

}
