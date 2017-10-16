package org.dao.imp;

import java.util.List;

import javax.persistence.Entity;
import org.dao.UploadDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.TableCommodity;
import org.model.TableUser;
import org.util.HibernateSessionFactory;

@Entity
public class UploadDaoImp implements UploadDao{

	Session session = HibernateSessionFactory.getSession();

	Transaction ts = session.beginTransaction();
	TableCommodity commodity = new TableCommodity();


	@Override
	public TableCommodity upLoad(TableCommodity tableCommodity) {

		if(tableCommodity.getUserName().equals("")){
			System.out.println("商品名不能为空！");
			return null;
		}else{
			
			Query query = session.createQuery("from TableUser where commodityTitle=? and commodityDetail=?");
			query.setString(0, tableCommodity.getCommodityTitle());
			query.setString(1, tableCommodity.getCommodityDetail());

			List list = query.list();
			if(list.size()==0){
				commodity.setCommodityTitle(tableCommodity.getCommodityTitle());
				commodity.setUserName(tableCommodity.getUserName());
				commodity.setCommodityDetail(tableCommodity.getCommodityDetail());
				commodity.setCommodityPicture1Id(tableCommodity.getCommodityPicture1Id());
				commodity.setCommodityPicture2Id(tableCommodity.getCommodityPicture2Id());
				commodity.setCommodityPicture3Id(tableCommodity.getCommodityPicture3Id());
				commodity.setCommodityPicture4Id(tableCommodity.getCommodityPicture4Id());
				commodity.setCommodityPicture5Id(tableCommodity.getCommodityPicture5Id());
				session.save(commodity);
				ts.commit();
				return commodity;
			}else{
				System.out.println("商品用户名已被使用");
				return null;
			}
		}
	}
	
	@Override
	public void delete(TableCommodity tableCommodity){
		Query query = session.createQuery("from TableCommodity where commodityTitle=? and commodityDetail=?");
		query.setString(0, tableCommodity.getCommodityTitle());
		query.setString(1, tableCommodity.getCommodityDetail());

		List list = query.list();
		commodity = (TableCommodity) query.uniqueResult();
		if(list.size()==0){
			session.delete(commodity);
			ts.commit();
		}else{
			System.out.println("用户名已被使用");
		}
	}

	@Override
	public void update(TableCommodity tableCommodity) {
		// TODO Auto-generated method stub
		
	}


}
