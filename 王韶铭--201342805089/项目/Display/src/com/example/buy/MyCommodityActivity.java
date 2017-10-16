package com.example.buy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.detail.DetailActivity;
import com.example.main1.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import Adapter.ShoppingAdapter;
import Adapter.MyCommodityAdapter;
import AsyncTask.DataTask;
import Interfaces.InitAndRefreshInterface;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyCommodityActivity extends Activity implements InitAndRefreshInterface {
	
	private PullToRefreshListView lv_my_commodity;
	private String selectCommodity;
	private List<String> list = new ArrayList<String>();
	private String select;
	private String uri;
	private String username;
	private int timer;

	List<String> tv_main_list_image = new ArrayList<String>();
	List<Bitmap> bm_main_list_image = new ArrayList<Bitmap>();
	List<String> tv_main_list_title = new ArrayList<String>(); 
	List<String> tv_main_list_content = new ArrayList<String>();  
	List<String> tv_main_list_detail = new ArrayList<String>();
	
	MyCommodityAdapter ma_main_list_title; 
	String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		url = getResources().getString(R.string.url_web);
		setContentView(R.layout.activity_my_commodity);
		
		selectCommodity = getIntent().getStringExtra("select");
//		Toast.makeText(getApplication(), selectCommodity, Toast.LENGTH_SHORT).show();
		Intent intent = getIntent();
		select = intent.getStringExtra("select");

		uri = url+"/show.action";
		username = intent.getStringExtra("username");
		switch (select) {
		case "fabu":
			getActionBar().setTitle("我发布的");
			break;
//		case "sale":
//			getActionBar().setTitle("我卖出的");
//			break;			
//		case "bought":
//			getActionBar().setTitle("我买到的");
//			break;
		default:
			break;
		}
		
		lv_my_commodity = (PullToRefreshListView) findViewById(R.id.lv_my_commodity);
		
		timer = 0;
		ConnServ();
		
		/*
		 * getLoadingLayoutProxy()方法必须在此处调用
		 * 若在别处调用很有可能会发生第一次下拉或上拉的时候不能正确显示
		 */
		lv_my_commodity.setMode(Mode.BOTH);//设置上拉加载和下拉刷新
		//设置拉动时的显示
		lv_my_commodity.getLoadingLayoutProxy(false, true).setPullLabel(getResources().getString(R.string.shanglajiazai));
		lv_my_commodity.getLoadingLayoutProxy(false, true).setRefreshingLabel(getResources().getString(R.string.zhengzaijiazai));
		lv_my_commodity.getLoadingLayoutProxy(false, true).setReleaseLabel(getResources().getString(R.string.songkaijiazai));
		lv_my_commodity.getLoadingLayoutProxy(true, false).setPullLabel(getResources().getString(R.string.xialashuaxin));
		lv_my_commodity.getLoadingLayoutProxy(true, false).setRefreshingLabel(getResources().getString(R.string.zhengzaishuaxin));
		lv_my_commodity.getLoadingLayoutProxy(true, false).setReleaseLabel(getResources().getString(R.string.songkaishuaxin));

		/*列表显示*/
		//刚开始使用的第一种方法直接把数据放入arraylist中，复杂度较大且不能放入多种数据
		//		List<String>list = new ArrayList<String>();	
		//		for(int i=0;i<10;i++){
		//			list.add("leimu111");}
		//		ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
		//		lv_xiangqing.setAdapter(adapter);

		//后来使用的第二种方法：新建一个Adapter类把数据放入类中。一个layout。
		//既能减少复杂度，又能放入一个view
		
		lv_my_commodity.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {

				timer += 10;
				
				new DataTask(MyCommodityActivity.this,lv_my_commodity,timer).execute();

				ConnServ();

			}

		});
		
//		init_Data();
		
		lv_my_commodity.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				//				tv_main_1_text = tv_main_1.get(arg2);
				//				tv_main_2_text = tv_main_2.get(arg2);
				//				charAt = string.charAt(2);
				//				Log.e("JUN", "" + charAt);
				Intent intent = new Intent(MyCommodityActivity.this,DetailActivity.class);
				
				Bitmap bitmap = bm_main_list_image.get(position-1);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
				byte[] bs = byteArrayOutputStream.toByteArray();
				String string = Base64.encodeToString(bs, Base64.DEFAULT);
				System.out.println(position-1);
				
