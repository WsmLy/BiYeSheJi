package org.model;

/**
 * TableShoppingcarId entity. @author MyEclipse Persistence Tools
 */

public class TableShoppingcarId implements java.io.Serializable {

	// Fields

	private String userName;
	private Integer commodityId;

	// Constructors

	/** default constructor */
	public TableShoppingcarId() {
	}

	/** full constructor */
	public TableShoppingcarId(String userName, Integer commodityId) {
		this.userName = userName;
		this.commodityId = commodityId;
	}

	// Property accessors

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TableShoppingcarId))
			return false;
		TableShoppingcarId castOther = (TableShoppingcarId) other;

		return ((this.getUserName() == castOther.getUserName()) || (this
				.getUserName() != null && castOther.getUserName() != null && this
				.getUserName().equals(castOther.getUserName())))
				&& ((this.getCommodityId() == castOther.getCommodityId()) || (this
						.getCommodityId() != null
						&& castOther.getCommodityId() != null && this
						.getCommodityId().equals(castOther.getCommodityId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserName() == null ? 0 : this.getUserName().hashCode());
		result = 37
				* result
				+ (getCommodityId() == null ? 0 : this.getCommodityId()
						.hashCode());
		return result;
	}

}