/**
 * 
 */
package com.ems.user.beans;

/**
 * @author Bharath Arya
 *
 */
public class ReportPropertiesBean {
	
	private boolean toFetchAllRecords;
	
	private boolean toDoWildCardSearch;
	
	private boolean toOrderByAsc;

	/**
	 * @return the toFetchAllRecords
	 */
	public boolean isToFetchAllRecords() {
		return toFetchAllRecords;
	}

	/**
	 * @param toFetchAllRecords the toFetchAllRecords to set
	 */
	public void setToFetchAllRecords(boolean toFetchAllRecords) {
		this.toFetchAllRecords = toFetchAllRecords;
	}

	/**
	 * @return the toDoWildCardSearch
	 */
	public boolean isToDoWildCardSearch() {
		return toDoWildCardSearch;
	}

	/**
	 * @param toDoWildCardSearch the toDoWildCardSearch to set
	 */
	public void setToDoWildCardSearch(boolean toDoWildCardSearch) {
		this.toDoWildCardSearch = toDoWildCardSearch;
	}

	/**
	 * @return the toOrderByAsc
	 */
	public boolean isToOrderByAsc() {
		return toOrderByAsc;
	}

	/**
	 * @param toOrderByAsc the toOrderByAsc to set
	 */
	public void setToOrderByAsc(boolean toOrderByAsc) {
		this.toOrderByAsc = toOrderByAsc;
	}

}