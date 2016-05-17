/*
 * @(#)GetUserIdFromTicketRequest.java $version 2013-5-16
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ufnet.ws.SimpleConstants;

/**
 * ufnet-ws
 * com.ufnet.ws.server.model.GetUserIdFromTicketRequest.java
 * @author st13902
 * @date 2013-5-16
 */
@XmlRootElement(name = "getUserIdFromTicket", namespace = SimpleConstants.NAMESPACE)
public class GetUserIdFromTicketRequest {
	private String userIp;
	private String onlineTime;

	@XmlElement(name = "userip")
	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@XmlElement(name = "onlinetime")
	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

}
