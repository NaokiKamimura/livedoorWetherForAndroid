/*
 * @(#)Http.java        0.50 13/06/25
 * Copyright(c) 2012-2013 NaokiKamimura,All rights reserved
 */
package jp.gr.java_conf.naoki_kamimura.wethernewsforlivedoor.json;

import java.util.List;

public class V1 {
	public List<PinpointLocations> getPinpointLocations() {
		return pinpointLocations;
	}

	public void setPinpointLocations(List<PinpointLocations> pinpointLocations) {
		this.pinpointLocations = pinpointLocations;
	}

	private List<PinpointLocations> pinpointLocations;
}
