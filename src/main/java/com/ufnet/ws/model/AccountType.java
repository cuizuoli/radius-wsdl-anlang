/*
 * @(#)AccountType.java $version 2013-5-15
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.model;

import lombok.Data;

/**
 * ufnet-ws
 * com.ufnet.ws.model.AccountType.java
 * @author st13902
 * @date 2013-5-15
 */
@Data
public class AccountType {
	private int id;
	private int typeId;
	private int payType;
	private int maxBand;
	private int pauseRuleId;
}
