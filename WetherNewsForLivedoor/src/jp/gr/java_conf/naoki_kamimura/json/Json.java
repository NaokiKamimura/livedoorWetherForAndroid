/*
 * @(#)Http.java        0.50 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.json;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.naoki_kamimura.util.LogUtil;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.SecondActivity;
import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

public class Json {
	private Context jsonContext;

	/**
	 * @version 1.00 25 June 2013
	 * @author NaokiKamimura Gsonライブラリを使い解析テスト用にまずJSON形式で生成する
	 */
	//
	public void createJson() {
		LogUtil log = new LogUtil();
		// Gsonのオブジェクト作成
		Gson gson = new Gson();
		String testJson = gson.toJson("test");
		log.output("testJson", testJson);
	}

	/**
	 * @version 1.00 25 June 2013
	 * @author NaokiKamimura JSONファイルを解析する
	 */
	public void readJson(Context context) {
		LogUtil log = new LogUtil();
		SecondActivity second = new SecondActivity();
		List<String> nameList = new ArrayList<String>();
		List<String> linkList = new ArrayList<String>();
		
		V1 v1;
		jsonContext = context;
		try {
			Gson gson = new Gson();
			//渡されたコンテキストを代入
			log.output("logFlag", "0");
			// AssetManagerでファイルパスを指定する
			AssetManager assetManager = context.getResources().getAssets();
			//JSONを読み込む
			InputStream is;
			is = assetManager.open("jsonfile/v1.json");
			Reader reader = new InputStreamReader(is);
			log.output("logFlag", "1");
			v1 = gson.fromJson(reader, V1.class);
			List<PinpointLocations> pinpointLocations = v1
					.getPinpointLocations();
			String name = "";
			String link = "";
			String jsonSize = String.valueOf(pinpointLocations.size());
			//読み込んだJSONの長さ分値を取得
			for (int i = 0; i < pinpointLocations.size(); i++) {
				name = pinpointLocations.get(i).getName();//市区町村の取得
				nameList.add(name);//市区町村をリストへ
				link = pinpointLocations.get(i).getLink();//天気予報画面の取得
				linkList.add(link);//リンクをリストへ
				log.output("jsonName:" + i, name);
				log.output("jsonLink:" + i, link);
			}
			second.listArray(nameList);//リストビューへ
			log.output("tag", "jsonSize:" + jsonSize);

		} catch (FileNotFoundException e) {
			log.output("fileNotFound", "");
		} catch (Exception e) {
			log.output("Error", "");
		}
	}

}
