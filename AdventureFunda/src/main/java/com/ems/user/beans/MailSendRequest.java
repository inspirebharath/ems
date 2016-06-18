/**
 * 
 */
package com.ems.user.beans;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Bharath Arya
 *
 */
public class MailSendRequest {

	private String text;

	private String[] toAddress;

	private String subject;

	private Map<String, byte[]> attachmentMap = new LinkedHashMap<>();

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the toAddress
	 */
	public String[] getToAddress() {
		return toAddress;
	}

	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the attachmentMap
	 */
	public Map<String, byte[]> getAttachmentMap() {
		return attachmentMap;
	}

	/**
	 * @param attachmentMap the attachmentMap to set
	 */
	public void setAttachmentMap(Map<String, byte[]> attachmentMap) {
		this.attachmentMap = attachmentMap;
	}
	
}