/*
 * @(#)ModifyUserInfoRequest.java $version 2013-5-16
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
 * com.ufnet.ws.server.model.ModifyUserInfoRequest.java
 * @author st13902
 * @date 2013-5-16
 */
@XmlRootElement(name = "ModifyUserInfo", namespace = SimpleConstants.NAMESPACE)
public class ModifyUserInfoRequest {
	private String userId;
	private int groupId;
	private int teamId;
	private String pwd;
	private String username;
	private String phone;
	private String address;
	private String limitDateEnd;
	private String userState;
	private String openDate;
	private String notes;
	private float remainFee;
	private String certNum;

	@XmlElement(name = "userid")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@XmlElement(name = "groupid")
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@XmlElement(name = "teamid")
	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	@XmlElement(name = "pwd")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@XmlElement(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlElement(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@XmlElement(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement(name = "limitdate_end")
	public String getLimitDateEnd() {
		return limitDateEnd;
	}

	public void setLimitDateEnd(String limitDateEnd) {
		this.limitDateEnd = limitDateEnd;
	}

	@XmlElement(name = "userstate")
	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	@XmlElement(name = "opendate")
	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	@XmlElement(name = "notes")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@XmlElement(name = "remain_fee")
	public float getRemainFee() {
		return remainFee;
	}

	public void setRemainFee(float remainFee) {
		this.remainFee = remainFee;
	}

	@XmlElement(name = "certNum")
	public String getCertNum() {
		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
}
