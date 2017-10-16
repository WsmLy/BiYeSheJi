package com.example.personal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.example.main1.R;

import android.app.Activity;
import android.content.ContentResolver;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalEdit extends Activity{

	TextView tvw_edit;
	EditText et_username,et_phone,et_qq,et_mail;
	Button commit;
	ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_edit);

		commit = (Button) findViewById(R.id.commit);
		image = (ImageView) findViewById(R.id.image);
		image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("image:/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(intent, 1);
			}
		});

		url = getResources().getString(R.string.url_web);

		String username = getIntent().getStringExtra("username");
		et_username = (EditText) findViewById(R.id.et_username);
		et_username.setHint(username);

		String phone = getIntent().getStringExtra("phone");
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_phone.setHint(phone);

		String qq = getIntent().getStringExtra("password");
		et_qq = (EditText) findViewById(R.id.et_password);
		et_qq.setHint(qq);

//		String mail = getIntent().getStringExtra("mail");
//		et_mail = (EditText) findViewById(R.id.et_mail);
//		et_mail.setHint(mail);

		commit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ConnServ();
				finish();
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RESULT_OK) {
			Uri uri = data.getData();
			ContentResolver cResolver = this.getContentResolver();
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(cResolver.openInputStream(uri));
				image.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	String url;
	public void ConnServ(){
		//连接服务器并获取列表数据
		new Thread(new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				Looper.prepare();
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpRequest;

				try {
					httpRequest = new HttpPost(url+"/personEdit.action");
					List<NameValuePair> send = new ArrayList<NameValuePair>();
					send.add(new BasicNameValuePair("name", et_username.getText().toString()));
					send.add(new BasicNameValuePair("phone", et_phone.getText().toString()));
					send.add(new BasicNameValuePair("qq", et_qq.getText().toString()));
					send.add(new BasicNameValuePair("mail", et_mail.getText().toString()));

					httpRequest.setEntity(new UrlEncodedFormEntity(send, HTTP.UTF_8));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//					httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
				//					httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);


				Looper.loop();
			}
		}).start();
	}
}

