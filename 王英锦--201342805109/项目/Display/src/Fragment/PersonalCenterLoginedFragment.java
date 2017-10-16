package Fragment;

import com.example.buy.MyCommodityActivity;
import com.example.commodity.CommodityUploadActivity;
import com.example.main1.MainActivity;
import com.example.main1.R;
import com.example.personal.PersonalEdit;
//import com.example.personal.PersonalSetting;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalCenterLoginedFragment extends Fragment{

	View personalCenter_logined_root_view;


	TextView tvw_set;
	TextView tvw_per;
	Button btn_exit;
	TextView tv_username;
	String username;
	TextView tv_public,tv_sale,tv_bought;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		personalCenter_logined_root_view = inflater.inflate(R.layout.fragment_personal_center_logined, container,false);

		tv_username = (TextView) personalCenter_logined_root_view.findViewById(R.id.tv_user);
		tvw_set =(TextView)personalCenter_logined_root_view.findViewById(R.id.tv_settle);
		tvw_per =(TextView)personalCenter_logined_root_view.findViewById(R.id.tv_edit);
		btn_exit =(Button)personalCenter_logined_root_view.findViewById(R.id.bt_exit);
		tv_public = (TextView) personalCenter_logined_root_view.findViewById(R.id.tv_public);
//		tv_sale = (TextView) personalCenter_logined_root_view.findViewById(R.id.tv_sale);
//		tv_bought = (TextView) personalCenter_logined_root_view.findViewById(R.id.tv_bought);

		username = getActivity().getIntent().getStringExtra("username");
		
		tv_username.setText(username);
		
		tvw_set.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

//				Intent i = new Intent(getActivity(),PersonalSetting.class);
//				startActivity(i);
				Intent share_intent = new Intent(Intent.ACTION_SEND);
				share_intent.setType("text/plain");
				share_intent.putExtra(Intent.EXTRA_TEXT, "欢迎使用旧时光旧物交易平台");//"这里存放app应用下载链接或者apk文件");
				startActivity(share_intent);			}
		});

		tvw_per.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(),PersonalEdit.class);
				i.putExtra("username", username);
				startActivity(i);
			}
		});
		
		btn_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),MainActivity.class);
				intent.putExtra("fragment_id", 0);
				startActivity(intent);
				
				getFragmentManager().beginTransaction()
				.replace(R.id.container, new PersonalCenterUnLoginedFragment())
				.commit();
			}
		});
		
		/*
		 * 一下三个复用同一个view
		 */
		//商品发布点击
		tv_public.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),MyCommodityActivity.class);
				intent.putExtra("select", "fabu");
				intent.putExtra("username", username);
				startActivity(intent);
			}
		});
//		//我卖出的商品点击
//		tv_sale.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(),MyCommodityActivity.class);
//				intent.putExtra("select", "sale");
//				intent.putExtra("username", username);
//				startActivity(intent);
//			}
//		});
//		//我买到的商品点击
//		tv_bought.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(),MyCommodityActivity.class);
//				intent.putExtra("select", "bought");
//				intent.putExtra("username", username);
//				startActivity(intent);
//			}
//		});

		return personalCenter_logined_root_view;
	}
}

