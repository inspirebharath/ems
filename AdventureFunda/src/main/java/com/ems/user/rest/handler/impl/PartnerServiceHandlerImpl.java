/**
 * 
 */
package com.ems.user.rest.handler.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ems.user.beans.FileInfoBean;
import com.ems.user.beans.LoginDetails;
import com.ems.user.beans.PartnerAdditionalData;
import com.ems.user.beans.PartnerDTO;
import com.ems.user.beans.Response;
import com.ems.user.beans.UniqueDataBean;
import com.ems.user.constants.AppCodes;
import com.ems.user.constants.AppEntityEnum;
import com.ems.user.constants.Constants;
import com.ems.user.constants.DbConstantsEnum;
import com.ems.user.constants.EmailConstants;
import com.ems.user.constants.SessionConstants;
import com.ems.user.dao.PartnerDao;
import com.ems.user.dao.model.PartnerInfo;
import com.ems.user.exception.EmsException;
import com.ems.user.rest.handler.EmailServiceHandler;
import com.ems.user.rest.handler.PartnerServiceHandler;
import com.ems.user.util.BeanUtil;
import com.ems.user.util.CommonUtil;
import com.ems.user.util.CryptographHandler;
import com.ems.user.util.DateTimeUtil;
import com.ems.user.util.FileUtil;
import com.ems.user.util.JsonUtil;
import com.ems.user.util.Properties;

/**
 * @author Bharath Arya
 *
 */
@Service
public class PartnerServiceHandlerImpl implements PartnerServiceHandler, SessionConstants, Constants {

	private static Logger logger = Logger.getLogger(PartnerServiceHandlerImpl.class);
	
	private final String CLASS_NAME = PartnerServiceHandlerImpl.class.getPackage().getName();
	
	@Autowired
	private EmailServiceHandler emailServiceHandler;
	
	@Autowired
	private PartnerDao partnerDao;
	
