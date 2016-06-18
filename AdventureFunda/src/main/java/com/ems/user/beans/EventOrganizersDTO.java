/**
 * 
 */
package com.ems.user.beans;

import java.sql.Timestamp;

import com.ems.user.dao.model.AdminInfo;
import com.ems.user.dao.model.PartnerInfo;

/**
 * @author Bharath Arya
 *
 */
public class EventOrganizersDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2050896538726427017L;

	private Long eventOrganizerId;

	private AdminInfo eventId;
		
	private AdminInfo adminId;
		
	private PartnerInfo partnerId;

	private Integer partnerQuotedPrice;
	
	private Timestamp paymentLastDate;

	private String medicalCertificateStatus;
	
	private String eventLocation;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String zipCode;	
	
	private Timestamp createdDate;

	private Timestamp updatedDate;

	private String createdBy;

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