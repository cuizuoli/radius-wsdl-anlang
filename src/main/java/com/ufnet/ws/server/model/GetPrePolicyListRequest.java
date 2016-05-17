/*
 * @(#)GetPrePolicyListRequest.java $version 2013-5-17
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
 * com.ufnet.ws.server.model.GetPrePolicyListRequest.java
 * @author st13902
 * @date 2013-5-17
 */
@XmlRootElement(name = "getPrePolicyList", namespace = SimpleConstants.NAMESPACE)
public class GetPrePolicyListRequest {
	private String userId;

	@XmlElement(name = "userID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
