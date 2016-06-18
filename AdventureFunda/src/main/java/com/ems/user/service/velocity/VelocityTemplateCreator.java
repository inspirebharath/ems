/**
 * 
 */
package com.ems.user.service.velocity;

import java.util.Map;

/**
 * @author Bharath Arya
 *
 */
public interface VelocityTemplateCreator {

	/**
	 * This method is used to create XMLRequest
	 * @param requestMap
	 * @param templatePath
	 * @return
	 * @throws Exception
	 */
	public String createEmailTemplate(Map<String, String> requestMap, String templatePath) throws Exception;
	
}
