/**
 * 
 */
package com.ems.user.dao.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ems.user.dao.model.UserInfo;

/**
 * @author Bharath Arya
 *
 */
public interface UserRepository extends JpaRepository<UserInfo,String>,
											QueryDslPredicateExecutor<UserInfo> {

	UserInfo findByUserNameOrEmailIdAndPasswordAndStatus(String userName, String emailId, String password, String status);
	
	UserInfo findByUserNameOrEmailIdAndPassword(String userName, String emailId, String password);
	
	UserInfo findByUserName(String userName);

	UserInfo findByUserNameOrEmailIdOrMobileNoAllIgnoreCase(String userName, String emailId, String mobileNo);
	
	@Transactional
	@Modifying
	@Query("UPDATE UserInfo SET lastLoginDateTime =:loginTime where id =:id")
	int updateLoginDateTime(@Param("id") Long id, @Param("loginTime") Timestamp loginTime);

	@Transactional
	@Modifying
	@Query("UPDATE UserInfo SET password =:password, updatedDate =:updatedDate, updatedBy =:updatedBy where id =:id")
	int updatePassword(@Param("id") Long id, @Param("password") String password, 
			@Param("updatedBy") String updatedBy, @Param("updatedDate") Timestamp updatedDate);

	UserInfo findByEmailIdAllIgnoreCase(String emailId);

}