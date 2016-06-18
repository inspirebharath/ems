/**
 * 
 */
package com.ems.user.dao.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ems.user.beans.EventDTO;
import com.ems.user.constants.Constants;
import com.ems.user.dao.EventDao;
import com.ems.user.dao.model.EventInfo;
import com.ems.user.dao.model.QEventInfo;
import com.ems.user.dao.model.QEventOrganizers;
import com.ems.user.dao.repository.EventRepository;
import com.ems.user.util.CommonUtil;
import com.ems.user.util.DateTimeUtil;
import com.ems.user.util.StringUtil;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.expr.BooleanExpression;

/**
 * @author Bharath Arya
 *
 */
@Repository("eventDao")
public class EventDaoImpl implements EventDao, Constants {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public boolean checkDataExistance(String eventName) throws DataAccessException {
		EventInfo eventInfo = eventRepository.findByEventName(eventName);
		if(!CommonUtil.isNull(eventInfo)) {
			return true;
		}
		
		return false;
	}

	@Override
	public EventInfo createEvent(EventInfo eventInfo) throws DataAccessException {
		return eventRepository.save(eventInfo);
	}

	@Override
	public EventInfo getSpecificEvent(Long partnerId) throws DataAccessException {
		return eventRepository.findOne(isIdEq(partnerId));
	}

	@Override
	public EventInfo updateEventInfo(EventInfo eventInfo) throws DataAccessException {
		return eventRepository.save(eventInfo);
	}

	@Override
	public EventInfo searchEvents(EventDTO eventDTO) throws DataAccessException, ParseException {
		getTotalNumberOfRecords(eventDTO);
		return null;
	}
	
	//Add difficultyGrade and fitnessLevel as search criterias
	
	private int getTotalNumberOfRecords(EventDTO eventDto) throws ParseException {
		
		boolean canDoWildCardSearch = false;
		
		boolean toOrderByAsc = false;
		
		if(!CommonUtil.isNull(eventDto.getReportPropertiesBean())) {
			if(eventDto.getReportPropertiesBean().isToDoWildCardSearch()) {
				canDoWildCardSearch = true;
			} else if(eventDto.getReportPropertiesBean().isToOrderByAsc()) {
				toOrderByAsc = true;
			}
		}
		
		Timestamp startDate = null;
		Timestamp endDate = null;

		if (!CommonUtil.isNull(eventDto.getStartDate())) {
			startDate = DateTimeUtil.getTimestamp(eventDto.getStartDate());
		}

		if (!CommonUtil.isNull(eventDto.getEndDate())) {
			endDate = DateTimeUtil.getTimestamp(eventDto.getEndDate());
		}
				
		JPAQuery query = new JPAQuery(entityManager);
		
		QEventInfo eventInfo = QEventInfo.eventInfo;
		
		List<Long> eventsList = query
				.from(eventInfo)
				.where(isEventNameEqOrLike(eventDto.getEventName(), canDoWildCardSearch),
						isEventLocationEqOrLike(eventDto.getEventOrganizersDTO().getEventLocation(), canDoWildCardSearch),
						isEventTypeEq(eventDto.getEventType()),
						isCountryEq(eventDto.getEventOrganizersDTO().getCountry()),
						isStateEq(eventDto.getEventOrganizersDTO().getState()),
						isCityEq(eventDto.getEventOrganizersDTO().getCity()),
						isStatusEq(eventDto.getEventStatus()),
						getDateRange(startDate, endDate))
				.orderBy(orderById(toOrderByAsc))
				.list(eventInfo.eventId);

		return (eventsList != null && !eventsList.isEmpty() ? eventsList.size() : 0);
		
	}
	
	private BooleanExpression isEventNameEqOrLike(String eventName, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(eventName)) ? QEventInfo.eventInfo.eventName.toUpperCase().like("%" + eventName.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(eventName)) ? QEventInfo.eventInfo.eventName.eq(eventName) : null;
		}
	}
	
	private BooleanExpression isEventLocationEqOrLike(String eventLocation, boolean doWildCardSearch) {
		if(doWildCardSearch) {
			return (!StringUtil.isNullAndEmpty(eventLocation)) ? QEventOrganizers.eventOrganizers.eventLocation.toUpperCase().like("%" + eventLocation.toUpperCase().replace("*", "") + "%") : null;
		} else {
			return (!StringUtil.isNullAndEmpty(eventLocation)) ? QEventOrganizers.eventOrganizers.eventLocation.eq(eventLocation) : null;
		}
	}
	
	private BooleanExpression isStatusEq(String status) {
		return (!StringUtil.isNullAndEmpty(status)) ? QEventInfo.eventInfo.eventStatus.eq(status) : null;
	}
	
	private BooleanExpression isCountryEq(String country) {
		return (!StringUtil.isNullAndEmpty(country)) ? QEventOrganizers.eventOrganizers.country.eq(country) : null;
	}
	
	private BooleanExpression isCityEq(String city) {
		return (!StringUtil.isNullAndEmpty(city)) ? QEventOrganizers.eventOrganizers.city.eq(city) : null;
	}

	private BooleanExpression isStateEq(String state) {
		return (!StringUtil.isNullAndEmpty(state)) ? QEventOrganizers.eventOrganizers.state.eq(state) : null;
	}
	
	private BooleanExpression isEventTypeEq(String eventType) {
		return (!StringUtil.isNullAndEmpty(eventType)) ? QEventInfo.eventInfo.eventType.eq(eventType) : null;
	}
	
	private OrderSpecifier<Long> orderById(boolean isToOrderByAsc) {
		if(isToOrderByAsc) {
			return QEventInfo.eventInfo.eventId.asc();
		} else {
			return QEventInfo.eventInfo.eventId.desc();
		}
	}
	
	private BooleanExpression getDateRange(Timestamp startDate, Timestamp endDate) {
		if ((startDate != null && endDate == null)) {
			return QEventInfo.eventInfo.startDate.goe(startDate);
		} 
		if ((startDate == null && endDate != null)) {
			return QEventInfo.eventInfo.endDate.loe(endDate);
		} 
		if ((startDate == null && endDate == null) || ("".equals(startDate) && "".equals(endDate))) {
			return null;
		} 

		return QEventInfo.eventInfo.startDate.goe(startDate).and(QEventInfo.eventInfo.endDate.loe(endDate));

	}

	private BooleanExpression isIdEq(Long eventId) {
		return (!CommonUtil.isNull(eventId)) ? QEventInfo.eventInfo.eventId.eq(eventId) : null;
	}

}