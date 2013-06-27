/*
 * @(#)Http.java        0.50 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.gr.java_conf.naoki_kamimura.json.PinpointLocations;
import jp.gr.java_conf.naoki_kamimura.json.V1;
import jp.gr.java_conf.naoki_kamimura.util.LogUtil;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
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
		readJson();// JSON解析
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
		adapter = getJSONAdapter();// JSONデータの格納されたadapterを貰う
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

	/*
	 * public ArrayAdapter<String> screenDisplay_List(List<String> list) {
	 * List<String> arrayList = new ArrayList<String>(); // TODO
	 * arrayListへJSONのListを入れる
	 * 
	 * // JSONのデータをadapterへ入れる ArrayAdapter<String> adapter = new
	 * ArrayAdapter<String>(this, android.R.layout.simple_list_item_1); String
	 * inArrayListText = ""; // arrayListの要素数だけadapterへ挿入 for (int i = 0; i <
	 * arrayList.size(); i++) { inArrayListText = arrayList.get(i);
	 * adapter.add(inArrayListText); } return adapter; }
	 */

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
	private void update() {
		// TODO　リスト内容を更新する処理
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura リストを更新するボタンの処理
	 * @return JSON文字列を格納したList
	 */
	private List<PinpointLocations> jsonText() {
		LogUtil log = new LogUtil();
		List<PinpointLocations> pinpointLocations = null;
		V1 v1;// JSONのマッピングしたオブジェクトのあるクラス
		try {
			Gson gson = new Gson();
			// 渡されたコンテキストを代入
			log.output("logFlag", "0");
			// AssetManagerでファイルパスを指定する
			AssetManager assetManager = getResources().getAssets();
			// JSONを読み込む
			InputStream iStream;
			iStream = assetManager.open("jsonfile/v1.json");
			Reader reader = new InputStreamReader(iStream);
			log.output("logFlag", "1");
			v1 = gson.fromJson(reader, V1.class);
			pinpointLocations = v1.getPinpointLocations();
		} catch (FileNotFoundException e) {
			log.output("fileNotFound", "");
		} catch (Exception e) {
			log.output("Error", "");
		}
		return pinpointLocations;
	}

	/**
	 * @version 1.00 25 June 2013
	 * @author NaokiKamimura JSONファイルを解析する
	 * @return 市区町村の入ったList
	 */
	public List<String> readJson() {
		LogUtil log = new LogUtil();
		List<String> nameList = new ArrayList<String>();
		List<String> linkList = new ArrayList<String>();
		try {
			List<PinpointLocations> pinpointLocations = jsonText();
			String name = "";// 市区町村の取得
			String link = "";// 天気予報画面URLの取得
			String jsonSize = String.valueOf(pinpointLocations.size());
			// 読み込んだJSONの長さ分値を取得
			for (int i = 0; i < pinpointLocations.size(); i++) {
				name = pinpointLocations.get(i).getName();
				nameList.add(name);// 市区町村をリストへ
				link = pinpointLocations.get(i).getLink();
				linkList.add(link);// リンクをリストへ
				log.output("jsonName:" + i, name);
				log.output("jsonLink:" + i, link);
			}
			log.output("tag", "jsonSize:" + jsonSize);
		} catch (Exception e) {
			log.output("Error", "");
		}
		return nameList;
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura
	 * @return JSONデータの入ったadapter
	 */
	private ArrayAdapter<String> getJSONAdapter() {
		List<String> adapterList = new ArrayList<String>();
		adapterList = readJson();
		// JSONのデータをadapterへ入れる
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		String inArrayListText = "";
		// arrayListの要素数だけadapterへ挿入
		for (int i = 0; i < adapterList.size(); i++) {
			inArrayListText = adapterList.get(i);
			adapter.add(inArrayListText);
		}
		return adapter;

	}

}
