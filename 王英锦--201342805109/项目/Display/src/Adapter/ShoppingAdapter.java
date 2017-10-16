package Adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.example.main1.R;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShoppingAdapter extends BaseAdapter{
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
	public ShoppingAdapter(List<String> tv_main_1,List<String> tv_main_2,List<Bitmap> bm_recommend_list_image) {
//		this.url = url;
		
		this.tv_main_list_content = tv_main_1;
		this.tv_main_list_title = tv_main_2;
//		this.jsonImageObj = jsonImageObj;
//		this.url = url;
		this.bm_main_list_image = bm_recommend_list_image;
		System.out.println(tv_main_1);
		
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
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder vh=null;
		
		if(convertView==null){
			vh=new ViewHolder();
			convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_shoppingcar, null);
			vh.iv_main=(ImageView) convertView.findViewById(R.id.iv_shopping_list);
			vh.tv_main_2=(TextView) convertView.findViewById(R.id.tv_shoppingcar_list_content);
			vh.tv_main_1=(TextView) convertView.findViewById(R.id.tv_shoppingcar_list_title);
			
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
		return convertView;
	}
	
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
