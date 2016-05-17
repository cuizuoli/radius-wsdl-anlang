/*
 * @(#)CardDelUserResponse.java $version 2013-5-16
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * nhn ufnet-ws
 * com.ufnet.ws.server.model.CardDelUserResponse.java
 * @author st13902
 * @date 2013-5-16
 */
@XmlRootElement(name = "CardDelUserResponse")
public class CardDelUserResponse {
	private int returnCode;

	@XmlElement(name = "CardDelUserReturn")
	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
}
