/*
 * @(#)Http.java        1.00 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */

package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.util.LogUtil;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;

public class Http {
	private String jsonData;// 解析されたJSONデータを格納

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura HTTP通信を開始する
	 * @param コンテキスト
	 *            ,URLアドレス名
	 */
	public void connection(Context context, String urlAddress) {
		LogUtil log = new LogUtil();
		HttpClient httpClient = new DefaultHttpClient();
		log.output("URL:", urlAddress);// URLのログを出す
		try {
			URI uri = new URI(urlAddress);// URLオブジェクトの生成
			HttpGet request = new HttpGet(uri);
			HttpResponse response = httpClient.execute(request);// レスポンスとして送信
			String statusCode = conditionCheck(response);// ステータスコードの取得
			// ステータスコードによって振り分け
			switch (response.getStatusLine().getStatusCode()) {
			// 接続OK
			case HttpStatus.SC_OK:
				log.output("status", "status code = " + statusCode);
				break;
			// 404
			case HttpStatus.SC_FORBIDDEN:
				log.output("status", "status code = " + statusCode);
				break;
			// どれにも当てはまらない場合
			default:
				log.output("status", "status code = " + statusCode);
				break;
			}

		} catch (URISyntaxException e) {
			log.output("URISyntaxException", e.toString());
		} catch (ClientProtocolException e) {
			log.output("ClientProtocolException", e.toString());
		} catch (IOException e) {
			log.output("IOException", e.toString());
		} catch (Exception e) {
			log.output("Exception", "何らかの例外が発生しました:" + e.toString());
		}

	}

	/**
	 * @version 1.00 03 July 2013
	 * @author NaokiKamimura
	 * @param HttpResponse
	 * @return String型JSONデータ
	 */
	public String convertJSONData(HttpResponse httpResponse) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			httpResponse.getEntity().writeTo(outputStream);// レスポンスの情報を書込
		} catch (IOException e) {
			Log.v("IOException:", e.toString());
		}
		String jsonData = outputStream.toString();// レスポンスデータのセット
		return jsonData;
	}

	/**
	 * @version 1.00 02 July 2013
	 * @author NaokiKamimura HTTP通信を開始する
	 * @param URLアドレス名
	 *            オーバーロード
	 */
	public void connection(String urlAddress) {
		LogUtil log = new LogUtil();
		HttpClient httpClient = new DefaultHttpClient();
		log.output("URL:", urlAddress);// URLのログを出す
		try {
			URI uri = new URI(urlAddress);// URLオブジェクトの生成
			HttpGet httpRequest = new HttpGet(uri);
			HttpResponse httpResponse = httpClient.execute(httpRequest);// レスポンスとして送信
			String httpStatusCode = conditionCheck(httpResponse);// ステータスコードの取得
			// ステータスコードによって振り分け
			switch (httpResponse.getStatusLine().getStatusCode()) {
			// 接続OK
			case HttpStatus.SC_OK:
				// もしOKなら、httpResponseの値をStringにするメソッドへ渡す
				jsonData = convertJSONData(httpResponse);// フィールド変数へレスポンスデータのセット
				log.output("status", "status code = " + httpStatusCode);
				break;
			// 404
			case HttpStatus.SC_FORBIDDEN:
				log.output("status", "status code = " + httpStatusCode);
				break;
			// どれにも当てはまらない場合
			default:
				log.output("status", "status code = " + httpStatusCode);
				break;
			}
		} catch (URISyntaxException e) {
			log.output("URISyntaxException", e.toString());
		} catch (ClientProtocolException e) {
			log.output("ClientProtocolException", e.toString());
		} catch (IOException e) {
			log.output("IOException", e.toString());
		} catch (Exception e) {
			log.output("Exception", "何らかの例外が発生しました:" + e.toString());
		} finally {
			disconnection(httpClient);// 通信を切断
		}
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura HTTP通信を切断する
	 * @param httpClient
	 */
	public void disconnection(HttpClient httpClient) {
		httpClient.getConnectionManager().shutdown();
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura HTTP通信の状態を確認する
	 * @param HttpResponse
	 * @return String型のステータスコード
	 */
	public String conditionCheck(HttpResponse response) {
		int httpStatusCode = response.getStatusLine().getStatusCode();
		String st_StatusCode = String.valueOf(httpStatusCode);// ステータスコードの取得
		return st_StatusCode;

	}
}
