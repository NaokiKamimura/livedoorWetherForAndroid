/*
 * @(#)Http.java        0.50 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import jp.gr.java_conf.naoki_kamimura.util.LogUtil;

import android.util.Log;

import com.google.gson.Gson;

public class Json {

	// Gsonライブラリを使い解析テスト用にまずJSON形式で生成する
	public void createJson() {
		LogUtil log = new LogUtil();
		// Gsonのオブジェクト作成
		Gson gson = new Gson();
		String testJson = gson.toJson("test");
		log.putLog("testJson", testJson);
	}

	// JSONの解析を行う
	public void readJson() {
		LogUtil log = new LogUtil();
		V1 v1;
		try {
			Gson gson = new Gson();
			log.putLog("logFlag", "0");
			// TODO ここから先が読み込まれていないので、パスの指定がおかしい気がする
			FileReader reader = new FileReader("jsonfile/v1.json");
			log.putLog("logFlag", "1");
			v1 = gson.fromJson(reader, V1.class);// JSON
			reader.close();
			List<PinpointLocations> pinpointLocations = v1
					.getPinpointLocations();
			String jsonLog = "";
			String jsonSize = String.valueOf(pinpointLocations.size());
			log.putLog("logFlag", "2");
			for (int i = 0; i < pinpointLocations.size(); i++) {
				jsonLog = pinpointLocations.get(i).toString();
				log.putLog("logFlag", "3");
				log.putLog("jsonLog", jsonSize);
				log.putLog("jsonLog", jsonLog);
			}

		} catch (FileNotFoundException e) {
			log.putLog("fileNotFound", "");
		} catch (Exception e) {
			log.putLog("Error", "");
		}
	}

}
