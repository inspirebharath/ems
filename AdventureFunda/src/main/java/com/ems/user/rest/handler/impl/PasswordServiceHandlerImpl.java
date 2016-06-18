/**
 * 
 */
package com.ems.user.rest.handler.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ems.user.beans.LoginDetails;
import com.ems.user.beans.PasswordBean;
import com.ems.user.beans.Response;
import com.ems.user.constants.AppCodes;
import com.ems.user.constants.AppEntityEnum;
import com.ems.user.constants.Constants;
import com.ems.user.constants.SessionConstants;
import com.ems.user.dao.AdminDao;
import com.ems.user.dao.PartnerDao;
import com.ems.user.dao.UserDao;
import com.ems.user.dao.model.AdminInfo;
import com.ems.user.dao.model.PartnerInfo;
import com.ems.user.dao.model.UserInfo;
import com.ems.user.rest.handler.EmailServiceHandler;
import com.ems.user.rest.handler.PasswordServiceHandler;
import com.ems.user.util.ApplicationUtil;
import com.ems.user.util.CommonUtil;
import com.ems.user.util.CryptographHandler;
import com.ems.user.util.DateTimeUtil;
import com.ems.user.util.Properties;
import com.ems.user.util.StringUtil;

/**
 * @author Bharath Arya
 *
 */
@Service
public class PasswordServiceHandlerImpl implements PasswordServiceHandler, SessionConstants, Constants {

	private static Logger logger = Logger.getLogger(ApplicationServiceHandlerImpl.class);
	
	private final String CLASS_NAME = ApplicationServiceHandlerImpl.class.getPackage().getName();
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private PartnerDao partnerDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailServiceHandler emailServiceHandler;
	
	@Override
	public Response changeUserPassword(PasswordBean passwordBean, HttpSession session) {
		logger.info("Entering :: " + CLASS_NAME + " :: changeUserPassword method");
		
		Response response = new Response();
		try {
			
			String userType = passwordBean.getUserType();
			
			logger.debug("++++++++    User Type :: " + userType + "++++++++");
			
			if(!StringUtil.isNullAndEmpty(userType) && userType.toUpperCase().equals(AppEntityEnum.ADMIN.value())) {
				response = changeAdminPassword(passwordBean, session);
			} else if(!StringUtil.isNullAndEmpty(userType) && userType.toUpperCase().equals(AppEntityEnum.PARTNER.value())) {
				response = changePartnerPassword(passwordBean, session);
			} else if(!StringUtil.isNullAndEmpty(userType) && userType.toUpperCase().equals(AppEntityEnum.USER.value())) {
				response = changeEndUserPassword(passwordBean, session);
			}
			
		} catch (DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS25, e);
			logger.error("ERROR :: " + CLASS_NAME + " :: changeUserPassword method ::::", e);
		}
		
