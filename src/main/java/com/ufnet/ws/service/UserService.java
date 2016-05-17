/*
 * @(#)UserService.java $version 2013-5-15
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.joda.time.LocalDate;

import com.ufnet.ws.SimpleConstants;
import com.ufnet.ws.dao.AccountTypeDao;
import com.ufnet.ws.dao.UserInfoDao;
import com.ufnet.ws.model.AccountType;
import com.ufnet.ws.model.GroupMap;
import com.ufnet.ws.model.PauseRule;
import com.ufnet.ws.model.PredictAccountType;
import com.ufnet.ws.model.UseHistory;
import com.ufnet.ws.model.UserInfo;
import com.ufnet.ws.model.UserIpMac;
import com.ufnet.ws.server.model.CardChangePWDRequest;
import com.ufnet.ws.server.model.CardDelUserRequest;
import com.ufnet.ws.server.model.CardNewUserRequest;
import com.ufnet.ws.server.model.GetPrePolicyListRequest;
import com.ufnet.ws.server.model.GetUserIdFromOnlineRequest;
import com.ufnet.ws.server.model.GetUserIdFromTicketRequest;
import com.ufnet.ws.server.model.GetUserInfoRequest;
import com.ufnet.ws.server.model.GetUserLimitEndDateRequest;
import com.ufnet.ws.server.model.GetUserPassWordRequest;
import com.ufnet.ws.server.model.ModifyPrePolicyRequest;
import com.ufnet.ws.server.model.ModifyUserInfoRequest;
import com.ufnet.ws.server.model.OffLineUserRequest;
import com.ufnet.ws.server.model.ResumeUserInfoRequest;
import com.ufnet.ws.utils.JodaDateUtil;

/**
 * nhn ufnet-ws
 * com.ufnet.ws.service.UserService.java
 * @author st13902
 * @date 2013-5-15
 */
@Slf4j
public class UserService {

	private Map<String, Integer> stateMap;

	private UserInfoDao userInfoDao;
	private AccountTypeDao accountTypeDao;

	// User Constants
	private int communityId;
	private String aptNo;
	private int authMove;
	private int availableAmount;
	private int availableTime;
	private int availableFlow;
	private int usedAmount;
	private int usedFlow;
	private int usedTime;
	private int maxNetTime;
	private int autoDownInterval;
	private int isPopup;
	private String needRealIp;
	private int isLog;
	private int loginSessionId;
	private int maxOnlineUsers;
	private int sex;
	private String mobilePhone;
	private String email;
	private int educationLevelId;
	private int vocationId;
	private int userClass;
	private int vip;
	private int registerType;
	private int certificateTypeId;
	private int isInstall;
	private int isWiring;
	private int vlanId;
	private String switchId;
	private int switchPort;
	private String sales;
	private String buildingNo;
	private int reasonOfNoInstall;
	private int complexIndex;
	private int independent;
	private int denyAgent;
	private int loginType;
	private int secondLine;
	private String gatewayId;
	private String ipAddress;
	private String mac;

