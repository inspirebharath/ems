/**
 * 
 */
package com.ems.user.rest.handler;

import javax.servlet.http.HttpSession;

import com.ems.user.beans.Response;
import com.ems.user.beans.UserDTO;

/**
 * @author Bharath Arya
 *
 */
public interface UserServiceHandler extends BaseHandler {
	
	Response registerUser(UserDTO userDto, HttpSession session);
	
	Response getSpecificUser(Long userId);
	
	Response updateUserInfo(UserDTO userDto, HttpSession session);

	Response deleteUser(Long userId, String reason, HttpSession session);

	Response changeUserStatus(Long userId, String status, String reason, HttpSession session);
	
	Response searchUsers(UserDTO userDto);

}