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
	 * @author NaokiKamimura Gson���C�u�������g����̓e�X�g�p�ɂ܂�JSON�`���Ő�������
	 */
	//
	public void createJson() {
		LogUtil log = new LogUtil();
		// Gson�̃I�u�W�F�N�g�쐬
		Gson gson = new Gson();
		String testJson = gson.toJson("test");
		log.output("testJson", testJson);
	}

	

}
