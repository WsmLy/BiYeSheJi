package com.example.main1;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.commodity.CommodityUploadActivity;
import com.example.commodity.SearchActivity;
import com.example.main1.R;

import Fragment.MainFragment;
import Fragment.PersonalCenterLoginedFragment;
import Fragment.PersonalCenterUnLoginedFragment;
import Fragment.RecommendFragment;
import Fragment.ShoppingCarFragment;
import Servers.MainDatabase;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;

@SuppressWarnings("deprecation")
@SuppressLint({ "HandlerLeak", "RtlHardcoded" })
public class MainActivity extends Activity {

	// 声明PopupWindow对象的引用
	private PopupWindow popupWindow;
	private PopupWindow popupWindow_contentus;
	
	//介质
	int login_sure = 2;
	String acname = null;
	String username = null;

	//字体大小
	float size13 = 0x13;
	float size10 = 0x10;

	//顶部
	ImageView iv_log,iv_menu;
	LinearLayout head,bottom;
	//左菜单
	Button contentus;
	Button gotoweb;
	Button open;
	//内容
	TextView tv_num;
	//底部
	Button mainPage;
	Button recommend;
	Button shoppingCar;
	Button personalCenter;
	//实体按键
	boolean isExit;

	//	String tv_main_1_text = "";
	//	String tv_main_2_text = "";

	//数据库
	MainDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//创建一个数据库对象并把数据库名字传给数据库系统
//		db = new MainDatabase(getApplicationContext());

		FindView();
		
		login_sure = getIntent().getIntExtra("fragment_id", 0);
		username = getIntent().getStringExtra("username");
		acname = getIntent().getStringExtra("activityName");
		
		
		/*
		 * 登录成功后更改登录为个人中心
		 * 但是问题是如何获取left——fragment的登录button
		 * 可以尝试更改string中的内容
//		 */
//		if(login_sure == 1){
//			open.setText("个人中心");
//		}

		OnClick();
				
		if("loginActivity".equals(acname)){
			/*
			 * 没必要加这三行
			 */
//			login_sure = getIntent().getIntExtra("fragment_id", 0);
//			username = getIntent().getStringExtra("username");
//			acname = getIntent().getStringExtra("activityName");
			head.setVisibility(View.GONE);
			
			getFragmentManager().beginTransaction()
			.replace(R.id.container, new PersonalCenterLoginedFragment())
			.commit();
//		}else if(login_sure==0){
//			head.setVisibility(View.GONE);
//			getFragmentManager().beginTransaction()
//			.replace(R.id.container, new PersonalCenterUnLoginedFragment())
//			.commit();
		}
		else{
//		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new MainFragment())
			.commit();
//		}
		}
		
	}

	//findviewbyid的方法
	private void FindView(){
		//顶部
		iv_log = (ImageView) findViewById(R.id.log);
		iv_menu = (ImageView) findViewById(R.id.menu);
		head = (LinearLayout) findViewById(R.id.main_head);
		//左菜单
		open = (Button) findViewById(R.id.open);
		contentus = (Button) findViewById(R.id.contentus);
		gotoweb = (Button) findViewById(R.id.gotoweb);
		//底部
		mainPage = (Button) findViewById(R.id.Main_page);
		mainPage.setTextSize(size13);
		recommend = (Button) findViewById(R.id.Recommend);
		recommend.setTextSize(size10);
		shoppingCar = (Button) findViewById(R.id.Shopping_Car);
		shoppingCar.setTextSize(size10);
		personalCenter = (Button) findViewById(R.id.Personal_Center);
		personalCenter.setTextSize(size10);
		bottom = (LinearLayout) findViewById(R.id.bottom);
	}
	
	/*底部四个按钮的点击事件*/
	//	//首页的点击事件，更改字体大小，转到首页的View中
	public void MainPage(View view){
		mainPage.setTextSize(size13);
		recommend.setTextSize(size10);
		shoppingCar.setTextSize(size10);
		personalCenter.setTextSize(size10);

		head.setVisibility(View.VISIBLE);

		getFragmentManager().beginTransaction()
		.replace(R.id.container, new MainFragment())
		.commit();
	}

	//	//推荐的点击事件，更改字体大小，转到推荐的View中
	public void Recommend(View view){
		mainPage.setTextSize(size10);
		recommend.setTextSize(size13);
		shoppingCar.setTextSize(size10);
		personalCenter.setTextSize(size10);

		head.setVisibility(View.VISIBLE);
		getFragmentManager().beginTransaction()
		.replace(R.id.container, new RecommendFragment())
		.commit();

	}

	//	//购物车的点击事件，更改字体大小，转到购物车的View中
	public void ShoppingCar(View view){
		mainPage.setTextSize(size10);
		recommend.setTextSize(size10);
		shoppingCar.setTextSize(size13);
		personalCenter.setTextSize(size10);

		head.setVisibility(View.VISIBLE);

		getFragmentManager().beginTransaction()
		.replace(R.id.container, new ShoppingCarFragment())
		.commit();
	}

	//	//个人中心的点击事件，更改字体大小，转到个人中心的View中
	public void PersonalCenter(View view){
		mainPage.setTextSize(size10);
		recommend.setTextSize(size10);
		shoppingCar.setTextSize(size10);
		personalCenter.setTextSize(size13);

		head.setVisibility(View.GONE);
		


//		Toast.makeText(getApplication(), acname, Toast.LENGTH_SHORT);
		


		if(login_sure==1){
			getFragmentManager().beginTransaction()
			.replace(R.id.container, new PersonalCenterLoginedFragment())
			.commit();
		}else{
			getFragmentManager().beginTransaction()
			.replace(R.id.container, new PersonalCenterUnLoginedFragment())
			.commit();
		}
		
		
		
	}

	/*左菜单的点击事件*/
