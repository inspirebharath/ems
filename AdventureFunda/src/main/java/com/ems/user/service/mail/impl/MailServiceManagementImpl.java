/**
 * 
 */
package com.ems.user.service.mail.impl;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ems.user.beans.Response;
import com.ems.user.constants.NotificationResponseCode;
import com.ems.user.exception.NotificationServiceException;
import com.ems.user.service.mail.MailServiceManagement;

/**
 * @author Bharath Arya
 *
 */
@Service
public class MailServiceManagementImpl implements MailServiceManagement {

	private final String CLASS_NAME = MailServiceManagementImpl.class.getSimpleName();

	private static Logger logger = Logger.getLogger(MailServiceManagementImpl.class);

	@Autowired
	JavaMailSender javaMailSender;
	
	/**
	 * Method to send mail in Plain-Text format to toAddress.
	 * 
	 */
	public Response sendMailPlainText(String fromAddress, String text,
			String[] toAddress, String subject) throws NotificationServiceException {

		logger.info("Entering:: " + CLASS_NAME + " : " + "sendMailPlainText");

		Response response = new Response();
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom(new InternetAddress(fromAddress));
			helper.setFrom(fromAddress);
			helper.setTo(toAddress);
			helper.setText(text);
			helper.setSubject(subject);
		} catch (MessagingException e) {
			logger.error("ERROR:: " + CLASS_NAME + e.getMessage());
			response.setResponseCode(NotificationResponseCode.CONFIGURATION_ERROR_CODE);
			response.setResponseMessage(NotificationResponseCode.CONFIGURATION_ERROR_MESSAGE);
		}

		try {
			javaMailSender.send(message);
			response.setResponseCode(NotificationResponseCode.SUCCESS_CODE);
			response.setResponseMessage(NotificationResponseCode.SUCCESS_MESSAGE);
		} catch (MailException e) {
			logger.error("ERROR:: " + CLASS_NAME + e.getMessage());
			response.setResponseCode(NotificationResponseCode.MAIL_SEND_ERROR_CODE);
			response.setResponseMessage(NotificationResponseCode.MAIL_SEND_ERROR_MESSAGE);
		}

		logger.info("Exiting:: " + CLASS_NAME + " : " + "sendMailPlainText");
		return response;
	}

	/**
	 * Method to send mail in HTML format to toAddress.
	 * 
	 */
	public Response sendMailHtml(String fromAddress, String text,
			String[] toAddress, String subject) throws NotificationServiceException {

		logger.info("Entering:: " + CLASS_NAME + " : " + "sendMailHtml");
		Response response = new Response();
		MimeMessage mimeMessage = null;
		MimeMessageHelper mimeMessageHelper;

		try {
			mimeMessage = javaMailSender.createMimeMessage();
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessageHelper.setFrom(new InternetAddress(fromAddress));
			mimeMessage.setContent(text, "text/html");
			mimeMessageHelper.setTo(toAddress);
			mimeMessageHelper.setSubject(subject);
		} catch (MessagingException e) {
			logger.error("ERROR:: " + CLASS_NAME + e.getMessage());
			response.setResponseCode(NotificationResponseCode.CONFIGURATION_ERROR_CODE);
			response.setResponseMessage(NotificationResponseCode.CONFIGURATION_ERROR_MESSAGE);
		}

		try {
			javaMailSender.send(mimeMessage);
			response.setResponseCode(NotificationResponseCode.SUCCESS_CODE);
			response.setResponseMessage(NotificationResponseCode.SUCCESS_MESSAGE);
		} catch (MailException e) {
			logger.error("ERROR:: " + CLASS_NAME + e.getMessage());
			response.setResponseCode(NotificationResponseCode.MAIL_SEND_ERROR_CODE);
			response.setResponseMessage(NotificationResponseCode.MAIL_SEND_ERROR_MESSAGE);
		}

		logger.info("Exiting:: " + CLASS_NAME + " : " + "sendMailHtml");
		return response;

	}

	/**
	 * Method to send mail with attachments to toAddress specified.
	 * 
	 */
	public Response sendMailwithAttachment(String fromAddress, String text,
			String[] toAddress, String subject,
			Map<String, byte[]> attachmentMap) throws NotificationServiceException {

		logger.info("Entering:: " + CLASS_NAME + " : " + "sendMailwithAttachment");

		Response response = new Response();

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		try {

			helper = new MimeMessageHelper(message, true);
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(text);
			helper.setFrom(new InternetAddress(fromAddress));

			for (Map.Entry<String, byte[]> entry : attachmentMap.entrySet()) {
				String fileName = entry.getKey();
				byte[] file = entry.getValue();

				helper.addAttachment(fileName, new ByteArrayResource(file));
			}
		} catch (MessagingException e) {
			logger.error("ERROR:: " + CLASS_NAME + e.getMessage());
			response.setResponseCode(NotificationResponseCode.CONFIGURATION_ERROR_CODE);
			response.setResponseMessage(NotificationResponseCode.CONFIGURATION_ERROR_MESSAGE);
		}

		try {
			javaMailSender.send(message);
			response.setResponseCode(NotificationResponseCode.SUCCESS_CODE);
			response.setResponseMessage(NotificationResponseCode.SUCCESS_MESSAGE);
		} catch (MailException e) {
			logger.error("ERROR:: " + CLASS_NAME + e.getMessage());
			response.setResponseCode(NotificationResponseCode.MAIL_SEND_ERROR_CODE);
			response.setResponseMessage(NotificationResponseCode.MAIL_SEND_ERROR_MESSAGE);
		}

		logger.info("Exiting:: " + CLASS_NAME + " : " + "sendMailwithAttachment");
		return response;

	}

}