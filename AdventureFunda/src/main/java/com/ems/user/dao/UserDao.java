/**
 * 
 */
package com.ems.user.dao;

import java.sql.Timestamp;

import org.springframework.dao.DataAccessException;

import com.ems.user.beans.UserDTO;
import com.ems.user.dao.model.UserInfo;

/**
 * @author Bharath Arya
 *
 */
public interface UserDao extends BaseDao {

	UserInfo createUser(UserInfo userInfo) throws DataAccessException;

	UserInfo isValidUser(String userName, String emailId, String password) throws DataAccessException;
	
	UserInfo getSpecificUser(Long userId) throws DataAccessException;

	UserInfo updateUserInfo(UserInfo userInfo) throws DataAccessException;
	
	int updateLoginDateTime(Long id, Timestamp loginTime);
	
	UserDTO searchUsers(UserDTO userDto) throws DataAccessException;
	
	int updatePassword(Long id, String password, String updatedBy, Timestamp updatedDate);
	
	UserInfo findUserByEmailId(String emailId) throws DataAccessException;
	
}