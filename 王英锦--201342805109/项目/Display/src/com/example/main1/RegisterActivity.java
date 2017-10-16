package com.example.main1;

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

import com.example.main1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private TextView titleTv,resendTv;
	private Button titleBtn,nextBtn,sendBtn;
	private ImageView return_Iv;
	private EditText et_user_number,et_password,et_password_sure,et_phone_number,et_verification_code,et_user_name;
	private String user_number,password,password_sure,phone_number,user_name;
	private String verification_code;


	public static void newInstance(Activity activity){
		Intent intent = new Intent(activity , RegisterActivity.class);
		activity.startActivity(intent);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.register);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

		findView();
		initTitle();
		nextOnclick();
		titleBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//				initTitle();				

				if (titleBtn.getText().toString()==getResources().getString(R.string.email_register_text)) {

					titleTv.setText(getResources().getString(R.string.email_register_text));
					titleBtn.setText(getResources().getString(R.string.phone_register_text));
					et_phone_number.setHint(getResources().getString(R.string.emailHint));
				}
				else if (titleBtn.getText().toString()==getResources().getString(R.string.phone_register_text)) {

					titleTv.setText(getResources().getString(R.string.phone_register_text));
					titleBtn.setText(getResources().getString(R.string.email_register_text));
					et_phone_number.setHint(getResources().getString(R.string.phoneHint));
				}
			}
		});
//		nextBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
////				sendBtn.setVisibility(View.GONE);
////				resendTv.setVisibility(View.VISIBLE);
//				//Toast.makeText(getApplicationContext(), R.string.sendtophone, Toast.LENGTH_SHORT).show();
//
//			}
//		});
	}

	private void initTitle(){
		titleTv.setText(getResources().getString(R.string.phone_register_text));
		titleBtn.setText(getResources().getString(R.string.email_register_text));
		et_phone_number.setHint(getResources().getString(R.string.phoneHint));



		return_Iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//				Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
				//				startActivity(intent);
				finish();
			}
		});
	}

	private void findView(){
		titleTv = (TextView)findViewById(R.id.login_text);
		titleBtn = (Button)findViewById(R.id.register);
		return_Iv = (ImageView) findViewById(R.id.back_image);

		et_password = (EditText) findViewById(R.id.password);
		et_password_sure = (EditText) findViewById(R.id.password_sure);
//		et_verification_code = (EditText) findViewById(R.id.verification_code);
		et_phone_number = (EditText) findViewById(R.id.phone);
		et_user_name = (EditText) findViewById(R.id.user_name);

//		sendBtn = (Button) findViewById(R.id.clicktocode);
		nextBtn = (Button) findViewById(R.id.next_btn);
//		resendTv = (TextView)findViewById(R.id.reclicktocode);
	}

	private void nextOnclick(){
		nextBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String verification_code,getcode;
				getcode = "";

//				{
//					startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
//				}

//				verification_code = et_verification_code.getText().toString();
				password = et_password.getText().toString();
				password_sure = et_password_sure.getText().toString();
//				verification_code = et_verification_code.getText().toString();
				user_name = et_user_name.getText().toString();
				
				if("".equals(user_name)) {
					Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
				}
				if(password.equals("")){
					Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
				}else if(!password.equals(password_sure)){
					Toast.makeText(getApplicationContext(), "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
//				}else if (verification_code!=getcode) {
//					Toast.makeText(getApplicationContext(), "验证码不正确", Toast.LENGTH_SHORT).show();
				}else{
					new Thread(new Runnable(){
						@Override
						public void run() {
							Looper.prepare();
							HttpPost httpRequest = new HttpPost(getResources().getString(R.string.url_web)+"/register.action");
							List<NameValuePair> params = new ArrayList<NameValuePair>();
//							params.add(new BasicNameValuePair("usernumber", user_number));
							params.add(new BasicNameValuePair("username", user_name));
							params.add(new BasicNameValuePair("password", password));
							params.add(new BasicNameValuePair("passwordSure", password_sure));
							params.add(new BasicNameValuePair("phone", phone_number));
//							params.add(new BasicNameValuePair("userImg", userImg));
							
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
										Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
										Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//										intent.putExtra("username", user_name);
//										intent.putExtra("password", password);
										startActivity(intent);
									}else{
										Toast.makeText(RegisterActivity.this, "用户号已被使用", Toast.LENGTH_SHORT).show();
									}


									//										Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
								}else{
									Toast.makeText(RegisterActivity.this, "没有获取到Android服务器端的响应！", Toast.LENGTH_LONG).show();
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
				}
			}
		});
	}

//	private void SetVerificationCode(){
//		int verificationCode = (int) (Math.random()*4000);
////		System.out.println(verificationCode);
//		verification_code = et_verification_code.getText().toString();
//		
//		
//	}
}
