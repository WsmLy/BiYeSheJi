package com.ghj.packageOfAction;

import java.util.List;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dao.RegisterDao;
import org.dao.UploadDao;
import org.dao.imp.RegisterDaoImp;
import org.dao.imp.UploadDaoImp;
import org.hibernate.Query;
import org.model.TableCommodity;
import org.model.TableUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UpLoadAction extends ActionSupport {

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

	private String commodityTitle,commodityDetail,
	commodityPicture1Id,commodityPicture2Id,commodityPicture3Id,
	commodityPicture4Id,commodityPicture5Id;
	private String userName;
	private Long commodityId = 1000000000l;
	private String select = this.request.getParameter("sometodo");

	public String getCommodityTitle() {
		commodityTitle = this.request.getParameter("commodityTitle");
		return commodityTitle;
	}

	public void setCommodityTitle(String commodityTitle) {
		this.commodityTitle = commodityTitle;
	}

	public String getCommodityDetail() {
		commodityDetail = this.request.getParameter("commodityDetail");
		return commodityDetail;
	}

	public void setCommodityDetail(String commodityDetail) {
		this.commodityDetail = commodityDetail;
	}

	public String getCommodityPicture1Id() {
		commodityPicture1Id = this.request.getParameter("image");
		return commodityPicture1Id;
	}

	public void setCommodityPicture1Id(String commodityPicture1Id) {
		this.commodityPicture1Id = commodityPicture1Id;
	}

	public String getCommodityPicture2Id() {
		commodityPicture2Id = this.request.getParameter("commodityPicture2Id");
		return commodityPicture2Id;
	}

	public void setCommodityPicture2Id(String commodityPicture2Id) {
		this.commodityPicture2Id = commodityPicture2Id;
	}

	public String getCommodityPicture3Id() {
		commodityPicture3Id = this.request.getParameter("commodityPicture3Id");
		return commodityPicture3Id;
	}

	public void setCommodityPicture3Id(String commodityPicture3Id) {
		this.commodityPicture3Id = commodityPicture3Id;
	}

	public String getCommodityPicture4Id() {
		commodityPicture4Id = this.request.getParameter("commodityPicture4Id");
		return commodityPicture4Id;
	}

	public void setCommodityPicture4Id(String commodityPicture4Id) {
		this.commodityPicture4Id = commodityPicture4Id;
	}

	public String getCommodityPicture5Id() {
		commodityPicture5Id = this.request.getParameter("commodityPicture5Id");
		return commodityPicture5Id;
	}

	public void setCommodityPicture5Id(String commodityPicture5Id) {
		this.commodityPicture5Id = commodityPicture5Id;
	}

	public String getUserName() {
		userName = this.request.getParameter("userName");
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getCommodityId() {
		commodityId++;// = this.request.getParameter("commodityId");
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public String execute() throws Exception{

		userName = getUserName();
		commodityTitle = getCommodityTitle();
		commodityDetail = getCommodityDetail();
		commodityPicture1Id = getCommodityPicture1Id();
		commodityPicture2Id = getCommodityPicture2Id();
		commodityPicture3Id = getCommodityPicture3Id();
		commodityPicture4Id = getCommodityPicture4Id();
		commodityPicture5Id = getCommodityPicture5Id();

		TableCommodity commodity = new TableCommodity();
		commodity.setCommodityDetail(commodityDetail);
		commodity.setCommodityTitle(commodityTitle);
		commodity.setUserName(userName);
		commodity.setCommodityPicture1Id(commodityPicture1Id);
		commodity.setCommodityPicture2Id(commodityPicture2Id);
		commodity.setCommodityPicture3Id(commodityPicture3Id);
		commodity.setCommodityPicture4Id(commodityPicture4Id);
		commodity.setCommodityPicture5Id(commodityPicture5Id);

		UploadDao uploadDao = new UploadDaoImp();
		
		if ("delete".equals(select)){
			uploadDao.delete(commodity);
			return null;
		}else if ("upload".equals(select)) {
		
			TableCommodity tableCommodity = uploadDao.upLoad(commodity);
			if(tableCommodity==null){
				return null;
			}else{
				//				Map userTableSession = (Map) ActionContext.getContext().get("request");
				//				userTableSession.put("user", .getUserName());
				return SUCCESS;
			}
		}else if ("update".equals(select)){

		}
		return null;
	}



}