	@Override
	public Response checkDataExistance(UniqueDataBean uniqueDataBean) {
		logger.info("Entering ::" + CLASS_NAME + ":: checkDataExistance method");

		Response response = new Response();
		try {
			
			/*String userName = (CommonUtil.isNull(uniqueDataBean.getUserName())) ? null : uniqueDataBean.getUserName().toUpperCase();
			String emailId = (CommonUtil.isNull(uniqueDataBean.getEmailId())) ? null : uniqueDataBean.getEmailId().toUpperCase();*/
			
			if(partnerDao.checkDataExistance(uniqueDataBean.getUserName(), uniqueDataBean.getEmailId(), uniqueDataBean.getMobileNo())) {
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
	public Response registerPartner(PartnerDTO partnerDto, HttpSession session) {
		logger.info("Entering ::" + CLASS_NAME + ":: registerPartner method");

		Response response = new Response();
		try {
			
			String sessionId = session.getId();
			
			String password = CryptographHandler.getSystemGeneratedPassword(10); 

			password = CryptographHandler.encodePassword(password);

			partnerDto.setPassword(password);
			partnerDto.setStatus(DbConstantsEnum.CREATED.value());
			
			String tempCreatedBy = UNKNOWN;
			if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
				LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
				tempCreatedBy = loginDetails.getUserName();
			}
			partnerDto.setCreatedBy(tempCreatedBy);

			PartnerInfo partnerInfo = BeanUtil.copyAllBeanProperties(partnerDto, PartnerInfo.class); 
			
			partnerInfo = partnerDao.createPartner(partnerInfo);
			
			if(!CommonUtil.isNull(partnerInfo) && partnerInfo.getStatus().equals(DbConstantsEnum.CREATED.value())) {
				
				String userType = AppEntityEnum.PARTNER.value();
				
				FileUtil fileUtil = new FileUtil();
				FileInfoBean fileInfoBean = new FileInfoBean();
				fileInfoBean.setEntityType(userType);
				fileInfoBean.setUploadedFilesFlag(partnerDto.getUploadedFilesFlag());
				
				PartnerAdditionalData partnerAdditionalData = null;
				try {
					fileUtil.moveFile(sessionId, fileInfoBean, partnerInfo.getId().toString());
				
					partnerAdditionalData = BeanUtil.copyAllBeanProperties(partnerDto, PartnerAdditionalData.class);
					partnerAdditionalData.setId(partnerInfo.getId());
				
					fileUtil.createEntityDataFile(userType, partnerInfo.getId().toString(), JsonUtil.convertObjectToJSON(partnerAdditionalData));
				} catch (EmsException | IOException e) {
					response.setResponseMessage(AppCodes.EAS15.value());
				}
				
				Map<String, String> infoMap = new HashMap<>();
				infoMap.put("firstName", partnerInfo.getFirstName());
				infoMap.put("userName", partnerInfo.getUserName());
				infoMap.put("tempPassword", password);
				infoMap.put("hLink", Properties.getProperty("ems.app.partner.link"));
				
				emailServiceHandler.sendEmail(infoMap, EmailConstants.ADMIN_REGISTER, new String[]{partnerInfo.getEmailId()}, response);
				
				infoMap.put("firstName", partnerInfo.getFirstName());
				infoMap.put("userName", partnerInfo.getUserName());
				infoMap.put("mountainsClimbed", partnerAdditionalData.getMountainsClimbed());
				infoMap.put("leadersDescription", partnerAdditionalData.getLeadersDescription());
				infoMap.put("facilityProvided", partnerAdditionalData.getFacilityProvided());
				infoMap.put("successStory", partnerAdditionalData.getSuccessStory());
				
				emailServiceHandler.sendEmail(infoMap, "/admin_create.vm", new String[]{partnerInfo.getEmailId()}, response);
				
			}
			
			CommonUtil.populateResponseSuccessData(response, AppCodes.EAS50);

		} catch(DataAccessException | InstantiationException | IllegalAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS51, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: registerPartner method ::::", e);
		}

		logger.info("Exiting ::" + CLASS_NAME + ":: registerPartner method");
		return response;
	}

	@Override
	public Response getSpecificPartner(Long partnerId) {
		logger.info("Entering ::" + CLASS_NAME + ":: getSpecificPartner method");
		
		Response response = new Response();
		try {
			PartnerInfo partnerInfo = partnerDao.getSpecificPartner(partnerId);
			
			if(!CommonUtil.isNull(partnerInfo)) {
				partnerInfo.setPassword("");

				Map<String,Object> responseObj = new HashMap<>();
				responseObj.put(EMS_SPECIFIC_PARTNER_DATA, partnerInfo);
				response.setResponseObj(responseObj);

			} else {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS551);
			}

			CommonUtil.populateResponseSuccessData(response);
			
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: getSpecificPartner method ::::", e);
		}
		
		logger.info("Exiting ::" + CLASS_NAME + ":: getSpecificPartner method");
		return response;
	}

	@Override
	public Response updatePartnerInfo(PartnerDTO partnerDto, HttpSession session) {
		logger.info("Entering ::" + CLASS_NAME + ":: updatePartnerInfo method");
		
		Response response = new Response();
		try {

			PartnerInfo partnerInfo = partnerDao.getSpecificPartner(partnerDto.getId());

			if(!CommonUtil.isNull(partnerInfo)) {
				partnerInfo.setFirstName(partnerDto.getFirstName());
				partnerInfo.setLastName(partnerDto.getLastName());
				//partnerInfo.setStatus(partnerDto.getStatus());
				partnerInfo.setUpdatedDate(DateTimeUtil.getCurrentTimeStampForDb());
				
				String tempUpdatedBy = UNKNOWN;
				if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
					LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
					tempUpdatedBy = loginDetails.getUserName();
				}
				partnerDto.setCreatedBy(tempUpdatedBy);

				partnerInfo = partnerDao.updatePartnerInfo(partnerInfo);

				if(!CommonUtil.isNull(partnerInfo)) {
					CommonUtil.populateResponseSuccessData(response, AppCodes.EAS54);
				} else {
					CommonUtil.populateResponseSuccessData(response, AppCodes.EAS55);
				}

			} else {
				CommonUtil.populateResponseSuccessData(response, AppCodes.EAS551);
			}
			
			CommonUtil.populateResponseSuccessData(response);
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS55, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: updatePartnerInfo method ::::", e);
		}
		
		logger.info("Exiting ::" + CLASS_NAME + ":: updatePartnerInfo method");
		return response;
		
	}

