package org.model;

/**
 * TableFenlei entity. @author MyEclipse Persistence Tools
 */

public class TableFenlei implements java.io.Serializable {

	// Fields

	private String commodityId;
	private String commodityLeibie;

	// Constructors

	/** default constructor */
	public TableFenlei() {
	}

	/** minimal constructor */
	public TableFenlei(String commodityId) {
		this.commodityId = commodityId;
	}

	/** full constructor */
	public TableFenlei(String commodityId, String commodityLeibie) {
		this.commodityId = commodityId;
		this.commodityLeibie = commodityLeibie;
	}

	// Property accessors

	public String getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityLeibie() {
		return this.commodityLeibie;
	}

	public void setCommodityLeibie(String commodityLeibie) {
		this.commodityLeibie = commodityLeibie;
	}

}