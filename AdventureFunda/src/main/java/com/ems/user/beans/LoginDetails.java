/**
 * 
 */
package com.ems.user.beans;

import java.io.Serializable;

/**
 * @author Bharath Arya
 *
 */
public class LoginDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1509285268189836916L;
	
	private Long id;
	
	private String userName;

	private String password;
	
	private String emailId;
	
	private String userType;

	private String jSessionId;

	private String loginIpAddress;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the jSessionId
	 */
	public String getjSessionId() {
		return jSessionId;
	}

	/**
	 * @param jSessionId the jSessionId to set
	 */
	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}

	/**
	 * @return the loginIpAddress
	 */
	public String getLoginIpAddress() {
		return loginIpAddress;
	}

	/**
	 * @param loginIpAddress the loginIpAddress to set
	 */
	public void setLoginIpAddress(String loginIpAddress) {
		this.loginIpAddress = loginIpAddress;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}