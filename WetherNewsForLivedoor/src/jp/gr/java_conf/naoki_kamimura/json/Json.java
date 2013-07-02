/*
 * @(#)Http.java        1.00 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.json;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.naoki_kamimura.http.Http;
import jp.gr.java_conf.naoki_kamimura.util.LogUtil;
import android.content.Context;
import android.content.res.AssetManager;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

public class Json {
	/**
	 * @version 1.00 25 June 2013
	 * @author NaokiKamimura Gsonライブラリを使い解析テスト用にまずJSON形式で生成する
	 */
	public void createJson() {
		LogUtil log = new LogUtil();
		// Gsonのオブジェクト作成
		Gson gson = new Gson();
		String testJson = gson.toJson("test");
		log.output("testJson", testJson);
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura リストを更新するボタンの処理
	 * @return JSON文字列を格納したList
	 */
	private List<PinpointLocations> jsonText(Context context) {
		LogUtil log = new LogUtil();
		List<PinpointLocations> pinpointLocations = null;
		V1 v1;// JSONのマッピングしたオブジェクトのあるクラス
		try {
			Gson gson = new Gson();
			// 渡されたコンテキストを代入
			log.output("logFlag", "0");
			// AssetManagerでファイルパスを指定する
			AssetManager assetManager = context.getResources().getAssets();
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

	private List<PinpointLocations> jsonText(String jsonData) {
		LogUtil log = new LogUtil();
		List<PinpointLocations> pinpointLocations = null;
		V1 v1;// JSONのマッピングしたオブジェクトのあるクラス
		try {
			Gson gson = new Gson();
			// 渡されたコンテキストを代入
			log.output("logFlag", "0");
			v1 = gson.fromJson(jsonData, V1.class);
			pinpointLocations = v1.getPinpointLocations();
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
	public List<String> readJsonNameList(Context context) {
		LogUtil log = new LogUtil();
		Http http = new Http();
		List<String> nameList = new ArrayList<String>();
		List<String> linkList = new ArrayList<String>();
		try {
			List<PinpointLocations> pinpointLocations = jsonText(context);
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
	 * @version 1.00 02 July 2013
	 * @author NaokiKamimura JSONファイルを解析する
	 * @return 天気予報リンクアドレスの入ったList
	 */
	public List<String> readJsonLinkList(Context context) {
		LogUtil log = new LogUtil();
		List<String> linkList = new ArrayList<String>();
		try {
			List<PinpointLocations> pinpointLocations = jsonText(context);
			String link = "";// 天気予報画面URLの取得
			// 読み込んだJSONの長さ分値を取得
			for (int i = 0; i < pinpointLocations.size(); i++) {
				link = pinpointLocations.get(i).getLink();
				linkList.add(link);// リンクをリストへ
			}
		} catch (Exception e) {
			log.output("Error", "");
		}
		return linkList;
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura
	 * @return JSON名前リストデータの入ったadapter
	 */
	public ArrayAdapter<String> getJSONNameListAdapter(Context context) {
		List<String> adapterList = new ArrayList<String>();
		adapterList = readJsonNameList(context);
		// JSONのデータをadapterへ入れる
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
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
