/**
 * 
 */
package com.ems.user.rest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ems.user.beans.PasswordBean;
import com.ems.user.beans.Response;
import com.ems.user.constants.RestServiceURLs;
import com.ems.user.rest.handler.PasswordServiceHandler;

/**
 * @author Bharath Arya
 *
 */
@RestController
@RequestMapping(value = "/adminService", consumes = MediaType.APPLICATION_JSON_VALUE, 
produces = MediaType.APPLICATION_JSON_VALUE)
public class PasswordService implements RestServiceURLs {

	@Autowired
	private PasswordServiceHandler passwordServiceHandler;

	@RequestMapping(value = EMS_USER_CHANGE_PWD, method = RequestMethod.POST)
	public Response changeUserPassword(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody PasswordBean passwordBean) {

		return passwordServiceHandler.changeUserPassword(passwordBean, session);

	}

	@RequestMapping(value = EMS_USER_FORGOT_PWD, method = RequestMethod.POST)
	public Response forgotPassword(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody PasswordBean passwordBean) {

		return passwordServiceHandler.sendResetPwdLink(passwordBean);
	}
	
	@RequestMapping(value = EMS_USER_RESET_PWD, method = RequestMethod.POST)
	public Response resetPassword(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody PasswordBean passwordBean) {

		return passwordServiceHandler.resetPassword(passwordBean);
	}

}