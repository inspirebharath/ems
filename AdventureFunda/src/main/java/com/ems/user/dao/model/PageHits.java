/**
 * 
 */
package com.ems.user.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bharath Arya
 *
 */

@Entity
@Table(name = "PAGE_HITS")
public class PageHits implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8065120604994222246L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PAGE_HIT_DATE")
	private String pageHitDate;
	
	@Column(name = "HIT_COUNT")
	private Integer hitCount;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the pageHitDate
	 */
	public String getPageHitDate() {
		return pageHitDate;
	}

	/**
	 * @param pageHitDate the pageHitDate to set
	 */
	public void setPageHitDate(String pageHitDate) {
		this.pageHitDate = pageHitDate;
	}

	/**
	 * @return the hitCount
	 */
	public Integer getHitCount() {
		return hitCount;
	}

	/**
	 * @param hitCount the hitCount to set
	 */
	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}

}