package com.ghj.packageOfAction;

import java.awt.Image;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.SearchDao;
import org.dao.ShowDao;
import org.dao.imp.SearchDaoImp;
import org.dao.imp.ShowDaoImp;
import org.model.TableCommodity;

import com.opensymphony.xwork2.ActionSupport;

@Entity
public class SearchAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;

	private String commodityTitle;
	private String commodityDetail;
	private Image commodityPicture;
	@Id
	@GeneratedValue
	private int commodityId;
	HttpServletRequest request;
	HttpServletResponse response;

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public Image getCommodityPicture() {
		return commodityPicture;
	}

	public void setCommodityPicture(Image commodityPicture) {
		this.commodityPicture = commodityPicture;
	}

	public String getCommodityTitle() {
		return commodityTitle;
	}

	public void setCommodityTitle(String commodityTitle) {
		this.commodityTitle = commodityTitle;
	}

	public String getCommodityDetail() {
		return commodityDetail;
	}

	public void setCommodityDetail(String commodityDetail) {
		this.commodityDetail = commodityDetail;
	}


	@SuppressWarnings("null")
	@Override
	public String execute() throws Exception {
		
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");

		commodityTitle = this.request.getParameter("searchCommodity");//getUserName();
System.out.println(commodityTitle);
		/*
		 * 提取存在arrayList中的TableCommidaty对象
		 */
		//必须先定义sd否则报错
		SearchDao searchDao = new SearchDaoImp();

		List queryList = searchDao.getSearchResult(commodityTitle);

		JSONObject searchCommodityJson = new JSONObject();
		JSONArray searchJsonArray = new JSONArray();


		TableCommodity tableCommodity;
		//		List<String> commodityNameList = null;
		//		List<String> commodityTitleList = null;
		//		List<Image> commodityImageList1 = null;
		for(int i = 1;i<=queryList.size();i++){

			JSONObject searchJson = new JSONObject();
			tableCommodity = (TableCommodity) queryList.get(i-1);
			
			/*
			 * 以下两种方法均可以
			 */
			searchJson.put("commodityUserName"+i, tableCommodity.getUserName());
			searchJson.put("commodityTitle"+i, tableCommodity.getCommodityTitle());
			searchJson.put("commodityImage"+i, tableCommodity.getCommodityPicture1Id());
//			showJson = JSONObject.fromObject(tableCommodity);

			searchJsonArray.add(searchJson);

			//			commodityNameList.add(tableCommodity.getCommodityName());
			//			commodityTitleList.add(tableCommodity.getCommodityTitle());
			//			commodityImageList1.add(tableCommodity.getCommodityPicture1Id());
		}
		searchCommodityJson.put("commodity", searchJsonArray);
		response.setCharacterEncoding("utf-8");//必须与客户端的编码方式相同
		PrintWriter printwriter = response.getWriter();
		//		printwriter.print(list);

//		JSONObject(showCommodityJson);//第三方控件json.jar中的方法
		//这才是json数据
		printwriter.print(searchCommodityJson);//传到客户端的数据不完整，应该是因为print writer不能传输过大的数据
											//之前想法不完全正确，因为没有关闭print writer，所以到时间自动终止数据传输。
	
		printwriter.close();


		//		@SuppressWarnings("unchecked")
		//		Map<String, List<String>> showSession = (Map<String, List<String>>) ActionContext.getContext().get("request");//get()方法中必须为"request"
		//		showSession.put("commodity_name_list", commodityNameList);
		//		showSession.put("commodity_title_list", commodityTitleList);
		//		for(int i=1000000006;;i++){
		//			if(i==0) break;
		//		}
		return SUCCESS;
	}
}
