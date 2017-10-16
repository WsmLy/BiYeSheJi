package org.model;

/**
 * TableUser entity. @author MyEclipse Persistence Tools
 */

public class TableUser implements java.io.Serializable {

	// Fields

	private String userName;
	private String password;
	private String userImg;
	private String phone;

	// Constructors

	/** default constructor */
	public TableUser() {
	}

	/** minimal constructor */
	public TableUser(String userName, String password, String phone) {
		this.userName = userName;
		this.password = password;
		this.phone = phone;
	}

	/** full constructor */
	public TableUser(String userName, String password, String userImg,
			String phone) {
		this.userName = userName;
		this.password = password;
		this.userImg = userImg;
		this.phone = phone;
	}

	// Property accessors

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserImg() {
		return this.userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}