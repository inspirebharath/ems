/**
 * 
 */
package com.ems.user.rest.handler.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ems.user.beans.EventDTO;
import com.ems.user.beans.FileInfoBean;
import com.ems.user.beans.LoginDetails;
import com.ems.user.beans.Response;
import com.ems.user.beans.UniqueDataBean;
import com.ems.user.constants.AppCodes;
import com.ems.user.constants.AppEntityEnum;
import com.ems.user.constants.Constants;
import com.ems.user.constants.DbConstantsEnum;
import com.ems.user.constants.SessionConstants;
import com.ems.user.dao.EventDao;
import com.ems.user.dao.model.EventInfo;
import com.ems.user.rest.handler.EmailServiceHandler;
import com.ems.user.rest.handler.EventServiceHandler;
import com.ems.user.util.BeanUtil;
import com.ems.user.util.CommonUtil;
import com.ems.user.util.DateTimeUtil;
import com.ems.user.util.FileUtil;

/**
 * @author Bharath Arya
 *
 */
@Service
public class EventServiceHandlerImpl implements EventServiceHandler, SessionConstants, Constants {
	
	private static Logger logger = Logger.getLogger(EventServiceHandlerImpl.class);
	
	private final String CLASS_NAME = PartnerServiceHandlerImpl.class.getPackage().getName();
	
	@Autowired
	private EmailServiceHandler emailServiceHandler;
	
	@Autowired
	private EventDao eventDao;

