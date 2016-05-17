/*
 * @(#)GetPrePolicyListResponse.java $version 2013-5-17
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ufnet-ws
 * com.ufnet.ws.server.model.GetPrePolicyListResponse.java
 * @author st13902
 * @date 2013-5-17
 */
@XmlRootElement(name = "getPrePolicyListResponse")
public class GetPrePolicyListResponse {
	private String returnCode;

	@XmlElement(name = "ReturnCode")
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
}
