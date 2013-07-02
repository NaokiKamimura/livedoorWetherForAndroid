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
	 * @param コンテキスト,通知する文字列
	 *            トースト通知を表示
	 */
	public void makeToast(Context context,String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT)
				.show();
	}

}
