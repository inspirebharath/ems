/**
 * 
 */
package com.ems.user.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.ems.user.dao.model.EventInfo;

/**
 * @author Bharath Arya
 *
 */
public interface EventRepository extends JpaRepository<EventInfo,String>,
											QueryDslPredicateExecutor<EventInfo> {
	
	EventInfo findByEventName(String eventName);
	
}