/*
 * @(#)UserServiceEndpoint.java $version 2013-5-14
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.server.endpoint;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ufnet.ws.SimpleConstants;
import com.ufnet.ws.server.model.CardChangePWDRequest;
import com.ufnet.ws.server.model.CardChangePWDResponse;
import com.ufnet.ws.server.model.CardDelUserRequest;
import com.ufnet.ws.server.model.CardDelUserResponse;
import com.ufnet.ws.server.model.CardNewUserRequest;
import com.ufnet.ws.server.model.CardNewUserResponse;
import com.ufnet.ws.server.model.GetPrePolicyListRequest;
import com.ufnet.ws.server.model.GetPrePolicyListResponse;
import com.ufnet.ws.server.model.GetUserIdFromOnlineRequest;
import com.ufnet.ws.server.model.GetUserIdFromOnlineResponse;
import com.ufnet.ws.server.model.GetUserIdFromTicketRequest;
import com.ufnet.ws.server.model.GetUserIdFromTicketResponse;
import com.ufnet.ws.server.model.GetUserInfoRequest;
import com.ufnet.ws.server.model.GetUserInfoResponse;
import com.ufnet.ws.server.model.GetUserLimitEndDateRequest;
import com.ufnet.ws.server.model.GetUserLimitEndDateResponse;
import com.ufnet.ws.server.model.GetUserPassWordRequest;
import com.ufnet.ws.server.model.GetUserPassWordResponse;
import com.ufnet.ws.server.model.ModifyPrePolicyRequest;
import com.ufnet.ws.server.model.ModifyPrePolicyResponse;
import com.ufnet.ws.server.model.ModifyUserInfoRequest;
import com.ufnet.ws.server.model.ModifyUserInfoResponse;
import com.ufnet.ws.server.model.OffLineUserRequest;
import com.ufnet.ws.server.model.OffLineUserResponse;
import com.ufnet.ws.server.model.ResumeUserInfoRequest;
import com.ufnet.ws.server.model.ResumeUserInfoResponse;
import com.ufnet.ws.service.UserService;

/**
 * nhn ufnet-ws
 * com.ufnet.ws.server.endpoint.UserServiceEndpoint.java
 * @author st13902
 * @date 2013-5-14
 */
@Endpoint
public class UserServiceEndpoint {

	@Resource
	private UserService userService;

	@PayloadRoot(localPart = "CardNewUser", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public CardNewUserResponse cardNewUser(@RequestPayload CardNewUserRequest request) {
		int returnCode = userService.cardNewUser(request);
		CardNewUserResponse response = new CardNewUserResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "CardDelUser", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public CardDelUserResponse cardDelUser(@RequestPayload CardDelUserRequest request) {
		int returnCode = userService.cardDelUser(request);
		CardDelUserResponse response = new CardDelUserResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "CardChangePWD2", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public CardChangePWDResponse cardChangePWD(@RequestPayload CardChangePWDRequest request) {
		int returnCode = userService.cardChangePWD(request);
		CardChangePWDResponse response = new CardChangePWDResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "getUserPassWord", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public GetUserPassWordResponse getUserPassWord(@RequestPayload GetUserPassWordRequest request) {
		String returnCode = userService.getUserPassWord(request);
		GetUserPassWordResponse response = new GetUserPassWordResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "getUserLimitEndDate", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public GetUserLimitEndDateResponse getUserLimitEndDate(@RequestPayload GetUserLimitEndDateRequest request) {
		String returnCode = userService.getUserLimitEndDate(request);
		GetUserLimitEndDateResponse response = new GetUserLimitEndDateResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "getUserInfo", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public GetUserInfoResponse getUserInfo(@RequestPayload GetUserInfoRequest request) {
		String returnCode = userService.getUserInfo(request);
		GetUserInfoResponse response = new GetUserInfoResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "ModifyUserInfo", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public ModifyUserInfoResponse modifyUserInfo(@RequestPayload ModifyUserInfoRequest request) {
		int returnCode = userService.modifyUserInfo(request);
		ModifyUserInfoResponse response = new ModifyUserInfoResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "getUserIdFromOnline", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public GetUserIdFromOnlineResponse getUserIdFromOnline(@RequestPayload GetUserIdFromOnlineRequest request) {
		String returnCode = userService.getUserIdFromOnline(request);
		GetUserIdFromOnlineResponse response = new GetUserIdFromOnlineResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "ResumeUserInfo", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public ResumeUserInfoResponse resumeUserInfo(@RequestPayload ResumeUserInfoRequest request) {
		int returnCode = userService.resumeUserInfo(request);
		ResumeUserInfoResponse response = new ResumeUserInfoResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "offLineUser", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public OffLineUserResponse offLineUser(@RequestPayload OffLineUserRequest request) {
		int returnCode = userService.offLineUser(request);
		OffLineUserResponse response = new OffLineUserResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "getUserIdFromTicket", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public GetUserIdFromTicketResponse getUserIdFromTicket(@RequestPayload GetUserIdFromTicketRequest request) {
		String returnCode = userService.getUserIdFromTicket(request);
		GetUserIdFromTicketResponse response = new GetUserIdFromTicketResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "getPrePolicyList", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public GetPrePolicyListResponse GetPrePolicyList(@RequestPayload GetPrePolicyListRequest request) {
		String returnCode = userService.getPrePolicyList(request);
		GetPrePolicyListResponse response = new GetPrePolicyListResponse();
		response.setReturnCode(returnCode);
		return response;
	}

	@PayloadRoot(localPart = "modifyPrePolicy", namespace = SimpleConstants.NAMESPACE)
	@ResponsePayload
	public ModifyPrePolicyResponse modifyPrePolicy(@RequestPayload ModifyPrePolicyRequest request) {
		boolean returnCode = userService.modifyPrePolicy(request);
		ModifyPrePolicyResponse response = new ModifyPrePolicyResponse();
		response.setReturnCode(returnCode);
		return response;
	}

}
