/**
 * 
 */
package com.ems.user.rest.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ems.user.beans.LoginDetails;
import com.ems.user.beans.Response;

/**
 * @author Bharath Arya
 *
 */
public interface LoginServiceHandler {
	
	Response authenticateUser(LoginDetails loginDetails, HttpServletRequest request, HttpSession session);

}
