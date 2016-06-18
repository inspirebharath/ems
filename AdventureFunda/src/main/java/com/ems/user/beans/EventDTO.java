/**
 * 
 */
package com.ems.user.beans;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Bharath Arya
 *
 */
public class EventDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3101986271685606786L;

	private Long eventId;

	private String eventName;

	private String startDate;
	
	private String endDate;
	
	private String shortDesc;
	
	private Integer adminQuotedPrice;

	private Integer maxNoOfParticipants;
	
	private Integer minNoOfParticipants;
	
	private Integer noOfBatches;
	
	private String eventStatus;
	
	private String reason;
	
	private Character difficultyGrade;
	
	private Character fitnessLevel;
	
	private String eventType;
	
	private Integer viewedCount;
	
	private Timestamp createdDate;

	private Timestamp updatedDate;

	private String createdBy;

	private String updatedBy;
	
	private EventOrganizersDTO eventOrganizersDTO;

	private List<EventOrganizersDTO> eventOrganizersDTOList;

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
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
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
	 * @return the eventOrganizersDTO
	 */
	public EventOrganizersDTO getEventOrganizersDTO() {
		return eventOrganizersDTO;
	}

	/**
	 * @param eventOrganizersDTO the eventOrganizersDTO to set
	 */
	public void setEventOrganizersDTO(EventOrganizersDTO eventOrganizersDTO) {
		this.eventOrganizersDTO = eventOrganizersDTO;
	}

	/**
	 * @return the eventOrganizersDTOList
	 */
	public List<EventOrganizersDTO> getEventOrganizersDTOList() {
		return eventOrganizersDTOList;
	}

	/**
	 * @param eventOrganizersDTOList the eventOrganizersDTOList to set
	 */
	public void setEventOrganizersDTOList(
			List<EventOrganizersDTO> eventOrganizersDTOList) {
		this.eventOrganizersDTOList = eventOrganizersDTOList;
	}
	
}