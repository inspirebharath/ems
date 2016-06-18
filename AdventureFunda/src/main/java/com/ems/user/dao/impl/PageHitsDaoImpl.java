/**
 * 
 */
package com.ems.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ems.user.dao.PageHitsDao;
import com.ems.user.dao.model.PageHits;
import com.ems.user.dao.repository.PageHitsRepository;

/**
 * @author Bharath Arya
 *
 */
@Repository("pageHitsDao")
public class PageHitsDaoImpl implements PageHitsDao {

	@Autowired
	private PageHitsRepository pageHitsRepository;

	@Override
	public PageHits getCurrentDayPageHits(String pageHitDate) throws DataAccessException {
		return pageHitsRepository.findByPageHitDate(pageHitDate);
	}

	@Override
	public PageHits createOrUpdate(PageHits pageHits) throws DataAccessException {
		return pageHitsRepository.save(pageHits);
	}
	
}