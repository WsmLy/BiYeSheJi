package com.ghj.packageOfAction;

import java.io.PrintWriter;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.LoginDao;
import org.dao.imp.LoginDaoImp;
import org.model.TableUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Entity
public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{// implements ServletResponseAware,ServletRequestAware{

	@Id
	@GeneratedValue
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	//	public void login(){
	//		try{
	//			this.response.setContentType("text/html;charset=utf-8");
	//			this.response.setCharacterEncoding("UTF-8");
	//			
	//			/**	
	//			if(this.request.getParameter("username").equals("123456")){
	//                 this.response.getWriter().write("真的很奇怪,日本人！");
	//            }else if(this.request.getParameter("username").equals("zhd")){
	//                this.response.getWriter().write("没有错，我就是东子哥！");
	//            }else{
	//                this.response.getWriter().write("我就是东子哥！");
	//            }
	//		**/
	//	
	//		
	//			Session session = HibernateSessionFactory.getSession();
	//
	//			userName = request.getParameter("username");
	//			password = request.getParameter("password");
	//
	//			Query query = session.createQuery("from TableUser where user_name=? and password=?");
	//			query.setString(0, userName);
	//			query.setString(1, password);
	//			List list = query.list();
	//			HibernateSessionFactory.closeSession();
	//
	////			response.setContentType("text/plain; charset=gbk");
	////			request.setCharacterEncoding("gbk");
	//			System.err.println(request.getParameter("username"));
	//			System.err.println(request.getParameter("password"));
	//			PrintWriter printWriter = response.getWriter();
	//			//		printWriter.print("您好Android客户端！");
	//			//		printWriter.print(tu1.getUserName());
	//
	//			if(list.size()==0){
	//				printWriter.print("用户名或密码错误，请重试");
	//			}else{
	//				TableUser tu1 = (TableUser) list.get(0);
	//				printWriter.print("1");
	//			}
	//			printWriter.flush();
	//			printWriter.close();
	//
	//		}catch(Exception e){
	//			e.printStackTrace();
	//		}
	//	}

	private String userName;
	private String password;


	public String getUserName() {
//		userName = this.request.getParameter("userName");
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		password = this.request.getParameter("password");
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String execute() throws Exception{
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		
		userName = this.request.getParameter("username");//getUserName();
		password = getPassword();
		
		System.out.println(userName);
		System.out.println(password);
		LoginDao loginDao = new LoginDaoImp(userName,password);
		TableUser userTable = loginDao.validate();
		if(userTable!=null){
			System.out.println(userTable.getUserName());
			Map userTableSession = (Map)ActionContext.getContext().get("request");//get()方法中必须为"request"
//			System.out.println(userTable.getPassword());
//			System.out.println(userName);
			userTableSession.put("user", userName);	
//			System.out.println(userTable.getUserName());
//			System.out.println(userTable.getPassword()+"aa");

			PrintWriter pw = response.getWriter();
			pw.print("1");
			pw.flush();
			pw.close();
			return SUCCESS;
		}else{

			PrintWriter pw = response.getWriter();
			pw.print("5");
			System.out.println("登录失败！");
			return ERROR;
		}

		//		Session session = HibernateSessionFactory.getSession();
		//

		//
		//		Query query = session.createQuery("from TableUser where userName=? and password=?");
		//		query.setString(0, userName);
		//		query.setString(1, password);
		//		List list = query.list();
		//		HibernateSessionFactory.closeSession();
		//
		//		response.setContentType("text/plain; charset=gbk");
		//		request.setCharacterEncoding("gbk");
		//		System.err.println(request.getParameter("username"));
		//		System.err.println(request.getParameter("password"));
		//		PrintWriter printWriter = response.getWriter();
		//		//		printWriter.print("您好Android客户端！");
		//		//		printWriter.print(tu1.getUserName());
		//
		//		if(list.size()==0){
		//			printWriter.print("用户名或密码错误，请重试");
		//			printWriter.flush();
		//			printWriter.close();
		//			Map request=(Map) ActionContext.getContext().get("request");
		//			request.put("name", userName);
		//
		//			return "error";
		//		}else{
		//			TableUser tu1 = (TableUser) list.get(0);
		//
		//			printWriter.flush();
		//			printWriter.close();
		//
		//			printWriter.print("1");
		//			return "success";
		//		}
	}

}
