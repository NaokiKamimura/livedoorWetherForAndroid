/*
 * @(#)Http.java        0.50 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.gr.java_conf.naoki_kamimura.json.PinpointLocations;
import jp.gr.java_conf.naoki_kamimura.json.V1;
import jp.gr.java_conf.naoki_kamimura.util.LogUtil;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		readJson();// JSON���
		// TODO ���X�g�r���[���Ăт���
		listView();
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura ���X�g��\��
	 */
	private void listView() {
		// ��ʕ\���pListView
		ListView listView = (ListView) findViewById(R.id.listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		adapter = getJSONAdapter();// JSON�f�[�^�̊i�[���ꂽadapter��Ⴄ
		listView.setAdapter(adapter);// adapter�̃Z�b�g
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListView list = (ListView) parent;
				String msg = "index:" + position;
				makeToast(msg);
				// (�e�X�g�p)�^�b�`���ꂽ�ꏊ�̃A�C�e�����擾����
				String item = "item:"
						+ (String) list.getItemAtPosition(position);
				makeToast(item);
			}
		});

	}

	/*
	 * public ArrayAdapter<String> screenDisplay_List(List<String> list) {
	 * List<String> arrayList = new ArrayList<String>(); // TODO
	 * arrayList��JSON��List������
	 * 
	 * // JSON�̃f�[�^��adapter�֓���� ArrayAdapter<String> adapter = new
	 * ArrayAdapter<String>(this, android.R.layout.simple_list_item_1); String
	 * inArrayListText = ""; // arrayList�̗v�f������adapter�֑}�� for (int i = 0; i <
	 * arrayList.size(); i++) { inArrayListText = arrayList.get(i);
	 * adapter.add(inArrayListText); } return adapter; }
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura �g�[�X�g�ʒm��\������
	 */
	public void makeToast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
				.show();
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura ���X�g���X�V����{�^���̏���
	 */
	private void update() {
		// TODO�@���X�g���e���X�V���鏈��
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura ���X�g���X�V����{�^���̏���
	 * @return JSON��������i�[����List
	 */
	private List<PinpointLocations> jsonText() {
		LogUtil log = new LogUtil();
		List<PinpointLocations> pinpointLocations = null;
		V1 v1;// JSON�̃}�b�s���O�����I�u�W�F�N�g�̂���N���X
		try {
			Gson gson = new Gson();
			// �n���ꂽ�R���e�L�X�g����
			log.output("logFlag", "0");
			// AssetManager�Ńt�@�C���p�X���w�肷��
			AssetManager assetManager = getResources().getAssets();
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

	/**
	 * @version 1.00 25 June 2013
	 * @author NaokiKamimura JSON�t�@�C������͂���
	 * @return �s�撬���̓�����List
	 */
	public List<String> readJson() {
		LogUtil log = new LogUtil();
		List<String> nameList = new ArrayList<String>();
		List<String> linkList = new ArrayList<String>();
		try {
			List<PinpointLocations> pinpointLocations = jsonText();
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
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura
	 * @return JSON�f�[�^�̓�����adapter
	 */
	private ArrayAdapter<String> getJSONAdapter() {
		List<String> adapterList = new ArrayList<String>();
		adapterList = readJson();
		// JSON�̃f�[�^��adapter�֓����
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
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
