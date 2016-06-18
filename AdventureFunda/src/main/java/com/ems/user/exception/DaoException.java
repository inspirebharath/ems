/**
 * 
 */
package com.ems.user.exception;

/**
 * @author Bharath Arya
 *
 */
public class DaoException extends EmsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public DaoException() {
		super();
	}

	/**
	 * Partial constructor
	 * 
	 * @param message
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * Partial constructor
	 * 
	 * @param cause
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}

	/**
	 * Full constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

}