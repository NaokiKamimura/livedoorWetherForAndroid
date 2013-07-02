/*
 * @(#)Http.java        1.00 13/07/01
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */

package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	
	/**
	 * @version 1.00 1 July 2013
	 * @author N.Kamimura
	 * @param �R���e�L�X�g,�ʒm���镶����
	 *            �g�[�X�g�ʒm��\��
	 */
	public void makeToast(Context context,String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT)
				.show();
	}

}
