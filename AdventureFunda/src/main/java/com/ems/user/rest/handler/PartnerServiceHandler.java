/**
 * 
 */
package com.ems.user.rest.handler;

import javax.servlet.http.HttpSession;

import com.ems.user.beans.PartnerDTO;
import com.ems.user.beans.Response;

/**
 * @author Bharath Arya
 *
 */
public interface PartnerServiceHandler extends BaseHandler {
	
	Response registerPartner(PartnerDTO partnerDto, HttpSession session);
	
	Response getSpecificPartner(Long partnerId);
	
	Response updatePartnerInfo(PartnerDTO partnerDto, HttpSession session);

	Response deletePartner(Long partnerId, String reason, HttpSession session);

	Response changePartnerStatus(Long partnerId, String status, String reason, HttpSession session);
	
	Response searchPartners(PartnerDTO partnerDto);
	
}