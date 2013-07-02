/*
 * @(#)Http.java        1.00 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.activity;

import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.R;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.http.ConnectionThread;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.json.Json;
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
import android.widget.TextView;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Json json = new Json();
		Context context = getApplicationContext();
		Button restart = (Button) findViewById(R.id.restart_button);
		json.readNameList(context);// JSON���
		listView();// ListView��\��

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
		nameListAdapter = json.getNameListAdapter(con);// JSON���O�f�[�^�̊i�[���ꂽadapter��Ⴄ
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
				String item = (String) list.getItemAtPosition(position);//�^�b�`�����ꏊ�̖��O���擾
				toast.makeToast(con, item);
				// ���X�g�̉��������ڂƈʒu�𑗂�
				List<String> linkList = new ArrayList<String>();// �����N�A�h���X���i�[����ArrayList
				linkList = json.readLinkList(context);// �����N�A�h���X���i�[
				String linkPosition = linkList.get(position);// ������index�ɑΉ�����A�h���X
				intent.putExtra("name", item);
				intent.putExtra("index", linkPosition);
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

}
