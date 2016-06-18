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

import com.ems.user.dao.model.PartnerInfo;

/**
 * @author Bharath Arya
 *
 */
public interface PartnerRepository extends JpaRepository<PartnerInfo,String>,
											QueryDslPredicateExecutor<PartnerInfo> {

	PartnerInfo findByUserNameOrEmailIdAndPasswordAndStatus(String userName, String emailId, String password, String status);
	
	PartnerInfo findByUserNameOrEmailIdAndPassword(String userName, String emailId, String password);
	
	PartnerInfo findByUserName(String userName);

	PartnerInfo findByUserNameOrEmailIdOrMobileNoAllIgnoreCase(String userName, String emailId, String mobileNo);
	
	@Transactional
	@Modifying
	@Query("UPDATE PartnerInfo SET lastLoginDateTime =:loginTime where id =:id")
	int updateLoginDateTime(@Param("id") Long id, @Param("loginTime") Timestamp loginTime);

	@Transactional
	@Modifying
	@Query("UPDATE PartnerInfo SET password =:password, updatedDate =:updatedDate, updatedBy =:updatedBy where id =:id")
	int updatePassword(@Param("id") Long id, @Param("password") String password, 
			@Param("updatedBy") String updatedBy, @Param("updatedDate") Timestamp updatedDate);

	PartnerInfo findByEmailIdAllIgnoreCase(String emailId);
	
}