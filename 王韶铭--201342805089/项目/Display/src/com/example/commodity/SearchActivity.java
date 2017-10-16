package com.example.commodity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.detail.DetailActivity;
import com.example.main1.BitMapToString;
import com.example.main1.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import Adapter.MainAdapter;
import Adapter.ShoppingAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchActivity extends Activity {
	private ImageButton searchReturn;
	private EditText searchEditText;
	private Button searchButton;
	private String searchText;

	private ListView searchResult;

	List<String> tv_search_list_title = new ArrayList<String>(); 
	List<String> tv_search_list_content = new ArrayList<String>(); 
	List<String> tv_search_list_image = new ArrayList<String>();
	List<Bitmap> bm_search_list_image = new ArrayList<Bitmap>();
//	ShoppingAdapter ma_search_list_title;
	MainAdapter ma_search_list_title;
	String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		FindView();
		System.out.println("----------");
		OnClickListener();
	}

	private void OnClickListener() {
		//返回按钮点击事件处理
		searchReturn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});	
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				url = getResources().getString(R.string.url_web);
				searchText = searchEditText.getText().toString();
				ConnServ();
			}
		});
		searchResult.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				//				tv_main_1_text = tv_main_1.get(arg2);
				//				tv_main_2_text = tv_main_2.get(arg2);
				//				charAt = string.charAt(2);
				//				Log.e("JUN", "" + charAt);
				Intent intent = new Intent(SearchActivity.this,DetailActivity.class);

				Bitmap bitmap = bm_search_list_image.get(position-1);
				//				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				//				bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
				byte[] bs = BitMapToString.bitmapToString(bitmap);//byteArrayOutputStream.toByteArray();
				//				String string = Base64.encodeToString(bs, Base64.DEFAULT);

				//				intent.putExtra("image", value)
//				intent.putExtra("username", username);
				intent.putExtra("title", tv_search_list_title.get(position-1));
				intent.putExtra("content", tv_search_list_content.get(position-1));
//				intent.putExtra("commodityId", tv_main_list_commodityId.get(position-1));
				intent.putExtra("image", bs);//string);//bm_main_list_image.get(position-1).toString());
				startActivity(intent);				
			}
		});
	}

	private void FindView(){//findViewById的方法
		searchReturn = (ImageButton) findViewById(R.id.ib_search_return);//返回按钮
		searchEditText = (EditText) findViewById(R.id.et_search);//输入搜索框
		searchButton = (Button) findViewById(R.id.btn_search);//搜索按钮
		searchResult = (ListView) findViewById(R.id.lv_search_result);//搜索结果显示在这个listview中
	}


	//创建连接服务器方法并在onCreate中调用
	public void ConnServ(){
		//连接服务器并获取列表数据
		new Thread(new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				Looper.prepare();
				HttpClient httpClient = new DefaultHttpClient();
				try {
				HttpPost httpRequest;
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("searchCommodity", searchText));
				
					httpRequest = new HttpPost(url+"/search.action");


					httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

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
							System.out.println(out);

							JsonUtils(out);
							for(int i=1;i<= tv_search_list_image.size();i++){
								bm_search_list_image.add(getUrlImage(url+"/"+tv_search_list_image.get(i-1)));
								//								bitmap[i-1] = (Bitmap) getUrlImage(url+"/"+tv_recommend_list_image.get(i-1));
							}
							//							message.obj = out;
							//							handler.sendMessage(message);
							ArrayList list = new ArrayList(); //这个list用于在budnle中传递 需要传递的ArrayList<Object>
							//							list.add(bm_recommend_list_image);
							list.add(bm_search_list_image);
							list.add(tv_search_list_title);
							list.add(tv_search_list_content);
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
	public void init_Data() {

		//		init_list_recommend();
		//		ma_recommend_list_title = new RecommendAdapter(getResources().getString(R.string.url_web)+"/show.action");//tv_recommend_list_content,tv_recommend_list_title);
//		ma_search_list_title  = new ShoppingAdapter(tv_search_list_title, tv_search_list_content,bm_search_list_image);
		ma_search_list_title = new MainAdapter(tv_search_list_title, tv_search_list_content, bm_search_list_image);
		
		searchResult.setAdapter(ma_search_list_title);

	}
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			//			String jsonStrng = msg.obj.toString();
			List image = msg.getData().getParcelableArrayList("list");

			bm_search_list_image =   (List<Bitmap>) image.get(0);
			tv_search_list_title = (List<String>) image.get(1);
			tv_search_list_content = (List<String>) image.get(2);
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
				tv_search_list_title.add(jsonObject.optString("commodityTitle"+i));
				tv_search_list_content.add(jsonObject.optString("commodityUserName"+i));
				tv_search_list_image.add(jsonObject.optString("commodityImage"+i));
				//					jsonImageObj.put("commodityImage"+i,jsonObject.optString("commodityImage"+i));
				//
			}

			//				init_Data();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
