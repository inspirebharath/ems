/**
 * 
 */
package com.ems.user.dao;

import java.sql.Timestamp;

import org.springframework.dao.DataAccessException;

import com.ems.user.beans.PartnerDTO;
import com.ems.user.dao.model.PartnerInfo;

/**
 * @author Bharath Arya
 *
 */
public interface PartnerDao extends BaseDao {

    PartnerInfo createPartner(PartnerInfo partnerInfo) throws DataAccessException;

	//Integer isPartnerExists(String userName, String emailId, String password);

	PartnerInfo isValidPartner(String userName, String emailId, String password) throws DataAccessException;
	
	PartnerInfo getSpecificPartner(Long partnerId) throws DataAccessException;

	PartnerInfo updatePartnerInfo(PartnerInfo partnerInfo) throws DataAccessException;
	
	int updateLoginDateTime(Long id, Timestamp loginTime);
	
	PartnerDTO searchPartners(PartnerDTO partnerDto) throws DataAccessException;
	
	int updatePassword(Long id, String password, String updatedBy, Timestamp updatedDate);
	
	PartnerInfo findPartnerByEmailId(String emailId) throws DataAccessException;

}