		logger.info("Exiting :: " + CLASS_NAME + " :: changeUserPassword method");
		return response;
	}
	
	private Response changeAdminPassword(PasswordBean passwordBean, HttpSession session) {
		Response response = new Response();

		int isPwdUpdateSuccess = 0;
		String sessionUser = null;
		if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
			LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
			sessionUser = loginDetails.getUserName();
			
			AdminInfo adminInfo = adminDao.isValidUser(sessionUser, loginDetails.getEmailId(), 
					CryptographHandler.encodePassword(passwordBean.getOldPassword()));
			
			if(adminInfo != null) {
			
				isPwdUpdateSuccess = adminDao.updatePassword(adminInfo.getId(), 
						CryptographHandler.encodePassword(passwordBean.getNewPassword()), 
						sessionUser, DateTimeUtil.getCurrentTimeStampForDb());
			
			} else {
				response.setResponseMessage(AppCodes.EAS26.value());
			}
			
		}
		
		if(isPwdUpdateSuccess > 0) {
			CommonUtil.populateResponseMsgData(response, AppCodes.EAS24);
		} else {
			CommonUtil.populateResponseMsgData(response, AppCodes.EAS25);
		}
		
		return response;
	}
	
	private Response changePartnerPassword(PasswordBean passwordBean, HttpSession session) {
		Response response = new Response();

		int isPwdUpdateSuccess = 0;
		String sessionUser = null;
		if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
			LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
			sessionUser = loginDetails.getUserName();
			
			PartnerInfo partnerInfo = partnerDao.isValidPartner(sessionUser, loginDetails.getEmailId(), 
					CryptographHandler.encodePassword(passwordBean.getOldPassword()));
			
			if(partnerInfo != null) {
			
				isPwdUpdateSuccess = partnerDao.updatePassword(partnerInfo.getId(), 
						CryptographHandler.encodePassword(passwordBean.getNewPassword()), 
						sessionUser, DateTimeUtil.getCurrentTimeStampForDb());
			
			} else {
				response.setResponseMessage(AppCodes.EAS26.value());
			}
			
		}
		
		if(isPwdUpdateSuccess > 0) {
			CommonUtil.populateResponseMsgData(response, AppCodes.EAS24);
		} else {
			CommonUtil.populateResponseMsgData(response, AppCodes.EAS25);
		}
		
		return response;
	}

	private Response changeEndUserPassword(PasswordBean passwordBean, HttpSession session) {
		Response response = new Response();

		int isPwdUpdateSuccess = 0;
		String sessionUser = null;
		if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
			LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
			sessionUser = loginDetails.getUserName();
			
			UserInfo userInfo = userDao.isValidUser(sessionUser, loginDetails.getEmailId(), 
					CryptographHandler.encodePassword(passwordBean.getOldPassword()));
			
			if(userInfo != null) {
			
				isPwdUpdateSuccess = userDao.updatePassword(userInfo.getId(), 
						CryptographHandler.encodePassword(passwordBean.getNewPassword()), 
						sessionUser, DateTimeUtil.getCurrentTimeStampForDb());
			
			} else {
				response.setResponseMessage(AppCodes.EAS26.value());
			}
			
		}
		
		if(isPwdUpdateSuccess > 0) {
			CommonUtil.populateResponseMsgData(response, AppCodes.EAS24);
		} else {
			CommonUtil.populateResponseMsgData(response, AppCodes.EAS25);
		}
		
		return response;
	}
	
	@Override
	public Response sendResetPwdLink(PasswordBean passwordBean) {
		logger.info("Entering :: " + CLASS_NAME + " :: sendResetPwdLink method");
		Response response = new Response();
		try {

			String userType = passwordBean.getUserType();
			
			logger.debug("++++++++    User Type :: " + userType + "++++++++");
			
			boolean isUserExists = true;
			
			if(!StringUtil.isNullAndEmpty(userType) && userType.toUpperCase().equals(AppEntityEnum.ADMIN.value())) {
				AdminInfo adminInfo = adminDao.findPartnerByEmailId(passwordBean.getEmailId());
				if(!CommonUtil.isNull(adminInfo)) {
					passwordBean.setId(adminInfo.getId());
					passwordBean.setUserName(adminInfo.getUserName());
					passwordBean.setUserType(userType);
				} else {
					isUserExists = false;
				}
			} else if(!StringUtil.isNullAndEmpty(userType) && userType.toUpperCase().equals(AppEntityEnum.PARTNER.value())) {
				PartnerInfo partnerInfo = partnerDao.findPartnerByEmailId(passwordBean.getEmailId());
				if(!CommonUtil.isNull(partnerInfo)) {
					passwordBean.setId(partnerInfo.getId());
					passwordBean.setUserName(partnerInfo.getUserName());
					passwordBean.setUserType(userType);
				} else {
					isUserExists = false;
				}
			} else if(!StringUtil.isNullAndEmpty(userType) && userType.toUpperCase().equals(AppEntityEnum.USER.value())) {
				UserInfo userInfo = userDao.findUserByEmailId(passwordBean.getEmailId());
				if(!CommonUtil.isNull(userInfo)) {
					passwordBean.setId(userInfo.getId());
					passwordBean.setUserName(userInfo.getUserName());
					passwordBean.setUserType(userType);
				} else {
					isUserExists = false;
				}
			}

			if(isUserExists) {
				String resetPwdToken = ApplicationUtil.getResetPassowrdToken(passwordBean);
				
				Map<String, String> infoMap = new HashMap<>();
				infoMap.put("userName", passwordBean.getUserName());
				infoMap.put("token", resetPwdToken);
				infoMap.put("hLink", Properties.getProperty("ems.app.resetPwd.link"));
				
				emailServiceHandler.sendEmail(infoMap, "/admin_create.vm", new String[]{passwordBean.getEmailId()}, response);
				CommonUtil.populateResponseSuccessData(response, AppCodes.EAS27);
			} else {
				CommonUtil.populateResponseSuccessData(response, AppCodes.EAS02);
			}
			
		} catch (DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, e);
			logger.error("ERROR :: " + CLASS_NAME + " :: sendResetPwdLink method ::::", e);
		}
		
		logger.info("Exiting :: " + CLASS_NAME + " :: sendResetPwdLink method");
		return response;
	}

	@Override
	public Response resetPassword(PasswordBean passwordBean) {
		logger.info("Entering :: " + CLASS_NAME + " :: resetPassword method");
		
		Response response = new Response();
		try {

			ApplicationUtil.populatePasswordBeanFromResetPassowrdToken(passwordBean);
			
			long timeDiff = DateTimeUtil.getTimeDiffOfDate(passwordBean.getTimeStamp());
			
			if(timeDiff > 24) {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS30);
			} else {
				int isPwdUpdateSuccess = 0;
				String userType = passwordBean.getUserType();
				
				logger.debug("++++++++    User Type :: " + userType + "++++++++");
				
				if(!StringUtil.isNullAndEmpty(userType) && userType.toUpperCase().equals(AppEntityEnum.ADMIN.value())) {
					isPwdUpdateSuccess = adminDao.updatePassword(passwordBean.getId(), CryptographHandler.encodePassword(passwordBean.getNewPassword()), 
											passwordBean.getUserName(), DateTimeUtil.getCurrentTimeStampForDb());
				} else if(!StringUtil.isNullAndEmpty(userType) && userType.toUpperCase().equals(AppEntityEnum.PARTNER.value())) {
					isPwdUpdateSuccess = partnerDao.updatePassword(passwordBean.getId(), CryptographHandler.encodePassword(passwordBean.getNewPassword()), 
											passwordBean.getUserName(), DateTimeUtil.getCurrentTimeStampForDb());
				} else if(!StringUtil.isNullAndEmpty(userType) && userType.toUpperCase().equals(AppEntityEnum.USER.value())) {
					isPwdUpdateSuccess = userDao.updatePassword(passwordBean.getId(), CryptographHandler.encodePassword(passwordBean.getNewPassword()), 
											passwordBean.getUserName(), DateTimeUtil.getCurrentTimeStampForDb());
				}
				
				if(isPwdUpdateSuccess > 0) {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS28);
				} else {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS29);
				}
			}
			
		} catch (DataAccessException | ParseException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS29, e);
			logger.error("ERROR :: " + CLASS_NAME + " :: resetPassword method ::::", e);
		}
		
		logger.info("Exiting :: " + CLASS_NAME + " :: resetPassword method");
		return response;
	}
	
}