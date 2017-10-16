package com.ghj.packageOfAction;

import java.awt.Image;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.ShowDao;
import org.dao.imp.ShowDaoImp;
import org.model.TableCommodity;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Entity
public class ShowAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	private static final long serialVersionUID = 1L;

	private String commodityTitle;
	private String commodityDetail;
	private Image commodityPicture;
	@Id
	@GeneratedValue
	private int commodityId;
	
	
	HttpServletRequest request;
	HttpServletResponse response;

	private int compare;
	private String s = "yes";

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

		commodityId = getCommodityId();
		
//		compare = 0;
		compare = Integer.parseInt(this.request.getParameter("timer"));

		/*
		 * ��ȡ����arrayList�е�TableCommidaty����
		 */
		//�����ȶ���sd���򱨴�
		ShowDao showDao = new ShowDaoImp(request.getParameter("frag"),request.getParameter("username"));
//		switch(request.getParameter("frag")) {
//		case "main":
//			
//			break;
//		case "recommend":
//			break;
//		case "shoppingcar":
//			break;
//		}

		List queryList =  showDao.getAllCommodity();

		JSONObject showCommodityJson = new JSONObject();
		JSONArray showAllJsonArray = new JSONArray();
		JSONArray showJsonArray = new JSONArray();
//		showAllJsonArray.clear();
		showJsonArray.clear();


		TableCommodity tableCommodity;
		//		List<String> commodityNameList = null;
		//		List<String> commodityTitleList = null;
		//		List<Image> commodityImageList1 = null;
		for(int i = 1;i<=queryList.size();i++){

			JSONObject showJson = new JSONObject();
			tableCommodity = (TableCommodity) queryList.get(i-1);

			/*
			 * �������ַ���������
			 */
			showJson.put("commodityUserName"+i, tableCommodity.getUserName());
			showJson.put("commodityTitle"+i, tableCommodity.getCommodityTitle());
			showJson.put("commodityImage"+i, tableCommodity.getCommodityPicture1Id());
			showJson.put("commodityDetail"+i, tableCommodity.getCommodityDetail());
			//			showJson = JSONObject.fromObject(tableCommodity);
//			System.out.println(showJson.get("commodityTitle"+i));

			showAllJsonArray.add(showJson);

			//			commodityNameList.add(tableCommodity.getCommodityName());
			//			commodityTitleList.add(tableCommodity.getCommodityTitle());
			//			commodityImageList1.add(tableCommodity.getCommodityPicture1Id());
		}

		for (int i = compare; i < compare+10; i++ ){
//			if (i >= 1000){
//				s = "next";
//			}
			if (showAllJsonArray.size() > i){
				showJsonArray.add(showAllJsonArray.get(i));

				showCommodityJson.put("commodity", showJsonArray);
//			}else{
//				s = "no";
			}else{
				break;
			}
			
		}
		showCommodityJson.put("commodity", showJsonArray);
//		showJsonArray.clear();
		response.setCharacterEncoding("utf-8");//������ͻ��˵ı��뷽ʽ��ͬ
		PrintWriter printwriter = response.getWriter();
		//		printwriter.print(list);

		//		JSONObject(showCommodityJson);//�������ؼ�json.jar�еķ���
		//�����json����
		System.out.println(showCommodityJson.opt("commodity"));
		printwriter.print(showCommodityJson);//�����ͻ��˵����ݲ�������Ӧ������Ϊprint writer���ܴ�����������
		//֮ǰ�뷨����ȫ��ȷ����Ϊû�йر�print writer�����Ե�ʱ���Զ���ֹ���ݴ��䡣
		printwriter.close();


		//		@SuppressWarnings("unchecked")
		//		Map<String, List<String>> showSession = (Map<String, List<String>>) ActionContext.getContext().get("request");//get()�����б���Ϊ"request"
		//		showSession.put("commodity_name_list", commodityNameList);
		//		showSession.put("commodity_title_list", commodityTitleList);
		//		for(int i=1000000006;;i++){
		//			if(i==0) break;
		//		}
		return SUCCESS;
	}

}
