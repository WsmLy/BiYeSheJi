package com.example.main1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class GotoWebActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goto_web);

		WebView webView = (WebView) findViewById(R.id.view_Webapp);
		webView.loadUrl(getResources().getString(R.string.url_web));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		Intent chrome_gotoweb_intent = new
				Intent("android.intent.action.VIEW",Uri.parse(getResources().getString(R.string.url_web)));

		startActivity(chrome_gotoweb_intent);
		return super.onMenuItemSelected(featureId, item);
	}
}
