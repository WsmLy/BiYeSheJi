package Fragment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.detail.DetailActivity;
import com.example.main1.BitMapToString;
import com.example.main1.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import Adapter.MainAdapter;
import Adapter.ShoppingAdapter;
import AsyncTask.DataTask;
import Interfaces.InitAndRefreshInterface;
import Servers.ConnServ;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.view.ViewGroup;

public class MainFragment extends Fragment implements InitAndRefreshInterface{

	View main_root_View;
	PullToRefreshListView lv_main_detail;
	HorizontalScrollView hsv_fenlei;
	MainAdapter ma_main_list_title; 

	List<String> tv_main_list_image = new ArrayList<String>();
	List<Bitmap> bm_main_list_image = new ArrayList<Bitmap>();
	List<String> tv_main_list_title = new ArrayList<String>(); 
	List<String> tv_main_list_content = new ArrayList<String>();  
	List<String> tv_main_list_detail = new ArrayList<String>();
	List<String> tv_main_list_commodityId = new ArrayList<>();

	String url;
	String username;

	int timer;

	//定义数据库
	//	MainDatabase dbMain;
	//	SQLiteDatabase dbMainReader;
	//	ContentValues cvMain;
	//	Cursor curMain;
	//	
	//服务器
	//	ConnServ connServ = new ConnServ();

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		url = getResources().getString(R.string.url_web);
		username = getActivity().getIntent().getStringExtra("username");
		timer = 0;
		ConnServ();

		main_root_View = inflater.inflate(R.layout.fragment_main, container,false);

		//		dbMain = new MainDatabase(getActivity());
		//		dbMainReader = dbMain.getReadableDatabase();
		//		curMain = dbMainReader.query(false, "MainTable", new String[]{"_id","imageId","sellerVendorName","sellerVendorId","commodityName","commodityId","originalBuyDate","originalCost","currentPrice"}, null, null, null, null, null, null, null);

		hsv_fenlei = (HorizontalScrollView) main_root_View.findViewById(R.id.main_fenlei);
		lv_main_detail = (PullToRefreshListView) main_root_View.findViewById(R.id.main_detailInfo);

		//		init_List();

		/*
		 * getLoadingLayoutProxy()方法必须在此处调用
		 * 若在别处调用很有可能会发生第一次下拉或上拉的时候不能正确显示
		 */
		lv_main_detail.setMode(Mode.BOTH);//设置上拉加载和下拉刷新
		//设置拉动时的显示
		lv_main_detail.getLoadingLayoutProxy(false, true).setPullLabel(getResources().getString(R.string.shanglajiazai));
		lv_main_detail.getLoadingLayoutProxy(false, true).setRefreshingLabel(getResources().getString(R.string.zhengzaijiazai));
		lv_main_detail.getLoadingLayoutProxy(false, true).setReleaseLabel(getResources().getString(R.string.songkaijiazai));
		lv_main_detail.getLoadingLayoutProxy(true, false).setPullLabel(getResources().getString(R.string.xialashuaxin));
		lv_main_detail.getLoadingLayoutProxy(true, false).setRefreshingLabel(getResources().getString(R.string.zhengzaishuaxin));
		lv_main_detail.getLoadingLayoutProxy(true, false).setReleaseLabel(getResources().getString(R.string.songkaishuaxin));

		/*列表显示*/
		//刚开始使用的第一种方法直接把数据放入arraylist中，复杂度较大且不能放入多种数据
		//		List<String>list = new ArrayList<String>();	
		//		for(int i=0;i<10;i++){
		//			list.add("leimu111");}
		//		ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
		//		lv_xiangqing.setAdapter(adapter);

		//后来使用的第二种方法：新建一个Adapter类把数据放入类中。一个layout。
		//既能减少复杂度，又能放入一个view

