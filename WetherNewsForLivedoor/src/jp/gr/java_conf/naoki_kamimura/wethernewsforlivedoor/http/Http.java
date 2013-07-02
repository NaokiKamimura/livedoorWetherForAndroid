/*
 * @(#)Http.java        1.00 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */

package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.json.Json;
import jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.util.LogUtil;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;

public class Http {

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura HTTP通信を開始する
	 * @param コンテキスト
	 *            ,アドレス名
	 */
	public void connection(Context context, String address) {
		LogUtil log = new LogUtil();
		HttpClient httpClient = new DefaultHttpClient();
		log.output("URL:", address);// URLのログを出す
		try {
			URI uri = new URI(address);// URLオブジェクトの生成
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
	 * @version 1.00 02 July 2013
	 * @author NaokiKamimura HTTP通信を開始する
	 * @param アドレス名
	 *            オーバーロード
	 */
	public void connection(String address) {
		LogUtil log = new LogUtil();
		Json json = new Json();
		HttpClient httpClient = new DefaultHttpClient();
		log.output("URL:", address);// URLのログを出す
		try {
			URI uri = new URI(address);// URLオブジェクトの生成
			HttpGet request = new HttpGet(uri);
			HttpResponse response = httpClient.execute(request);// レスポンスとして送信
			String statusCode = conditionCheck(response);// ステータスコードの取得
			// ステータスコードによって振り分け
			switch (response.getStatusLine().getStatusCode()) {
			// 接続OK
			case HttpStatus.SC_OK:
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				response.getEntity().writeTo(outputStream);// レスポンスの情報を書込
				//TODO dataを解析メソッドへ受け渡す方法を確立する
				String data = outputStream.toString();// レスポンスデータのセット
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
		int status = response.getStatusLine().getStatusCode();
		String statusCode = String.valueOf(status);// ステータスコードの取得
		return statusCode;

	}
}
