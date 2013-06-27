/*
 * @(#)Http.java        0.50 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		// TODO リストビューを呼びたい
		listView();
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura リストを表示
	 */
	private void listView() {
		// 画面表示用ListView
		ListView listView = (ListView) findViewById(R.id.listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		listView.setAdapter(adapter);// adapterのセット
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListView list = (ListView) parent;
				String msg = "index:" + position;
				makeToast(msg);
				// (テスト用)タッチされた場所のアイテムを取得する
				String item = "item:"
						+ (String) list.getItemAtPosition(position);
				makeToast(item);
			}
		});

	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura
	 * @param JSONファイルの格納されたlist
	 * @return JSONデータの入ったadapter
	 */
	public ArrayAdapter<String> screenDisplay_List(List<String> list) {
		List<String> arrayList = new ArrayList<String>();
		// TODO arrayListへJSONのListを入れる
		
		// JSONのデータをadapterへ入れる
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		String inArrayListText = "";
		// arrayListの要素数だけadapterへ挿入
		for (int i = 0; i < arrayList.size(); i++) {
			inArrayListText = arrayList.get(i);
			adapter.add(inArrayListText);
		}
		return adapter;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura トースト通知を表示する
	 */
	public void makeToast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
				.show();
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura リストを更新するボタンの処理
	 */
	private void updateButton() {
		// TODO　リスト内容を更新するボタンの処理
	}

}
