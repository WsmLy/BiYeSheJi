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
import org.model.TableUser;
import org.util.HibernateSessionFactory;

@Entity
public class RegisterServlet extends HttpServlet {

	String username,password;
	int usernumber;
	PrintWriter printWriter;

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

		username = request.getParameter("username");
		password = request.getParameter("password");

		Transaction ts = session.beginTransaction();
		TableUser tu = new TableUser();

		Query query = session.createQuery("from TableUser where user_name=?");
		query.setString(0, username);
		List list = query.list();
		if(list.size()==0){

			tu.setPassword(password);
			tu.setUserName(username);
			session.save(tu);
			ts.commit();
			printWriter.print(1);
		}else{

			printWriter = response.getWriter();
			printWriter.print("用户名已被使用");
		}

		HibernateSessionFactory.closeSession();
	}




}