	/**
	 * cardNewUser
	 * @param request
	 * @return
	 */
	public int cardNewUser(CardNewUserRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return 0;
		}
		int size = userInfoDao.selectCountByLoginName(userId);
		if (size > 0) {
			log.error("userid[" + userId + "] exists.");
			return -1;
		}
		int groupId = request.getGroupId();
		int accounttypeId = 0;
		GroupMap data = new GroupMap();
		data.setGroupId(groupId);
		GroupMap groupMap = accountTypeDao.selectGroupMap(data);
		if (groupMap != null) {
			accounttypeId = groupMap.getAccounttypeId();
		} else {
			AccountType accountType = accountTypeDao.selectOne(groupId);
			if (accountType != null) {
				accounttypeId = groupId;
			} else {
				log.error("groupid[" + groupId + "] invalid.");
				return 0;
			}
		}
		int teamId = request.getTeamId();
		String pwd = request.getPwd();
		if (StringUtils.isBlank(pwd)) {
			log.warn("pwd[" + pwd + "] is blank. using default pwd[" + SimpleConstants.DEFAULT_PASSWORD + "]");
			request.setPwd(SimpleConstants.DEFAULT_PASSWORD);
		}
		String userName = request.getUsername();
		if (StringUtils.isBlank(userName)) {
			userName = userId;
		}
		String phone = request.getPhone();
		//		String address = request.getAddress();
		String limitDateEnd = request.getLimitDateEnd();
		if (StringUtils.isBlank(limitDateEnd)) {
			log.error("limitDateEnd[" + limitDateEnd + "] is blank.");
			return 0;
		}
		String userState = request.getUserState();
		if (StringUtils.isBlank(userState)) {
			log.error("userstate[" + userState + "] is blank.");
			return 0;
		}
		int currentState = 0;
		if (stateMap.containsKey(userState)) {
			currentState = stateMap.get(userState);
		} else {
			log.error("userstate[" + userState + "] invalid.");
			return 0;
		}
		String openDate = request.getOpenDate();
		if (StringUtils.isBlank(openDate)) {
			log.error("opendate[" + openDate + "] is blank.");
			return 0;
		}
		String notes = request.getNotes();
		String certNum = request.getCertNum();
		//		if (StringUtils.isBlank(certNum)) {
		//			log.error("certNum[" + certNum + "] is blank.");
		//			return 0;
		//		}
		//		if (StringUtils.length(certNum) != 15
		//			&& StringUtils.length(certNum) != 18) {
		//			log.error("Certificate no[" + certNum + "] invalid.");
		//			return 0;
		//		}
		// Process
		int returnCode = 1;
		try {
			Date date = new LocalDate().toDate();
			int accountId = userInfoDao.selectAccountId();
			String loginName = userId;
			String password = pwd;
			int payAccountId = accountId;
			AccountType accountType = accountTypeDao.selectOne(accounttypeId);
			int authTypeId = accountType.getTypeId();
			int bandwidth = accountType.getMaxBand();
			Date dateOfEnd = JodaDateUtil.parseDate(limitDateEnd);
			Date dateOfKaitong = JodaDateUtil.parseDate(openDate);
			Date lastLoginTime = date;
			Date birthday = date;
			Date dateOfApply = date;
			Date dateOfOpen = date;
			Date dateOfSignContract = date;
			Date dateOfEndContract = date;
			int adminId = teamId;
			int openAdmin = teamId;
			String remark = notes;
			String certificateNo = certNum;

			// UserInfo
			UserInfo userInfo = new UserInfo();
			userInfo.setAccountId(accountId);
			userInfo.setUserName(userName);
			userInfo.setCommunityId(communityId);
			userInfo.setAptNo(aptNo);
			userInfo.setLoginName(loginName);
			userInfo.setPassword(password);
			userInfo.setAccounttypeId(accounttypeId);
			userInfo.setAuthTypeId(authTypeId);
			userInfo.setAuthMove(authMove);
			userInfo.setDateOfEnd(dateOfEnd);
			userInfo.setAvailableAmount(availableAmount);
			userInfo.setAvailableTime(availableTime);
			userInfo.setAvailableFlow(availableFlow);
			userInfo.setBandwidth(bandwidth);
			userInfo.setUsedAmount(usedAmount);
			userInfo.setUsedFlow(usedFlow);
			userInfo.setUsedTime(usedTime);
			userInfo.setMaxNetTime(maxNetTime);
			userInfo.setAutoDownInterval(autoDownInterval);
			userInfo.setIsPopup(isPopup);
			userInfo.setNeedRealIp(needRealIp);
			userInfo.setIsLog(isLog);
			userInfo.setCurrentState(currentState);
			userInfo.setDateOfKaitong(dateOfKaitong);
			userInfo.setLastLoginTime(lastLoginTime);
			userInfo.setLoginSessionId(loginSessionId);
			userInfo.setMaxOnlineUsers(maxOnlineUsers);
			userInfo.setSex(sex);
			userInfo.setBirthday(birthday);
			userInfo.setPhone(phone);
			userInfo.setMobilePhone(mobilePhone);
			userInfo.setEmail(email);
			userInfo.setEducationLevelId(educationLevelId);
			userInfo.setVocationId(vocationId);
			userInfo.setUserClass(userClass);
			userInfo.setVip(vip);
			userInfo.setRegisterType(registerType);
			userInfo.setCertificateTypeId(certificateTypeId);
			userInfo.setCertificateNo(certificateNo);
			userInfo.setDateOfApply(dateOfApply);
			userInfo.setDateOfOpen(dateOfOpen);
			userInfo.setDateOfSignContract(dateOfSignContract);
			userInfo.setDateOfEndContract(dateOfEndContract);
			userInfo.setIsInstall(isInstall);
			userInfo.setIsWiring(isWiring);
			userInfo.setVlanId(vlanId);
			userInfo.setSwitchId(switchId);
			userInfo.setSwitchPort(switchPort);
			userInfo.setSales(sales);
			userInfo.setAdminId(adminId);
			userInfo.setBuildingNo(buildingNo);
			userInfo.setReasonOfNoInstall(reasonOfNoInstall);
			userInfo.setComplexIndex(complexIndex);
			userInfo.setPayAccountId(payAccountId);
			userInfo.setIndependent(independent);
			userInfo.setRemark(remark);
			userInfo.setDenyAgent(denyAgent);
			userInfo.setLoginType(loginType);
			userInfo.setOpenAdmin(openAdmin);
			userInfo.setSecondLine(secondLine);

			// insert user_info
			userInfoDao.insert(userInfo);

			if (StringUtils.isNotEmpty(gatewayId)) {
				String[] gatewayIds = StringUtils.split(gatewayId, ",");
				for (String id : gatewayIds) {
					// insert user_ip_mac
					UserIpMac userIpMac = new UserIpMac();
					userIpMac.setAccountId(accountId);
					userIpMac.setGatewayId(Integer.valueOf(id));
					userIpMac.setIpAddress(ipAddress);
					userIpMac.setMac(mac);
					userInfoDao.insertUserIpMac(userIpMac);
				}
			}
		} catch (Exception e) {
			returnCode = 0;
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return returnCode;
	}

