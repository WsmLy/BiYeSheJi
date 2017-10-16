//package com.ghj.packageofservlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.validator.Length;
//import org.model.TableUser;
//import org.util.HibernateSessionFactory;
//
//@Entity
//public class LoginServlet extends HttpServlet {
//
//	@Id
//	@GeneratedValue
//	private static final long serialVersionUID = 6792396567928634227L;
//	@ManyToOne
//	private Test ts;
//	private String username,password;
//	private Boolean result;
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
//		
//		Session session = HibernateSessionFactory.getSession();
//		
//		
////		Transaction ts = session.beginTransaction();
////		TableUser tu = new TableUser();
////		
////		tu.setPassword("123456");
////		tu.setUserId(new Integer(4));
////		tu.setUserName("test2");
////		
////		session.save(tu);
////		ts.commit();
//		
//
//
//		username = request.getParameter("username");
//		password = request.getParameter("password");
//		
//		Query query = session.createQuery("from TableUser where user_name = ? and pass_word=?");
//		query.setString(0, username);
//		query.setString(1, password);
//		List list = query.list();
//		HibernateSessionFactory.closeSession();
//
//		response.setContentType("text/plain; charset=gbk");
//		request.setCharacterEncoding("gbk");
//		System.err.println(request.getParameter("username"));
//		System.err.println(request.getParameter("password"));
//		PrintWriter printWriter = response.getWriter();
////		printWriter.print("您好Android客户端！");
////		printWriter.print(tu1.getUserName());
//
//		if(list.size()==0){
//			printWriter.print("用户名或密码错误，请重试");
//		}else{
//			TableUser tu1 = (TableUser) list.get(0);
//		
//			printWriter.print("1");
//		}
//		printWriter.flush();
//		printWriter.close();
//	}
//}