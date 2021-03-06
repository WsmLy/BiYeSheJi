package Servers;

/**
 * TableCommodity entity. @author MyEclipse Persistence Tools
 */

public class TableCommodity implements java.io.Serializable {

	// Fields

	private Integer commodityId;
	private Integer userId;
	private String commodityTitle;
	private String commodityDetail;
	private String commodityPicture1Id;
	private String commodityPicture2Id;
	private String commodityPicture3Id;
	private String commodityPicture4Id;
	private String commodityPicture5Id;
	private String commodityName;

	// Constructors

	/** default constructor */
	public TableCommodity() {
	}

	/** minimal constructor */
	public TableCommodity(Integer commodityId, Integer userId,
			String commodityTitle, String commodityDetail,
			String commodityPicture1Id) {
		this.commodityId = commodityId;
		this.userId = userId;
		this.commodityTitle = commodityTitle;
		this.commodityDetail = commodityDetail;
		this.commodityPicture1Id = commodityPicture1Id;
	}

	/** full constructor */
	public TableCommodity(Integer commodityId, Integer userId,
			String commodityTitle, String commodityDetail,
			String commodityPicture1Id, String commodityPicture2Id,
			String commodityPicture3Id, String commodityPicture4Id,
			String commodityPicture5Id, String commodityName) {
		this.commodityId = commodityId;
		this.userId = userId;
		this.commodityTitle = commodityTitle;
		this.commodityDetail = commodityDetail;
		this.commodityPicture1Id = commodityPicture1Id;
		this.commodityPicture2Id = commodityPicture2Id;
		this.commodityPicture3Id = commodityPicture3Id;
		this.commodityPicture4Id = commodityPicture4Id;
		this.commodityPicture5Id = commodityPicture5Id;
		this.commodityName = commodityName;
	}

	// Property accessors

	public Integer getCommodityId() {
		return this.commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCommodityTitle() {
		return this.commodityTitle;
	}

	public void setCommodityTitle(String commodityTitle) {
		this.commodityTitle = commodityTitle;
	}

	public String getCommodityDetail() {
		return this.commodityDetail;
	}

	public void setCommodityDetail(String commodityDetail) {
		this.commodityDetail = commodityDetail;
	}

	public String getCommodityPicture1Id() {
		return this.commodityPicture1Id;
	}

	public void setCommodityPicture1Id(String commodityPicture1Id) {
		this.commodityPicture1Id = commodityPicture1Id;
	}

	public String getCommodityPicture2Id() {
		return this.commodityPicture2Id;
	}

	public void setCommodityPicture2Id(String commodityPicture2Id) {
		this.commodityPicture2Id = commodityPicture2Id;
	}

	public String getCommodityPicture3Id() {
		return this.commodityPicture3Id;
	}

	public void setCommodityPicture3Id(String commodityPicture3Id) {
		this.commodityPicture3Id = commodityPicture3Id;
	}

	public String getCommodityPicture4Id() {
		return this.commodityPicture4Id;
	}

	public void setCommodityPicture4Id(String commodityPicture4Id) {
		this.commodityPicture4Id = commodityPicture4Id;
	}

	public String getCommodityPicture5Id() {
		return this.commodityPicture5Id;
	}

	public void setCommodityPicture5Id(String commodityPicture5Id) {
		this.commodityPicture5Id = commodityPicture5Id;
	}

	public String getCommodityName() {
		return this.commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

}