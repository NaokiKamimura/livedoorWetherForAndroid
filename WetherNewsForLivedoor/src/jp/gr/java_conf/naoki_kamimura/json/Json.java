/*
 * @(#)Http.java        1.00 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.json;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.naoki_kamimura.http.Http;
import jp.gr.java_conf.naoki_kamimura.util.LogUtil;
import android.content.Context;
import android.content.res.AssetManager;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

public class Json {
	/**
	 * @version 1.00 25 June 2013
	 * @author NaokiKamimura Gson���C�u�������g����̓e�X�g�p�ɂ܂�JSON�`���Ő�������
	 */
	public void createJson() {
		LogUtil log = new LogUtil();
		// Gson�̃I�u�W�F�N�g�쐬
		Gson gson = new Gson();
		String testJson = gson.toJson("test");
		log.output("testJson", testJson);
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura ���X�g���X�V����{�^���̏���
	 * @return JSON��������i�[����List
	 */
	private List<PinpointLocations> jsonText(Context context) {
		LogUtil log = new LogUtil();
		List<PinpointLocations> pinpointLocations = null;
		V1 v1;// JSON�̃}�b�s���O�����I�u�W�F�N�g�̂���N���X
		try {
			Gson gson = new Gson();
			// �n���ꂽ�R���e�L�X�g����
			log.output("logFlag", "0");
			// AssetManager�Ńt�@�C���p�X���w�肷��
			AssetManager assetManager = context.getResources().getAssets();
			// JSON��ǂݍ���
			InputStream iStream;
			iStream = assetManager.open("jsonfile/v1.json");
			Reader reader = new InputStreamReader(iStream);
			log.output("logFlag", "1");
			v1 = gson.fromJson(reader, V1.class);
			pinpointLocations = v1.getPinpointLocations();
		} catch (FileNotFoundException e) {
			log.output("fileNotFound", "");
		} catch (Exception e) {
			log.output("Error", "");
		}
		return pinpointLocations;
	}

	private List<PinpointLocations> jsonText(String jsonData) {
		LogUtil log = new LogUtil();
		List<PinpointLocations> pinpointLocations = null;
		V1 v1;// JSON�̃}�b�s���O�����I�u�W�F�N�g�̂���N���X
		try {
			Gson gson = new Gson();
			// �n���ꂽ�R���e�L�X�g����
			log.output("logFlag", "0");
			v1 = gson.fromJson(jsonData, V1.class);
			pinpointLocations = v1.getPinpointLocations();
		} catch (Exception e) {
			log.output("Error", "");
		}
		return pinpointLocations;
	}

	/**
	 * @version 1.00 25 June 2013
	 * @author NaokiKamimura JSON�t�@�C������͂���
	 * @return �s�撬���̓�����List
	 */
	public List<String> readJsonNameList(Context context) {
		LogUtil log = new LogUtil();
		Http http = new Http();
		List<String> nameList = new ArrayList<String>();
		List<String> linkList = new ArrayList<String>();
		try {
			List<PinpointLocations> pinpointLocations = jsonText(context);
			String name = "";// �s�撬���̎擾
			String link = "";// �V�C�\����URL�̎擾
			String jsonSize = String.valueOf(pinpointLocations.size());
			// �ǂݍ���JSON�̒������l���擾
			for (int i = 0; i < pinpointLocations.size(); i++) {
				name = pinpointLocations.get(i).getName();
				nameList.add(name);// �s�撬�������X�g��
				link = pinpointLocations.get(i).getLink();
				linkList.add(link);// �����N�����X�g��
				log.output("jsonName:" + i, name);
				log.output("jsonLink:" + i, link);
			}
			log.output("tag", "jsonSize:" + jsonSize);
		} catch (Exception e) {
			log.output("Error", "");
		}
		return nameList;
	}

	/**
	 * @version 1.00 02 July 2013
	 * @author NaokiKamimura JSON�t�@�C������͂���
	 * @return �V�C�\�񃊃��N�A�h���X�̓�����List
	 */
	public List<String> readJsonLinkList(Context context) {
		LogUtil log = new LogUtil();
		List<String> linkList = new ArrayList<String>();
		try {
			List<PinpointLocations> pinpointLocations = jsonText(context);
			String link = "";// �V�C�\����URL�̎擾
			// �ǂݍ���JSON�̒������l���擾
			for (int i = 0; i < pinpointLocations.size(); i++) {
				link = pinpointLocations.get(i).getLink();
				linkList.add(link);// �����N�����X�g��
			}
		} catch (Exception e) {
			log.output("Error", "");
		}
		return linkList;
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura
	 * @return JSON���O���X�g�f�[�^�̓�����adapter
	 */
	public ArrayAdapter<String> getJSONNameListAdapter(Context context) {
		List<String> adapterList = new ArrayList<String>();
		adapterList = readJsonNameList(context);
		// JSON�̃f�[�^��adapter�֓����
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1);
		String inArrayListText = "";
		// arrayList�̗v�f������adapter�֑}��
		for (int i = 0; i < adapterList.size(); i++) {
			inArrayListText = adapterList.get(i);
			adapter.add(inArrayListText);
		}
		return adapter;
	}

}
