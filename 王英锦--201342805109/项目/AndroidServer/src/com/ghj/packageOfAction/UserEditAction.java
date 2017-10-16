package com.ghj.packageOfAction;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.dao.UploadDao;
import org.dao.UserEditDao;
import org.dao.imp.UploadDaoImp;
import org.dao.imp.UserEditDaoImp;
import org.model.TableCommodity;
import org.model.TableUser;

import com.opensymphony.xwork2.ActionSupport;

public class UserEditAction extends ActionSupport {

	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private static final long serialVersionUID = 1L;//如果没有这句就会出错，提示找不到action

	private String userName,password,phone,userImg;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String execute() throws Exception{
		userName = getUserName();
		password = getPassword();
		phone = getPhone();
		userImg = getUserImg();
		TableUser user = new TableUser();
		user.setUserName(userName);
		user.setPassword(password);
		user.setPhone(phone);
		user.setUserImg(userImg);
		
		UserEditDao userEditDao = new UserEditDaoImp();
		TableUser tableUser = userEditDao.Edit(user);
		if(tableUser==null){
			return null;
		}else{
			//				Map userTableSession = (Map) ActionContext.getContext().get("request");
			//				userTableSession.put("user", .getUserName());
			return SUCCESS;
		}
	}


}
