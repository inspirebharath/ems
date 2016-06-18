/**
 * 
 */
package com.ems.user.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Bharath Arya
 *
 */

@Entity
@Table(name = "EVENT_INFO")
public class EventInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2020606720769743248L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "EVENT_ID")
	private Long eventId;

	@Column(name = "EVENT_NAME")
	private String eventName;

	@Column(name = "START_DATE")
	private Timestamp startDate;
	
	@Column(name = "END_DATE")
	private Timestamp endDate;
	
	@Column(name = "SHORT_DESCRIPTION")
	private String shortDesc;
	
	@Column(name = "ADMIN_QUOTED_PRICE")
	private Integer adminQuotedPrice;

	@Column(name = "MAX_NO_OF_PARTICIPANTS")
	private Integer maxNoOfParticipants;
	
	@Column(name = "MIN_NO_OF_PARTICIPANTS")
	private Integer minNoOfParticipants;
	
	@Column(name = "NO_OF_BATCHES")
	private Integer noOfBatches;
	
	@Column(name = "EVENT_STATUS")
	private String eventStatus;
	
	@Column(name = "REASON")
	private String reason;
	
	@Column(name = "DIFFICULTY_GRADE")
	private Character difficultyGrade;
	
	@Column(name = "FITNESS_LEVEL")
	private Character fitnessLevel;
	
	@Column(name = "EVENT_TYPE")
	private String eventType;
	
	@Column(name = "VIEW_COUNT")
	private Integer viewedCount;
	
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "EVENT_ID")
	private List<EventOrganizers> eventOrganizers;

	/**
	 * @return the eventId
	 */
	public Long getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the startDate
	 */
	public Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the shortDesc
	 */
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * @param shortDesc the shortDesc to set
	 */
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	/**
	 * @return the adminQuotedPrice
	 */
	public Integer getAdminQuotedPrice() {
		return adminQuotedPrice;
	}

	/**
	 * @param adminQuotedPrice the adminQuotedPrice to set
	 */
	public void setAdminQuotedPrice(Integer adminQuotedPrice) {
		this.adminQuotedPrice = adminQuotedPrice;
	}

	/**
	 * @return the maxNoOfParticipants
	 */
	public Integer getMaxNoOfParticipants() {
		return maxNoOfParticipants;
	}

	/**
	 * @param maxNoOfParticipants the maxNoOfParticipants to set
	 */
	public void setMaxNoOfParticipants(Integer maxNoOfParticipants) {
		this.maxNoOfParticipants = maxNoOfParticipants;
	}

	/**
	 * @return the minNoOfParticipants
	 */
	public Integer getMinNoOfParticipants() {
		return minNoOfParticipants;
	}

	/**
	 * @param minNoOfParticipants the minNoOfParticipants to set
	 */
	public void setMinNoOfParticipants(Integer minNoOfParticipants) {
		this.minNoOfParticipants = minNoOfParticipants;
	}

	/**
	 * @return the noOfBatches
	 */
	public Integer getNoOfBatches() {
		return noOfBatches;
	}

	/**
	 * @param noOfBatches the noOfBatches to set
	 */
	public void setNoOfBatches(Integer noOfBatches) {
		this.noOfBatches = noOfBatches;
	}

	/**
	 * @return the eventStatus
	 */
	public String getEventStatus() {
		return eventStatus;
	}

	/**
	 * @param eventStatus the eventStatus to set
	 */
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the difficultyGrade
	 */
	public Character getDifficultyGrade() {
		return difficultyGrade;
	}

	/**
	 * @param difficultyGrade the difficultyGrade to set
	 */
	public void setDifficultyGrade(Character difficultyGrade) {
		this.difficultyGrade = difficultyGrade;
	}

	/**
	 * @return the fitnessLevel
	 */
	public Character getFitnessLevel() {
		return fitnessLevel;
	}

	/**
	 * @param fitnessLevel the fitnessLevel to set
	 */
	public void setFitnessLevel(Character fitnessLevel) {
		this.fitnessLevel = fitnessLevel;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the viewedCount
	 */
	public Integer getViewedCount() {
		return viewedCount;
	}

	/**
	 * @param viewedCount the viewedCount to set
	 */
	public void setViewedCount(Integer viewedCount) {
		this.viewedCount = viewedCount;
	}

	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the eventOrganizers
	 */
	public List<EventOrganizers> getEventOrganizers() {
		return eventOrganizers;
	}

	/**
	 * @param eventOrganizers the eventOrganizers to set
	 */
	public void setEventOrganizers(List<EventOrganizers> eventOrganizers) {
		this.eventOrganizers = eventOrganizers;
	}

 
}