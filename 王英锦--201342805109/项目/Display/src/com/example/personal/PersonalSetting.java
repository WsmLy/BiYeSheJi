package com.example.personal;

import com.example.main1.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalSetting extends Activity{

private TextView tvw_edit;
private TextView tv_share,tv_update;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_setting);
		
		tv_share = (TextView) findViewById(R.id.tv_share);
		tv_update = (TextView) findViewById(R.id.tv_update);
		
		tv_share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent share_intent = new Intent(Intent.ACTION_SEND);
				share_intent.setType("text/plain");
				share_intent.putExtra(Intent.EXTRA_TEXT, "欢迎使用旧时光旧物交易平台");//"这里存放app应用下载链接或者apk文件");
				startActivity(share_intent);
			}
		});
		
		tv_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(PersonalSetting.this, "已是最新版本", Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	private PopupWindow popupWindow_contentus;
	public void Contentus(View view) {
		ClickComunicate(findViewById(R.id.container));
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

}