/*
 * @(#)UseHistory.java $version 2013-5-16
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.model;

import java.util.Date;

import lombok.Data;

/**
 * ufnet-ws
 * com.ufnet.ws.model.UseHistory.java
 * @author st13902
 * @date 2013-5-16
 */
@Data
public class UseHistory {
	private String ipAddress;
	private Date onlineTime;
}
