/**
 * 
 */
package com.ems.user.constants;

/**
 * @author Bharath Arya
 *
 */
public interface EmailConstants {
	
	// Template File Paths
	
	static final String ADMIN_REGISTER = "/adminCreate.vm";
	
	static final String PARTNER_REGISTER = "/partnerCreate.vm";
	
	static final String PARTNER_REGISTER_NOTIFY_TO_ADMINS = "/partnerCreateNotify.vm";
	
	static final String USER_REGISTER = "/userCreate.vm";
	
	static final String EVENT_REGISTER = "/eventCreate.vm";
	
	static final String ADMIN_UPDATE = "/adminUpdate.vm";
	
	static final String PARTNER_UPDATE = "/partnerUpdate.vm";
	
	static final String USER_UPDATE = "/userUpdate.vm";
	
	static final String EVENT_UPDATE = "/eventUpdate.vm";
	
	static final String ADMIN_STATUS_CHANGE = "/adminStatusChange.vm";
	
	static final String PARTNER_STATUS_CHANGE = "/partnerStatusChange.vm";
	
	static final String USER_STATUS_CHANGE = "/userStatusChange.vm";
	
	static final String EVENT_STATUS_CHANGE = "/eventStatusChange.vm";
	
	static final String USER_PWD_CHANGE = "/passwordChange.vm";
	
	static final String USER_PWD_RESET = "/resetPassword.vm";
	
	// Email Subjects
	
	static final String ADMIN_REGISTER_SUBJECT = "ems.email.admin.creation";
	
	static final String PARTNER_REGISTER_SUBJECT = "ems.email.partner.creation";
	
	static final String PARTNER_REGISTER_NOTIFY_TO_ADMINS_SUBJECT = "ems.email.partner.creation.notify";
	
	static final String USER_REGISTER_SUBJECT = "ems.email.user.creation";
	
	static final String EVENT_REGISTER_SUBJECT = "ems.email.event.creation";
	
	static final String ADMIN_UPDATE_SUBJECT = "ems.email.admin.update";
	
	static final String PARTNER_UPDATE_SUBJECT = "ems.email.partner.update";
	
	static final String USER_UPDATE_SUBJECT = "ems.email.user.update";
	
	static final String EVENT_UPDATE_SUBJECT = "ems.email.event.update";
	
	static final String ADMIN_STATUS_CHANGE_SUBJECT = "ems.email.admin.statusChange";
	
	static final String PARTNER_STATUS_CHANGE_SUBJECT = "ems.email.partner.statusChange";
	
	static final String USER_STATUS_CHANGE_SUBJECT = "ems.email.user.statusChange";
	
	static final String EVENT_STATUS_CHANGE_SUBJECT = "ems.email.event.statusChange";
	
	static final String USER_PWD_CHANGE_SUBJECT = "ems.email.user.passwordChange";
	
	static final String USER_PWD_RESET_SUBJECT = "ems.email.user.passwordReset";
	
}