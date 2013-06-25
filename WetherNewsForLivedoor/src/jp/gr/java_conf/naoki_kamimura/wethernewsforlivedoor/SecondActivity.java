package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor;

import android.app.Activity;
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

		// ��ʕ\�����X�g
		ListView list = (ListView) findViewById(R.id.listview);
		// �_�~�[�f�[�^
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		for (int i = 0; i < 20; i++) {
			adapter.add("Apple");
		}
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListView list = (ListView) parent;
				String msg = "index:" + position;
				makeToast(msg);
				//�^�b�`���ꂽ�ꏊ�̃A�C�e�����擾����
				String item = (String) list.getItemAtPosition(position);
				makeToast(item);

			}
		});
		//�_�~�[�f�[�^�I���

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	public void makeToast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
				.show();
	}

}
