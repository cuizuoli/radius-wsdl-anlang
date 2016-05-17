/*
 * @(#)AbstractDaoTest.java $version 2013-5-14
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * ufnet-ws
 * com.ufnet.ws.test.AbstractDaoTest.java
 * @author st13902
 * @date 2013-5-14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context-datasource.xml", "classpath:context-mybatis.xml"})
@TransactionConfiguration
@Transactional
public abstract class AbstractDaoTest {

}
