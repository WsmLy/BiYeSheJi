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

		button.setOnClickListener(new OnClickListener() {//点击跳过按钮
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				timer.cancel();//取消时间进程，直接跳转到MainActivity
				startActivity(intent);
			}
		});

		final TimerTask timerTask = new TimerTask() {//时间进程
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startActivity(intent);
			}
		};

		timer.schedule(timerTask, 1000*3);//三秒后执TimerTask中的run方法
//		timer.schedule(timerTask, 1000*i);
	}

}
