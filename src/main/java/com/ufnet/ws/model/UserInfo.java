/*
 * @(#)UserInfo.java $version 2013-5-14
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.model;

import java.util.Date;

import lombok.Data;

/**
 * nhn ufnet-ws
 * com.ufnet.ws.model.UserInfo.java
 * @author st13902
 * @date 2013-5-14
 */
@Data
public class UserInfo {
	private int accountId;
	private String userName;
	private int communityId;
	private String aptNo;
	private String loginName;
	private String password;
	private int accounttypeId;
	private int authTypeId;
	private int authMove;
	private Date dateOfEnd;
	private float availableAmount;
	private int availableTime;
	private int availableFlow;
	private int bandwidth;
	private int usedAmount;
	private int usedFlow;
	private int usedTime;
	private int maxNetTime;
	private int autoDownInterval;
	private int isPopup;
	private String needRealIp;
	private int isLog;
	private int currentState;
	private Date dateOfKaitong;
	private Date lastLoginTime;
	private int loginSessionId;
	private int maxOnlineUsers;
	private int sex;
	private Date birthday;
	private String phone;
	private String mobilePhone;
	private String email;
	private int educationLevelId;
	private int vocationId;
	private int userClass;
	private int vip;
	private int registerType;
	private int certificateTypeId;
	private String certificateNo;
	private Date dateOfApply;
	private Date dateOfOpen;
	private Date dateOfSignContract;
	private Date dateOfEndContract;
	private int isInstall;
	private int isWiring;
	private int vlanId;
	private String switchId;
	private int switchPort;
	private String sales;
	private int adminId;
	private String buildingNo;
	private int reasonOfNoInstall;
	private int complexIndex;
	private int payAccountId;
	private int independent;
	private String remark;
	private int denyAgent;
	private int loginType;
	private int openAdmin;
	private int secondLine;
}