	/**
	 * cardDelUser
	 * @param request
	 * @return
	 */
	public int cardDelUser(CardDelUserRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return -1;
		}
		// Process
		int returnCode = -1;
		try {
			// 查询余额
			UserInfo userInfo = userInfoDao.selectOneByLoginName(userId);
			if (userInfo != null) {
				returnCode = new BigDecimal(userInfo.getAvailableAmount(), new MathContext(0, RoundingMode.FLOOR)).toBigInteger().intValue();
				// 销户
				userInfoDao.delete(userId);
				userInfoDao.deleteUserIpMac(userInfo.getAccountId());
			} else {
				log.error("userid[" + userId + "] not exists.");
				return -1;
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return returnCode;
	}

	/**
	 * cardChangePWD
	 * @param request
	 * @return
	 */
	public int cardChangePWD(CardChangePWDRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return 0;
		}
		int size = userInfoDao.selectCountByLoginName(userId);
		if (size == 0) {
			log.error("userid[" + userId + "] not exists.");
			return -1;
		}
		String destPwd = request.getDestPwd();
		if (StringUtils.isBlank(userId)) {
			log.error("destpwd[" + destPwd + "] is blank.");
			return 0;
		}
		if (StringUtils.length(destPwd) > SimpleConstants.PASSWORD_MAX_LENGTH) {
			log.error("destpwd[" + destPwd + "] over " + SimpleConstants.PASSWORD_MAX_LENGTH + ".");
			return 0;
		}
		// Process
		int returnCode = 1;
		try {
			UserInfo data = new UserInfo();
			data.setLoginName(userId);
			data.setPassword(destPwd);
			userInfoDao.update(data);
		} catch (Exception e) {
			returnCode = 0;
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return returnCode;
	}

	/**
	 * getUserPassWord
	 * @param request
	 * @return
	 */
	public String getUserPassWord(GetUserPassWordRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return "";
		}
		// Process
		String password = "";
		try {
			UserInfo userInfo = userInfoDao.selectOneByLoginName(userId);
			if (userInfo != null) {
				password = userInfo.getPassword();
			} else {
				log.error("userid[" + userId + "] not exists.");
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return password;
	}

	/**
	 * getUserLimitEndDate
	 * @param request
	 * @return
	 */
	public String getUserLimitEndDate(GetUserLimitEndDateRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return "";
		}
		// Process
		String dateOfEnd = "";
		try {
			UserInfo userInfo = userInfoDao.selectOneByLoginName(userId);
			if (userInfo != null) {
				dateOfEnd = JodaDateUtil.formatDate(userInfo.getDateOfEnd());
			} else {
				log.error("userid[" + userId + "] not exists.");
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return dateOfEnd;
	}

	/**
	 * getUserInfo
	 * @param request
	 * @return
	 */
	public String getUserInfo(GetUserInfoRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return "";
		}
		// Process
		StringBuffer result = new StringBuffer();
		try {
			UserInfo userInfo = userInfoDao.selectOneByLoginName(userId);
			if (userInfo != null) {
				String loginName = userInfo.getLoginName();
				int accounttypeId = userInfo.getAccounttypeId();
				GroupMap data = new GroupMap();
				data.setAccounttypeId(accounttypeId);
				GroupMap groupMap = accountTypeDao.selectGroupMap(data);
				int groupId = 0;
				if (groupMap != null) {
					groupId = groupMap.getGroupId();
				} else {
					groupId = accounttypeId;
				}
				String userName = userInfo.getUserName();
				String password = userInfo.getPassword();
				String phone = userInfo.getPhone();
				Date dateOfEnd = userInfo.getDateOfEnd();
				Date dateOfOpen = userInfo.getDateOfOpen();
				String remark = userInfo.getRemark();
				String address = " ";
				float availableAmount = userInfo.getAvailableAmount();
				int currentState = userInfo.getCurrentState();
				String userState = "";
				Iterator<Entry<String, Integer>> stateIterator = stateMap.entrySet().iterator();
				while (stateIterator.hasNext()) {
					Entry<String, Integer> entry = stateIterator.next();
					if (entry.getValue() == currentState) {
						userState = entry.getKey();
						break;
					}
				}
				AccountType accountType = accountTypeDao.selectOne(accounttypeId);
				int payType = accountType.getPayType();
				result.append(loginName).append("##")
					.append(groupId).append("##")
					.append(userName).append("##")
					.append(password).append("##")
					.append(address).append("##")
					.append(phone).append("##")
					.append(JodaDateUtil.formatDate(dateOfEnd)).append("##")
					.append(JodaDateUtil.formatDate(dateOfOpen)).append("##")
					.append(remark).append("##")
					.append(availableAmount).append("##")
					.append(userState).append("##")
					.append(payType);
			} else {
				log.error("userid[" + userId + "] not exists.");
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return result.toString();
	}

	/**
	 * modifyUserInfo
	 * @param request
	 * @return
	 */
	public int modifyUserInfo(ModifyUserInfoRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return 0;
		}
		int size = userInfoDao.selectCountByLoginName(userId);
		if (size == 0) {
			log.error("userid[" + userId + "] not exists.");
			return 0;
		}
		int groupId = request.getGroupId();
		int accounttypeId = 0;
		if (groupId != 0) {
			GroupMap data = new GroupMap();
			data.setGroupId(groupId);
			GroupMap groupMap = accountTypeDao.selectGroupMap(data);
			if (groupMap != null) {
				accounttypeId = groupMap.getAccounttypeId();
			} else {
				AccountType accountType = accountTypeDao.selectOne(groupId);
				if (accountType != null) {
					accounttypeId = groupId;
				} else {
					log.error("groupid[" + groupId + "] invalid.");
					return 0;
				}
			}
		}
		int teamId = request.getTeamId();
		String pwd = request.getPwd();
		if (StringUtils.isBlank(pwd)) {
			log.error("pwd[" + pwd + "] is blank. using default pwd[" + SimpleConstants.DEFAULT_PASSWORD + "]");
			request.setPwd(SimpleConstants.DEFAULT_PASSWORD);
		}
		String userName = request.getUsername();
		String phone = request.getPhone();
		//		String address = request.getAddress();
		String limitDateEnd = request.getLimitDateEnd();
		String userState = request.getUserState();
		int currentState = 0;
		if (StringUtils.isNotBlank(userState)) {
			if (stateMap.containsKey(userState)) {
				currentState = stateMap.get(userState);
			} else {
				log.error("userstate[" + userState + "] invalid.");
				return 0;
			}
		}
		String openDate = request.getOpenDate();
		String notes = request.getNotes();
		float remainFee = request.getRemainFee();
		String certNum = request.getCertNum();
		// Process
		int returnCode = 1;
		try {
			String loginName = userId;
			String password = pwd;
			Date dateOfEnd = null;
			if (StringUtils.isNotEmpty(limitDateEnd)) {
				dateOfEnd = JodaDateUtil.parseDate(limitDateEnd);
			}
			Date dateOfKaitong = null;
			if (StringUtils.isNotEmpty(openDate)) {
				dateOfKaitong = JodaDateUtil.parseDate(openDate);
			}
			int adminId = teamId;
			int openAdmin = teamId;
			String remark = notes;
			String certificateNo = certNum;
			int authTypeId = 0;
			int bandwidth = 0;
			if (accounttypeId != 0) {
				AccountType accountType = accountTypeDao.selectOne(accounttypeId);
				authTypeId = accountType.getTypeId();
				bandwidth = accountType.getMaxBand();
			}

			// UserInfo
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName(userName);
			userInfo.setLoginName(loginName);
			userInfo.setPassword(password);
			userInfo.setAvailableAmount(remainFee);
			userInfo.setAccounttypeId(accounttypeId);
			userInfo.setAuthTypeId(authTypeId);
			userInfo.setBandwidth(bandwidth);
			userInfo.setDateOfEnd(dateOfEnd);
			userInfo.setCurrentState(currentState);
			userInfo.setDateOfKaitong(dateOfKaitong);
			userInfo.setPhone(phone);
			userInfo.setCertificateNo(certificateNo);
			userInfo.setAdminId(adminId);
			userInfo.setRemark(remark);
			userInfo.setOpenAdmin(openAdmin);

			// update user_info
			userInfoDao.update(userInfo);
		} catch (Exception e) {
			returnCode = 0;
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return returnCode;
	}

	/**
	 * getUserIdFromOnline
	 * @param request
	 * @return
	 */
	public String getUserIdFromOnline(GetUserIdFromOnlineRequest request) {
		// Request && Validation
		String userIp = request.getUserIp();
		if (StringUtils.isBlank(userIp)) {
			log.error("Userip[" + userIp + "] is blank.");
			return "";
		}
		try {
			String loginName = userInfoDao.selectUserIdFromOnline(userIp);
			if (StringUtils.isNotEmpty(loginName)) {
				return loginName;
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return "";
	}

	/**
	 * offLineUser
	 * @param request
	 * @return
	 */
	public int offLineUser(OffLineUserRequest request) {
		// Request && Validation
		String userIp = request.getUserIp();
		if (StringUtils.isBlank(userIp)) {
			log.error("userip[" + userIp + "] is blank.");
			return 0;
		}
		String userMac = request.getUserMac();
		if (StringUtils.isBlank(userIp)) {
			log.error("usermac[" + userMac + "] is blank.");
			return 0;
		}
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return 0;
		}
		// Process
		int returnCode = 1;
		try {
			userInfoDao.updateOffLineUser(userId, userIp, userMac);
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
			returnCode = 0;
		}
		return returnCode;
	}

	/**
	 * getUserIdFromTicket
	 * @param request
	 * @return
	 */
	public String getUserIdFromTicket(GetUserIdFromTicketRequest request) {
		String userIp = request.getUserIp();
		if (StringUtils.isBlank(userIp)) {
			log.error("userip[" + userIp + "] is blank.");
			return "";
		}
		String onlineTime = request.getOnlineTime();
		if (StringUtils.isBlank(onlineTime)) {
			log.error("onlinetime[" + onlineTime + "] is blank.");
			return "";
		}
		try {
			UseHistory data = new UseHistory();
			data.setIpAddress(userIp);
			data.setOnlineTime(JodaDateUtil.parseDateTime(onlineTime));
			String loginName = userInfoDao.selectUserIdFromTicket(data);
			if (StringUtils.isNotEmpty(loginName)) {
				return loginName;
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return "";
	}

	/**
	 * resumeUserInfo
	 * @param request
	 * @return
	 */
	public int resumeUserInfo(ResumeUserInfoRequest request) {
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return 0;
		}
		int size = userInfoDao.selectCountByLoginName(userId);
		if (size == 0) {
			log.error("userid[" + userId + "] not exists.");
			return 0;
		}
		int returnCode = 1;
		try {
			UserInfo userInfo = userInfoDao.selectOneByLoginName(userId);
			AccountType accountType = accountTypeDao.selectOne(userInfo.getAccounttypeId());
			PauseRule pauseRule = accountTypeDao.selectOnePauseRule(accountType.getPauseRuleId());
			float availableAmount = 0;
			if (pauseRule != null) {
				availableAmount = userInfo.getAvailableAmount() - pauseRule.getPauseFee();
				if (availableAmount < 0) {
					log.error("availableAmount[" + userInfo.getAvailableAmount() + "] < pauseFee["
						+ pauseRule.getPauseFee() + "].");
					return 0;
				}
			}
			UserInfo data = new UserInfo();
			data.setLoginName(userId);
			data.setCurrentState(SimpleConstants.STATE_OPEN);
			data.setAvailableAmount(availableAmount);
			userInfoDao.update(data);
		} catch (Exception e) {
			returnCode = 0;
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return returnCode;
	}

	/**
	 * getPrePolicyList
	 * @param request
	 * @return
	 */
	public String getPrePolicyList(GetPrePolicyListRequest request) {
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userID[" + userId + "] is blank.");
			return "";
		}
		try {
			PredictAccountType predictAccountType = userInfoDao.selectPrePolicyList(userId);
			if (predictAccountType != null) {
				return new StringBuffer()
					.append(predictAccountType.getOriginTypeId())
					.append("==")
					.append(predictAccountType.getOriginTypeName())
					.append("#####")
					.append(predictAccountType.getTargetTypeId())
					.append("==")
					.append(predictAccountType.getTargetTypeName())
					.toString();
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return "";
	}

	/**
	 * modifyPrePolicy
	 * @param request
	 * @return
	 */
	public boolean modifyPrePolicy(ModifyPrePolicyRequest request) {
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userID[" + userId + "] is blank.");
			return false;
		}
		int userGroupId = request.getUserGroupId();
		int accounttypeId = 0;
		GroupMap data = new GroupMap();
		data.setGroupId(userGroupId);
		GroupMap groupMap = accountTypeDao.selectGroupMap(data);
		if (groupMap != null) {
			accounttypeId = groupMap.getAccounttypeId();
		} else {
			log.error("userGroupID[" + userGroupId + "] invalid.");
			return false;
		}
		String prDate = request.getPrDate();
		if (StringUtils.isBlank(prDate)) {
			log.error("prDate[" + prDate + "] is blank.");
			return false;
		}
		try {
			PredictAccountType predictAccountType = new PredictAccountType();
			predictAccountType.setLoginName(userId);
			predictAccountType.setTargetTypeId(accounttypeId);
			predictAccountType.setImplementDate(JodaDateUtil.parseDate(prDate));
			int i = userInfoDao.updatePrePolicy(predictAccountType);
			if (i == 0) {
				return false;
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
			return false;
		}
		return true;
	}

	public void setStateMap(Map<String, Integer> stateMap) {
		this.stateMap = stateMap;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public void setAccountTypeDao(AccountTypeDao accountTypeDao) {
		this.accountTypeDao = accountTypeDao;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}

	public void setAuthMove(int authMove) {
		this.authMove = authMove;
	}

	public void setAvailableAmount(int availableAmount) {
		this.availableAmount = availableAmount;
	}

	public void setAvailableTime(int availableTime) {
		this.availableTime = availableTime;
	}

	public void setAvailableFlow(int availableFlow) {
		this.availableFlow = availableFlow;
	}

	public void setUsedAmount(int usedAmount) {
		this.usedAmount = usedAmount;
	}

	public void setUsedFlow(int usedFlow) {
		this.usedFlow = usedFlow;
	}

	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}

	public void setMaxNetTime(int maxNetTime) {
		this.maxNetTime = maxNetTime;
	}

	public void setAutoDownInterval(int autoDownInterval) {
		this.autoDownInterval = autoDownInterval;
	}

	public void setIsPopup(int isPopup) {
		this.isPopup = isPopup;
	}

	public void setNeedRealIp(String needRealIp) {
		this.needRealIp = needRealIp;
	}

	public void setIsLog(int isLog) {
		this.isLog = isLog;
	}

	public void setLoginSessionId(int loginSessionId) {
		this.loginSessionId = loginSessionId;
	}

	public void setMaxOnlineUsers(int maxOnlineUsers) {
		this.maxOnlineUsers = maxOnlineUsers;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEducationLevelId(int educationLevelId) {
		this.educationLevelId = educationLevelId;
	}

	public void setVocationId(int vocationId) {
		this.vocationId = vocationId;
	}

	public void setUserClass(int userClass) {
		this.userClass = userClass;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public void setRegisterType(int registerType) {
		this.registerType = registerType;
	}

	public void setCertificateTypeId(int certificateTypeId) {
		this.certificateTypeId = certificateTypeId;
	}

	public void setIsInstall(int isInstall) {
		this.isInstall = isInstall;
	}

	public void setIsWiring(int isWiring) {
		this.isWiring = isWiring;
	}

	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	public void setSwitchPort(int switchPort) {
		this.switchPort = switchPort;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public void setReasonOfNoInstall(int reasonOfNoInstall) {
		this.reasonOfNoInstall = reasonOfNoInstall;
	}

	public void setComplexIndex(int complexIndex) {
		this.complexIndex = complexIndex;
	}

	public void setIndependent(int independent) {
		this.independent = independent;
	}

	public void setDenyAgent(int denyAgent) {
		this.denyAgent = denyAgent;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public void setSecondLine(int secondLine) {
		this.secondLine = secondLine;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
