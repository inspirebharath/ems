/**
 * 
 */
package com.ems.user.rest.handler.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.user.beans.Response;
import com.ems.user.constants.AppCodes;
import com.ems.user.constants.EmailConstants;
import com.ems.user.exception.NotificationServiceException;
import com.ems.user.rest.handler.EmailServiceHandler;
import com.ems.user.service.mail.MailServiceManagement;
import com.ems.user.service.velocity.VelocityTemplateCreator;
import com.ems.user.util.Properties;

/**
 * @author Bharath Arya
 *
 */
@Service
public class EmailServiceHandlerImpl implements EmailServiceHandler {

	private static Logger logger = Logger.getLogger(EmailServiceHandlerImpl.class);
	
	@Autowired
	private VelocityTemplateCreator velocityTemplateCreator;

	@Autowired
	private MailServiceManagement mailServiceManagement;
	
	@Override
	public Response sendEmail(Map<String, String> infoMap, String templateName, String[] toAddresses, Response response) {

		logger.info("Entering :: EmailServiceHandlerImpl :: sendEmail method");
		
		// Assign from address and template file path to a static variable. Assign it in postConstruct
		
		try {

			String body = velocityTemplateCreator.createEmailTemplate(infoMap, 
					Properties.getProperty("ems.email.template.file.path") + templateName);
			mailServiceManagement.sendMailHtml(Properties.getProperty("ems.from.email.id"), body,
					toAddresses, Properties.getProperty(getEmailSubjectOnTemplate(templateName)));
			
		} catch (NotificationServiceException e) {
			response.setMessageCode(AppCodes.EAS12.name());
			response.setMessageDesc(AppCodes.EAS12.value());
			response.setResponseMessage(e.getErrorMessage());
			logger.error("ERROR :: EmailServiceHandlerImpl :: sendEmail method", e);
		} catch (Exception e) {
			response.setMessageCode(AppCodes.EAS12.name());
			response.setMessageDesc(AppCodes.EAS12.value());
			response.setResponseMessage(e.getMessage());
			logger.error("ERROR :: EmailServiceHandlerImpl :: sendEmail method", e);
		}
		
		logger.info("Exiting :: EmailServiceHandlerImpl :: sendEmail method");
		return response;
		
	}
	
	private String getEmailSubjectOnTemplate(String templateName) {
		
		switch(templateName) {
		
			case EmailConstants.ADMIN_REGISTER : return EmailConstants.ADMIN_REGISTER_SUBJECT;
			
			case EmailConstants.PARTNER_REGISTER : return EmailConstants.PARTNER_REGISTER_SUBJECT;
			
			case EmailConstants.USER_REGISTER : return EmailConstants.USER_REGISTER_SUBJECT;
			
			case EmailConstants.EVENT_REGISTER : return EmailConstants.EVENT_REGISTER_SUBJECT;
			
			case EmailConstants.ADMIN_UPDATE : return EmailConstants.ADMIN_UPDATE_SUBJECT;
			
			case EmailConstants.PARTNER_UPDATE : return EmailConstants.PARTNER_UPDATE_SUBJECT;
			
			case EmailConstants.USER_UPDATE : return EmailConstants.USER_UPDATE_SUBJECT;
			
			case EmailConstants.EVENT_UPDATE : return EmailConstants.EVENT_UPDATE_SUBJECT;
			
			case EmailConstants.ADMIN_STATUS_CHANGE : return EmailConstants.ADMIN_STATUS_CHANGE_SUBJECT;
			
			case EmailConstants.PARTNER_STATUS_CHANGE : return EmailConstants.PARTNER_STATUS_CHANGE_SUBJECT;
			
			case EmailConstants.USER_STATUS_CHANGE : return EmailConstants.USER_STATUS_CHANGE_SUBJECT;
			
			case EmailConstants.EVENT_STATUS_CHANGE : return EmailConstants.EVENT_STATUS_CHANGE_SUBJECT;
			
			case EmailConstants.USER_PWD_CHANGE : return EmailConstants.USER_PWD_CHANGE_SUBJECT;
			
			case EmailConstants.USER_PWD_RESET : return EmailConstants.USER_PWD_RESET_SUBJECT;
			
			default : return null;
			
		}
		
	}

}