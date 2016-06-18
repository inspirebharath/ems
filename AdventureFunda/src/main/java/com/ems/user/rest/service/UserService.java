/**
 * 
 */
package com.ems.user.rest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ems.user.beans.Response;
import com.ems.user.beans.UniqueDataBean;
import com.ems.user.beans.UserDTO;
import com.ems.user.constants.RestServiceURLs;
import com.ems.user.rest.handler.UserServiceHandler;

/**
 * @author Bharath Arya
 *
 */

@RestController
@RequestMapping(value = "/userService", consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class UserService implements RestServiceURLs {
	
	@Autowired
	private UserServiceHandler userServiceHandler;
	
	@RequestMapping(value = EMS_UNIQUE_DATA_CHECK, method = RequestMethod.GET)
	public Response isDataExists(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody UniqueDataBean uniqueDataBean) {
		
		return userServiceHandler.checkDataExistance(uniqueDataBean);
		
	}
	
	@RequestMapping(value = EMS_USER_REGISTER, method = RequestMethod.POST)
	public Response registerUser(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody UserDTO userDto) {
		
		return userServiceHandler.registerUser(userDto, session);
		
	}
	
	@RequestMapping(value = EMS_GET_USER_INFO + "/{userId}", method = RequestMethod.GET)
	public Response getUserInfo(HttpServletRequest request, HttpServletResponse response, 
							HttpSession session, @PathVariable Long userId) {
		
		return userServiceHandler.getSpecificUser(userId);
		
	}
	
	@RequestMapping(value = EMS_UPDATE_USER_INFO, method = RequestMethod.POST)
	public Response updateUserInfo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody UserDTO userDto) {
		
		return userServiceHandler.updateUserInfo(userDto, session);
		
	}
	
	@RequestMapping(value = EMS_DELETE_USER + "/{userId}/{reason}", method = RequestMethod.GET)
	public Response deleteUser(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@PathVariable Long userId, @PathVariable String reason) {
		
		return userServiceHandler.deleteUser(userId, reason, session);
		
	}
	
	@RequestMapping(value = EMS_CHANGE_USER_STATUS + "/{userId}/{status}/{reason}", method = RequestMethod.GET)
	public Response changeUserStatus(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@PathVariable Long userId, @PathVariable String status, 
			@PathVariable String reason) {
		
		return userServiceHandler.changeUserStatus(userId, status, reason, session);
		
	}
	
	@RequestMapping(value = EMS_SEARCH_USERS, method = RequestMethod.POST)
	public Response searchUsers(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody UserDTO userDto) {
		
		return userServiceHandler.searchUsers(userDto);
		
	}

}