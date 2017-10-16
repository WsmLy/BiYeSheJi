package org.dao.imp;

import java.util.List;

import org.dao.UserEditDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.TableCommodity;
import org.model.TableUser;
import org.util.HibernateSessionFactory;

public class UserEditDaoImp implements UserEditDao {

	@Override
	public TableUser Edit(TableUser user) {
		Session session = HibernateSessionFactory.getSession();

		Transaction ts = session.beginTransaction();
		TableUser tableUser = new TableUser();

		Query query = session.createQuery("from TableUser where userName=?");
		query.setString(0, user.getUserName());

		List list = query.list();
		if(list.size()==0){
			tableUser.setUserName(user.getUserName());
			tableUser.setPassword(user.getPassword());
			tableUser.setPhone(user.getPhone());
			tableUser.setUserImg(user.getUserImg());
			session.save(tableUser);
			ts.commit();
			return tableUser;
		}else{
			System.out.println("用户名已被使用");
			return null;
		}

	}


}
