/**
 * 
 */
package com.ems.user.controller.model;

import java.io.Serializable;

/**
 * @author Bharath Arya
 *
 */
public class LoginDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2455502736130332768L;

	private String userName;

	private String userPwd;
	
	private String userType;

	private String jSession;

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
	 * @return the userPwd
	 */
	public String getUserPwd() {
		return userPwd;
	}

	/**
	 * @param userPwd the userPwd to set
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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
	 * @return the jSession
	 */
	public String getjSession() {
		return jSession;
	}

	/**
	 * @param jSession the jSession to set
	 */
	public void setjSession(String jSession) {
		this.jSession = jSession;
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

}