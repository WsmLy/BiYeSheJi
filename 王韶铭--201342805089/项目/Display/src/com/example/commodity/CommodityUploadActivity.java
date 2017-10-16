package com.example.commodity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
import com.example.main1.MainActivity;
import com.example.main1.R;
import com.example.main1.RegisterActivity;
import com.example.main1.R.layout;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.Toast;

public class CommodityUploadActivity extends Activity {

	EditText commodityTitle,commodityDetail,price;
	ImageButton imageSwitcher;
	Button fabu;

	String commodityTitleText,commodityDetailText,priceText,image;
	Bitmap bm;
	String username;// = getIntent().getStringExtra("username");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commodity_upload);
		username = getIntent().getStringExtra("username");
		findView();

		imageSwitcher.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				//使用intent调用系统提供的相册功能，使用startActivityForResult是为了获取用户选择的图片
				Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
				getAlbum.setType(IMAGE_TYPE);
				startActivityForResult(getAlbum, IMAGE_CODE);

				imageSwitcher.setImageBitmap(bm);
				
			}
		});

		fabu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String verification_code,getcode;
				getcode = "";

				commodityTitleText = commodityTitle.getText().toString();
				commodityDetailText = commodityDetail.getText().toString();
				//				verification_code = et_verification_code.getText().toString();
				priceText = price.getText().toString();

				//重写onActivityResult以获得你需要的信息

				byte[] by = BitMapToString.bitmapToString(bm);

				try {
					image = new String( by ,"UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new Thread(new Runnable(){
					@SuppressWarnings("deprecation")
					@Override
					public void run() {
						Looper.prepare();
						HttpPost httpRequest = new HttpPost(getResources().getString(R.string.url_web)+"/upload.action");
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						//							params.add(new BasicNameValuePair("usernumber", user_number));
						params.add(new BasicNameValuePair("commodityTitle", commodityTitleText));
						params.add(new BasicNameValuePair("commodityDetail", commodityDetailText));
						params.add(new BasicNameValuePair("price", priceText));
						params.add(new BasicNameValuePair("image", image));
						params.add(new BasicNameValuePair("userName", username));
						params.add(new BasicNameValuePair("sometodo", "upload"));

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

								//									if(result.equals("1")){
								//										Intent intent = new Intent(CommodityUploadActivity.this,MainActivity.class);
								////										intent.putExtra("username", user_name);
								////										intent.putExtra("password", password);
								//										startActivity(intent);
								//									}else{
								//										Toast.makeText(CommodityUploadActivity.this, "用户号已被使用", Toast.LENGTH_SHORT).show();
								//									}


								//										Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
							}else{
								Toast.makeText(CommodityUploadActivity.this, "没有获取到Android服务器端的响应！", Toast.LENGTH_LONG).show();
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
				finish();
			}

		});
	}

	private final String IMAGE_TYPE = "image/*";
	private final int IMAGE_CODE = 0; //这里的IMAGE_CODE是自己任意定义的

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (resultCode != RESULT_OK) { //此处的 RESULT_OK 是系统自定义得一个常量
			//	Log.e(TAG,"ActivityResult resultCode error");
			return;
		}
		//	Bitmap bm = null;
		//外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
		ContentResolver resolver = getContentResolver();
		//此处的用于判断接收的Activity是不是你想要的那个
		if (requestCode == IMAGE_CODE) {
			try {
				Uri originalUri = data.getData(); //获得图片的uri
				bm = MediaStore.Images.Media.getBitmap(resolver, originalUri); //显得到bitmap图片
				//这里开始的第二部分，获取图片的路径：
				String[] proj = {MediaStore.Images.Media.DATA};
				//好像是android多媒体数据库的封装接口，具体的看Android文档
				Cursor cursor = managedQuery(originalUri, proj, null, null, null);
				//按我个人理解 这个是获得用户选择的图片的索引值
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				//将光标移至开头 ，这个很重要，不小心很容易引起越界
				cursor.moveToFirst();
				//最后根据索引值获取图片路径www.2cto.com
				String path = cursor.getString(column_index);
			}catch (IOException e) {
				//	Log.e(TAG,e.toString());
			}
		}
	}

	private void findView() {
		commodityTitle = (EditText) findViewById(R.id.et_commodity_name);
		commodityDetail = (EditText) findViewById(R.id.et_commodity_title);
		price = (EditText) findViewById(R.id.et_price);
		imageSwitcher = (ImageButton) findViewById(R.id.is_image);

		fabu = (Button) findViewById(R.id.upload);
	}
}