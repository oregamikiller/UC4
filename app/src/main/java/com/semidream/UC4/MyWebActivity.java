package com.semidream.UC4;



import net.youmi.android.spot.SpotManager;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mywebview);

		WebView mywebView = (WebView) findViewById(R.id.mywebview);
		setContentView(mywebView);
		WebSettings webSettings = mywebView.getSettings();
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		SpotManager.getInstance(MyWebActivity.this).showSpotAds(
				MyWebActivity.this);

		webSettings.setJavaScriptEnabled(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setSupportZoom(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		mywebView.setWebViewClient(new WebViewClient(){

			public boolean shouldOverrideUrlLoading(WebView view,String url){
				view.loadUrl(url);
				return true;


			}
		});
		mywebView.loadUrl(url);

	}
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			//land
		}
		else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
		{
			//port
		}
	}
}