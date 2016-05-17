/*
 * @(#)UserInfoDaoTest.java $version 2013-5-14
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.dao;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import com.ufnet.ws.test.AbstractDaoTest;

/**
 * ufnet-ws
 * com.ufnet.ws.dao.UserInfoDaoTest.java
 * @author st13902
 * @date 2013-5-14
 */
@Slf4j
public class UserInfoDaoTest extends AbstractDaoTest {

	@Resource
	private UserInfoDao userInfoDao;

	@Test
	public void selectList() {
		int size = userInfoDao.selectCountByLoginName("test");
		log.debug(String.valueOf(size));
	}
}
