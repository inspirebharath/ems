/**
 * 
 */
package com.ems.user.exception;

/**
 * @author Bharath Arya
 *
 */
public class EmsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public EmsException() {
		super();
	}

	/**
	 * Partial constructor
	 * 
	 * @param message
	 */
	public EmsException(String message) {
		super(message);
	}

	/**
	 * Partial constructor
	 * 
	 * @param cause
	 */
	public EmsException(Throwable cause) {
		super(cause);
	}

	/**
	 * Full constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public EmsException(String message, Throwable cause) {
		super(message, cause);
	}

}