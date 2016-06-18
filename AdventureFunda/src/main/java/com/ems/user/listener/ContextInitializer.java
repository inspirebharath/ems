/**
 * 
 */
package com.ems.user.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Bharath Arya
 *
 */
public class ContextInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        //String contextPath = context.getContextPath();
        String contextPath = context.getRealPath("/");
        System.setProperty("contextPath", contextPath);
        System.out.println(contextPath);
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
	
}