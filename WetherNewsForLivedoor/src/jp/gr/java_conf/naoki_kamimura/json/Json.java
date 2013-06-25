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

	// Gson���C�u�������g����̓e�X�g�p�ɂ܂�JSON�`���Ő�������
	public void createJson() {
		LogUtil log = new LogUtil();
		// Gson�̃I�u�W�F�N�g�쐬
		Gson gson = new Gson();
		String testJson = gson.toJson("test");
		log.putLog("testJson", testJson);
	}

	// JSON�̉�͂��s��
	public void readJson() {
		LogUtil log = new LogUtil();
		V1 v1;
		try {
			Gson gson = new Gson();
			log.putLog("logFlag", "0");
			// TODO ��������悪�ǂݍ��܂�Ă��Ȃ��̂ŁA�p�X�̎w�肪���������C������
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
