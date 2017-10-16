package com.example.main1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.json.JSONException;
import org.json.JSONObject;

import com.example.main1.R;

import Fragment.PersonalCenterLoginedFragment;
import Fragment.ShoppingCarFragment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class LoginActivity extends Activity  {

	//title控件
	private TextView titleTv;
	private Button titleBtn,loginBtn;
	private ImageView imageView;

	//页面控件
	private Button registerBtn;
	private EditText accountEditText,passwordEditText;
	
	String username;
	String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.login);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

		FindView();
		initTitle();
		addBtnClick();
	}

	@SuppressLint("CutPasteId")
	private void FindView(){
		registerBtn = (Button)findViewById(R.id.register);
		titleTv = (TextView)findViewById(R.id.login_text);
		titleBtn = (Button)findViewById(R.id.register);
		imageView = (ImageView) findViewById(R.id.back_image);
		loginBtn = (Button) findViewById(R.id.login_btn);
		accountEditText = (EditText) findViewById(R.id.account);	
		passwordEditText = (EditText) findViewById(R.id.password);
	}
	
	private void addBtnClick(){
		registerBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				RegisterActivity.newInstance(LoginActivity.this);
			}

		});
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				username = accountEditText.getText().toString();
				password = passwordEditText.getText().toString();
				if(username.equals("")){
					Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
				}else if(password.equals("")){
					Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
				}else{
					new Thread(new Runnable(){
						@Override
						public void run() {
							Looper.prepare();
							HttpPost httpRequest = new HttpPost(getResources().getString(R.string.url_web)+"/login.action");
							List<NameValuePair> params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("username", username));
							params.add(new BasicNameValuePair("password", password));
							try {
								httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));//设置请求参数项
								HttpClient httpClient = new DefaultHttpClient();

								//设置超时检测
				                httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
				                httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
				            	
				                HttpResponse httpResponse = httpClient.execute(httpRequest);//执行请求返回响应
				                System.out.println(httpResponse.getStatusLine().getStatusCode());
								if(httpResponse.getStatusLine().getStatusCode() == 200){//判断是否请求成功
									String result = EntityUtils.toString(httpResponse.getEntity());
									
									if("1".equals(result)){
										Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
										Intent intent = new Intent(LoginActivity.this,MainActivity.class);
										intent.putExtra("username", username);
										intent.putExtra("password", password);
										intent.putExtra("fragment_id",1);
										intent.putExtra("activityName", "loginActivity");// R.layout.fragment_personal_center_logined);
										finish(); 
										startActivity(intent);
									}else{
										Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
									}
									
									
									Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
								}else{
									Toast.makeText(LoginActivity.this, "没有获取到Android服务器端的响应！", Toast.LENGTH_LONG).show();
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
		
//		loginBtn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				   String url = getResources().getString(R.string.url_web)+"/AndroidServerApp/login.action";   
//				   getPDAServerData(url);   
//				 
//			}
//		});
	}

	private void initTitle(){
		titleTv.setText(getResources().getString(R.string.login));
		titleBtn.setText(getResources().getString(R.string.register));
		passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
	}
	
//	  private void getPDAServerData(String url) {   
//          HttpClient client = new DefaultHttpClient();   
//          //提拱默认的HttpClient实现   
//          HttpPost request;   
//          try {   
//              request = new HttpPost(new URI(url));   
//              HttpResponse response = client.execute(request);   
//              // 判断请求是否成功   
//              if (response.getStatusLine().getStatusCode() == 200) { //200表示请求成功   
//                  HttpEntity entity = response.getEntity();   
//                  if (entity != null) {   
//                      String out = EntityUtils.toString(entity);   
//                      JSONObject jsonObject;   
//                      String username = "";   
//                      String password = "";   
//                      String stateStr="";   
//                         
//                      UserBean userBean=new UserBean();   
//                      try {   
//                 
//                          //{"userbean":{"username":"100196","password":"1234453","State":1}}   
//                          //JSONObject jsonObject = new JSONObject(builder.toString()).getJSONObject("userbean");    
//                         
//                          jsonObject = new JSONObject(out).getJSONObject("userbean");   
//                             
//                             
//                          userBean.setUsername(jsonObject.getString("username"));   
//                          userBean.setPassword( jsonObject.getString("password"));   
//                          userBean.setState(Integer.parseInt(jsonObject.getString("state")));   
//                             
//                             
//                             
//                      } catch (JSONException e) {   
//                          e.printStackTrace();   
//                      }   
//                      new AlertDialog.Builder(this).setMessage(   
//                              userBean.getUsername() + ":" + userBean.getState()).create().show();   
//                  }   
//              }   
//          } catch (URISyntaxException e) {   
//              e.printStackTrace();   
//              new AlertDialog.Builder(this).setMessage(e.getMessage()).create()   
//                      .show();   
//          } catch (ClientProtocolException e) {   
//              e.printStackTrace();   
//              new AlertDialog.Builder(this).setMessage(e.getMessage()).create()   
//                      .show();   
//          } catch (IOException e) {   
//              e.printStackTrace();   
//              new AlertDialog.Builder(this).setMessage(e.getMessage()).create()   
//                      .show();   
//          }   
//      }  
}
