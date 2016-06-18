/**
 * 
 */
package com.ems.user.rest.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ems.user.beans.LoginDetails;
import com.ems.user.beans.Response;
import com.ems.user.constants.RestServiceURLs;
import com.ems.user.rest.handler.LoginServiceHandler;
import com.ems.user.util.ApplicationUtil;

/**
 * @author Bharath Arya
 *
 */
@RestController
@RequestMapping(value = "/userLoginService", consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginService implements RestServiceURLs {
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private LoginServiceHandler loginServiceHandler;

	@RequestMapping(value = EMS_USER_AUTHENTICATE, method = RequestMethod.POST)
	public Response authenticateUser(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody LoginDetails loginDetails) {
		
		ApplicationUtil.getContextPath(context);
		
		return loginServiceHandler.authenticateUser(loginDetails, request, session);
	}
	
}