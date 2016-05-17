/*
 * @(#)WebServiceClient.java $version 2013-5-14
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.client;

import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * nhn ufnet-ws
 * com.ufnet.ws.client.WebServiceClient.java
 * @author st13902
 * @date 2013-5-14
 */

public class WebServiceClient {

	private static final String CARD_NEW_USER = "<CardNewUser4Request xmlns=\"http://amtium.com\">"
		+ "<userid>TestUser015</userid>"
		+ "<groupid>10</groupid>"
		+ "<teamid>11</teamid>"
		+ "<pwd>654123</pwd>"
		+ "<username>崔作利</username>"
		+ "<phone>0411-88884444</phone>"
		+ "<address>软件园</address>"
		+ "<limitdate_end>2013-10-31</limitdate_end>"
		+ "<userstate>1</userstate>"
		+ "<opendate>2013-05-01</opendate>"
		+ "<notes>备注项目</notes>"
		+ "<certNum>210112198011253145</certNum>"
		+ "</CardNewUser4Request>";

	private static final String CARD_DEL_USER = "<CardDelUserRequest xmlns=\"http://amtium.com\">"
		+ "<userid>TestUser014</userid>"
		+ "</CardDelUserRequest>";

	private static final String RESUME_USER_INFO = "<ResumeUserInfoRequest xmlns=\"http://amtium.com\">"
		+ "<userid>TestUser001</userid>"
		+ "</ResumeUserInfoRequest>";

	private static final String CARD_CHANGE_PWD = "<CardChangePWD2Request xmlns=\"http://amtium.com\">"
		+ "<userid>TestUser015</userid>"
		+ "<destpwd>666333</destpwd>"
		+ "</CardChangePWD2Request>";

	private static final String GET_USER_PASSWORD = "<getUserPassWordRequest xmlns=\"http://amtium.com\">"
		+ "<userid>TestUser015</userid>"
		+ "</getUserPassWordRequest>";

	private static final String GET_USER_LIMIT_END_DATE = "<GetUserLimitEndDateRequest xmlns=\"http://amtium.com\">"
		+ "<userid>TestUser001</userid>"
		+ "</GetUserLimitEndDateRequest>";

	private static final String MODIFY_USER_INFO = "<ModifyUserInfo3Request xmlns=\"http://amtium.com\">"
		+ "<userid>TestUser015</userid>"
		+ "<groupid>12</groupid>"
		+ "<teamid></teamid>"
		+ "<pwd>456789</pwd>"
		+ "<username>张三李四</username>"
		+ "<phone>144789654</phone>"
		+ "<address></address>"
		+ "<limitdate_end>2013-11-01</limitdate_end>"
		+ "<userstate>1</userstate>"
		+ "<opendate>2009-01-01</opendate>"
		+ "<notes>Hello World!</notes>"
		+ "<remain_fee>11.11</remain_fee>"
		+ "<certNum></certNum>"
		+ "</ModifyUserInfo3Request>";

	private static final String GET_USER_INFO = "<GetUserInfoRequest xmlns=\"http://amtium.com\">"
		+ "<userid>TestUser001</userid>"
		+ "</GetUserInfoRequest>";

	private static final String GET_USER_ID_FROM_ONLINE = "<GetUserIdFromOnlineRequest xmlns=\"http://amtium.com\">"
		+ "<Userip>192.168.1.102</Userip>"
		+ "</GetUserIdFromOnlineRequest>";

	private static final String OFFLINE_USER = "<OffLineUserRequest xmlns=\"http://amtium.com\">"
		+ "<userip>192.168.1.102</userip>"
		+ "<usermac>10-BF-48-7A-14-DD</usermac>"
		+ "<userid>TestUser001</userid>"
		+ "</OffLineUserRequest>";

	private static final String GET_USER_ID_FROM_TICKER = "<GetUserIdFromTicketRequest xmlns=\"http://amtium.com\">"
		+ "<userip>192.168.1.102</userip>"
		+ "<onlinetime>2013-05-18 11:05:30</onlinetime>"
		+ "</GetUserIdFromTicketRequest>";

	private static final String GET_PRE_POLICY_LIST = "<GetPrePolicyListRequest xmlns=\"http://amtium.com\">"
		+ "<userID>TestUser001</userID>"
		+ "</GetPrePolicyListRequest>";

	private static final String MODIFY_PRE_POLICY = "<ModifyPrePolicyRequest xmlns=\"http://amtium.com\">"
		+ "<userID>TestUser001</userID>"
		+ "<userGroupID>12</userGroupID>"
		+ "<prDate>2013-05-21</prDate>"
		+ "</ModifyPrePolicyRequest>";

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:context-client.xml");
		WebServiceTemplate webServiceTemplate = applicationContext.getBean(WebServiceTemplate.class);
		StreamSource source = new StreamSource(new StringReader(CARD_NEW_USER));
		StreamResult result = new StreamResult(System.out);
		webServiceTemplate.sendSourceAndReceiveToResult(source, result);
	}

}
