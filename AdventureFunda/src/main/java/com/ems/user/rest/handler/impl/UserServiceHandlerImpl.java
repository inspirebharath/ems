/**
 * 
 */
package com.ems.user.rest.handler.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ems.user.beans.LoginDetails;
import com.ems.user.beans.Response;
import com.ems.user.beans.UniqueDataBean;
import com.ems.user.beans.UserDTO;
import com.ems.user.constants.AppCodes;
import com.ems.user.constants.Constants;
import com.ems.user.constants.DbConstantsEnum;
import com.ems.user.constants.SessionConstants;
import com.ems.user.dao.UserDao;
import com.ems.user.dao.model.UserInfo;
import com.ems.user.rest.handler.EmailServiceHandler;
import com.ems.user.rest.handler.UserServiceHandler;
import com.ems.user.util.BeanUtil;
import com.ems.user.util.CommonUtil;
import com.ems.user.util.CryptographHandler;
import com.ems.user.util.DateTimeUtil;
import com.ems.user.util.Properties;

/**
 * @author Bharath Arya
 *
 */
@Service
public class UserServiceHandlerImpl implements UserServiceHandler, SessionConstants, Constants {

	private static Logger logger = Logger.getLogger(PartnerServiceHandlerImpl.class);
	
	private final String CLASS_NAME = UserServiceHandlerImpl.class.getPackage().getName();
	
	@Autowired
	private EmailServiceHandler emailServiceHandler;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Response checkDataExistance(UniqueDataBean uniqueDataBean) {
		logger.info("Entering ::" + CLASS_NAME + ":: checkDataExistance method");

		Response response = new Response();
		try {
			
			/*String userName = (CommonUtil.isNull(uniqueDataBean.getUserName())) ? null : uniqueDataBean.getUserName().toUpperCase();
			String emailId = (CommonUtil.isNull(uniqueDataBean.getEmailId())) ? null : uniqueDataBean.getEmailId().toUpperCase();*/
			
			if(userDao.checkDataExistance(uniqueDataBean.getUserName(), uniqueDataBean.getEmailId(), uniqueDataBean.getMobileNo())) {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS01);
			} else {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS02);
			}
			
