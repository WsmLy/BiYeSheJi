package com.ghj.packageofservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.TableCommodity;
import org.model.TableUser;
import org.util.HibernateSessionFactory;

@Entity
public class CommodityShowServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CommodityShowServlet() {
		super();  
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();
		TableCommodity tc = new TableCommodity();
		Query query = session.createQuery("from TableCommodity");
		
		query.setInteger(0, tc.getCommodityId());
		query.setString(1, tc.getCommodityTitle());
//		query.setString(1, tc.getCommodityName());
		query.setString(2, tc.getCommodityDetail());
		query.setString(3, tc.getCommodityPicture1Id());
//		query.setString(4, tc.getCommodityPicture2Id());
//		query.setString(5, tc.getCommodityPicture3Id());
//		query.setString(6, tc.getCommodityPicture4Id());
//		query.setString(7, tc.getCommodityPicture5Id());
		
		List list = query.list();
		HibernateSessionFactory.closeSession();

		response.setContentType("text/plain; charset=gbk");
		request.setCharacterEncoding("gbk");
		System.err.println(request.getParameter("username"));
		System.err.println(request.getParameter("password"));
		PrintWriter printWriter = response.getWriter();
//		printWriter.print("您好Android客户端！");
//		printWriter.print(tu1.getUserName());

		if(list.size()==0){
			printWriter.print("用户名或密码错误，请重试");
		}else{
			TableUser tu1 = (TableUser) list.get(0);
		
			printWriter.print("1");
		}
		printWriter.flush();
		printWriter.close();
	}

	
}
