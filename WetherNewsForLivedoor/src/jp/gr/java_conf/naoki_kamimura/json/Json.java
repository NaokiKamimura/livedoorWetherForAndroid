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
	 * Gson���C�u�������g����̓e�X�g�p�ɂ܂�JSON�`���Ő�������
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
	 * @author NaokiKamimura
	 * JSON�t�@�C������͂���
	 */
	public void readJson() {
		LogUtil log = new LogUtil();
		V1 v1;
		try {
			Gson gson = new Gson();
			log.output("logFlag", "0");
			// TODO ��������悪�ǂݍ��܂�Ă��Ȃ��̂ŁA�p�X�̎w�肪���������C������
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
