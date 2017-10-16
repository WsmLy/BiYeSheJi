package Adapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.main1.R;

import android.annotation.SuppressLint;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.speech.tts.SynthesisCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class RecommendAdapter extends BaseAdapter{

	List<String> tv_recommend_list_title = new ArrayList<String>(); 
	List<String> tv_recommend_list_content = new ArrayList<String>(); 
	List<String> tv_recommend_list_image = new ArrayList<String>();
	JSONObject jsonImageObj;
	List<Bitmap> bm_recommend_list_image = new ArrayList<Bitmap>();
	String url;
//	List<String> tv_main_1 = new ArrayList<String>();
//	List<String> tv_main_2 = new ArrayList<String>();
	
	public RecommendAdapter(List<String> tv_main_1,List<String> tv_main_2,List<Bitmap> bm_recommend_list_image) {//String url){
//		this.url = url;
		
		this.tv_recommend_list_content = tv_main_1;
		this.tv_recommend_list_title = tv_main_2;
//		this.jsonImageObj = jsonImageObj;
//		this.url = url;
		this.bm_recommend_list_image = bm_recommend_list_image;
		
//		for (int i = 1; i <= jsonImageObj.length(); i++) {
//			tv_recommend_list_image.add(jsonImageObj.optString("commodityImage"+i));
//		}
//		ConnServ();
//		JsonUtils(out);
		
		
//		BitmapThread bThread = new BitmapThread();
//		bThread.start();
		
		
//		this.tv_main_1=tv_main_1;
//		this.tv_main_2=tv_main_2;
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
		return tv_recommend_list_title.size();
	}

	@Override
	public Object getItem(int position) {
		return tv_recommend_list_title.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/*
		 * 复用convertView减少view的创建次数，以优化listview中的数据
		 * 创建一个ViewHolder类减少findViewById的使用次数，优化了内存，
		 */

		ViewHolder vh=null;

		if(convertView==null){
			vh=new ViewHolder();
			convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_format, null);
			vh.iv_main=(ImageView) convertView.findViewById(R.id.iv_main_list);
			vh.tv_main_2=(TextView) convertView.findViewById(R.id.tv_main_list_content);
			vh.tv_main_1=(TextView) convertView.findViewById(R.id.tv_main_list_title);
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder) convertView.getTag();
		}
//		vh.iv_main.setImageResource(imageview.get(position));
//		String image = tv_recommend_list_image.get(position);
//		vh.iv_main.setImageBitmap(bm_recommend_list_image.get(position));
		vh.tv_main_2.setText(tv_recommend_list_title.get(position));
		
//		asyncImage(vh.iv_main,position);
		vh.iv_main.setImageBitmap(bm_recommend_list_image.get(position));
		vh.tv_main_1.setText(tv_recommend_list_content.get(position));

		return convertView;
	} 

//	private void asyncloadImage(ImageView iv_header, String path) {
//        ContactService service = new ContactService();
//        AsyncImageTask task = new AsyncImageTask(service, iv_header);
//        task.execute(path);
//    }
//	 private final class AsyncImageTask extends AsyncTask<String, Integer, Uri> {
//
//	        private ContactService service;
//	        private ImageView iv_header;
//
//	        public AsyncImageTask(ContactService service, ImageView iv_header) {
//	            this.service = service;
//	            this.iv_header = iv_header;
//	        }
//
//	        // 后台运行的子线程子线程
//	        @Override
//	        protected Uri doInBackground(String... params) {
//	            try {
//	                return service.getImageURI(params[0], cache);
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
//	            return null;
//	        }
//
//	        // 这个放在在ui线程中执行
//	        @Override
//	        protected void onPostExecute(Uri result) {
//	            super.onPostExecute(result); 
//	            // 完成图片的绑定
//	            if (iv_header != null && result != null) {
//	                iv_header.setImageURI(result);
//	            }
//	        }
//	    }
	
	 private class ViewHolder{
		ImageView iv_main;
		TextView tv_main_1;
		TextView tv_main_2;
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
	                bm_recommend_list_image =   (List<Bitmap>) image.get(0);
//	                if (msg.what == 0) {
//	                    Uri uri = (Uri) msg.obj;
//	                    if ( bm_recommend_list_image != null) {
	                        iv_header.setImageBitmap(bm_recommend_list_image.get(position));//.setImageURI(uri);
//	                    }
	                        

//	                }

	            }
	        };
		}
		
//		Bitmap[] bitmap = new Bitmap[2];
//	 class BitmapThread extends Thread{
//			@Override
//			public void run() {
//				for(int i=1;i<= tv_recommend_list_image.size();i++){
//					bm_recommend_list_image.add(getUrlImage(url+"/"+tv_recommend_list_image.get(i-1)));
////					bitmap[i-1] = (Bitmap) getUrlImage(url+"/"+tv_recommend_list_image.get(i-1));
//				}
//				ArrayList list = new ArrayList(); //这个list用于在budnle中传递 需要传递的ArrayList<Object>
////				list.add(bm_recommend_list_image);
//				list.add(bm_recommend_list_image);
//				Message message = new Message();
//				Bundle bundle = new Bundle();
//				bundle.putParcelableArrayList("list",list);
//				message.setData(bundle);
////				message.obj = bm_recommend_list_image;
//				handler.sendMessage(message);
//				super.run();
//			}
//		}
		
		
		
//	 Handler handler = new Handler(){
//		 public void handleMessage(Message msg) {
//			 
//			 bm_recommend_list_image = msg.getData().getParcelableArrayList("list");
//			 System.out.println(bm_recommend_list_image);
//			 super.handleMessage(msg);
//		 };
//	 };
	 	 
}
