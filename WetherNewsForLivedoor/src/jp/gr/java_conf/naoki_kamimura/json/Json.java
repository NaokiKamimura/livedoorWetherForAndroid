/*
 * @(#)Http.java        0.50 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.json;

import jp.gr.java_conf.naoki_kamimura.util.LogUtil;
import android.content.Context;

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

	

}
