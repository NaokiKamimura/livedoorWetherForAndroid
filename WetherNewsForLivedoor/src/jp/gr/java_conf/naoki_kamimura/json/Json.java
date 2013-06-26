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

	/**
	 * @version 1.00 25 June 2013
	 * @author NaokiKamimura
	 * Gsonライブラリを使い解析テスト用にまずJSON形式で生成する
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
	 * @author NaokiKamimura
	 * JSONファイルを解析する
	 */
	public void readJson() {
		LogUtil log = new LogUtil();
		V1 v1;
		try {
			Gson gson = new Gson();
			log.output("logFlag", "0");
			// TODO ここから先が読み込まれていないので、パスの指定がおかしい気がする
			FileReader reader = new FileReader("/jsonfile/v1.json");
			log.output("logFlag", "1");
			v1 = gson.fromJson(reader, V1.class);// JSON
			reader.close();
			List<PinpointLocations> pinpointLocations = v1
					.getPinpointLocations();
			String jsonLog = "";
			String jsonSize = String.valueOf(pinpointLocations.size());
			log.output("logFlag", "2");
			for (int i = 0; i < pinpointLocations.size(); i++) {
				jsonLog = pinpointLocations.get(i).toString();
				log.output("logFlag", "3");
				log.output("jsonLog", jsonSize);
				log.output("jsonLog", jsonLog);
			}

		} catch (FileNotFoundException e) {
			log.output("fileNotFound", "");
		} catch (Exception e) {
			log.output("Error", "");
		}
	}

}
