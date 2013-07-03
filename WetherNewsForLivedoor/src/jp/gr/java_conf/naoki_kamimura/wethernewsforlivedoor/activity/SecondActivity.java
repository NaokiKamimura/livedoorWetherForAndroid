/*
 * @(#)Http.java        1.00 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.R;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.http.ConnectionThread;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.json.Json;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.json.PinpointLocations;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.json.V1;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.util.LogUtil;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.util.ToastUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Json json = new Json();
		Context context = getApplicationContext();
		Button restart = (Button) findViewById(R.id.restart_button);
		json.wetherPlaceNameList(context);// JSON���
		listView();// ListView��\��

		// �R�l�N�V�����̊J�n
		Thread thread = new Thread(new ConnectionThread());
		thread.start();

		// �X�V�{�^���̏���
		restart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// �R�l�N�V�����̊J�n
				Thread thread = new Thread(new ConnectionThread());
				thread.start();
			}
		});
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura ���X�g��\��
	 */
	private void listView() {
		// ��ʕ\���pListView
		Json json = new Json();
		ListView listView = (ListView) findViewById(R.id.listview);
		final ToastUtil toast = new ToastUtil();
		final Context con = getApplicationContext();
		ArrayAdapter<String> nameListAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		nameListAdapter = json.placeNameAdapter(con);// JSON���O�f�[�^�̊i�[���ꂽadapter��Ⴄ
		listView.setAdapter(nameListAdapter);// adapter�̃Z�b�g
		// ListView�̏���
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Json json = new Json();
				Context context = getApplicationContext();
				Intent intent = new Intent(SecondActivity.this,
						WetherView.class);
				ListView list = (ListView) parent;
				String msg = "index:" + position;
				toast.makeToast(con, msg);
				String item = (String) list.getItemAtPosition(position);// �^�b�`�����ꏊ�̖��O���擾
				toast.makeToast(con, item);
				// ���X�g�̉��������ڂƈʒu�𑗂�
				List<String> linkList = new ArrayList<String>();// �����N�A�h���X���i�[����ArrayList
				linkList = json.wetherLinkList(context);// �����N�A�h���X���i�[
				String linkPosition = linkList.get(position);// ������index�ɑΉ�����A�h���X
				intent.putExtra("name", item);// �n����n��
				intent.putExtra("index", linkPosition);// �����N�A�h���X��index�ԍ���n��
				startActivity(intent);
				finish();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura ���X�g���X�V����{�^���̏���
	 */
	private void update() {
		// TODO�@���X�g���e���X�V���鏈��
	}
	public List<String> wetherPlaceNameList(String jsonData) {
		LogUtil log = new LogUtil();
		ArrayList<String> json_WetherPlaceNameList = new ArrayList<String>();
		try {
			//TODO scanJSON��jsonData����ꂽ��
			List<PinpointLocations> pinpointLocations = scanJSON(jsonData);
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

}
