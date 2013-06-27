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
		SecondActivity second = new SecondActivity();
		List<String> nameList = new ArrayList<String>();
		List<String> linkList = new ArrayList<String>();
		
		V1 v1;
		jsonContext = context;
		try {
			Gson gson = new Gson();
			//�n���ꂽ�R���e�L�X�g����
			log.output("logFlag", "0");
			// AssetManager�Ńt�@�C���p�X���w�肷��
			AssetManager assetManager = context.getResources().getAssets();
			//JSON��ǂݍ���
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
			//�ǂݍ���JSON�̒������l���擾
			for (int i = 0; i < pinpointLocations.size(); i++) {
				name = pinpointLocations.get(i).getName();//�s�撬���̎擾
				nameList.add(name);//�s�撬�������X�g��
				link = pinpointLocations.get(i).getLink();//�V�C�\���ʂ̎擾
				linkList.add(link);//�����N�����X�g��
				log.output("jsonName:" + i, name);
				log.output("jsonLink:" + i, link);
			}
			second.listArray(nameList);//���X�g�r���[��
			log.output("tag", "jsonSize:" + jsonSize);

		} catch (FileNotFoundException e) {
			log.output("fileNotFound", "");
		} catch (Exception e) {
			log.output("Error", "");
		}
	}

}
