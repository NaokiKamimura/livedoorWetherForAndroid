/*
 * @(#)Http.java        0.50 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import jp.gr.java_conf.naoki_kamimura.util.LogUtil;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

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

	/**
	 * @version 1.00 25 June 2013
	 * @author NaokiKamimura JSON�t�@�C������͂���
	 */
	public void readJson(Context context) {
		LogUtil log = new LogUtil();
		V1 v1;
		try {
			Gson gson = new Gson();
			jsonContext = context;
			log.output("logFlag", "0");
			// AssetManager�Ńt�@�C���p�X���w�肷��
			AssetManager assetManager = context.getResources().getAssets();
			//JSON��ǂݍ���
			InputStream is;
			is = assetManager.open("jsonfile/v1.json");
			Reader reader = new InputStreamReader(is);
			log.output("logFlag", "1");
			v1 = gson.fromJson(reader, V1.class);// JSON
			List<PinpointLocations> pinpointLocations = v1
					.getPinpointLocations();
			String jsonLog = "";
			String jsonSize = String.valueOf(pinpointLocations.size());//�ǂݍ���
			log.output("logFlag", "2");
			for (int i = 0; i < pinpointLocations.size(); i++) {
				jsonLog = pinpointLocations.get(i).toString();
				log.output("jsonLog", jsonLog);
			}
			log.output("tag", "jsonSize:" + jsonSize);

		} catch (FileNotFoundException e) {
			log.output("fileNotFound", "");
		} catch (Exception e) {
			log.output("Error", "");
		}
	}

}