//				intent.putExtra("image", value)
				intent.putExtra("title", tv_main_list_title.get(position-1));
				intent.putExtra("content", tv_main_list_detail.get(position-1));
				intent.putExtra("image", string);//bm_main_list_image.get(position-1).toString());
				startActivity(intent);
			}
		});
	}
	

	@Override
	public void init_Data() {
		ma_main_list_title = new MyCommodityAdapter(tv_main_list_title, tv_main_list_content,bm_main_list_image);
		lv_my_commodity.setAdapter(ma_main_list_title);		
	}
	
	//创建连接服务器方法并在onCreate中调用
	public void ConnServ(){
		//连接服务器并获取列表数据
		new Thread(new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				Looper.prepare();
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpRequest;
				try {

					httpRequest = new HttpPost(uri);
					List<NameValuePair> send = new ArrayList<NameValuePair>();
					send.add(new BasicNameValuePair("timer", timer+""));
					send.add(new BasicNameValuePair("frag", select));
					send.add(new BasicNameValuePair("username", username));
				
					httpRequest.setEntity(new UrlEncodedFormEntity(send, HTTP.UTF_8));

//					httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
//					httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

					HttpResponse httpResponse;
					httpResponse = httpClient.execute(httpRequest);
					
					if(httpResponse.getStatusLine().getStatusCode() == 200){
						HttpEntity entity = httpResponse.getEntity();

						if(entity!=null){
//							String out = readParse(getResources().getString(R.string.url_web)+"/show.action");
							String out = EntityUtils.toString(entity,"utf-8");
//							Message message = new Message();

							onStop();
							JsonUtils(out);
							for(int i=1;i<= tv_main_list_image.size();i++){
								bm_main_list_image.add(getUrlImage(url+"/"+tv_main_list_image.get(i-1)));
//								bitmap[i-1] = (Bitmap) getUrlImage(url+"/"+tv_recommend_list_image.get(i-1));
							}
//							message.obj = out;
//							handler.sendMessage(message);
							ArrayList list = new ArrayList(); //这个list用于在budnle中传递 需要传递的ArrayList<Object>
//							list.add(bm_recommend_list_image);
							list.add(bm_main_list_image);
							list.add(tv_main_list_title);
							list.add(tv_main_list_content);
							Message message = new Message();
							Bundle bundle = new Bundle();
							bundle.putParcelableArrayList("list",list);
							message.setData(bundle);
//							message.obj = bm_recommend_list_image;
							handler.sendMessage(message);
						}
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (NotFoundException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Looper.loop();
			}
		}).start();
	
	}
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
//			String jsonStrng = msg.obj.toString();
            List image = msg.getData().getParcelableArrayList("list");

            bm_main_list_image =   (List<Bitmap>) image.get(0);
            tv_main_list_title = (List<String>) image.get(1);
            tv_main_list_content = (List<String>) image.get(2);
			init_Data();
			super.handleMessage(msg);
		};
	};
	
	private Bitmap getUrlImage(String url) {
		Bitmap bitmap = null;
		try {
			URL picture = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) picture.openConnection();
			connection.setConnectTimeout(6000);//设置超时
			connection.setDoInput(true);
			connection.setUseCaches(false);//不缓存
			connection.connect();
			InputStream iStream = connection.getInputStream();//获取图片的数据流
			bitmap = BitmapFactory.decodeStream(iStream);
			iStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	JSONArray jsonArray;
	JSONObject jsonImageObj = new JSONObject();
	public void JsonUtils(String out){
		
			
			try {
				jsonArray = new JSONObject(out).getJSONArray("commodity");
				for(int i=1;i<=jsonArray.length();i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i-1);
					tv_main_list_title.add(jsonObject.optString("commodityTitle"+i));
					tv_main_list_content.add(jsonObject.optString("commodityUserName"+i));
					tv_main_list_image.add(jsonObject.optString("commodityImage"+i));
					tv_main_list_detail.add(jsonObject.optString("commodityDetail"+i));
//					jsonImageObj.put("commodityImage"+i,jsonObject.optString("commodityImage"+i));
//
				}
				
//				init_Data();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

}
