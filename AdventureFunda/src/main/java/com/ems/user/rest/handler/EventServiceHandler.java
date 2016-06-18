/**
 * 
 */
package com.ems.user.rest.handler;

import javax.servlet.http.HttpSession;

import com.ems.user.beans.EventDTO;
import com.ems.user.beans.Response;
import com.ems.user.beans.UniqueDataBean;

/**
 * @author Bharath Arya
 *
 */
public interface EventServiceHandler {

	Response checkDataExistance(UniqueDataBean uniqueDataBean);
	
	Response registerEvent(EventDTO eventDTO, HttpSession session);
	
	Response getSpecificEvent(Long eventId);
	
	Response updateEventInfo(EventDTO eventDTO, HttpSession session);

	Response deleteEvent(Long eventId, String reason, HttpSession session);

	Response changeEventStatus(Long eventId, String status, String reason, HttpSession session);

	Response searchEvents(EventDTO eventDTO);

}