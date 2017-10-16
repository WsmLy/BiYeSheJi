package org.model;

/**
 * TableRecommend entity. @author MyEclipse Persistence Tools
 */

public class TableRecommend implements java.io.Serializable {

	// Fields

	private Integer commodityId;
	private String commodityTitle;
	private String commodityImageId;

	// Constructors

	/** default constructor */
	public TableRecommend() {
	}

	/** minimal constructor */
	public TableRecommend(Integer commodityId) {
		this.commodityId = commodityId;
	}

	/** full constructor */
	public TableRecommend(Integer commodityId, String commodityTitle,
			String commodityImageId) {
		this.commodityId = commodityId;
		this.commodityTitle = commodityTitle;
		this.commodityImageId = commodityImageId;
	}

	// Property accessors

	public Integer getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityTitle() {
		return this.commodityTitle;
	}

	public void setCommodityTitle(String commodityTitle) {
		this.commodityTitle = commodityTitle;
	}

	public String getCommodityImageId() {
		return this.commodityImageId;
	}

	public void setCommodityImageId(String commodityImageId) {
		this.commodityImageId = commodityImageId;
	}

}