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
				share_intent.putExtra(Intent.EXTRA_TEXT, "��ӭʹ�þ�ʱ����ｻ��ƽ̨");//"������appӦ���������ӻ���apk�ļ�");
				startActivity(share_intent);
			}
		});
		
		tv_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(PersonalSetting.this, "�������°汾", Toast.LENGTH_SHORT).show();
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

}