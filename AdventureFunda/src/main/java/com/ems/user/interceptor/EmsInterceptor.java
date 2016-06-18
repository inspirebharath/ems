/**
 * 
 */
package com.ems.user.interceptor;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ems.user.constants.Constants;
import com.ems.user.controller.model.LoginDetails;
import com.ems.user.util.CryptographHandler;
import com.ems.user.util.Properties;
import com.ems.user.util.StringUtil;

/**
 * @author Bharath Arya
 *
 */
public class EmsInterceptor extends HandlerInterceptorAdapter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(EmsInterceptor.class);

	@Autowired
	private SessionRegistryImpl sessionRegistry;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response/*, Object object*/) {

		boolean canForwardRequest = false;

		if (null != request) {

			Locale locale=(Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
			/*if (locale == null) {
				locale = this.setUserLocale(request,response);
			}*/
			if (locale != null) {
				LocaleResolver resolver =  	RequestContextUtils.getLocaleResolver(request);
				if (resolver != null) {
					resolver.setLocale(request,response,locale);
				}
			}

			canForwardRequest = isRequestBeforeSession(request.getRequestURI(), request);
			if(canForwardRequest) {
				// Do Nothing coz these are initial requests which donot need session.
			} else {

				final HttpSession session = request.getSession(false);
				if (null != session) {
					/*String userName = (String) session.getAttribute(URLMappingConstants.EMS_APP_USER);
					if(null==userName){
						try {
							response.sendRedirect(request.getContextPath()
									+ Constants.URL_SPERATOR + Constants.EMS_INVALID_SESSION);
						} catch (IOException e) {
						}
					}*/
					canForwardRequest = checkUserRegistry(request, response, session);
				}
				if (!canForwardRequest) {
					try{
						response.sendRedirect(request.getContextPath()
								+ Constants.URL_SPERATOR
								+ Constants.EMS_INVALID_ACCESS);
					} catch(Exception e){
						logger.error("EmsInterceptor | preHandle "+e);
					}
				}
			}
		}
		return canForwardRequest;
	}

	private boolean isRequestBeforeSession(final String requestURI, HttpServletRequest request) {
		boolean isValidReq = false;
		if (StringUtil.isNullAndEmpty(requestURI)) {
			isValidReq = true;
		} else if(true/*requestURI.contains(URLMappingConstants.EMS_ADMIN_LOGIN)
		            || requestURI.contains(Constants.EMS_INVALID_SESSION) 
		            || requestURI.contains(Constants.EMS_INVALID_ACCESS)
		            || requestURI.contains(URLMappingConstants.EMS_ADMIN_LOG_OUT)
		            || requestURI.contains(URLMappingConstants.FORGOT_PASSWORD)
		            ||requestURI.contains(URLMappingConstants.PASSWORD_RESET)
		            ||requestURI.contains(URLMappingConstants.RESET_PASSWORD)
		            ||requestURI.contains(URLMappingConstants.INVALID_REQUEST_PAGE)*/) {
			isValidReq = true;
		}

		return isValidReq;
	}

	private boolean checkUserRegistry(HttpServletRequest request, 
			HttpServletResponse response, HttpSession session) {

		String userName = (String) session.getAttribute(Constants.EMS_APP_USER);
		if (!StringUtil.isNullAndEmpty(userName)) {

			String userAgent = request.getHeader("user-agent");
			if (null != userAgent) {
				userAgent = userAgent.replaceAll("\\ ", "");
			}
			String encUName = CryptographHandler.encrypt(userName);

			// String encUName = (!StringUtil.isNullAndEmpty(userName)) ? 
			// CryptographHandler.encrypt(userName) : "";

			LoginDetails loginResponseData = null;
			SessionInformation sessionInformation = null;
			Cookie[] cookies = null;
			String cookieValue = "";
			for (Object object : sessionRegistry.getAllPrincipals()) {
				loginResponseData = (LoginDetails) object;
				if (null != loginResponseData && loginResponseData.getUserName().trim().equals(userName)) {

					cookies = request.getCookies();
					for (Cookie cookie : cookies) {
						if (Constants.COOKIE_EMS_NAME.equalsIgnoreCase(cookie.getName())) {
							cookieValue = cookie.getValue();

							// Resetting Logged in user encrypted data into Cookie
							cookie.setMaxAge(15 * 60);
							response.addCookie(cookie);
							break;
						}
					}

					sessionInformation = sessionRegistry.getSessionInformation(encUName);

					if (null == sessionInformation) {
						return false;
					} else {
						sessionInformation.refreshLastRequest();
						if (!loginResponseData.getjSession().equals(userAgent + cookieValue)) {
							Date lastSessionRequestDate = sessionInformation.getLastRequest();
							Date lastRequestDate = new Date(lastSessionRequestDate.getTime());
							lastRequestDate.setTime(lastRequestDate.getTime()
									+ (Integer.parseInt(Properties.getProperty("ems.app.session.timeout")) * 60000));
							Date curDate = new Date();
							if (lastRequestDate.after(curDate)) {
								Cookie myCookie = new Cookie(Constants.COOKIE_EMS_NAME, null);
								myCookie.setMaxAge(0);
								response.addCookie(myCookie);
								sessionRegistry.removeSessionInformation(encUName);
								session.setAttribute(Constants.EMS_APP_USER,null);
								try {
									response.sendRedirect(request.getContextPath() + Constants.URL_SPERATOR + Constants.EMS_INVALID_SESSION);
								} catch (IOException e) {
									logger.error("EmsInterceptor | checkUserRegistry "+e);
								}
								return false;
							}
						}
						// sessionInformation.refreshLastRequest();
					}
				}
			}
		} else {
			Cookie myCookie = new Cookie(Constants.COOKIE_EMS_NAME, null);
			myCookie.setMaxAge(0);
			response.addCookie(myCookie);
			return true;
		}
		return true;
	}
}