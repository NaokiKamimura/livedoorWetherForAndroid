package jp.gr.java_conf.naoki_kamimura.http;


public class ConnectionThread implements Runnable{
	/**
	 * @version 1.00 01 July 2013
	 * @author NaokiKamimura �ʐM��ʃX���b�h�ōs��
	 */
	@Override
	public void run() {
		Http http = new Http();
		//�b���URL���ł�
		http.connection("http://weather.livedoor.com/forecast/webservice/json/v1?city=130010");
	}

}
