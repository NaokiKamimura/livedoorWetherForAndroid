/*
 * @(#)Http.java        1.00 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.json;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.util.LogUtil;
import android.content.Context;
import android.content.res.AssetManager;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

public class Json {

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura リストを更新するボタンの処理
	 * @param コンテキスト
	 * @return JSON文字列を格納したList
	 */
	private List<PinpointLocations> scanJSON(Context context) {
		LogUtil log = new LogUtil();
		List<PinpointLocations> pinpointLocations = null;
		V1 v1;// JSONのマッピングしたオブジェクトのあるクラス
		try {
			Gson gson = new Gson();
			// AssetManagerでファイルパスを指定する
			AssetManager assetManager = context.getResources().getAssets();
			// JSONを読み込む
			InputStream iStream;
			iStream = assetManager.open("jsonfile/v1.json");
			Reader reader = new InputStreamReader(iStream);
			v1 = gson.fromJson(reader, V1.class);
			pinpointLocations = v1.getPinpointLocations();
		} catch (FileNotFoundException e) {
			log.output("fileNotFound", e.toString());
		} catch (Exception e) {
			log.output("Error", e.toString());
		}
		return pinpointLocations;
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura リストを更新するボタンの処理
	 * @return JSON文字列を格納したList オーバーロード
	 * @param jsonの入ったString
	 */
	private List<PinpointLocations> scanJSON(String jsonData) {
		LogUtil log = new LogUtil();
		List<PinpointLocations> pinpointLocations = null;
		V1 v1;// JSONのマッピングしたオブジェクトクラス
		try {
			Gson gson = new Gson();
			// TODO　最終的にString型のjsonDataが入るようにする
			v1 = gson.fromJson(jsonData, V1.class);
			pinpointLocations = v1.getPinpointLocations();
		} catch (Exception e) {
			log.output("Error", e.toString());
		}
		return pinpointLocations;
	}

	/**
	 * @version 1.00 25 June 2013
	 * @author NaokiKamimura JSONファイルを解析する
	 * @return 市区町村の入ったList
	 * @param コンテキスト
	 */
	public List<String> wetherPlaceNameList(Context context) {
		LogUtil log = new LogUtil();
		ArrayList<String> json_WetherPlaceNameList = new ArrayList<String>();
		try {
			//TODO scanJSONへjsonDataを入れたい
			List<PinpointLocations> pinpointLocations = scanJSON(context);
			String placeName = "";// 市区町村の取得
			String jsonSize = String.valueOf(pinpointLocations.size());
			// 読み込んだJSONの長さ分値を取得
			for (int i = 0; i < pinpointLocations.size(); i++) {
				placeName = pinpointLocations.get(i).getName();
				json_WetherPlaceNameList.add(placeName);// 市区町村をリストへ
				log.output("jsonName:" + i, placeName);
			}
			log.output("tag", "jsonSize:" + jsonSize);
		} catch (Exception e) {
			log.output("Error", "");
		}
		return json_WetherPlaceNameList;
	}

	/**
	 * @version 1.00 02 July 2013
	 * @author NaokiKamimura JSONファイルを解析する
	 * @return 天気予報リンクアドレスの入ったList
	 * @param コンテキスト
	 */
	public List<String> wetherLinkList(Context context) {
		LogUtil log = new LogUtil();
		List<String> json_WetherLinkList = new ArrayList<String>();
		try {
			List<PinpointLocations> pinpointLocations = scanJSON(context);
			String wetherLink = "";// 天気予報画面URLの取得
			// 読み込んだJSONの長さ分値を取得
			for (int i = 0; i < pinpointLocations.size(); i++) {
				wetherLink = pinpointLocations.get(i).getLink();
				json_WetherLinkList.add(wetherLink);// リンクをリストへ
			}
		} catch (Exception e) {
			log.output("Error", "何らかのエラー");
		}
		return json_WetherLinkList;
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura
	 * @return JSON名前リストデータの入ったadapter
	 * @param コンテキスト
	 */
	public ArrayAdapter<String> placeNameAdapter(Context context) {
		List<String> adapterList = new ArrayList<String>();
		adapterList = wetherPlaceNameList(context);
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
