/*
 * @(#)AccountTypeDaoTest.java $version 2013-5-15
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.dao;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import com.ufnet.ws.model.AccountType;
import com.ufnet.ws.test.AbstractDaoTest;

/**
 * nhn ufnet-ws
 * com.ufnet.ws.dao.AccountTypeDaoTest.java
 * @author st13902
 * @date 2013-5-15
 */
@Slf4j
public class AccountTypeDaoTest extends AbstractDaoTest {

	@Resource
	private AccountTypeDao accountTypeDao;

	@Test
	public void selectOne() {
		int id = 1;
		AccountType accountType = accountTypeDao.selectOne(id);
		log.debug(accountType.toString());
	}

}
