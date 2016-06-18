/**
 * 
 */
package com.ems.user.rest.handler.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ems.user.beans.LoginDetails;
import com.ems.user.beans.Response;
import com.ems.user.constants.AppCodes;
import com.ems.user.constants.AppEntityEnum;
import com.ems.user.constants.DbConstantsEnum;
import com.ems.user.constants.SessionConstants;
import com.ems.user.dao.AdminDao;
import com.ems.user.dao.PageHitsDao;
import com.ems.user.dao.PartnerDao;
import com.ems.user.dao.UserDao;
import com.ems.user.dao.model.AdminInfo;
import com.ems.user.dao.model.PageHits;
import com.ems.user.dao.model.PartnerInfo;
import com.ems.user.dao.model.UserInfo;
import com.ems.user.rest.handler.LoginServiceHandler;
import com.ems.user.util.CommonUtil;
import com.ems.user.util.CryptographHandler;
import com.ems.user.util.DateTimeUtil;

/**
 * @author Bharath Arya
 *
 */
@Service
public class LoginServiceHandlerImpl implements LoginServiceHandler, SessionConstants {
	
	private static Logger logger = Logger.getLogger(LoginServiceHandlerImpl.class);
	
	private final String CLASS_NAME = LoginServiceHandlerImpl.class.getPackage().getName();
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private PartnerDao partnerDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PageHitsDao pageHitsDao;
	
	@Override
	public Response authenticateUser(LoginDetails loginDetails, HttpServletRequest request, HttpSession session) {
	
		logger.info("Entering :: " + CLASS_NAME + " :: authenticateUser method");
		
		Response response = new Response();
		try {
			String userType = loginDetails.getUserType();
			
			logger.debug("++++++++    User Type :: " + userType + "++++++++");
			
			if(!CommonUtil.isNull(userType) && userType.toUpperCase().equals(AppEntityEnum.ADMIN.value())) {
				response = authenticateAdmin(loginDetails);
			} else if(!CommonUtil.isNull(userType) && userType.toUpperCase().equals(AppEntityEnum.PARTNER.value())) {
				response = authenticatePartner(loginDetails);
			} else if(!CommonUtil.isNull(userType) && userType.toUpperCase().equals(AppEntityEnum.USER.value())) {
				response = authenticateUser(loginDetails);
			}
			
			loginDetails.setPassword("");
			loginDetails.setjSessionId(session.getId());
			loginDetails.setLoginIpAddress(request.getRemoteAddr());
			session.setAttribute(EMS_USER_LOGIN_DETAILS, loginDetails);
			
		} catch(DataAccessException | ParseException e) {
			CommonUtil.populateResponseErrorData(response, e);
			logger.error("ERROR :: " + CLASS_NAME + " :: authenticateUser method", e);
		}
		
		logger.info("Exiting :: " + CLASS_NAME + " :: authenticateUser method");
		return response;
				
	}
	
