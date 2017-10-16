//package com.example.buy;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.example.main1.R;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//import android.widget.AdapterView.OnItemSelectedListener;
//
//public class IndentActivity extends Activity {
//	
//	Spinner sp_sheng,sp_shi;
//	ArrayAdapter<String> adapter_sheng,adapter_shi;
//	List<String> list_sheng,list_shi;
//	String w_sheng,w_shi;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_indent);
//		
//		findView();
//		AddList();
//		SetSpinner();
//	}
//
//	private void findView() {
//		sp_sheng = (Spinner) findViewById(R.id.spinner_sheng);
//		sp_shi = (Spinner) findViewById(R.id.spinner_shi);
//	}
//	
//	private void SetSpinner(){
//		adapter_sheng=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_sheng);
//		adapter_sheng.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		sp_sheng.setAdapter(adapter_sheng);
//		sp_sheng.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//				//w_sheng = list_sheng.get(position);
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//
//			}
//		});
//		
//		adapter_shi=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_shi);
//		adapter_shi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		sp_shi.setAdapter(adapter_shi);
//		sp_shi.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//				//w_shi = list_shi.get(position);
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//
//			}
//		});
//	}
//	
//	private void AddList() {
//		list_sheng = new ArrayList<String>();
//		list_sheng.add("省/直辖市/自治区");
//		list_sheng.add("山东省");
//		
//
//		list_shi = new ArrayList<String>();
//		list_shi.add("市/自治旗");
//		list_shi.add("青岛市");
//	}
//	
//}
