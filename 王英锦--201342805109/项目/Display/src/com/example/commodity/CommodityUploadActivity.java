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

				//ʹ��intent����ϵͳ�ṩ����Ṧ�ܣ�ʹ��startActivityForResult��Ϊ�˻�ȡ�û�ѡ���ͼƬ
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

				//��дonActivityResult�Ի������Ҫ����Ϣ

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
							httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));//�������������
							HttpClient httpClient = new DefaultHttpClient();

							//���ó�ʱ���
							httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
							httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000    );

							HttpResponse httpResponse = httpClient.execute(httpRequest);//ִ�����󷵻���Ӧ
							System.out.println(httpResponse.getStatusLine().getStatusCode());
							if(httpResponse.getStatusLine().getStatusCode() == 200){//�ж��Ƿ�����ɹ�
								String result = EntityUtils.toString(httpResponse.getEntity());

								//									if(result.equals("1")){
								//										Intent intent = new Intent(CommodityUploadActivity.this,MainActivity.class);
								////										intent.putExtra("username", user_name);
								////										intent.putExtra("password", password);
								//										startActivity(intent);
								//									}else{
								//										Toast.makeText(CommodityUploadActivity.this, "�û����ѱ�ʹ��", Toast.LENGTH_SHORT).show();
								//									}


								//										Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
							}else{
								Toast.makeText(CommodityUploadActivity.this, "û�л�ȡ��Android�������˵���Ӧ��", Toast.LENGTH_LONG).show();
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
	private final int IMAGE_CODE = 0; //�����IMAGE_CODE���Լ����ⶨ���

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (resultCode != RESULT_OK) { //�˴��� RESULT_OK ��ϵͳ�Զ����һ������
			//	Log.e(TAG,"ActivityResult resultCode error");
			return;
		}
		//	Bitmap bm = null;
		//���ĳ������ContentProvider���ṩ���� ����ͨ��ContentResolver�ӿ�
		ContentResolver resolver = getContentResolver();
		//�˴��������жϽ��յ�Activity�ǲ�������Ҫ���Ǹ�
		if (requestCode == IMAGE_CODE) {
			try {
				Uri originalUri = data.getData(); //���ͼƬ��uri
				bm = MediaStore.Images.Media.getBitmap(resolver, originalUri); //�Եõ�bitmapͼƬ
				//���￪ʼ�ĵڶ����֣���ȡͼƬ��·����
				String[] proj = {MediaStore.Images.Media.DATA};
				//������android��ý�����ݿ�ķ�װ�ӿڣ�����Ŀ�Android�ĵ�
				Cursor cursor = managedQuery(originalUri, proj, null, null, null);
				//���Ҹ������ ����ǻ���û�ѡ���ͼƬ������ֵ
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				//�����������ͷ ���������Ҫ����С�ĺ���������Խ��
				cursor.moveToFirst();
				//����������ֵ��ȡͼƬ·��www.2cto.com
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