	private Response authenticateAdmin(LoginDetails loginDetails) throws DataAccessException, ParseException {

		Response response = new Response();
		AdminInfo adminInfo = adminDao.isValidUser(loginDetails.getUserName(), loginDetails.getEmailId(), CryptographHandler.encodePassword(loginDetails.getPassword()));
		if(adminInfo != null) {

			if(adminInfo.getStatus().equalsIgnoreCase(DbConstantsEnum.ACTIVE.name())) {
				
				adminDao.updateLoginDateTime(adminInfo.getId(), DateTimeUtil.getCurrentTimeStampForDb());
				
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS442);

				loginDetails.setId(adminInfo.getId());
				loginDetails.setUserName(adminInfo.getUserName());
				loginDetails.setEmailId(adminInfo.getEmailId());
				loginDetails.setUserType((adminInfo.getAdminType().equals(DbConstantsEnum.SUPER_ADMIN.name()) ? AppEntityEnum.SUPER_ADMIN.value() : AppEntityEnum.ADMIN.value() ));
				/*loginDetails.setjSessionId(session.getId());
				loginDetails.setLoginIpAddress(request.getRemoteAddr());
				loginDetails.setPassword("");*/

				PageHits pageHits = pageHitsDao.getCurrentDayPageHits(DateTimeUtil.getCurrentDateInString());

				Map<String,Object> responseObj = new HashMap<>();
				responseObj.put(EMS_USER_LOGIN_DETAILS, loginDetails);
				responseObj.put(EMS_CURRENT_DAY_PAGEHITS_COUNT, (CommonUtil.isNull(pageHits)) ? 0 : pageHits.getHitCount());

				response.setResponseObj(responseObj);
				/*session.setAttribute(EMS_USER_LOGIN_DETAILS, loginDetails);*/
			} else {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS444);
			}

		} else {
			CommonUtil.populateResponseMsgData(response, AppCodes.EAS443);
		}
		CommonUtil.populateResponseSuccessData(response);

		// session.setAttribute(EMS_USER_LOGIN_DETAILS, loginDetails);
		
		return response;
	}
	
	private Response authenticatePartner(LoginDetails loginDetails) throws DataAccessException, ParseException {

		Response response = new Response();
		PartnerInfo partnerInfo = partnerDao.isValidPartner(loginDetails.getUserName(), loginDetails.getEmailId(), CryptographHandler.encodePassword(loginDetails.getPassword()));
		if(partnerInfo != null) {

			if(partnerInfo.getStatus().equalsIgnoreCase(DbConstantsEnum.ACTIVE.name())) {
				
				partnerDao.updateLoginDateTime(partnerInfo.getId(), DateTimeUtil.getCurrentTimeStampForDb());
				
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS552);

				loginDetails.setId(partnerInfo.getId());
				loginDetails.setUserName(partnerInfo.getUserName());
				loginDetails.setEmailId(partnerInfo.getEmailId());
				loginDetails.setUserType(AppEntityEnum.PARTNER.value());
				/*loginDetails.setjSessionId(session.getId());
				loginDetails.setLoginIpAddress(request.getRemoteAddr());*/
				loginDetails.setPassword("");

				Map<String,Object> responseObj = new HashMap<>();
				responseObj.put(EMS_USER_LOGIN_DETAILS, loginDetails);

				response.setResponseObj(responseObj);
				/*session.setAttribute(EMS_USER_LOGIN_DETAILS, loginDetails);*/
			} else {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS554);
			}

		} else {
			CommonUtil.populateResponseMsgData(response, AppCodes.EAS553);
		}
		CommonUtil.populateResponseSuccessData(response);

		// session.setAttribute(EMS_USER_LOGIN_DETAILS, loginDetails);
		
		return response;
	}
	
	private Response authenticateUser(LoginDetails loginDetails) throws DataAccessException, ParseException {
		Response response = new Response();
		UserInfo userInfo = userDao.isValidUser(loginDetails.getUserName(), loginDetails.getEmailId(), CryptographHandler.encodePassword(loginDetails.getPassword()));
		if(userInfo != null) {

			if(userInfo.getStatus().equalsIgnoreCase(DbConstantsEnum.ACTIVE.name())) {
				
				partnerDao.updateLoginDateTime(userInfo.getId(), DateTimeUtil.getCurrentTimeStampForDb());
				
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS772);

				loginDetails.setId(userInfo.getId());
				loginDetails.setUserName(userInfo.getUserName());
				loginDetails.setEmailId(userInfo.getEmailId());
				loginDetails.setUserType(AppEntityEnum.USER.value());
				/*loginDetails.setjSessionId(session.getId());
				loginDetails.setLoginIpAddress(request.getRemoteAddr());*/
				loginDetails.setPassword("");

				Map<String,Object> responseObj = new HashMap<>();
				responseObj.put(EMS_USER_LOGIN_DETAILS, loginDetails);

				response.setResponseObj(responseObj);
				/*session.setAttribute(EMS_USER_LOGIN_DETAILS, loginDetails);*/
			} else {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS774);
			}

		} else {
			CommonUtil.populateResponseMsgData(response, AppCodes.EAS773);
		}
		CommonUtil.populateResponseSuccessData(response);

		// session.setAttribute(EMS_USER_LOGIN_DETAILS, loginDetails);
		
		return response;
	}
	
}