	@Override
	public Response deletePartner(Long partnerId, String reason, HttpSession session) {
		logger.info("Entering ::" + CLASS_NAME + ":: deletePartner method");
		
		Response response = new Response();
		try {
			
			PartnerInfo partnerInfo = partnerDao.getSpecificPartner(partnerId);
			if(!CommonUtil.isNull(partnerInfo)) {
				partnerInfo.setStatus(DbConstantsEnum.DELETED.name());
				partnerInfo.setUpdatedDate(DateTimeUtil.getCurrentTimeStampForDb());
				partnerInfo.setReason(reason);
				
				String tempCreatedBy = UNKNOWN;
				if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
					LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
					tempCreatedBy = loginDetails.getUserName();
				}
				partnerInfo.setCreatedBy(tempCreatedBy);

				partnerInfo = partnerDao.updatePartnerInfo(partnerInfo);

				if(!CommonUtil.isNull(partnerInfo) && partnerInfo.getStatus().equals(DbConstantsEnum.DELETED.name())) {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS52);
				} else {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS53);
				}
			} else {
				CommonUtil.populateResponseSuccessData(response, AppCodes.EAS551);
			}

			CommonUtil.populateResponseSuccessData(response);
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS53, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: deletePartner method ::::", e);
		}
		
		logger.info("Exiting ::" + CLASS_NAME + ":: deletePartner method");
		return response;
	}

	@Override
	public Response changePartnerStatus(Long partnerId, String status, String reason, HttpSession session) {
		
		logger.info("Entering ::" + CLASS_NAME + ":: changePartnerStatus method");
		
		Response response = new Response();
		try {

			PartnerInfo partnerInfo = partnerDao.getSpecificPartner(partnerId);

			if(!CommonUtil.isNull(partnerInfo)) {

				String previousStatus = partnerInfo.getStatus();

				partnerInfo.setStatus(status.toUpperCase());
				partnerInfo.setUpdatedDate(DateTimeUtil.getCurrentTimeStampForDb());
				partnerInfo.setReason(reason);
				
				String tempCreatedBy = UNKNOWN;
				if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
					LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
					tempCreatedBy = loginDetails.getUserName();
				}
				partnerInfo.setCreatedBy(tempCreatedBy);

				partnerInfo = partnerDao.updatePartnerInfo(partnerInfo);

				if(!CommonUtil.isNull(partnerInfo) && !partnerInfo.getStatus().equals(previousStatus)) {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS56);
				} else {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS57);
				}
			} else {
				CommonUtil.populateResponseSuccessData(response, AppCodes.EAS551);
			}

			CommonUtil.populateResponseSuccessData(response);
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS57, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: changePartnerStatus method ::::", e);
		}
		
		logger.info("Exiting ::" + CLASS_NAME + ":: changePartnerStatus method");
		return response;
	}

	@Override
	public Response searchPartners(PartnerDTO partnerDto) {
		logger.info("Entering ::" + CLASS_NAME + ":: searchPartners method");
		
		Response response = new Response();
		try {
			partnerDao.searchPartners(partnerDto);

			Map<String,Object> responseObj = new HashMap<>();
			responseObj.put(EMS_PARTNER_SEARCH_DATA, partnerDto);
			response.setResponseObj(responseObj);

			CommonUtil.populateResponseSuccessData(response, AppCodes.EAS58);
			
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS59, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: searchAdmins method ::::", e);
		}

		logger.info("Exiting ::" + CLASS_NAME + ":: searchAdmins method");
		return response;
		
	}
	
}