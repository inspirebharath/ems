/**
 * 
 */
package com.ems.user.util;

import java.io.IOException;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author Bharath Arya
 *
 */
public final class Properties extends PropertyPlaceholderConfigurer {

	private static java.util.Properties properties = new java.util.Properties();

	protected java.util.Properties mergeProperties() throws IOException {
		properties = super.mergeProperties();
		return properties;
	}

	/**
	 * Get the value of given key from the property file.
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(final String key) {
		String value = properties.getProperty(key);
		return ((value == null) ? "" : value);
	}
	
}