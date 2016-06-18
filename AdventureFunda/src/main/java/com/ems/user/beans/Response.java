/**
 * 
 */
package com.ems.user.beans;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Bharath Arya
 *
 */
public class Response implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3598033003251250860L;

	private String responseCode; // Success Code or Error code
	
	private String responseType; // Success or Error
	
	private String responseMessage; // Success Msg or Error Msg
	
	private String messageCode; // Specific code for request response
	
	private String messageDesc; // Specific description for request response
	
	private Integer totalNoOfRecords;
	
	private Map<String,Object> responseObj;

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseType
	 */
	public String getResponseType() {
		return responseType;
	}

	/**
	 * @param responseType the responseType to set
	 */
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	/**
	 * @return the messageDesc
	 */
	public String getMessageDesc() {
		return messageDesc;
	}

	/**
	 * @param messageDesc the messageDesc to set
	 */
	public void setMessageDesc(String messageDesc) {
		this.messageDesc = messageDesc;
	}

	/**
	 * @return the totalNoOfRecords
	 */
	public Integer getTotalNoOfRecords() {
		return totalNoOfRecords;
	}

	/**
	 * @param totalNoOfRecords the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(Integer totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}

	/**
	 * @return the responseObj
	 */
	public Map<String, Object> getResponseObj() {
		return responseObj;
	}

	/**
	 * @param responseObj the responseObj to set
	 */
	public void setResponseObj(Map<String, Object> responseObj) {
		this.responseObj = responseObj;
	}

}