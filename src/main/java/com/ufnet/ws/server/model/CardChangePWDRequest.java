/*
 * @(#)CardChangePWDRequest.java $version 2013-5-16
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
 * com.ufnet.ws.server.model.CardChangePWDRequest.java
 * @author st13902
 * @date 2013-5-16
 */
@XmlRootElement(name = "CardChangePWD2", namespace = SimpleConstants.NAMESPACE)
public class CardChangePWDRequest {
	private String userId;
	private String destPwd;

	@XmlElement(name = "userid")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@XmlElement(name = "destpwd")
	public String getDestPwd() {
		return destPwd;
	}

	public void setDestPwd(String destPwd) {
		this.destPwd = destPwd;
	}
}
