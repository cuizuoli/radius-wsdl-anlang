/*
 * @(#)OffLineUserRequest.java $version 2013-5-17
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
 * com.ufnet.ws.server.model.OffLineUserRequest.java
 * @author st13902
 * @date 2013-5-17
 */
@XmlRootElement(name = "offLineUser", namespace = SimpleConstants.NAMESPACE)
public class OffLineUserRequest {
	private String userIp;
	private String userMac;
	private String userId;

	@XmlElement(name = "userip")
	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@XmlElement(name = "usermac")
	public String getUserMac() {
		return userMac;
	}

	public void setUserMac(String userMac) {
		this.userMac = userMac;
	}

	@XmlElement(name = "userid")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
