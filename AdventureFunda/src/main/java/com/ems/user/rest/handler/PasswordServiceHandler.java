/**
 * 
 */
package com.ems.user.rest.handler;

import javax.servlet.http.HttpSession;

import com.ems.user.beans.PasswordBean;
import com.ems.user.beans.Response;

/**
 * @author Bharath Arya
 *
 */
public interface PasswordServiceHandler {
	
	Response changeUserPassword(PasswordBean passwordBean, HttpSession session);

	Response sendResetPwdLink(PasswordBean passwordBean);
	
	Response resetPassword(PasswordBean passwordBean);

}