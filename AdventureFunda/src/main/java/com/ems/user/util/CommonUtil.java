/**
 * 
 */
package com.ems.user.util;

import com.ems.user.beans.Response;
import com.ems.user.constants.AppCodes;
import com.ems.user.constants.ResponseConstants;


/**
 * @author Bharath Arya
 *
 */
public class CommonUtil {

	public static boolean isNull(Object obj) {
		if(null == obj) {
			return true; 
		} else {
			 return false;
		}
	}
	
	public static void populateResponseMsgData(Response response, AppCodes appCodes) {
		response.setMessageCode(appCodes.name());
		response.setMessageDesc(appCodes.value());
	}
	
	public static void populateResponseSuccessData(Response response) {
		response.setResponseType(ResponseConstants.SUCCESS);
		response.setResponseCode(ResponseConstants.SUCCESS_CODE);
	}
	
	public static void populateResponseSuccessData(Response response, AppCodes appCodes) {
		response.setResponseType(ResponseConstants.SUCCESS);
		response.setResponseCode(ResponseConstants.SUCCESS_CODE);
		
		response.setMessageCode(appCodes.name());
		response.setMessageDesc(appCodes.value());
	}
	
	public static void populateResponseErrorData(Response response, Exception e) {
		response.setResponseType(ResponseConstants.ERROR);
		response.setResponseCode(ResponseConstants.ERROR_CODE);
		response.setResponseMessage(e.getMessage());
	}
	
	public static void populateResponseErrorData(Response response, AppCodes appCodes, Exception e) {
		response.setResponseType(ResponseConstants.ERROR);
		response.setResponseCode(ResponseConstants.ERROR_CODE);
		response.setResponseMessage(e.getMessage());
		
		response.setMessageCode(appCodes.name());
		response.setMessageDesc(appCodes.value());
	}
	
}