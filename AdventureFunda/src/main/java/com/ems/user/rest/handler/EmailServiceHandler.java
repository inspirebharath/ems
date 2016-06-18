/**
 * 
 */
package com.ems.user.rest.handler;

import java.util.Map;

import com.ems.user.beans.Response;

/**
 * @author Bharath Arya
 *
 */
public interface EmailServiceHandler {
	
	Response sendEmail(Map<String, String> infoMap, String templateName, String[] toAddresses, Response response);

}