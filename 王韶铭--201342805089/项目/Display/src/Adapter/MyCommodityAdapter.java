package Adapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.acl.Group;
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
import org.json.JSONObject;

import com.example.buy.MyCommodityActivity;
import com.example.main1.R;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyCommodityAdapter extends BaseAdapter{
//
//	List<Integer> imageview = new ArrayList<Integer>();
//	List<String> tv_main_1 = new ArrayList<String>();
//	List<String> tv_main_2 = new ArrayList<String>();
//	
	List<String> tv_main_list_title = new ArrayList<String>(); 
	List<String> tv_main_list_content = new ArrayList<String>(); 
	List<String> tv_main_list_image = new ArrayList<String>();
	JSONObject jsonImageObj;
	List<Bitmap> bm_main_list_image = new ArrayList<Bitmap>();
	String url;
//	
//	Context context;
	public MyCommodityAdapter(List<String> tv_main_1,List<String> tv_main_2,List<Bitmap> bm_recommend_list_image) {
//		this.url = url;
		
		this.tv_main_list_content = tv_main_1;
		this.tv_main_list_title = tv_main_2;
//		this.jsonImageObj = jsonImageObj;
//		this.url = url;
		this.bm_main_list_image = bm_recommend_list_image;
		
//		this.context = context;
//		this.tv_main_1=tv_main_1;
//		imageview.add(R.drawable.baisefanbuxie);
//		imageview.add(R.drawable.bangqiumao);
//		imageview.add(R.drawable.erding);
//		imageview.add(R.drawable.kuabao);
//		imageview.add(R.drawable.meibi);
//		imageview.add(R.drawable.meizukuaichong);
//		imageview.add(R.drawable.mianmashan);
//		imageview.add(R.drawable.pingguoerji);
//		imageview.add(R.drawable.sanxingshouji);
//		imageview.add(R.drawable.taiyangmao);
//		imageview.add(R.drawable.xiezhuangshijin);
//		imageview.add(R.drawable.password_image);
//		imageview.add(R.drawable.ic_launcher);
//		imageview.add(R.drawable.password_image);
//		tv_main_2.add("aaa");
//		tv_main_2.add("bbb");
//		tv_main_2.add("aaa");
//		tv_main_2.add("bbb");
//		tv_main_2.add("aaa");
//		tv_main_2.add("bbb");
//		tv_main_2.add("aaa");
//		tv_main_2.add("bbb");
//		tv_main_2.add("aaa");
//		tv_main_2.add("bbb");
//		tv_main_2.add("aaa");
//		tv_main_2.add("bbb");
//		tv_main_2.add("aaa");
//		tv_main_2.add("bbb");
	}
	
	@Override
	public int getCount() {
		return tv_main_list_title.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return tv_main_list_title.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		ViewHolder vh=null;
		
		if(convertView==null){
			vh=new ViewHolder();
			convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_format_extend, null);
			vh.iv_main=(ImageView) convertView.findViewById(R.id.iv_main_list);
			vh.tv_main_2=(TextView) convertView.findViewById(R.id.tv_main_list_content);
			vh.tv_main_1=(TextView) convertView.findViewById(R.id.tv_main_list_title);
			vh.button = (Button) convertView.findViewById(R.id.button);
			vh.button2 = (Button) convertView.findViewById(R.id.button2);
			
//			LinearLayout linearLayout = new LinearLayout(context);
//			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//			linearLayout.setLayoutParams(params);
//			linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//			linearLayo
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder) convertView.getTag();
		}
		if (bm_main_list_image.get(position)!=null) {
			vh.iv_main.setImageBitmap(bm_main_list_image.get(position));
//			System.out.println(bm_main_list_image.get(position));
		}else {
			vh.iv_main.setBackgroundResource(R.drawable.ic_launcher);
		}
		vh.tv_main_2.setText(tv_main_list_title.get(position));
		vh.tv_main_1.setText(tv_main_list_content.get(position));
		vh.button.setText("删除");
		vh.button2.setText("修改");
		vh.button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//连接服务器并获取列表数据
				new Thread(new Runnable() {
					@SuppressWarnings("deprecation")
					public void run() {
						Looper.prepare();
						HttpClient httpClient = new DefaultHttpClient();
						HttpPost httpRequest;
						try {

							httpRequest = new HttpPost("http://192.168.191.1:8080/AndroidServer/upload.action");
							List<NameValuePair> send = new ArrayList<NameValuePair>();
							send.add(new BasicNameValuePair("sometodo", "delete"));
							send.add(new BasicNameValuePair("commodityTitle", tv_main_list_title.get(position)));
							send.add(new BasicNameValuePair("commodityDetail", tv_main_list_content.get(position)));
						
							httpRequest.setEntity(new UrlEncodedFormEntity(send, HTTP.UTF_8));

//							httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
//							httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

							HttpResponse httpResponse;
							httpResponse = httpClient.execute(httpRequest);
							
							if(httpResponse.getStatusLine().getStatusCode() == 200){
								HttpEntity entity = httpResponse.getEntity();

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
		});
		vh.button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//连接服务器并获取列表数据
				new Thread(new Runnable() {
					@SuppressWarnings("deprecation")
					public void run() {
						Looper.prepare();
						HttpClient httpClient = new DefaultHttpClient();
						HttpPost httpRequest;
						try {

							httpRequest = new HttpPost("http://192.168.191.1:8080/AndroidServer/upload.action");
							List<NameValuePair> send = new ArrayList<NameValuePair>();
							send.add(new BasicNameValuePair("sometodo", "update"));
							send.add(new BasicNameValuePair("commodityTitle", tv_main_list_title.get(position)));
							send.add(new BasicNameValuePair("commodityDetail", tv_main_list_content.get(position)));
						
							httpRequest.setEntity(new UrlEncodedFormEntity(send, HTTP.UTF_8));

//							httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
//							httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

							HttpResponse httpResponse;
							httpResponse = httpClient.execute(httpRequest);
							
							if(httpResponse.getStatusLine().getStatusCode() == 200){
								HttpEntity entity = httpResponse.getEntity();

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
		});
		return convertView;
	}
	
	 private class ViewHolder{
		ImageView iv_main;
		TextView tv_main_1;
		TextView tv_main_2;
		Button button;
		Button button2;
	}
	 /*
	  * 可以显示图片了
	  * 但是只能显示最后一张
	  * 原因应该是调用position只调用了最后一个
	  */
	 Handler handler;
		private void asyncImage(final ImageView iv_header, final int position) {
			
	         handler = new Handler() {
	            @Override
	            public void handleMessage(Message msg) {
	                super.handleMessage(msg);
	                List image = msg.getData().getParcelableArrayList("list");
	                bm_main_list_image =   (List<Bitmap>) image.get(0);
//	                if (msg.what == 0) {
//	                    Uri uri = (Uri) msg.obj;
//	                    if ( bm_recommend_list_image != null) {
	                        iv_header.setImageBitmap(bm_main_list_image.get(position));//.setImageURI(uri);
//	                    }
	                        

//	                }

	            }
	        };
		}
		
	
}
