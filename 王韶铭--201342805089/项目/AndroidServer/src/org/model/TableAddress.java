package org.model;

/**
 * TableAddress entity. @author MyEclipse Persistence Tools
 */

public class TableAddress implements java.io.Serializable {

	// Fields

	private TableAddressId id;

	// Constructors

	/** default constructor */
	public TableAddress() {
	}

	/** full constructor */
	public TableAddress(TableAddressId id) {
		this.id = id;
	}

	// Property accessors

	public TableAddressId getId() {
		return this.id;
	}

	public void setId(TableAddressId id) {
		this.id = id;
	}

}