/*
 * @(#)Http.java        0.50 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */

package jp.gr.java_conf.naoki_kamimura.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Http {

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura HTTP�ʐM���J�n����
	 */
	public void connection(String address) {
		LogUtil log = new LogUtil();
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			URI uri = new URI(address);
			HttpGet request = new HttpGet(uri);
			HttpResponse response = httpClient.execute(request);
		} catch (URISyntaxException e) {
			log.output("URISyntaxException", "URL�Ƃ��ĉ�͏o���܂���ł���");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			log.output("ClientProtocolException", "URL�Ƃ��ĉ�͏o���܂���ł���");
			e.printStackTrace();
		} catch (IOException e) {
			log.output("IOException", "URL�Ƃ��ĉ�͏o���܂���ł���");
			e.printStackTrace();
		}

	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura HTTP�ʐM��ؒf����
	 */
	public void disconnection() {

	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura HTTP�ʐM�̏�Ԃ��m�F����
	 */
	public void conditionCheck() {

	}

}
