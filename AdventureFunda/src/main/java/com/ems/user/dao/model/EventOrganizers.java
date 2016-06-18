/**
 * 
 */
package com.ems.user.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bharath Arya
 *
 */

@Entity
@Table(name = "EVENT_INFO")
public class EventOrganizers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2669068557104153128L;
	
	@Id
	@Column(name = "EMAIL_ORGANIZER_ID")
	private Long eventOrganizerId;

	@Column(name = "EVENT_ID")
	private AdminInfo eventId;
		
	@Column(name = "ADMIN_ID")
	private AdminInfo adminId;
		
	@Column(name = "PARTNER_ID")
	private PartnerInfo partnerId;

	@Column(name = "PARTNER_QUOTED_PRICE")
	private Integer partnerQuotedPrice;
	
	@Column(name = "PAYMENT_LAST_DATE")
	private Timestamp paymentLastDate;

	@Column(name = "MEDICAL_CERTIFICATE_STATUS")
	private String medicalCertificateStatus;
	
	@Column(name = "EVENT_LOCATION")
	private String eventLocation;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "ZIPCODE")
	private String zipCode;
	
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	/**
	 * @return the eventOrganizerId
	 */
	public Long getEventOrganizerId() {
		return eventOrganizerId;
	}

	/**
	 * @param eventOrganizerId the eventOrganizerId to set
	 */
	public void setEventOrganizerId(Long eventOrganizerId) {
		this.eventOrganizerId = eventOrganizerId;
	}

	/**
	 * @return the eventId
	 */
	public AdminInfo getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(AdminInfo eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the adminId
	 */
	public AdminInfo getAdminId() {
		return adminId;
	}

	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(AdminInfo adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return the partnerId
	 */
	public PartnerInfo getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(PartnerInfo partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return the partnerQuotedPrice
	 */
	public Integer getPartnerQuotedPrice() {
		return partnerQuotedPrice;
	}

	/**
	 * @param partnerQuotedPrice the partnerQuotedPrice to set
	 */
	public void setPartnerQuotedPrice(Integer partnerQuotedPrice) {
		this.partnerQuotedPrice = partnerQuotedPrice;
	}

	/**
	 * @return the paymentLastDate
	 */
	public Timestamp getPaymentLastDate() {
		return paymentLastDate;
	}

	/**
	 * @param paymentLastDate the paymentLastDate to set
	 */
	public void setPaymentLastDate(Timestamp paymentLastDate) {
		this.paymentLastDate = paymentLastDate;
	}

	/**
	 * @return the medicalCertificateStatus
	 */
	public String getMedicalCertificateStatus() {
		return medicalCertificateStatus;
	}

	/**
	 * @param medicalCertificateStatus the medicalCertificateStatus to set
	 */
	public void setMedicalCertificateStatus(String medicalCertificateStatus) {
		this.medicalCertificateStatus = medicalCertificateStatus;
	}

	/**
	 * @return the eventLocation
	 */
	public String getEventLocation() {
		return eventLocation;
	}

	/**
	 * @param eventLocation the eventLocation to set
	 */
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}