/**
 * 
 */
package com.ems.user.beans;

import java.io.Serializable;

/**
 * @author Bharath Arya
 *
 */
public class EventJsonData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 742869911553780390L;

	private String synopsis;

	private String organizerData;
	
	private String eventBatches;
	
	private String[] itenary;
	
	private String safetyAndPreparatons;
	
	private String personalItemsToCarry;
	
	private String termsAndConditions;

	/**
	 * @return the synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * @param synopsis the synopsis to set
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * @return the organizerData
	 */
	public String getOrganizerData() {
		return organizerData;
	}

	/**
	 * @param organizerData the organizerData to set
	 */
	public void setOrganizerData(String organizerData) {
		this.organizerData = organizerData;
	}

	/**
	 * @return the eventBatches
	 */
	public String getEventBatches() {
		return eventBatches;
	}

	/**
	 * @param eventBatches the eventBatches to set
	 */
	public void setEventBatches(String eventBatches) {
		this.eventBatches = eventBatches;
	}

	/**
	 * @return the itenary
	 */
	public String[] getItenary() {
		return itenary;
	}

	/**
	 * @param itenary the itenary to set
	 */
	public void setItenary(String[] itenary) {
		this.itenary = itenary;
	}

	/**
	 * @return the safetyAndPreparatons
	 */
	public String getSafetyAndPreparatons() {
		return safetyAndPreparatons;
	}

	/**
	 * @param safetyAndPreparatons the safetyAndPreparatons to set
	 */
	public void setSafetyAndPreparatons(String safetyAndPreparatons) {
		this.safetyAndPreparatons = safetyAndPreparatons;
	}

	/**
	 * @return the personalItemsToCarry
	 */
	public String getPersonalItemsToCarry() {
		return personalItemsToCarry;
	}

	/**
	 * @param personalItemsToCarry the personalItemsToCarry to set
	 */
	public void setPersonalItemsToCarry(String personalItemsToCarry) {
		this.personalItemsToCarry = personalItemsToCarry;
	}

	/**
	 * @return the termsAndConditions
	 */
	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	/**
	 * @param termsAndConditions the termsAndConditions to set
	 */
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
		
}