			CommonUtil.populateResponseSuccessData(response);
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: checkDataExistance method ::::", e);
		}

		logger.info("Exiting ::" + CLASS_NAME + ":: checkDataExistance method");
		return response;
	}

	@Override
	public Response registerUser(UserDTO userDto, HttpSession session) {
		logger.info("Entering ::" + CLASS_NAME + ":: registerUser method");

		Response response = new Response();
		try {
			
			// String sessionId = session.getId(); // use it in file management
			
			String password = CryptographHandler.getSystemGeneratedPassword(10); 

			password = CryptographHandler.encodePassword(password);

			userDto.setPassword(password);
			userDto.setStatus(DbConstantsEnum.CREATED.value());
			
			String tempCreatedBy = SELF;
			if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
				LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
				tempCreatedBy = loginDetails.getUserName();
			}
			userDto.setCreatedBy(tempCreatedBy);

			UserInfo userInfo = BeanUtil.copyAllBeanProperties(userDto, UserInfo.class); 
			
			userInfo = userDao.createUser(userInfo);
			
			if(!CommonUtil.isNull(userInfo)) {
				
				if (userInfo.getStatus().equals(DbConstantsEnum.CREATED.value())) {
					
					Map<String, String> infoMap = new HashMap<>();
					infoMap.put("userName", userInfo.getUserName());
					infoMap.put("hLink", Properties.getProperty("ems.app.home.link"));
					
					emailServiceHandler.sendEmail(infoMap, "/admin_create.vm", new String[]{userInfo.getEmailId()}, response);
					
				}
				
			}
			
			CommonUtil.populateResponseSuccessData(response, AppCodes.EAS70);

		} catch(DataAccessException | InstantiationException | IllegalAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS71, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: registerUser method ::::", e);
		}

		logger.info("Exiting ::" + CLASS_NAME + ":: registerUser method");
		return response;
	}

	@Override
	public Response getSpecificUser(Long userId) {
		logger.info("Entering ::" + CLASS_NAME + ":: getSpecificUser method");
		
		Response response = new Response();
		try {
			UserInfo userInfo = userDao.getSpecificUser(userId);
			
			if(!CommonUtil.isNull(userInfo)) {
				userInfo.setPassword("");

				Map<String,Object> responseObj = new HashMap<>();
				responseObj.put(EMS_SPECIFIC_USER_DATA, userInfo);
				response.setResponseObj(responseObj);

			} else {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS771);
			}

			CommonUtil.populateResponseSuccessData(response);
			
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: getSpecificUser method ::::", e);
		}
		
		logger.info("Exiting ::" + CLASS_NAME + ":: getSpecificUser method");
		return response;
	}

	@Override
	public Response updateUserInfo(UserDTO userDto, HttpSession session) {
		logger.info("Entering ::" + CLASS_NAME + ":: updateUserInfo method");
		
		Response response = new Response();
		try {

			UserInfo userInfo = userDao.getSpecificUser(userDto.getId());

			if(!CommonUtil.isNull(userInfo)) {
				userInfo.setFirstName(userDto.getFirstName());
				userInfo.setLastName(userDto.getLastName());
				userInfo.setUpdatedDate(DateTimeUtil.getCurrentTimeStampForDb());
				
				String tempCreatedBy = UNKNOWN;
				if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
					LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
					tempCreatedBy = loginDetails.getUserName();
				}
				userDto.setCreatedBy(tempCreatedBy);

				userInfo = userDao.updateUserInfo(userInfo);

				if(!CommonUtil.isNull(userInfo)) {
					CommonUtil.populateResponseSuccessData(response, AppCodes.EAS74);
				} else {
					CommonUtil.populateResponseSuccessData(response, AppCodes.EAS75);
				}
				
				// Include logic for handling files (profile pic)

			} else {
				CommonUtil.populateResponseSuccessData(response, AppCodes.EAS771);
			}
			
			CommonUtil.populateResponseSuccessData(response);
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS75, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: updateUserInfo method ::::", e);
		}
		
		logger.info("Exiting ::" + CLASS_NAME + ":: updateUserInfo method");
		return response;
	}

	@Override
	public Response deleteUser(Long userId, String reason, HttpSession session) {
		logger.info("Entering ::" + CLASS_NAME + ":: deleteUser method");
		
		Response response = new Response();
		try {
			
			UserInfo userInfo = userDao.getSpecificUser(userId);
			if(!CommonUtil.isNull(userInfo)) {
				userInfo.setStatus(DbConstantsEnum.DELETED.name());
				userInfo.setUpdatedDate(DateTimeUtil.getCurrentTimeStampForDb());
				userInfo.setReason(reason);
				
				String tempCreatedBy = UNKNOWN;
				if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
					LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
					tempCreatedBy = loginDetails.getUserName();
				}
				userInfo.setCreatedBy(tempCreatedBy);

				userInfo = userDao.updateUserInfo(userInfo);

				if(!CommonUtil.isNull(userInfo) && userInfo.getStatus().equals(DbConstantsEnum.DELETED.name())) {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS72);
				} else {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS73);
				}
			} else {
				CommonUtil.populateResponseSuccessData(response, AppCodes.EAS771);
			}

			CommonUtil.populateResponseSuccessData(response);
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS73, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: deleteUser method ::::", e);
		}
		
		logger.info("Exiting ::" + CLASS_NAME + ":: deleteUser method");
		return response;
	}

	@Override
	public Response changeUserStatus(Long userId, String status, String reason, HttpSession session) {
		logger.info("Entering ::" + CLASS_NAME + ":: changeUserStatus method");
		
		Response response = new Response();
		try {

			UserInfo userInfo = userDao.getSpecificUser(userId);

			if(!CommonUtil.isNull(userInfo)) {

				String previousStatus = userInfo.getStatus();

				userInfo.setStatus(status.toUpperCase());
				userInfo.setUpdatedDate(DateTimeUtil.getCurrentTimeStampForDb());
				userInfo.setReason(reason);
				
				String tempCreatedBy = UNKNOWN;
				if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
					LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
					tempCreatedBy = loginDetails.getUserName();
				}
				userInfo.setCreatedBy(tempCreatedBy);

				userInfo = userDao.updateUserInfo(userInfo);

				if(!CommonUtil.isNull(userInfo) && !userInfo.getStatus().equals(previousStatus)) {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS76);
				} else {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS77);
				}
			} else {
				CommonUtil.populateResponseSuccessData(response, AppCodes.EAS771);
			}

			CommonUtil.populateResponseSuccessData(response);
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS77, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: changeUserStatus method ::::", e);
		}
		
		logger.info("Exiting ::" + CLASS_NAME + ":: changeUserStatus method");
		return response;
	}

	@Override
	public Response searchUsers(UserDTO userDto) {
		logger.info("Entering ::" + CLASS_NAME + ":: searchUsers method");
		
		Response response = new Response();
		try {
			userDao.searchUsers(userDto);

			Map<String,Object> responseObj = new HashMap<>();
			responseObj.put(EMS_PARTNER_SEARCH_DATA, userDto);
			response.setResponseObj(responseObj);

			CommonUtil.populateResponseSuccessData(response, AppCodes.EAS78);
			
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS79, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: searchUsers method ::::", e);
		}

		logger.info("Exiting ::" + CLASS_NAME + ":: searchUsers method");
		return response;
		
	}
	
}