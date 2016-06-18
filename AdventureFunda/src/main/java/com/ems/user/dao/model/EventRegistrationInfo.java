/**
 * 
 */
package com.ems.user.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Bharath Arya
 *
 */
public class EventRegistrationInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8792837816077608609L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "EVENT_REGISTRATION_ID")
	private Long eventRegistrationId;
	
	@Column(name = "USER_NAME")
	private String userName;
		
	@Column(name = "PARTNER_ID")
	private String partnerId;
	
	@Column(name = "EVENT_ID")
	private Integer eventId;

	@Column(name = "NO_OF_PARTICIPANTS")
	private Integer NoOfParticipants;
	
	@Column(name = "EVENT_PRICE")
	private Integer price;
	
	@Column(name = "EVENT_RATING")
	private Integer eventRating;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REASON")
	private String reason;
	
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	/**
	 * @return the eventRegistrationId
	 */
	public Long getEventRegistrationId() {
		return eventRegistrationId;
	}

	/**
	 * @param eventRegistrationId the eventRegistrationId to set
	 */
	public void setEventRegistrationId(Long eventRegistrationId) {
		this.eventRegistrationId = eventRegistrationId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the partnerId
	 */
	public String getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return the eventId
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the noOfParticipants
	 */
	public Integer getNoOfParticipants() {
		return NoOfParticipants;
	}

	/**
	 * @param noOfParticipants the noOfParticipants to set
	 */
	public void setNoOfParticipants(Integer noOfParticipants) {
		NoOfParticipants = noOfParticipants;
	}

	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * @return the eventRating
	 */
	public Integer getEventRating() {
		return eventRating;
	}

	/**
	 * @param eventRating the eventRating to set
	 */
	public void setEventRating(Integer eventRating) {
		this.eventRating = eventRating;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

}