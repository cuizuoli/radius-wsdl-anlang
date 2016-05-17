/*
 * @(#)PredictAccountType.java $version 2013-5-17
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.model;

import java.util.Date;

import lombok.Data;

/**
 * ufnet-ws
 * com.ufnet.ws.model.PredictAccountType.java
 * @author st13902
 * @date 2013-5-17
 */
@Data
public class PredictAccountType {
	private String loginName;
	private int originTypeId;
	private String originTypeName;
	private int targetTypeId;
	private String targetTypeName;
	private Date implementDate;
}
