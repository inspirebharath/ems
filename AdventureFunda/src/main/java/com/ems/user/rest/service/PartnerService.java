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

import com.ems.user.beans.PartnerDTO;
import com.ems.user.beans.Response;
import com.ems.user.beans.UniqueDataBean;
import com.ems.user.constants.RestServiceURLs;
import com.ems.user.rest.handler.PartnerServiceHandler;

@RestController
@RequestMapping(value = "/partnerService", consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnerService implements RestServiceURLs {
	
	@Autowired
	private PartnerServiceHandler partnerServiceHandler;
	
	@RequestMapping(value = EMS_UNIQUE_DATA_CHECK, method = RequestMethod.GET)
	public Response isDataExists(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody UniqueDataBean uniqueDataBean) {
		
		return partnerServiceHandler.checkDataExistance(uniqueDataBean);
		
	}
	
	@RequestMapping(value = EMS_PARTNER_REGISTER, method = RequestMethod.POST)
	public Response registerAdmin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody PartnerDTO partnerDto) {
				
		return partnerServiceHandler.registerPartner(partnerDto, session);
		
	}
	
	@RequestMapping(value = EMS_GET_PARTNER_INFO + "/{partnerId}", method = RequestMethod.GET)
	public Response getAdminInfo(HttpServletRequest request, HttpServletResponse response, 
							HttpSession session, @PathVariable Long partnerId) {
		
		return partnerServiceHandler.getSpecificPartner(partnerId);
		
	}
	
	@RequestMapping(value = EMS_UPDATE_PARTNER_INFO + "/{partnerId}", method = RequestMethod.GET)
	public Response updateAdminInfo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody PartnerDTO partnerDto) {
		
		return partnerServiceHandler.updatePartnerInfo(partnerDto, session);
		
	}
	
	@RequestMapping(value = EMS_DELETE_PARTNER + "/{partnerId}/{reason}", method = RequestMethod.GET)
	public Response deleteAdmin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@PathVariable Long partnerId, @PathVariable String reason) {
		
		return partnerServiceHandler.deletePartner(partnerId, reason, session);
		
	}
	
	@RequestMapping(value = EMS_CHANGE_PARTNER_STATUS + "/{partnerId}/{status}/{reason}", method = RequestMethod.GET)
	public Response changeAdminStatus(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@PathVariable Long partnerId, @PathVariable String status, 
			@PathVariable String reason) {
		
		return partnerServiceHandler.changePartnerStatus(partnerId, status, reason, session);
		
	}
	
	@RequestMapping(value = EMS_SEARCH_PARTNERS, method = RequestMethod.POST)
	public Response searchAdmins(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody PartnerDTO partnerDto) {
		
		return partnerServiceHandler.searchPartners(partnerDto);
		
	}

}