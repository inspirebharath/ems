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

import com.ems.user.beans.EventDTO;
import com.ems.user.beans.Response;
import com.ems.user.beans.UniqueDataBean;
import com.ems.user.constants.RestServiceURLs;
import com.ems.user.rest.handler.EventServiceHandler;

/**
 * @author Bharath Arya
 *
 */
@RestController
@RequestMapping(value = "/eventService", consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class EventService implements RestServiceURLs {

	@Autowired
	private EventServiceHandler eventServiceHandler;
	
	@RequestMapping(value = EMS_UNIQUE_DATA_CHECK, method = RequestMethod.GET)
	public Response isDataExists(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody UniqueDataBean uniqueDataBean) {
		
		return eventServiceHandler.checkDataExistance(uniqueDataBean);
		
	}
	
	@RequestMapping(value = EMS_EVENT_REGISTER, method = RequestMethod.POST)
	public Response registerEvent(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody EventDTO eventDTO) {
				
		return eventServiceHandler.registerEvent(eventDTO, session);
		
	}
	
	@RequestMapping(value = EMS_GET_EVENT_INFO + "/{eventId}", method = RequestMethod.GET)
	public Response getEventInfo(HttpServletRequest request, HttpServletResponse response, 
							HttpSession session, @PathVariable Long eventId) {
		
		return eventServiceHandler.getSpecificEvent(eventId);
		
	}
	
	@RequestMapping(value = EMS_UPDATE_EVENT_INFO + "/{eventId}", method = RequestMethod.POST)
	public Response updateEventInfo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody EventDTO eventDTO) {
		
		return eventServiceHandler.updateEventInfo(eventDTO, session);
		
	}
	
	@RequestMapping(value = EMS_DELETE_EVENT + "/{eventId}/{reason}", method = RequestMethod.GET)
	public Response deleteEvent(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@PathVariable Long eventId, @PathVariable String reason) {
		
		return eventServiceHandler.deleteEvent(eventId, reason, session);
		
	}
	
	@RequestMapping(value = EMS_CHANGE_EVENT_STATUS + "/{eventId}/{status}/{reason}", method = RequestMethod.GET)
	public Response changeEventStatus(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@PathVariable Long eventId, @PathVariable String status, 
			@PathVariable String reason) {
		
		return eventServiceHandler.changeEventStatus(eventId, status, reason, session);
		
	}
	
	@RequestMapping(value = EMS_SEARCH_EVENTS, method = RequestMethod.POST)
	public Response searchEvents(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, 
			@RequestBody EventDTO eventDTO) {
		
		return eventServiceHandler.searchEvents(eventDTO);
		
	}
	
}