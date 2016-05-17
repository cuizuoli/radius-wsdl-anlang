/*
 * @(#)UserIpMac.java $version 2013-5-20
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.model;

import lombok.Data;

/**
 * ufnet-ws
 * com.ufnet.ws.model.UserIpMac.java
 * @author st13902
 * @date 2013-5-20
 */
@Data
public class UserIpMac {
	private int accountId;
	private String ipAddress;
	private String mac;
	private int gatewayId;
}
