/*
 * @(#)WetherView.java        1.00 13/07/02
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.activity;

import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.R;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.R.id;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.R.layout;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.R.menu;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class WetherView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wether_view);
		TextView wetherViewTextView = (TextView) findViewById(R.id.wetherview_textView);
		wetherViewTextView.setText(name());
		WebView myWebView = (WebView) findViewById(R.id.wetherview);
		myWebView.setWebViewClient(new WebViewClient());
		myWebView.loadUrl(Url());
		returnButton();//–ß‚éƒ{ƒ^ƒ“
	}
	/**
	 * @version 1.00 02 July 2013
	 * @author NaokiKamimura
	 */
	private void returnButton(){
		Button button = (Button)findViewById(R.id.wetherview_button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(WetherView.this,SecondActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	/**
	 * @version 1.00 02 July 2013
	 * @author NaokiKamimura
	 * @return Žæ“¾‚µ‚½URL
	 */
	private String Url() {
		Intent intent = getIntent();
		String url = intent.getStringExtra("index");
		return url;
	}
	/**
	 * @version 1.00 02 July 2013
	 * @author NaokiKamimura
	 * @return Žæ“¾‚µ‚½’n–¼
	 */
	private String name() {
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		return name;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wether_view, menu);
		return true;
	}

}
