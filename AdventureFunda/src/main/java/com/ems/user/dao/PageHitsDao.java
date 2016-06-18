/**
 * 
 */
package com.ems.user.dao;

import org.springframework.dao.DataAccessException;

import com.ems.user.dao.model.PageHits;

/**
 * @author Bharath Arya
 *
 */
public interface PageHitsDao {
	
	PageHits getCurrentDayPageHits(String pageHitDate) throws DataAccessException;
	
	PageHits createOrUpdate(PageHits pageHits) throws DataAccessException;

}