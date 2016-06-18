/**
 * 
 */
package com.ems.user.exception;

import com.ems.user.constants.NotificationResponseCode;

/**
 * @author Bharath Arya
 *
 */
public class NotificationServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6048224818472899924L;

	private String errorCode;

	private String errorMessage;

	public NotificationServiceException(Exception ex) {
		initCause(ex);
		setErrorCode(NotificationResponseCode.GENERAL_NOTIFICATION_ERROR_CODE);
		setErrorMessage(NotificationResponseCode.GENERAL_NOTIFICATION_ERROR_MESSAGE);
	}

	public NotificationServiceException(String errorCode, String errorMessage) {
		setErrorCode(errorCode);
		setErrorMessage(errorMessage);
	}

	public NotificationServiceException(Exception ex, String errorCode, String message) {
		initCause(ex);
		setErrorCode(errorCode);
		setErrorMessage(message);
	}

	public NotificationServiceException(Exception ex, String errorCode) {
		initCause(ex);
		setErrorCode(errorCode);
		setErrorMessage(NotificationResponseCode.GENERAL_NOTIFICATION_ERROR_MESSAGE);
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}