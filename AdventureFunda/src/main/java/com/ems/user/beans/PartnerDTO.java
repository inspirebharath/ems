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
public class PartnerDTO extends BaseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1775003540577667194L;

	private Long id;
	
	private String userName;
	
	private String emailId;

	private String firstName;

	private String lastName;
	
	private String password;

	private String mobileNo;

	private String alternativeNo;
	
	private String gender;
	
	private String dob;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String otp;
	
	private String verificationStatus;
	
	private String status;
	
	private String reason;

	private String partnerType;
	
	private String organizationName;
	
	private String merchantDesc;
	
	private String websiteAddress;
	
	private Timestamp lastLoginDateTime;

	private Timestamp createdDate;

	private Timestamp updatedDate;

	private String createdBy;

	private String updatedBy;
	
	private String mountainsClimbed;
	
	private String leadersDescription;
	
	private String facilityProvided;
	
	private String successStory;
	
	private String uploadedFilesFlag;
	
	private List<PartnerDTO> partnerInfoList;

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
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the alternativeNo
	 */
	public String getAlternativeNo() {
		return alternativeNo;
	}

	/**
	 * @param alternativeNo the alternativeNo to set
	 */
	public void setAlternativeNo(String alternativeNo) {
		this.alternativeNo = alternativeNo;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
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
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * @param otp the otp to set
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * @return the verificationStatus
	 */
	public String getVerificationStatus() {
		return verificationStatus;
	}

	/**
	 * @param verificationStatus the verificationStatus to set
	 */
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
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
	 * @return the partnerType
	 */
	public String getPartnerType() {
		return partnerType;
	}

	/**
	 * @param partnerType the partnerType to set
	 */
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}

	/**
	 * @return the organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * @return the merchantDesc
	 */
	public String getMerchantDesc() {
		return merchantDesc;
	}

	/**
	 * @param merchantDesc the merchantDesc to set
	 */
	public void setMerchantDesc(String merchantDesc) {
		this.merchantDesc = merchantDesc;
	}

	/**
	 * @return the websiteAddress
	 */
	public String getWebsiteAddress() {
		return websiteAddress;
	}

	/**
	 * @param websiteAddress the websiteAddress to set
	 */
	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}

	/**
	 * @return the lastLoginDateTime
	 */
	public Timestamp getLastLoginDateTime() {
		return lastLoginDateTime;
	}

	/**
	 * @param lastLoginDateTime the lastLoginDateTime to set
	 */
	public void setLastLoginDateTime(Timestamp lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
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
	 * @return the partnerInfoList
	 */
	public List<PartnerDTO> getPartnerInfoList() {
		return partnerInfoList;
	}

	/**
	 * @param partnerInfoList the partnerInfoList to set
	 */
	public void setPartnerInfoList(List<PartnerDTO> partnerInfoList) {
		this.partnerInfoList = partnerInfoList;
	}

	/**
	 * @return the mountainsClimbed
	 */
	public String getMountainsClimbed() {
		return mountainsClimbed;
	}

	/**
	 * @param mountainsClimbed the mountainsClimbed to set
	 */
	public void setMountainsClimbed(String mountainsClimbed) {
		this.mountainsClimbed = mountainsClimbed;
	}

	/**
	 * @return the leadersDescription
	 */
	public String getLeadersDescription() {
		return leadersDescription;
	}

	/**
	 * @param leadersDescription the leadersDescription to set
	 */
	public void setLeadersDescription(String leadersDescription) {
		this.leadersDescription = leadersDescription;
	}

	/**
	 * @return the facilityProvided
	 */
	public String getFacilityProvided() {
		return facilityProvided;
	}

	/**
	 * @param facilityProvided the facilityProvided to set
	 */
	public void setFacilityProvided(String facilityProvided) {
		this.facilityProvided = facilityProvided;
	}

	/**
	 * @return the successStory
	 */
	public String getSuccessStory() {
		return successStory;
	}

	/**
	 * @param successStory the successStory to set
	 */
	public void setSuccessStory(String successStory) {
		this.successStory = successStory;
	}

	/**
	 * @return the uploadedFilesFlag
	 */
	public String getUploadedFilesFlag() {
		return uploadedFilesFlag;
	}

	/**
	 * @param uploadedFilesFlag the uploadedFilesFlag to set
	 */
	public void setUploadedFilesFlag(String uploadedFilesFlag) {
		this.uploadedFilesFlag = uploadedFilesFlag;
	}

}