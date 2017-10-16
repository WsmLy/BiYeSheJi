package com.example.main1;

import java.util.Timer;
import java.util.TimerTask;

import com.example.main1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LauncherActivity extends Activity {
	
	Button button;
	int i;
	Intent intent;
	Timer timer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		button = (Button) findViewById(R.id.button1);
		
		intent = new Intent(this,MainActivity.class);
		timer = new Timer();

		button.setOnClickListener(new OnClickListener() {//���������ť
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				timer.cancel();//ȡ��ʱ����̣�ֱ����ת��MainActivity
				startActivity(intent);
			}
		});

		final TimerTask timerTask = new TimerTask() {//ʱ�����
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startActivity(intent);
			}
		};

		timer.schedule(timerTask, 1000*3);//�����ִTimerTask�е�run����
//		timer.schedule(timerTask, 1000*i);
	}

}
