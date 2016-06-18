/**
 * 
 */
package com.ems.user.test;

import com.ems.user.beans.Response;
import com.ems.user.beans.UniqueDataBean;
import com.ems.user.constants.AppCodes;
import com.ems.user.rest.handler.impl.AdminServiceHandlerImpl;

/**
 * @author Bharath Arya
 *
 */
public class Test {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(AppCodes.EAS01.name() + ":" + AppCodes.EAS01.value());
		
		AdminServiceHandlerImpl adminServiceHandler = new AdminServiceHandlerImpl();
		
		//applicationServiceHandler.updateCurrentDayPageHits();
		
		UniqueDataBean uniqueDataBean = new UniqueDataBean();
		uniqueDataBean.setUserName("bharath");
		
		Response response = adminServiceHandler.checkDataExistance(uniqueDataBean);
		
		System.out.println(response);
		
	}

}
