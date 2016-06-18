/**
 * 
 */
package com.ems.user.spring.util;

import java.io.Serializable;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistryImpl;

/**
 * @author Bharath Arya
 *
 */
public class EmsAuthenticationProvider implements AuthenticationProvider, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5789717771891240999L;
	
	private SessionRegistryImpl sessionRegistry;

	/**
	 * Method to authenticate the user
	 * 
	 * @param authentication
	 */
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authentication.getCredentials(),
				authentication.getCredentials(),
				authentication.getAuthorities());
		return authenticationToken;
	}

	/**
	 * Method to supports authenticated user
	 * 
	 * @param authentication
	 */
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	/**
	 * @return the sessionRegistry
	 */
	public SessionRegistryImpl getSessionRegistry() {
		return sessionRegistry;
	}

	/**
	 * @param sessionRegistry
	 *          the sessionRegistry to set
	 */
	public void setSessionRegistry(SessionRegistryImpl sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}
	
}
