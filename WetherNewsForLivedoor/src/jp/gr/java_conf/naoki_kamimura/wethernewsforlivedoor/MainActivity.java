/*
 * @(#)Http.java        0.50 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor;

import jp.gr.java_conf.naoki_kamimura.json.Json;
import jp.gr.java_conf.naoki_kamimura.util.LogUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Json�N���X�Ńe�X�g��f���o��
		Json json = new Json();
		LogUtil log = new LogUtil();
		Context context = getApplicationContext();
		Log.v("logFlag","-1");
		json.readJson(context);
		Button button = (Button) findViewById(R.id.main_button);
		//�\������{�^����������SecondActivity�ֈړ�����
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Json json = new Json();
				Intent intent = new Intent(MainActivity.this,SecondActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * @version 1.00 24 June 2013
	 * @author N.Kamimura
	 * @param �ʒm���镶����
	 *            �g�[�X�g�ʒm��\��
	 */
	public void makeToast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
				.show();
	}

	/**
	 * @version 1.00 24 June 2013
	 * @author N.Kamimura
	 * @param �^�C�g��
	 *            ,�{�� �A���[�g�_�C�A���O��\��
	 */
	public void makeDialog(String title, String message) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setPositiveButton("OK", null);
		dialog.show();
	}

}
