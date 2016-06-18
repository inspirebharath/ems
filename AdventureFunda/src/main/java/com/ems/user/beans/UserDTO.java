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
public class UserDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3896324739991617467L;

	private Long id;
	
	private String userName;
	
	private String emailId;

	private String firstName;

	private String lastName;
	
	private String password;

	private String mobileNo;

	private String gender;
	
	private String dob;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String zipCode;
	
	private String otp;
	
	private String isGuestUser;
	
	private Integer points;
	
	private Integer referalCode;
	
	private Integer referedCode;
	
	private String verificationStatus;
	
	private String status;
	
	private String reason;

	private Timestamp lastLoginDateTime;

	private Timestamp createdDate;

	private Timestamp updatedDate;

	private String createdBy;

	private String updatedBy;
	
	private List<UserDTO> userInfoList;

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
	 * @return the isGuestUser
	 */
	public String getIsGuestUser() {
		return isGuestUser;
	}

	/**
	 * @param isGuestUser the isGuestUser to set
	 */
	public void setIsGuestUser(String isGuestUser) {
		this.isGuestUser = isGuestUser;
	}

	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}

	/**
	 * @return the referalCode
	 */
	public Integer getReferalCode() {
		return referalCode;
	}

	/**
	 * @param referalCode the referalCode to set
	 */
	public void setReferalCode(Integer referalCode) {
		this.referalCode = referalCode;
	}

	/**
	 * @return the referedCode
	 */
	public Integer getReferedCode() {
		return referedCode;
	}

	/**
	 * @param referedCode the referedCode to set
	 */
	public void setReferedCode(Integer referedCode) {
		this.referedCode = referedCode;
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
	 * @return the userInfoList
	 */
	public List<UserDTO> getUserInfoList() {
		return userInfoList;
	}

	/**
	 * @param userInfoList the userInfoList to set
	 */
	public void setUserInfoList(List<UserDTO> userInfoList) {
		this.userInfoList = userInfoList;
	}
	
}