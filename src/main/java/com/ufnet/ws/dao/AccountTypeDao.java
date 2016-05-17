/*
 * @(#)AccountTypeDao.java $version 2013-5-15
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ufnet.ws.model.AccountType;
import com.ufnet.ws.model.GroupMap;
import com.ufnet.ws.model.PauseRule;

/**
 * ufnet-ws
 * com.ufnet.ws.dao.AccountTypeDao.java
 * @author st13902
 * @date 2013-5-15
 */
@Repository
public interface AccountTypeDao {
	AccountType selectOne(@Param("id") int id);

	PauseRule selectOnePauseRule(@Param("pauseRuleId") int pauseRuleId);

	GroupMap selectGroupMap(GroupMap data);
}
