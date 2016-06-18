/**
 * 
 */
package com.ems.user.beans;

import java.io.Serializable;

/**
 * @author Bharath Arya
 *
 */
public class PartnerAdditionalData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2896382083971510396L;

	private Long Id;

	private String mountainsClimbed;

	private String leadersDescription;

	private String facilityProvided;

	private String successStory;

	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
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

}