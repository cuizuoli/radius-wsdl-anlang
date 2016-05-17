/*
 * @(#)ModifyPrePolicyRequest.java $version 2013-5-17
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
 * com.ufnet.ws.server.model.ModifyPrePolicyRequest.java
 * @author st13902
 * @date 2013-5-17
 */
@XmlRootElement(name = "modifyPrePolicy", namespace = SimpleConstants.NAMESPACE)
public class ModifyPrePolicyRequest {
	private String userId;
	private int userGroupId;
	private String prDate;

	@XmlElement(name = "userID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@XmlElement(name = "userGroupID")
	public int getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}

	@XmlElement(name = "prDate")
	public String getPrDate() {
		return prDate;
	}

	public void setPrDate(String prDate) {
		this.prDate = prDate;
	}
}