	@Override
	public Response checkDataExistance(UniqueDataBean uniqueDataBean) {
		
		logger.info("Entering ::" + CLASS_NAME + ":: checkDataExistance method");

		Response response = new Response();
		try {
			
			if(eventDao.checkDataExistance(uniqueDataBean.getEventName())) {
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
	public Response registerEvent(EventDTO eventDTO, HttpSession session) {
		logger.info("Entering ::" + CLASS_NAME + ":: registerEvent method");

		Response response = new Response();
		try {
			
			String sessionId = session.getId();
			
			eventDTO.setEventStatus(DbConstantsEnum.CREATED.value());
			
			String tempCreatedBy = UNKNOWN;
			if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
				LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
				tempCreatedBy = loginDetails.getUserName();
			}
			eventDTO.setCreatedBy(tempCreatedBy);
			

			EventInfo eventInfo = BeanUtil.copyAllBeanProperties(eventDTO, EventInfo.class); 
			
			eventInfo.setStartDate(DateTimeUtil.getTimestamp(eventDTO.getStartDate()));
			eventInfo.setEndDate(DateTimeUtil.getTimestamp(eventDTO.getEndDate()));
			
			eventInfo = eventDao.createEvent(eventInfo);
			
			if(!CommonUtil.isNull(eventInfo)) {
				FileUtil fileUtil = new FileUtil();
				FileInfoBean fileInfoBean = new FileInfoBean();
				fileInfoBean.setEntityType(AppEntityEnum.EVENTS.value());
				fileUtil.moveFile(sessionId, fileInfoBean, eventInfo.getEventId().toString());
			}
			
			CommonUtil.populateResponseSuccessData(response, AppCodes.EAS60);

			// To Do Work on it by discussing.
			if (CommonUtil.isNull(eventInfo) && eventInfo.getEventStatus().equals(DbConstantsEnum.CREATED.value())) {
				
				Map<String, String> infoMap = new HashMap<>();
				infoMap.put("firstName", eventInfo.getEventName());
				infoMap.put("userName", eventInfo.getEventType());
				infoMap.put("hLink", "localhost:8090/AdventureFunda/admin");
				
				emailServiceHandler.sendEmail(infoMap, "/admin_create.vm", new String[]{}, response);
			}
		} catch(DataAccessException | InstantiationException | IllegalAccessException | IOException | ParseException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS61, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: registerEvent method ::::", e);
		}

		logger.info("Exiting ::" + CLASS_NAME + ":: registerEvent method");
		return response;
	}

	@Override
	public Response getSpecificEvent(Long eventId) {
		logger.info("Entering ::" + CLASS_NAME + ":: getSpecificEvent method");

		Response response = new Response();
		try {

			EventInfo eventInfo = eventDao.getSpecificEvent(eventId);
			
			if(!CommonUtil.isNull(eventInfo)) {

				Map<String,Object> responseObj = new HashMap<>();
				responseObj.put(EMS_SPECIFIC_ADMIN_DATA, eventInfo);
				response.setResponseObj(responseObj);

			}  else {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS661);
			}
			
			CommonUtil.populateResponseSuccessData(response);
			
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: getSpecificEvent method ::::", e);
		}

		logger.info("Exiting ::" + CLASS_NAME + ":: getSpecificEvent method");
		return response;
	}

	@Override
	public Response updateEventInfo(EventDTO eventDTO, HttpSession session) {
		logger.info("Entering ::" + CLASS_NAME + ":: updateEventInfo method");

		Response response = new Response();
		try {

			EventInfo eventInfo = eventDao.getSpecificEvent(eventDTO.getEventId());

			if(!CommonUtil.isNull(eventInfo)) {
				// To do see and set the parameters that can be modified
				/*adminInfo.setFirstName(adminDto.getFirstName());
				adminInfo.setLastName(adminDto.getLastName());*/
				//adminInfo.setStatus(adminDto.getStatus());
				eventInfo.setUpdatedDate(DateTimeUtil.getCurrentTimeStampForDb());
				
				String tempUpdatedBy = UNKNOWN;
				if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
					LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
					tempUpdatedBy = loginDetails.getUserName();
				}
				eventInfo.setUpdatedBy(tempUpdatedBy);

				eventInfo = eventDao.updateEventInfo(eventInfo);

				if(!CommonUtil.isNull(eventInfo)) {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS64);
				} else {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS65);
				}

			} else {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS661);
			}

			CommonUtil.populateResponseSuccessData(response);
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS65, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: updateEventInfo method ::::", e);
		}

		logger.info("Exiting ::" + CLASS_NAME + ":: updateEventInfo method");
		return response;
	}

	@Override
	public Response deleteEvent(Long eventId, String reason, HttpSession session) {
		logger.info("Entering ::" + CLASS_NAME + ":: deleteEvent method");

		Response response = new Response();
		try {

			EventInfo eventInfo = eventDao.getSpecificEvent(eventId);
			if(!CommonUtil.isNull(eventInfo)) {
				eventInfo.setEventStatus(DbConstantsEnum.DELETED.name());
				eventInfo.setUpdatedDate(DateTimeUtil.getCurrentTimeStampForDb());
				eventInfo.setReason(reason);
				
				String tempCreatedBy = UNKNOWN;
				if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
					LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
					tempCreatedBy = loginDetails.getUserName();
				}
				eventInfo.setUpdatedBy(tempCreatedBy);

				eventInfo = eventDao.updateEventInfo(eventInfo);

				if(!CommonUtil.isNull(eventInfo) && eventInfo.getEventStatus().equals(DbConstantsEnum.DELETED.name())) {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS62);
				} else {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS63);
				}
			} else {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS661);
			}

			CommonUtil.populateResponseSuccessData(response);
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS63, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: deleteEvent method ::::", e);
		}

		logger.info("Exiting ::" + CLASS_NAME + ":: deleteEvent method");
		return response;
	}

	@Override
	public Response changeEventStatus(Long eventId, String status, String reason, HttpSession session) {
		logger.info("Entering ::" + CLASS_NAME + ":: changeEventStatus method");

		Response response = new Response();
		try {

			EventInfo eventInfo = eventDao.getSpecificEvent(eventId);

			if(!CommonUtil.isNull(eventInfo)) {

				String previousStatus = eventInfo.getEventStatus();

				eventInfo.setEventStatus(status.toUpperCase());
				eventInfo.setUpdatedDate(DateTimeUtil.getCurrentTimeStampForDb());
				eventInfo.setReason(reason);
				
				String tempCreatedBy = UNKNOWN;
				if(!CommonUtil.isNull(session.getAttribute(EMS_USER_LOGIN_DETAILS))) {
					LoginDetails loginDetails = (LoginDetails) session.getAttribute(EMS_USER_LOGIN_DETAILS);
					tempCreatedBy = loginDetails.getUserName();
				}
				eventInfo.setUpdatedBy(tempCreatedBy);

				eventInfo = eventDao.updateEventInfo(eventInfo);

				if(!CommonUtil.isNull(eventInfo) && !eventInfo.getEventStatus().equals(previousStatus)) {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS66);
				} else {
					CommonUtil.populateResponseMsgData(response, AppCodes.EAS67);
				}
			} else {
				CommonUtil.populateResponseMsgData(response, AppCodes.EAS661);
			}

			CommonUtil.populateResponseSuccessData(response);
		} catch(DataAccessException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS67, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: changeEventStatus method ::::", e);
		}

		logger.info("Exiting ::" + CLASS_NAME + ":: changeEventStatus method");
		return response;
	}
	
	@Override
	public Response searchEvents(EventDTO eventDTO) {
		logger.info("Entering ::" + CLASS_NAME + ":: searchEvents method");

		Response response = new Response();
		try {
			eventDao.searchEvents(eventDTO);

			Map<String,Object> responseObj = new HashMap<>();
			responseObj.put(EMS_ADMIN_SEARCH_DATA, eventDTO);
			response.setResponseObj(responseObj);

			CommonUtil.populateResponseSuccessData(response, AppCodes.EAS68);

		} catch(DataAccessException | ParseException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS69, e);
			logger.error("ERROR ::" + CLASS_NAME + ":: searchEvents method ::::", e);
		}

		logger.info("Exiting ::" + CLASS_NAME + ":: searchEvents method");
		return response;
	}

}