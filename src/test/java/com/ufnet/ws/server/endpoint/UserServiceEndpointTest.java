/*
 * @(#)UserServiceEndpointTest.java $version 2013-5-14
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.server.endpoint;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatchers;
import org.springframework.xml.transform.StringSource;

import com.ufnet.ws.test.AbstractTest;

/**
 * ufnet-ws
 * com.ufnet.ws.server.endpoint.UserServiceEndpointTest.java
 * @author st13902
 * @date 2013-5-14
 */
public class UserServiceEndpointTest extends AbstractTest {

	@Autowired
	private ApplicationContext applicationContext;

	private MockWebServiceClient mockClient;

	@Before
	public void init() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}

	@Test
	public void cardNewUser() {
		Source requestPayload = new StringSource(
			"<CardNewUser4Request xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser02</userid>"
				+ "<groupid>10</groupid>"
				+ "<teamid>11</teamid>"
				+ "<pwd>654123</pwd>"
				+ "<username>崔作利</username>"
				+ "<phone>0411-88888</phone>"
				+ "<address>软件园</address>"
				+ "<limitdate_end>2013-10-31</limitdate_end>"
				+ "<userstate>1</userstate>"
				+ "<opendate>2013-05-01</opendate>"
				+ "<notes>备注项目</notes>"
				+ "<certNum>210112198011</certNum>"
				+ "</CardNewUser4Request>");
		Source responsePayload = new StringSource(
			"<CardNewUser4Response>"
				+ "<CardNewUser4Return>1</CardNewUser4Return>"
				+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// user exists
		requestPayload = new StringSource(
			"<CardNewUser4Request xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser01</userid>"
				+ "<groupid>10</groupid>"
				+ "<teamid>11</teamid>"
				+ "<pwd>654123</pwd>"
				+ "<username>崔作利</username>"
				+ "<phone>0411-88888888</phone>"
				+ "<address>软件园</address>"
				+ "<limitdate_end>2013-10-31</limitdate_end>"
				+ "<userstate>1</userstate>"
				+ "<opendate>2013-05-01</opendate>"
				+ "<notes>备注项目</notes>"
				+ "<certNum>210112198011253145</certNum>"
				+ "</CardNewUser4Request>");
		responsePayload = new StringSource(
			"<CardNewUser4Response>"
				+ "<CardNewUser4Return>-1</CardNewUser4Return>"
				+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// groupid invalid
		requestPayload = new StringSource(
			"<CardNewUser4Request xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser03</userid>"
				+ "<groupid>13</groupid>"
				+ "<teamid>11</teamid>"
				+ "<pwd>654123</pwd>"
				+ "<username>崔作利</username>"
				+ "<phone>0411-88888888</phone>"
				+ "<address>软件园</address>"
				+ "<limitdate_end>2013-10-31</limitdate_end>"
				+ "<userstate>1</userstate>"
				+ "<opendate>2013-05-01</opendate>"
				+ "<notes>备注项目</notes>"
				+ "<certNum>210112198011253145</certNum>"
				+ "</CardNewUser4Request>");
		responsePayload = new StringSource(
			"<CardNewUser4Response>"
				+ "<CardNewUser4Return>0</CardNewUser4Return>"
				+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// limitdate_end invalid
		requestPayload = new StringSource(
			"<CardNewUser4Request xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser04</userid>"
				+ "<groupid>10</groupid>"
				+ "<teamid>11</teamid>"
				+ "<pwd>654123</pwd>"
				+ "<username>崔作利</username>"
				+ "<phone>0411-88888888</phone>"
				+ "<address>软件园</address>"
				+ "<limitdate_end>201310-31</limitdate_end>"
				+ "<userstate>1</userstate>"
				+ "<opendate>2013-05-01</opendate>"
				+ "<notes>备注项目</notes>"
				+ "<certNum>210112198011253145</certNum>"
				+ "</CardNewUser4Request>");
		responsePayload = new StringSource(
			"<CardNewUser4Response>"
				+ "<CardNewUser4Return>0</CardNewUser4Return>"
				+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// userstate invalid
		requestPayload = new StringSource(
			"<CardNewUser4Request xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser05</userid>"
				+ "<groupid>10</groupid>"
				+ "<teamid>11</teamid>"
				+ "<pwd>654123</pwd>"
				+ "<username>崔作利</username>"
				+ "<phone>0411-88888888</phone>"
				+ "<address>软件园</address>"
				+ "<limitdate_end>201310-31</limitdate_end>"
				+ "<userstate>8</userstate>"
				+ "<opendate>2013-05-01</opendate>"
				+ "<notes>备注项目</notes>"
				+ "<certNum>210112198011253145</certNum>"
				+ "</CardNewUser4Request>");
		responsePayload = new StringSource(
			"<CardNewUser4Response>"
				+ "<CardNewUser4Return>0</CardNewUser4Return>"
				+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// userstate invalid
		requestPayload = new StringSource(
			"<CardNewUser4Request xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser06</userid>"
				+ "<groupid>10</groupid>"
				+ "<teamid>11</teamid>"
				+ "<pwd>654123</pwd>"
				+ "<username>崔作利</username>"
				+ "<phone>0411-88888888</phone>"
				+ "<address>软件园</address>"
				+ "<limitdate_end>201310-31</limitdate_end>"
				+ "<userstate>1</userstate>"
				+ "<opendate>2013-05-01</opendate>"
				+ "<notes>备注项目</notes>"
				+ "<certNum>21011219853145</certNum>"
				+ "</CardNewUser4Request>");
		responsePayload = new StringSource(
			"<CardNewUser4Response>"
				+ "<CardNewUser4Return>0</CardNewUser4Return>"
				+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void cardDelUser() {
		Source requestPayload = new StringSource(
			"<CardDelUserRequest xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser01</userid>"
				+ "</CardDelUserRequest>");
		Source responsePayload = new StringSource(
			"<CardDelUserResponse>"
				+ "<CardDelUserReturn>0</CardDelUserReturn>"
				+ "</CardDelUserResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource(
			"<CardDelUserRequest xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser02</userid>"
				+ "</CardDelUserRequest>");
		responsePayload = new StringSource(
			"<CardDelUserResponse>"
				+ "<CardDelUserReturn>-1</CardDelUserReturn>"
				+ "</CardDelUserResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void cardChangePWD() {
		Source requestPayload = new StringSource(
			"<CardChangePWD2Request xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser01</userid>"
				+ "<destpwd>66666</destpwd>"
				+ "</CardChangePWD2Request>");
		Source responsePayload = new StringSource(
			"<CardChangePWD2Response>"
				+ "<CardChangePWD2Return>1</CardChangePWD2Return>"
				+ "</CardChangePWD2Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource(
			"<CardChangePWD2Request xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser02</userid>"
				+ "<destpwd>66666</destpwd>"
				+ "</CardChangePWD2Request>");
		responsePayload = new StringSource(
			"<CardChangePWD2Response>"
				+ "<CardChangePWD2Return>-1</CardChangePWD2Return>"
				+ "</CardChangePWD2Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getUserPassWord() {
		Source requestPayload = new StringSource(
			"<getUserPassWordRequest xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser01</userid>"
				+ "</getUserPassWordRequest>");
		Source responsePayload = new StringSource(
			"<getUserPassWordResponse>"
				+ "<getUserPassWordReturn>654123</getUserPassWordReturn>"
				+ "</getUserPassWordResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource(
			"<getUserPassWordRequest xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser02</userid>"
				+ "</getUserPassWordRequest>");
		responsePayload = new StringSource(
			"<getUserPassWordResponse>"
				+ "<getUserPassWordReturn></getUserPassWordReturn>"
				+ "</getUserPassWordResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getUserLimitEndDate() {
		Source requestPayload = new StringSource(
			"<getUserLimitEndDateRequest xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser01</userid>"
				+ "</getUserLimitEndDateRequest>");
		Source responsePayload = new StringSource(
			"<getUserLimitEndDateResponse>"
				+ "<getUserLimitEndDateReturn>2013-10-31</getUserLimitEndDateReturn>"
				+ "</getUserLimitEndDateResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource(
			"<getUserLimitEndDateRequest xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser02</userid>"
				+ "</getUserLimitEndDateRequest>");
		responsePayload = new StringSource(
			"<getUserLimitEndDateResponse>"
				+ "<getUserLimitEndDateReturn></getUserLimitEndDateReturn>"
				+ "</getUserLimitEndDateResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getUserInfo() {
		Source requestPayload = new StringSource(
			"<getUserInfoRequest xmlns=\"http://amtium.com\">"
				+ "<userid>TestUser001</userid>"
				+ "</getUserInfoRequest>");
		Source responsePayload = new StringSource(
			"<getUserInfoResponse>"
				+ "<getUserInfoReturn>TestUser001##11##崔作利1##654123## ##0411-88888881##2013-10-31##2013-05-18##备注项目 软件园1##0.0##1##1</getUserInfoReturn>"
				+ "</getUserInfoResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource(
			"<getUserInfoRequest xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser02</userid>"
				+ "</getUserInfoRequest>");
		responsePayload = new StringSource(
			"<getUserInfoResponse>"
				+ "<getUserInfoReturn></getUserInfoReturn>"
				+ "</getUserInfoResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void modifyUserInfo() {
		Source requestPayload = new StringSource(
			"<ModifyUserInfo3Request xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser01</userid>"
				+ "<groupid>10</groupid>"
				+ "<teamid>11</teamid>"
				+ "<pwd>654123</pwd>"
				+ "<username>崔作利</username>"
				+ "<phone>0411-8888</phone>"
				+ "<address>软件园</address>"
				+ "<limitdate_end>2013-10-31</limitdate_end>"
				+ "<userstate>1</userstate>"
				+ "<opendate>2013-05-01</opendate>"
				+ "<notes>备注项目</notes>"
				+ "<remain_fee>Other</remain_fee>"
				+ "<certNum>2101121</certNum>"
				+ "</ModifyUserInfo3Request>");
		Source responsePayload = new StringSource(
			"<ModifyUserInfo3Response>"
				+ "<ModifyUserInfo3Return>1</ModifyUserInfo3Return>"
				+ "</ModifyUserInfo3Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// userid not exists
		requestPayload = new StringSource(
			"<ModifyUserInfo3Request xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser02</userid>"
				+ "<groupid>10</groupid>"
				+ "<teamid>11</teamid>"
				+ "<pwd>654123</pwd>"
				+ "<username>崔作利</username>"
				+ "<phone>0411-88888888</phone>"
				+ "<address>软件园</address>"
				+ "<limitdate_end>2013-10-31</limitdate_end>"
				+ "<userstate>1</userstate>"
				+ "<opendate>2013-05-01</opendate>"
				+ "<notes>备注项目</notes>"
				+ "<remain_fee>Other</remain_fee>"
				+ "<certNum>210112198011253145</certNum>"
				+ "</ModifyUserInfo3Request>");
		responsePayload = new StringSource(
			"<ModifyUserInfo3Response>"
				+ "<ModifyUserInfo3Return>0</ModifyUserInfo3Return>"
				+ "</ModifyUserInfo3Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// groupid invalid
		requestPayload = new StringSource(
			"<ModifyUserInfo3Request xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser01</userid>"
				+ "<groupid>3</groupid>"
				+ "<teamid>11</teamid>"
				+ "<pwd>654123</pwd>"
				+ "<username>崔作利</username>"
				+ "<phone>0411-88888888</phone>"
				+ "<address>软件园</address>"
				+ "<limitdate_end>2013-10-31</limitdate_end>"
				+ "<userstate>1</userstate>"
				+ "<opendate>2013-05-01</opendate>"
				+ "<notes>备注项目</notes>"
				+ "<remain_fee>Other</remain_fee>"
				+ "<certNum>210112198011253145</certNum>"
				+ "</ModifyUserInfo3Request>");
		responsePayload = new StringSource(
			"<ModifyUserInfo3Response>"
				+ "<ModifyUserInfo3Return>0</ModifyUserInfo3Return>"
				+ "</ModifyUserInfo3Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getUserIdFromOnline() {
		Source requestPayload = new StringSource(
			"<getUserIdFromOnlineRequest xmlns=\"http://amtium.com\">"
				+ "<userip>192.168.1.102</userip>"
				+ "</getUserIdFromOnlineRequest>");
		Source responsePayload = new StringSource(
			"<getUserIdFromOnlineResponse>"
				+ "<getUserIdFromOnlineReturn>TestUser001</getUserIdFromOnlineReturn>"
				+ "</getUserIdFromOnlineResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource(
			"<getUserIdFromOnlineRequest xmlns=\"http://amtium.com\">"
				+ "<userip>192.168.1.11</userip>"
				+ "</getUserIdFromOnlineRequest>");
		responsePayload = new StringSource(
			"<getUserIdFromOnlineResponse>"
				+ "<getUserIdFromOnlineReturn></getUserIdFromOnlineReturn>"
				+ "</getUserIdFromOnlineResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void resumeUserInfo() {
		Source requestPayload = new StringSource(
			"<ResumeUserInfoRequest xmlns=\"http://amtium.com\">"
				+ "<userid>CardNewUser01</userid>"
				+ "</ResumeUserInfoRequest>");
		Source responsePayload = new StringSource(
			"<ResumeUserInfoResponse>"
				+ "<ResumeUserInfoReturn>1</ResumeUserInfoReturn>"
				+ "</ResumeUserInfoResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void offLineUser() {
		Source requestPayload = new StringSource(
			"<offLineUserRequest xmlns=\"http://amtium.com\">"
				+ "<userip>192.168.1.102</userip>"
				+ "<usermac>10-BF-48-7A-14-DD</usermac>"
				+ "<userid>TestUser001</userid>"
				+ "</offLineUserRequest>");
		Source responsePayload = new StringSource(
			"<offLineUserResponse>"
				+ "<offLineUserReturn>1</offLineUserReturn>"
				+ "</offLineUserResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getUserIdFromTicket() {
		Source requestPayload = new StringSource(
			"<getUserIdFromTicketRequest xmlns=\"http://amtium.com\">"
				+ "<userip>192.168.1.102</userip>"
				+ "<onlinetime>2013-05-18 11:05:30</onlinetime>"
				+ "</getUserIdFromTicketRequest>");
		Source responsePayload = new StringSource(
			"<getUserIdFromTicketResponse>"
				+ "<getUserIdFromTicketReturn>TestUser001</getUserIdFromTicketReturn>"
				+ "</getUserIdFromTicketResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getPrePolicyList() {
		Source requestPayload = new StringSource(
			"<getPrePolicyListRequest xmlns=\"http://amtium.com\">"
				+ "<userID>TestUser001</userID>"
				+ "</getPrePolicyListRequest>");
		Source responsePayload = new StringSource(
			"<getPrePolicyListResponse>"
				+ "<ReturnCode>61==测试_1M#####81==测试_10M</ReturnCode>"
				+ "</getPrePolicyListResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void modifyPrePolicy() {
		Source requestPayload = new StringSource(
			"<modifyPrePolicyRequest xmlns=\"http://amtium.com\">"
				+ "<userID>TestUser001</userID>"
				+ "<userGroupID>12</userGroupID>"
				+ "<prDate>2013-05-21</prDate>"
				+ "</modifyPrePolicyRequest>");
		Source responsePayload = new StringSource(
			"<modifyPrePolicyResponse>"
				+ "<modifyPrePolicyReturn>true</modifyPrePolicyReturn>"
				+ "</modifyPrePolicyResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

}
