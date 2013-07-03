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
	private String jsonData;// ��͂��ꂽJSON�f�[�^���i�[

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura HTTP�ʐM���J�n����
	 * @param �R���e�L�X�g
	 *            ,URL�A�h���X��
	 */
	public void connection(Context context, String urlAddress) {
		LogUtil log = new LogUtil();
		HttpClient httpClient = new DefaultHttpClient();
		log.output("URL:", urlAddress);// URL�̃��O���o��
		try {
			URI uri = new URI(urlAddress);// URL�I�u�W�F�N�g�̐���
			HttpGet request = new HttpGet(uri);
			HttpResponse response = httpClient.execute(request);// ���X�|���X�Ƃ��đ��M
			String statusCode = conditionCheck(response);// �X�e�[�^�X�R�[�h�̎擾
			// �X�e�[�^�X�R�[�h�ɂ���ĐU�蕪��
			switch (response.getStatusLine().getStatusCode()) {
			// �ڑ�OK
			case HttpStatus.SC_OK:
				log.output("status", "status code = " + statusCode);
				break;
			// 404
			case HttpStatus.SC_FORBIDDEN:
				log.output("status", "status code = " + statusCode);
				break;
			// �ǂ�ɂ����Ă͂܂�Ȃ��ꍇ
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
			log.output("Exception", "���炩�̗�O���������܂���:" + e.toString());
		}

	}

	/**
	 * @version 1.00 03 July 2013
	 * @author NaokiKamimura
	 * @param HttpResponse
	 * @return String�^JSON�f�[�^
	 */
	public String convertJSONData(HttpResponse httpResponse) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			httpResponse.getEntity().writeTo(outputStream);// ���X�|���X�̏�������
		} catch (IOException e) {
			Log.v("IOException:", e.toString());
		}
		String jsonData = outputStream.toString();// ���X�|���X�f�[�^�̃Z�b�g
		return jsonData;
	}

	/**
	 * @version 1.00 02 July 2013
	 * @author NaokiKamimura HTTP�ʐM���J�n����
	 * @param URL�A�h���X��
	 *            �I�[�o�[���[�h
	 */
	public void connection(String urlAddress) {
		LogUtil log = new LogUtil();
		HttpClient httpClient = new DefaultHttpClient();
		log.output("URL:", urlAddress);// URL�̃��O���o��
		try {
			URI uri = new URI(urlAddress);// URL�I�u�W�F�N�g�̐���
			HttpGet httpRequest = new HttpGet(uri);
			HttpResponse httpResponse = httpClient.execute(httpRequest);// ���X�|���X�Ƃ��đ��M
			String httpStatusCode = conditionCheck(httpResponse);// �X�e�[�^�X�R�[�h�̎擾
			// �X�e�[�^�X�R�[�h�ɂ���ĐU�蕪��
			switch (httpResponse.getStatusLine().getStatusCode()) {
			// �ڑ�OK
			case HttpStatus.SC_OK:
				// ����OK�Ȃ�AhttpResponse�̒l��String�ɂ��郁�\�b�h�֓n��
				jsonData = convertJSONData(httpResponse);// �t�B�[���h�ϐ��փ��X�|���X�f�[�^�̃Z�b�g
				log.output("status", "status code = " + httpStatusCode);
				break;
			// 404
			case HttpStatus.SC_FORBIDDEN:
				log.output("status", "status code = " + httpStatusCode);
				break;
			// �ǂ�ɂ����Ă͂܂�Ȃ��ꍇ
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
			log.output("Exception", "���炩�̗�O���������܂���:" + e.toString());
		} finally {
			disconnection(httpClient);// �ʐM��ؒf
		}
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura HTTP�ʐM��ؒf����
	 * @param httpClient
	 */
	public void disconnection(HttpClient httpClient) {
		httpClient.getConnectionManager().shutdown();
	}

	/**
	 * @version 1.00 27 June 2013
	 * @author NaokiKamimura HTTP�ʐM�̏�Ԃ��m�F����
	 * @param HttpResponse
	 * @return String�^�̃X�e�[�^�X�R�[�h
	 */
	public String conditionCheck(HttpResponse response) {
		int httpStatusCode = response.getStatusLine().getStatusCode();
		String st_StatusCode = String.valueOf(httpStatusCode);// �X�e�[�^�X�R�[�h�̎擾
		return st_StatusCode;

	}
}
