/**
 * 
 */
package com.ems.user.dao;

import java.text.ParseException;

import org.springframework.dao.DataAccessException;

import com.ems.user.beans.EventDTO;
import com.ems.user.dao.model.EventInfo;

/**
 * @author Bharath Arya
 *
 */
public interface EventDao {

	boolean checkDataExistance(String eventName) throws DataAccessException;
	
	EventInfo createEvent(EventInfo eventInfo) throws DataAccessException;

	EventInfo getSpecificEvent(Long eventId) throws DataAccessException;

	EventInfo updateEventInfo(EventInfo eventInfo) throws DataAccessException;
	
	EventInfo searchEvents(EventDTO eventDTO) throws DataAccessException, ParseException ;

}