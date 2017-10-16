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

	// ����PopupWindow���������
	private PopupWindow popupWindow;
	private PopupWindow popupWindow_contentus;
	
	//����
	int login_sure = 2;
	String acname = null;
	String username = null;

	//�����С
	float size13 = 0x13;
	float size10 = 0x10;

	//����
	ImageView iv_log,iv_menu;
	LinearLayout head,bottom;
	//��˵�
	Button contentus;
	Button gotoweb;
	Button open;
	//����
	TextView tv_num;
	//�ײ�
	Button mainPage;
	Button recommend;
	Button shoppingCar;
	Button personalCenter;
	//ʵ�尴��
	boolean isExit;

	//	String tv_main_1_text = "";
	//	String tv_main_2_text = "";

	//���ݿ�
	MainDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//����һ�����ݿ���󲢰����ݿ����ִ������ݿ�ϵͳ
//		db = new MainDatabase(getApplicationContext());

		FindView();
		
		login_sure = getIntent().getIntExtra("fragment_id", 0);
		username = getIntent().getStringExtra("username");
		acname = getIntent().getStringExtra("activityName");
		
		
		/*
		 * ��¼�ɹ�����ĵ�¼Ϊ��������
		 * ������������λ�ȡleft����fragment�ĵ�¼button
		 * ���Գ��Ը���string�е�����
//		 */
//		if(login_sure == 1){
//			open.setText("��������");
//		}

		OnClick();
				
		if("loginActivity".equals(acname)){
			/*
			 * û��Ҫ��������
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

	//findviewbyid�ķ���
	private void FindView(){
		//����
		iv_log = (ImageView) findViewById(R.id.log);
		iv_menu = (ImageView) findViewById(R.id.menu);
		head = (LinearLayout) findViewById(R.id.main_head);
		//��˵�
		open = (Button) findViewById(R.id.open);
		contentus = (Button) findViewById(R.id.contentus);
		gotoweb = (Button) findViewById(R.id.gotoweb);
		//�ײ�
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
	
	/*�ײ��ĸ���ť�ĵ���¼�*/
	//	//��ҳ�ĵ���¼������������С��ת����ҳ��View��
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

	//	//�Ƽ��ĵ���¼������������С��ת���Ƽ���View��
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

	//	//���ﳵ�ĵ���¼������������С��ת�����ﳵ��View��
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

	//	//�������ĵĵ���¼������������С��ת���������ĵ�View��
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

	/*��˵��ĵ���¼�*/
//	
//	public boolean onNavigationItemSelected(MenuItem item){
//		return false;
//		
//	}
	//��¼����¼�
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
	
	//��������¼�
	public void Search(View view){
		startActivity(new Intent(MainActivity.this,SearchActivity.class));
		popupWindow.dismiss();
	}
	//��ϵ���ǵ���¼�
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
	    // ��ȡ�Զ��岼���ļ�activity_popupwindow_left.xml����ͼ
        View popupWindow_view = getLayoutInflater().inflate(R.layout.contentus, null, false);
        // ����PopupWindowʵ��,200,LayoutParams.MATCH_PARENT�ֱ��ǿ�Ⱥ͸߶�
        popupWindow_contentus = new PopupWindow(popupWindow_view, 400, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // ���ö���Ч��
        // popupWindow.setAnimationStyle(R.style.AnimationFade);
        // ��������ط���ʧ
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



	//����ҳ�����¼�,error
	public void GotoWeb(View view){
//		((ViewGroup) findViewById(R.id.container)).removeAllViews();
//		getFragmentManager().beginTransaction()
//		.replace(R.id.container, new WebAppViewFragment())
//		.commit();	
		startActivity(new Intent(MainActivity.this,GotoWebActivity.class));
		popupWindow.dismiss();
	}
	
	//��������¼�
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
	
	

	/*����¼�*/
	public void OnClick() {
		/*����������ť�ĵ���¼�*/
		//��¼��ť�ĵ���¼���ת��RegisterActivity
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
		//�˵���ť�ĵ���¼�
		findViewById(R.id.menu).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {	
				
				getPopupWindow();
				// ������λ����ʾ��ʽ,����Ļ�����
				popupWindow.showAtLocation(v, Gravity.LEFT, 0, 0);
			}
		});


	}
	/**
	 * ����PopupWindow
	 */
	@SuppressLint({ "RtlHardcoded", "InflateParams", "ClickableViewAccessibility" })
	protected void initPopuptWindow() {
		// TODO Auto-generated method stub
		// ��ȡ�Զ��岼���ļ�activity_popupwindow_left.xml����ͼ
		View popupWindow_view = getLayoutInflater().inflate(R.layout.fragment_left, null,
				false);
		/*
		 * ׼�����ĵ�¼��ť������
		 */
//		if (login_sure!=0) {
//			open = (Button) findViewById(R.id.open);
//			System.out.println(getResources().getString(R.string.personal_center));
//			open.setText(getResources().getString(R.string.personal_center));
//		}
		// ����PopupWindowʵ��,200,LayoutParams.MATCH_PARENT�ֱ��ǿ�Ⱥ͸߶�
		popupWindow = new PopupWindow(popupWindow_view, 400, LayoutParams.MATCH_PARENT, true);
		// ���ö���Ч��
		popupWindow.setAnimationStyle(R.style.AnimationFade);
		// ��������ط���ʧ
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
	 * ��ȡPopupWindowʵ��
	 */
	private void getPopupWindow() {
		if (null != popupWindow) {
			popupWindow.dismiss();
			return;
		} else {		
			initPopuptWindow();
		}
	}

	/*ʵ�尴������¼�*/
	//���ؼ�����¼�//���ε���˳�����
	public boolean onKeyDown(int KeyCode,KeyEvent event){
		if(KeyCode==KeyEvent.KEYCODE_BACK){
			exit();
			return false;
		}
		else{
			return super.onKeyDown(KeyCode, event);
		}
	}

	//���ؼ������ִ�еķ���
	public void exit(){  
		if (!isExit) {  
			isExit = true;  
			Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();  
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
	
	//���ӷ�����
	@SuppressWarnings("unused")
	private void ConnectServers(){
		new Thread(
			new Runnable() {
				public void run() {
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(getResources().getString(R.string.url_web));
					try {
						HttpResponse httpResponse = httpClient.execute(httpGet);
						//���Ĳ��������Ӧ״̬�Ƿ����������״̬��ֵΪ200������
						if (httpResponse.getStatusLine().getStatusCode()==200) {
							//���岽������Ӧ������ȡ�����ݣ��ŵ�entity��
							HttpEntity entity = httpResponse.getEntity();
							String response = EntityUtils.toString(entity,"utf-8");//��entity�е�����ת�����ַ���
							//�����߳��н�message�����ͳ�ȥ
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

	//�½�handler���󣬽���message������textview�ؼ�����
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
