package com.example.detail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.example.main1.BitMapToString;
import com.example.main1.LoginActivity;
//import com.example.buy.IndentActivity;
import com.example.main1.R;
import com.example.main1.RegisterActivity;

import Fragment.MainFragment;
import Fragment.ShoppingCarFragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity{

	Button btn_buy;
	//	Button btn_collect;
	Button btn_car;
	String url;
	ImageView imageView;
	Intent intent;

	String username;
	String commodityId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		username = getIntent().getStringExtra("username");
		commodityId = getIntent().getStringExtra("commodityId");

		intent = getIntent();
		url = getResources().getString(R.string.url_web);
		btn_buy = (Button) findViewById(R.id.bt_buy);
		btn_car = (Button) findViewById(R.id.bt_car);
		//		btn_collect = (Button) findViewById(R.id.bt_collect);
		TextView textView = (TextView) findViewById(R.id.goods);
		imageView = (ImageView) findViewById(R.id.image1);

		//		String string = intent.getByteArrayExtra("image"name);//.getStringExtra("image");
		byte[] by = intent.getByteArrayExtra("image");//string.getBytes();
//		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(by);
		Bitmap bitmap = BitMapToString.StringToBitmap(by);//BitmapFactory.decodeStream(byteArrayInputStream);

		textView.setText(intent.getStringExtra("title")+"\n"+intent.getStringExtra("content"));
		imageView.setImageBitmap(bitmap);

		btn_buy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("aaa");
				//				Intent i = new Intent(DetailActivity.this,IndentActivity.class);
				//				startActivity(i);
				Toast.makeText(DetailActivity.this, "卖家名", Toast.LENGTH_SHORT).show();

				AlertDialog isExit=new AlertDialog.Builder(DetailActivity.this).create();
				isExit.setTitle("系统提示");
				isExit.setMessage("卖家联系方式："+phonenumber);
				isExit.setButton("打电话", listener);
				//	            isExit.setButton2("再学一会",listener);
				isExit.show();
			}
		});
		//		btn_collect.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				Toast.makeText(getApplicationContext(), R.string.addcollect, Toast.LENGTH_SHORT).show();
		//			}
		//		});

		btn_car.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				new Thread(new Runnable(){
					@Override
					public void run() {
						Looper.prepare();
						HttpPost httpRequest = new HttpPost(getResources().getString(R.string.url_web)+"/register.action");
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						//						params.add(new BasicNameValuePair("usernumber", user_number));
						params.add(new BasicNameValuePair("commodityId", commodityId));
						params.add(new BasicNameValuePair("username", username));

						try {
							httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));//设置请求参数项
							HttpClient httpClient = new DefaultHttpClient();

							//设置超时检测
							httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
							httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000    );

							HttpResponse httpResponse = httpClient.execute(httpRequest);//执行请求返回响应
							System.out.println(httpResponse.getStatusLine().getStatusCode());
							if(httpResponse.getStatusLine().getStatusCode() == 200){//判断是否请求成功
								String result = EntityUtils.toString(httpResponse.getEntity());

								if(result.equals("1")){
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.addcar), Toast.LENGTH_SHORT).show();
								}else{
									Toast.makeText(DetailActivity.this, "未收藏至购物车", Toast.LENGTH_SHORT).show();
								}


								//										Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
							}else{
								Toast.makeText(DetailActivity.this, "没有获取到Android服务器端的响应！", Toast.LENGTH_LONG).show();
							}
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						Looper.loop();
					}
				}).start();
				Toast.makeText(DetailActivity.this, getResources().getString(R.string.addcar), Toast.LENGTH_SHORT).show();
			}
		});
	}
	String phonenumber = "18363994438";
	public DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener(){
		public void onClick(DialogInterface dialog,int which){
			switch(which){
			case AlertDialog.BUTTON_POSITIVE:
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phonenumber));
				DetailActivity.this.startActivity(intent);
				//                case AlertDialog.BUTTON_NEGATIVE:
				//                    break;
			default:
				break;
			}
		}
	};

}
