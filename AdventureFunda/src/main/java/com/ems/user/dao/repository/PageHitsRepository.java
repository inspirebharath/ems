/**
 * 
 */
package com.ems.user.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.ems.user.dao.model.PageHits;

/**
 * @author Bharath Arya
 *
 */
public interface PageHitsRepository extends JpaRepository<PageHits,String>,
											QueryDslPredicateExecutor<PageHits> {

	PageHits findByPageHitDate(String date);
	
}