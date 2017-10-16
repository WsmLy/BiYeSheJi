package org.model;

/**
 * TableAddressId entity. @author MyEclipse Persistence Tools
 */

public class TableAddressId implements java.io.Serializable {

	// Fields

	private String userId;
	private String address;

	// Constructors

	/** default constructor */
	public TableAddressId() {
	}

	/** full constructor */
	public TableAddressId(String userId, String address) {
		this.userId = userId;
		this.address = address;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TableAddressId))
			return false;
		TableAddressId castOther = (TableAddressId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		return result;
	}

}