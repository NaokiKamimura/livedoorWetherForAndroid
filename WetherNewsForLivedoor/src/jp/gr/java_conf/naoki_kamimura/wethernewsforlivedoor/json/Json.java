/*
 * @(#)Http.java        1.00 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.json;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.util.LogUtil;
import android.content.Context;
import android.content.res.AssetManager;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

public class Json {

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura ���X�g���X�V����{�^���̏���
	 * @param �R���e�L�X�g
	 * @return JSON��������i�[����List
	 */
	private List<PinpointLocations> scanJSON(Context context) {
		LogUtil log = new LogUtil();
		List<PinpointLocations> pinpointLocations = null;
		V1 v1;// JSON�̃}�b�s���O�����I�u�W�F�N�g�̂���N���X
		try {
			Gson gson = new Gson();
			// AssetManager�Ńt�@�C���p�X���w�肷��
			AssetManager assetManager = context.getResources().getAssets();
			// JSON��ǂݍ���
			InputStream iStream;
			iStream = assetManager.open("jsonfile/v1.json");
			Reader reader = new InputStreamReader(iStream);
			v1 = gson.fromJson(reader, V1.class);
			pinpointLocations = v1.getPinpointLocations();
		} catch (FileNotFoundException e) {
			log.output("fileNotFound", e.toString());
		} catch (Exception e) {
			log.output("Error", e.toString());
		}
		return pinpointLocations;
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura ���X�g���X�V����{�^���̏���
	 * @return JSON��������i�[����List �I�[�o�[���[�h
	 * @param json�̓�����String
	 */
	private List<PinpointLocations> scanJSON(String jsonData) {
		LogUtil log = new LogUtil();
		List<PinpointLocations> pinpointLocations = null;
		V1 v1;// JSON�̃}�b�s���O�����I�u�W�F�N�g�N���X
		try {
			Gson gson = new Gson();
			// TODO�@�ŏI�I��String�^��jsonData������悤�ɂ���
			v1 = gson.fromJson(jsonData, V1.class);
			pinpointLocations = v1.getPinpointLocations();
		} catch (Exception e) {
			log.output("Error", e.toString());
		}
		return pinpointLocations;
	}

	/**
	 * @version 1.00 25 June 2013
	 * @author NaokiKamimura JSON�t�@�C������͂���
	 * @return �s�撬���̓�����List
	 * @param �R���e�L�X�g
	 */
	public List<String> wetherPlaceNameList(Context context) {
		LogUtil log = new LogUtil();
		ArrayList<String> json_WetherPlaceNameList = new ArrayList<String>();
		try {
			//TODO scanJSON��jsonData����ꂽ��
			List<PinpointLocations> pinpointLocations = scanJSON(context);
			String placeName = "";// �s�撬���̎擾
			String jsonSize = String.valueOf(pinpointLocations.size());
			// �ǂݍ���JSON�̒������l���擾
			for (int i = 0; i < pinpointLocations.size(); i++) {
				placeName = pinpointLocations.get(i).getName();
				json_WetherPlaceNameList.add(placeName);// �s�撬�������X�g��
				log.output("jsonName:" + i, placeName);
			}
			log.output("tag", "jsonSize:" + jsonSize);
		} catch (Exception e) {
			log.output("Error", "");
		}
		return json_WetherPlaceNameList;
	}

	/**
	 * @version 1.00 02 July 2013
	 * @author NaokiKamimura JSON�t�@�C������͂���
	 * @return �V�C�\�񃊃��N�A�h���X�̓�����List
	 * @param �R���e�L�X�g
	 */
	public List<String> wetherLinkList(Context context) {
		LogUtil log = new LogUtil();
		List<String> json_WetherLinkList = new ArrayList<String>();
		try {
			List<PinpointLocations> pinpointLocations = scanJSON(context);
			String wetherLink = "";// �V�C�\����URL�̎擾
			// �ǂݍ���JSON�̒������l���擾
			for (int i = 0; i < pinpointLocations.size(); i++) {
				wetherLink = pinpointLocations.get(i).getLink();
				json_WetherLinkList.add(wetherLink);// �����N�����X�g��
			}
		} catch (Exception e) {
			log.output("Error", "���炩�̃G���[");
		}
		return json_WetherLinkList;
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura
	 * @return JSON���O���X�g�f�[�^�̓�����adapter
	 * @param �R���e�L�X�g
	 */
	public ArrayAdapter<String> placeNameAdapter(Context context) {
		List<String> adapterList = new ArrayList<String>();
		adapterList = wetherPlaceNameList(context);
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
