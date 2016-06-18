/**
 * 
 */
package com.ems.user.service.mail.impl;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Bharath Arya
 *
 */
public class ForkAdvice {

	public void fork(final ProceedingJoinPoint proceedingJoinPoint) {
		new Thread(new Runnable() {
			public void run() {
				try {
					proceedingJoinPoint.proceed();
				} catch (Throwable t) {
				}
			}
		}).start();
	}
	
}