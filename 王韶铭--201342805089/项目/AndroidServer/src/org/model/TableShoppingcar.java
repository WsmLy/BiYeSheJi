package org.model;

/**
 * TableShoppingcar entity. @author MyEclipse Persistence Tools
 */

public class TableShoppingcar implements java.io.Serializable {

	// Fields

	private Integer commodityId;
	private String userName;

	// Constructors

	/** default constructor */
	public TableShoppingcar() {
	}

	/** full constructor */
	public TableShoppingcar(Integer commodityId, String userName) {
		this.commodityId = commodityId;
		this.userName = userName;
	}

	// Property accessors

	public Integer getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}