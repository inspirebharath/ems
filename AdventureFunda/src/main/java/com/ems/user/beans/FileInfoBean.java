/**
 * 
 */
package com.ems.user.beans;

import java.io.Serializable;

/**
 * @author Bharath Arya
 *
 */
public class FileInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1409947988244586825L;

	private String entityType;
	
	private String entityOperation;
	
	private String imageType;
	
	private String uploadedFilesFlag;
	
	private String isProfilePic;

	/**
	 * @return the entityType
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * @return the entityOperation
	 */
	public String getEntityOperation() {
		return entityOperation;
	}

	/**
	 * @param entityOperation the entityOperation to set
	 */
	public void setEntityOperation(String entityOperation) {
		this.entityOperation = entityOperation;
	}

	/**
	 * @return the imageType
	 */
	public String getImageType() {
		return imageType;
	}

	/**
	 * @param imageType the imageType to set
	 */
	public void setImageType(String imageType) {
		this.imageType = imageType;
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

	/**
	 * @return the isProfilePic
	 */
	public String getIsProfilePic() {
		return isProfilePic;
	}

	/**
	 * @param isProfilePic the isProfilePic to set
	 */
	public void setIsProfilePic(String isProfilePic) {
		this.isProfilePic = isProfilePic;
	}
		
}