		lv_main_detail.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {

				if(lv_main_detail.isHeaderShown()){
					timer = 0;
					tv_main_list_title.clear();
					tv_main_list_content.clear();
					bm_main_list_image.clear();
				}else{
					timer += 10;
				}
				new DataTask(getActivity(),lv_main_detail,timer).execute();

				ConnServ();
			}

		});

		//		init_Data();

		lv_main_detail.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				//				tv_main_1_text = tv_main_1.get(arg2);
				//				tv_main_2_text = tv_main_2.get(arg2);
				//				charAt = string.charAt(2);
				//				Log.e("JUN", "" + charAt);
				Intent intent = new Intent(getActivity(),DetailActivity.class);

				Bitmap bitmap = bm_main_list_image.get(position-1);
				//				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				//				bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
				byte[] bs = BitMapToString.bitmapToString(bitmap);//byteArrayOutputStream.toByteArray();
				//				String string = Base64.encodeToString(bs, Base64.DEFAULT);

				//				intent.putExtra("image", value)
				intent.putExtra("username", username);
				intent.putExtra("title", tv_main_list_title.get(position-1));
				intent.putExtra("content", tv_main_list_detail.get(position-1));
				intent.putExtra("commodityId", tv_main_list_commodityId.get(position-1));
				intent.putExtra("image", bs);//string);//bm_main_list_image.get(position-1).toString());
				startActivity(intent);
			}
		});


		return main_root_View;
	}



	//定义初始化首页list
	//	public void init_List() {
	//		//		tv_main_2.add("1:1");
	//		//		tv_main_2.add("1:3");
	//		//		dbMainReader.execSQL("select commodityName from MainTable");
	//		//		tv_main_list_title.add(curMain.getString((curMain.getColumnIndex("commodityName"))));
	//		//		tv_main_list_title.add("BBB");
	//
	//		tv_main_list_title.add("白色帆布鞋");
	//		tv_main_list_title.add("棒球帽");
	//		tv_main_list_title.add("耳钉");
	//		tv_main_list_title.add("挎包");
	//		tv_main_list_title.add("眉笔");
	//		tv_main_list_title.add("魅族快充");
	//		tv_main_list_title.add("棉麻衫");
	//		tv_main_list_title.add("苹果耳机");
	//		tv_main_list_title.add("三星手机");
	//		tv_main_list_title.add("太阳帽");
	//		tv_main_list_title.add("卸妆湿巾");
	//		tv_main_list_title.add("BBB");
	//		tv_main_list_title.add("AAA");
	//		tv_main_list_title.add("BBB");
	//	}


	@Override
	public void init_Data() {
		ma_main_list_title = new MainAdapter(tv_main_list_title, tv_main_list_content,bm_main_list_image);
		lv_main_detail.setAdapter(ma_main_list_title);		
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

					httpRequest = new HttpPost(url+"/show.action");
					List<NameValuePair> send = new ArrayList<NameValuePair>();
					send.add(new BasicNameValuePair("timer", timer+""));
					send.add(new BasicNameValuePair("frag", "main"));

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

							if (out == null){//"".equals(out)) {
								Toast.makeText(getActivity(), "已经全部加载完成", Toast.LENGTH_SHORT).show();
							}
							//							onStop();
							JsonUtils(out);
							for(int i=1;i<= tv_main_list_image.size();i++){
								bm_main_list_image.add(getUrlImage(url+"/"+tv_main_list_image.get(i-1+timer)));
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
//			int m;
			for(int m=1;m<=jsonArray.length();m++){
//				m = timer + i;
				JSONObject jsonObject = jsonArray.getJSONObject(m-1);
				tv_main_list_title.add(jsonObject.optString("commodityTitle"+(m+timer)));
				tv_main_list_content.add(jsonObject.optString("commodityUserName"+(m+timer)));
				tv_main_list_image.add(jsonObject.optString("commodityImage"+(m+timer)));
				tv_main_list_detail.add(jsonObject.optString("commodityDetail"+(m+timer)));
				tv_main_list_commodityId.add(jsonObject.optString("commodityId"+(m+timer)));
//				System.out.println(jsonObject);
				//					jsonImageObj.put("commodityImage"+i,jsonObject.optString("commodityImage"+i));
				//
			}

			//				init_Data();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	//上拉加载的实现失败
	//			lv_recommend_detail.setOnRefreshListener(new OnRefreshListener2() {
	//
	//				public void onPullDownToRefresh(PullToRefreshBase refreshView) {
	//					
	//				}
	//				public void onPullUpToRefresh(PullToRefreshBase refreshView){
	//					
	//				}
	//			});

}
