package Fragment;

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

//import com.example.buy.IndentActivity;
import com.example.detail.DetailActivity;
import com.example.main1.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import Adapter.MainAdapter;
import Adapter.ShoppingAdapter;
import Adapter.ShoppingAdapter;
import AsyncTask.DataTask;
import Interfaces.InitAndRefreshInterface;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShoppingCarFragment extends Fragment implements InitAndRefreshInterface {

	View shoppingcar_root_view;
	PullToRefreshListView lv_shoppingcar_detail;
	CheckBox cb_shopping;
	LinearLayout ll_pay;
	TextView tv_num,shoppingisnull;
	//	ShoppingAdapter ma_shopping_list_title;
	MainAdapter ma_shoppingcar_list_title;

	List<String> tv_shoppingcar_list_title = new ArrayList<String>(); 
	List<String> tv_shoppingcar_list_content = new ArrayList<String>(); 
	List<String> tv_shoppingcar_list_image = new ArrayList<String>();
	List<Bitmap> bm_shoppingcar_list_image = new ArrayList<Bitmap>();
	Button payment;
	String username;

	int timer;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		url = getResources().getString(R.string.url_web);
		timer = 0;
		username = getActivity().getIntent().getStringExtra("username");
		ConnServ();

		shoppingcar_root_view = inflater.inflate(R.layout.fragment_shopping_car, container,false);
		
		ll_pay = (LinearLayout) shoppingcar_root_view.findViewById(R.id.shoppingcar_shoppingSure);
		tv_num = (TextView) shoppingcar_root_view.findViewById(R.id.shoppingcar_num);
		shoppingisnull = (TextView) shoppingcar_root_view.findViewById(R.id.shoppingcar_itisnull);
		cb_shopping =  (CheckBox) shoppingcar_root_view.findViewById(R.id.cb_shoppingcar_list);
		payment = (Button) shoppingcar_root_view.findViewById(R.id.shoppingcar_payment);
		lv_shoppingcar_detail = (PullToRefreshListView) shoppingcar_root_view.findViewById(R.id.shoppingcar_detailInfo);

		if (tv_shoppingcar_list_title.size() != 0) {
			lv_shoppingcar_detail.setVisibility(View.VISIBLE);
			shoppingisnull.setVisibility(View.GONE);
		} else {
			lv_shoppingcar_detail.setVisibility(View.GONE);
			shoppingisnull.setVisibility(View.VISIBLE);
		}
		//		init_List();
		lv_shoppingcar_detail.setMode(Mode.BOTH);//�����������غ�����ˢ��

		/*
		 * getLoadingLayoutProxy()���������ڴ˴�����
		 * ���ڱ𴦵��ú��п��ܻᷢ����һ��������������ʱ������ȷ��ʾ
		 */
		//��������ʱ����ʾ
		lv_shoppingcar_detail.getLoadingLayoutProxy(false, true).setPullLabel(getResources().getString(R.string.shanglajiazai));
		lv_shoppingcar_detail.getLoadingLayoutProxy(false, true).setRefreshingLabel(getResources().getString(R.string.zhengzaijiazai));
		lv_shoppingcar_detail.getLoadingLayoutProxy(false, true).setReleaseLabel(getResources().getString(R.string.songkaijiazai));
		lv_shoppingcar_detail.getLoadingLayoutProxy(true, false).setPullLabel(getResources().getString(R.string.xialashuaxin));
		lv_shoppingcar_detail.getLoadingLayoutProxy(true, false).setRefreshingLabel(getResources().getString(R.string.zhengzaishuaxin));
		lv_shoppingcar_detail.getLoadingLayoutProxy(true, false).setReleaseLabel(getResources().getString(R.string.songkaishuaxin));


		lv_shoppingcar_detail.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {

				timer += 10;

				new DataTask(getActivity(),lv_shoppingcar_detail,timer).execute();//ע�⣺����execute�Ͳ���ֹͣ

				ConnServ();
			}
		});

		//		init_Data();

		lv_shoppingcar_detail.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


				Intent intent = new Intent(getActivity(),DetailActivity.class);

				Bitmap bitmap = bm_shoppingcar_list_image.get(position-1);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
				byte[] bs = byteArrayOutputStream.toByteArray();
				//				String string = Base64.encodeToString(bs, Base64.DEFAULT);

				//				intent.putExtra("image", value)
				intent.putExtra("title", tv_shoppingcar_list_title.get(position-1));
				intent.putExtra("content", tv_shoppingcar_list_content.get(position-1));
				intent.putExtra("image", bs);//string);//bm_main_list_image.get(position-1).toString());
				startActivity(intent);
			}
		});

		//�տ�ʼʹ��radioButton��֮���ΪcheckBox,���Ե���ڶ���ȡ��ѡ��
		//		rb_shopping.setOnTouchListener(new OnTouchListener() {
		//			
		//			@Override
		//			public boolean onTouch(View v, MotionEvent event) {
		//				if(rb_shopping.isChecked()==true){
		//					rb_shopping.setBackgroundResource(R.drawable.rb_unchecked);
		//				}else if(rb_shopping.isChecked()==false){
		//					rb_shopping.setBackgroundResource(R.drawable.rb_checked);
		//				}
		//			
		//				return false;
		//			}
		//		});


		//���ﳵ���㣬ת������������activity��
		payment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//				Intent i = new Intent(getActivity(),IndentActivity.class);
				//				startActivity(i);
				Toast.makeText(getActivity(), "������", Toast.LENGTH_SHORT).show();

				AlertDialog isExit=new AlertDialog.Builder(getActivity()).create();
				isExit.setTitle("ϵͳ��ʾ");
				isExit.setMessage("������ϵ��ʽ��"+phonenumber);
				isExit.setButton("��绰", listener);
				//	            isExit.setButton2("��ѧһ��",listener);
				isExit.show();
			}
		});

		return shoppingcar_root_view;
	}
	String phonenumber = "18363994438";
	public DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener(){
		public void onClick(DialogInterface dialog,int which){
			switch(which){
			case AlertDialog.BUTTON_POSITIVE:
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phonenumber));
				ShoppingCarFragment.this.startActivity(intent);
				//                case AlertDialog.BUTTON_NEGATIVE:
				//                    break;
			default:
				break;
			}
		}
	};
	//	//�����ʼ�����ﳵlist
	//	private void init_list_shopping(){
	//		tv_shopping_list_title.add("aaa");
	//		tv_shopping_list_title.add("bbb");
	//		tv_shopping_list_title.add("aaa");
	//		tv_shopping_list_title.add("bbb");
	//		tv_shopping_list_title.add("aaa");
	//		tv_shopping_list_title.add("bbb");
	//		tv_shopping_list_title.add("aaa");
	//		tv_shopping_list_title.add("bbb");
	//		tv_shopping_list_title.add("aaa");
	//		tv_shopping_list_title.add("bbb");
	//		tv_shopping_list_title.add("aaa");
	//		tv_shopping_list_title.add("bbb");
	//		tv_shopping_list_title.add("aaa");
	//		tv_shopping_list_title.add("bbb");
	//		tv_shopping_list_title.add("aaa");
	//		tv_shopping_list_title.add("bbb");
	//		tv_shopping_list_title.add("aaa");
	//		tv_shopping_list_title.add("bbb");
	//	}

	@Override
	public void init_Data() {
		//		init_list_recommend();
		//		ma_recommend_list_title = new RecommendAdapter(getResources().getString(R.string.url_web)+"/show.action");//tv_recommend_list_content,tv_recommend_list_title);
		ma_shoppingcar_list_title  = new MainAdapter(tv_shoppingcar_list_title, tv_shoppingcar_list_content,bm_shoppingcar_list_image);

		lv_shoppingcar_detail.setAdapter(ma_shoppingcar_list_title);

	}

	String url;

	//�������ӷ�������������onCreate�е���
	public void ConnServ(){
		//���ӷ���������ȡ�б�����
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
					send.add(new BasicNameValuePair("username", username));
					send.add(new BasicNameValuePair("frag", "shoppingcar"));

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

							if ("".equals(out)) {
								Toast.makeText(getActivity(), "�Ѿ�ȫ���������", Toast.LENGTH_SHORT).show();
							}
							onStop();
							//							Message message = new Message();
							JsonUtils(out);
							for(int i=1;i<= tv_shoppingcar_list_image.size();i++){
								bm_shoppingcar_list_image.add(getUrlImage(url+"/"+tv_shoppingcar_list_image.get(i-1)));
								//								bitmap[i-1] = (Bitmap) getUrlImage(url+"/"+tv_recommend_list_image.get(i-1));
							}
							//							message.obj = out;
							//							handler.sendMessage(message);
							ArrayList list = new ArrayList(); //���list������budnle�д��� ��Ҫ���ݵ�ArrayList<Object>
							//							list.add(bm_shoppingcar_list_image);
							list.add(bm_shoppingcar_list_image);
							list.add(tv_shoppingcar_list_title);
							list.add(tv_shoppingcar_list_content);
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

			bm_shoppingcar_list_image =   (List<Bitmap>) image.get(0);
			tv_shoppingcar_list_title = (List<String>) image.get(1);
			tv_shoppingcar_list_content = (List<String>) image.get(2);
			init_Data();
			super.handleMessage(msg);
		};
	};

	private Bitmap getUrlImage(String url) {
		Bitmap bitmap = null;
		try {
			URL picture = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) picture.openConnection();
			connection.setConnectTimeout(6000);//���ó�ʱ
			connection.setDoInput(true);
			connection.setUseCaches(false);//������
			connection.connect();
			InputStream iStream = connection.getInputStream();//��ȡͼƬ��������
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
				tv_shoppingcar_list_title.add(jsonObject.optString("commodityTitle"+(i+timer)));
				tv_shoppingcar_list_content.add(jsonObject.optString("commodityUserName"+(i+timer)));
				tv_shoppingcar_list_image.add(jsonObject.optString("commodityImage"+(i+timer)));
				//					jsonImageObj.put("commodityImage"+i,jsonObject.optString("commodityImage"+i));
				//
			}

			//				init_Data();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


}