//	
//	public boolean onNavigationItemSelected(MenuItem item){
//		return false;
//		
//	}
	//登录点击事件
	public void LoginUser(View view){
		if(login_sure == 1){
			head.setVisibility(view.GONE);
			getFragmentManager().beginTransaction()
			.replace(R.id.container, new PersonalCenterLoginedFragment())
			.commit();
		}else{
			startActivity(new Intent(MainActivity.this,LoginActivity.class));
		}
		popupWindow.dismiss();
	}
	
	//搜索点击事件
	public void Search(View view){
		startActivity(new Intent(MainActivity.this,SearchActivity.class));
		popupWindow.dismiss();
	}
	//联系我们点击事件
	public void Contentus(View view) {
		ClickComunicate(findViewById(R.id.container));
		popupWindow.dismiss();;
	}   
	private void ClickComunicate(View view){
        if (null != popupWindow_contentus) {
            popupWindow_contentus.dismiss();
            return;
        } else {
            initPopupContentWindow();
        }
        popupWindow_contentus.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
	@SuppressLint("InflateParams")
	private void initPopupContentWindow() {
	    // 获取自定义布局文件activity_popupwindow_left.xml的视图
        View popupWindow_view = getLayoutInflater().inflate(R.layout.contentus, null, false);
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupWindow_contentus = new PopupWindow(popupWindow_view, 400, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // 设置动画效果
        // popupWindow.setAnimationStyle(R.style.AnimationFade);
        // 点击其他地方消失
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
			@Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow_contentus != null && popupWindow_contentus.isShowing()) {
                    popupWindow_contentus.dismiss();
                    popupWindow_contentus = null;
                }
                return false;
            }
        });		
	}



	//打开网页版点击事件,error
	public void GotoWeb(View view){
//		((ViewGroup) findViewById(R.id.container)).removeAllViews();
//		getFragmentManager().beginTransaction()
//		.replace(R.id.container, new WebAppViewFragment())
//		.commit();	
		startActivity(new Intent(MainActivity.this,GotoWebActivity.class));
		popupWindow.dismiss();
	}
	
	//发布点击事件
	public void Fabu(View view){
		if(login_sure == 1) {
			Intent intent = new Intent(MainActivity.this,CommodityUploadActivity.class);
			intent.putExtra("username", username);
			startActivity(intent);
		} else {
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle(getResources().getString(R.string.ostishi));
			alertDialog.setMessage(getResources().getString(R.string.unLogined));
			alertDialog.setButton(getResources().getString(R.string.login),listener);
			alertDialog.setButton2(getResources().getString(R.string.cancel), listener);
			alertDialog.show();
		}
		popupWindow.dismiss();
	}
public DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch(which){
			case AlertDialog.BUTTON_POSITIVE:
				startActivity(new Intent(MainActivity.this, LoginActivity.class));
				break;
			case AlertDialog.BUTTON_NEGATIVE:
				break;
			}
				
		}
	};
	
	

	/*点击事件*/
	public void OnClick() {
		/*顶部两个按钮的点击事件*/
		//登录按钮的点击事件，转到RegisterActivity
		findViewById(R.id.log).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(login_sure == 1){
					head.setVisibility(View.GONE);
					getFragmentManager().beginTransaction()
					.replace(R.id.container, new PersonalCenterLoginedFragment())
					.commit();
				}else{
					startActivity(new Intent(MainActivity.this,LoginActivity.class));
				}
			}
		});
		//菜单按钮的点击事件
		findViewById(R.id.menu).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {	
				
				getPopupWindow();
				// 这里是位置显示方式,在屏幕的左侧
				popupWindow.showAtLocation(v, Gravity.LEFT, 0, 0);
			}
		});


	}
	/**
	 * 创建PopupWindow
	 */
	@SuppressLint({ "RtlHardcoded", "InflateParams", "ClickableViewAccessibility" })
	protected void initPopuptWindow() {
		// TODO Auto-generated method stub
		// 获取自定义布局文件activity_popupwindow_left.xml的视图
		View popupWindow_view = getLayoutInflater().inflate(R.layout.fragment_left, null,
				false);
		/*
		 * 准备更改登录按钮的文字
		 */
//		if (login_sure!=0) {
//			open = (Button) findViewById(R.id.open);
//			System.out.println(getResources().getString(R.string.personal_center));
//			open.setText(getResources().getString(R.string.personal_center));
//		}
		// 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
		popupWindow = new PopupWindow(popupWindow_view, 400, LayoutParams.MATCH_PARENT, true);
		// 设置动画效果
		popupWindow.setAnimationStyle(R.style.AnimationFade);
		// 点击其他地方消失
		popupWindow_view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}
				return false;
			}
		});
	}
	/***
	 * 获取PopupWindow实例
	 */
	private void getPopupWindow() {
		if (null != popupWindow) {
			popupWindow.dismiss();
			return;
		} else {		
			initPopuptWindow();
		}
	}

	/*实体按键点击事件*/
	//返回键点击事件//两次点击退出程序
	public boolean onKeyDown(int KeyCode,KeyEvent event){
		if(KeyCode==KeyEvent.KEYCODE_BACK){
			exit();
			return false;
		}
		else{
			return super.onKeyDown(KeyCode, event);
		}
	}

	//返回键点击后执行的方法
	public void exit(){  
		if (!isExit) {  
			isExit = true;  
			Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();  
			mHandler.sendEmptyMessageDelayed(0, 2000);  
		} else {  
			Intent intent = new Intent(Intent.ACTION_MAIN);  
			intent.addCategory(Intent.CATEGORY_HOME);  
			startActivity(intent);  
			System.exit(0);  
		}  
	}  

	Handler mHandler = new Handler() {  

		@Override  
		public void handleMessage(Message msg) {  
			super.handleMessage(msg);  
			isExit = false;  
		}  

	};  
	
	//连接服务器
	@SuppressWarnings("unused")
	private void ConnectServers(){
		new Thread(
			new Runnable() {
				public void run() {
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(getResources().getString(R.string.url_web));
					try {
						HttpResponse httpResponse = httpClient.execute(httpGet);
						//第四步：检查相应状态是否正常，检查状态码值为200是正常
						if (httpResponse.getStatusLine().getStatusCode()==200) {
							//第五步：从相应对象中取出数据，放到entity中
							HttpEntity entity = httpResponse.getEntity();
							String response = EntityUtils.toString(entity,"utf-8");//将entity中的数据转换成字符串
							//在子线程中将message对象发送出去
							Message message = new Message();
							message.what = SHOW_RESPONSE;
							message.obj = response.toString();
							handler_conn.sendMessage(message);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
				
			}
			
		).start();
	}
	public static final int SHOW_RESPONSE = 0;
	TextView textView_response;

	//新建handler对象，接收message并更新textview控件内容
		private Handler handler_conn = new Handler(){
			public void handleMessage(android.os.Message msg) {
				super.handleMessage(msg);
				switch(msg.what){
				case SHOW_RESPONSE:
					String response = (String) msg.obj;
					textView_response.setText(response);
					break;
				default:
					break;
				}
			}
		};
}
