/*
 * @(#)UserInfoDao.java $version 2013-5-14
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ufnet.ws.model.PredictAccountType;
import com.ufnet.ws.model.UseHistory;
import com.ufnet.ws.model.UserInfo;
import com.ufnet.ws.model.UserIpMac;

/**
 * nhn ufnet-ws
 * com.ufnet.ws.dao.UserInfoDao.java
 * @author st13902
 * @date 2013-5-14
 */
@Repository
public interface UserInfoDao {
	int selectCountByLoginName(@Param("loginName") String loginName);

	UserInfo selectOneByLoginName(@Param("loginName") String loginName);

	int selectAccountId();

	int insert(UserInfo userInfo);

	int update(UserInfo userInfo);

	int delete(String loginName);

	String selectUserIdFromOnline(@Param("userIp") String userIp);

	String selectUserIdFromTicket(UseHistory useHistory);

	PredictAccountType selectPrePolicyList(@Param("loginName") String loginName);

	int updatePrePolicy(PredictAccountType predictAccountType);

	int updateOffLineUser(@Param("loginName") String loginName, @Param("ipAddress") String ipAddress,
			@Param("macAddress") String macAddress);

	int insertUserIpMac(UserIpMac userIpMac);

	void deleteUserIpMac(int accountId);
}
