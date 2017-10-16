package com.ghj.packageOfAction;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.RegisterDao;
import org.dao.imp.RegisterDaoImp;
import org.model.TableUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Entity
public class RegisterAction extends ActionSupport {
	
	HttpServletRequest request;
	HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private static final long serialVersionUID = 1L;//如果没有这句就会出错，提示找不到action

	private String userName;
	private String password;
	private String passwordSure;
	private String phone;
	private String userImg;

	public String getPhone() {
		phone = this.request.getParameter("phone");
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getUserImg() {
		userImg = this.request.getParameter("userImg");
		return userImg;
	}


	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}


	public String getUserName() {
		userName = this.request.getParameter("userName");
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


	public String getPasswordSure() {
		passwordSure = this.request.getParameter("passwordSure");
		return passwordSure;
	}


	public void setPasswordSure(String passwordSure) {
		this.passwordSure = passwordSure;
	}


	public String execute() throws Exception{
		userName = getUserName();
		password = getPassword();
		passwordSure = getPasswordSure();
		phone = getPhone();
		userImg = getUserImg();

		if(!password.equals(passwordSure)){
			System.out.println("两次输入的密码不一致！\n"+password+"\n"+passwordSure);
			return ERROR;
		}else{
			RegisterDao registerDao = new RegisterDaoImp();
			TableUser tableUser = registerDao.register(userName, password,phone,userImg);
			if(tableUser==null){
				return null;
			}else{
				Map userTableSession = (Map) ActionContext.getContext().get("request");
				userTableSession.put("user", tableUser.getUserName());
				return SUCCESS;
			}
		}

	}

}
