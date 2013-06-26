/*
 * @(#)Http.java        0.50 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.util;

import android.util.Log;

/**
 * @version 1.00 25 June 2013
 * @author N.Kamimura
 */
public class LogUtil {
	/*
	 * @author NaokiKamimura
	 * @param タグ,ログに表示する内容
	 * タグと内容を送り、ログに表示
	 */
	public void output(String tag, String log) {
		Log.v(tag, log);
	}

}
