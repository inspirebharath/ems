/**
 * 
 */
package com.ems.user.util;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import com.ems.user.constants.Constants;
import com.ems.user.exception.EmsException;

/**
 * @author Bharath Arya
 *
 */
@SuppressWarnings("rawtypes")
public final class StringUtil {

	private static final String TOKEN_DELIMITER = "@A@";

	/**
	 * 
	 */
	private StringUtil() {}

	public static boolean isNullAndEmpty(String data) {
		return (data == null || "".equals(data.trim()));
	}

	public static boolean isListNotNullNEmpty(List list) {
		return (list != null && !list.isEmpty());
	}

	public static boolean isListNullNEmpty(List<Object> list) {
		return (list == null || list.isEmpty());
	}

	/**
	 * @param number
	 * @return
	 */
	public static String toString(Number number) {
		if(null == number) {
			return "";
		} else {
			return String.valueOf(number);
		}
	}

	public static boolean isNullEmpty(String input) {
		return (input == null || "".equals(input.trim()));
	}

	public static String getDateValueForWSAPI(String raw, String time) {
		if(raw == null || "".equals(raw.trim()))
			return null;
		try {
			if(raw.indexOf("/") != -1 || raw.indexOf("-") != -1 || raw.indexOf(".") != -1) {
				String result = "";
				String splitVariable = (raw.indexOf("/") != -1) ? "/" : 
										((raw.indexOf("-") != -1) ? "-" : "\\.");
				String[] raws = raw.split(splitVariable);
				result = (raws[0].length() < 2) ? "0" + raws[0] : raws[0];
				result += "/" + ((raws[1].length() < 2) ? "0" + raws[1] : raws[1]);
				result += "/" + raws[2];
				result += " " + time;
				return result;
			}
		} catch(Exception e) {
		
		}
		return null;
	}

	public static String toAmount(Object object) {
		String amount = "0.00";
		try {
			Double doubleValue = null;
			if(object != null) {
				doubleValue = Double.valueOf(object.toString());
				if(null != doubleValue) {
					amount = String.format("%.2f", doubleValue);
				}
			}
		} catch(NumberFormatException e) {
		
		}
		return amount;
	}

	public static List<Object> startIndexList(int size, int index) {
		List<Object> list = new ArrayList<>();
		for(int i = 0; i <= size / index; i++) {
			list.add((i * index) + 1);
		}
		return list;
	}

	public static List<Object> endIndex(int size, int index) {
		List<Object> list = new ArrayList<>();
		if(size == 0) {
			list.add(1);
		}
		for(int i = 1; i <= size / index; i++) {
			list.add((i * index));
		}
		return list;
	}

	public static String[] convertToArray(String data) {
		if(!isNullEmpty(data)) {
			String arrayData[] = data.split(",");
			return arrayData;
		}
		return null;
	}

	public static String convertString(String[] arrayData) {
		if(arrayData != null && arrayData.length > 0) {
			StringBuilder sb = new StringBuilder();
			for(String arr : arrayData) {
				sb.append(arr + ",");
			}
			return sb.toString().substring(0, sb.toString().length() - 1);
		}
		return "";
	}

	/**
	 * Method to convert String List to String array
	 * 
	 * @param featureList
	 * @return
	 */
	public static String convertListToString(List<String> featureList) {

		if(StringUtil.isListNotNullNEmpty(featureList)) {
			StringBuilder sb = new StringBuilder();
			for(String feature : featureList) {
				sb.append(feature + ",");
			}
			return sb.toString().substring(0, sb.toString().length() - 1);
		}
		return "";
	}

	/**
	 * Method to validate Mobile Number
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean validatePhone(String phone) {
		boolean flag = true;
		Pattern pattern = Pattern.compile("^[0-9]+$");
		Matcher matcher = pattern.matcher(phone);
		flag = matcher.matches();
		return flag;
	}


	public static Boolean checkEquality(String newValue, String oldValue, boolean mand) {
		if(mand) {
			return (newValue.trim().equals(oldValue.trim())) ? true : false;
		} else {
			newValue = (StringUtil.isNullAndEmpty(newValue)) ? "" : newValue.trim();
			oldValue = (StringUtil.isNullAndEmpty(oldValue)) ? "" : oldValue.trim();
			return (newValue.equals(oldValue)) ? true : false;
		}
	}

	/**
	 * Method to get String based on Long value
	 * 
	 * @param value
	 * @return
	 */
	public static String getString(Long value) {
		if(null == value) {
			return null;
		}
		return value.toString();
	}

	/**
	 * Method to get String based on Long value
	 * 
	 * @param value
	 * @return
	 */
	public static Long getLong(String value) {
		if(null == value) {
			return null;
		}
		try {
			return Long.valueOf(value);
		} catch(NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Method to get String based on Long value
	 * 
	 * @param value
	 * @return
	 */
	public static Long getLong(Long value) {
		if(null == value) {
			return 0L;
		}
		return value;
	}

	public static String getSupportedType(String BarType, String qrType) {
		if(!StringUtil.isNullAndEmpty(BarType) && StringUtil.isNullAndEmpty(qrType))
			return "|" + BarType + "|";
		else if(StringUtil.isNullAndEmpty(BarType) && !StringUtil.isNullAndEmpty(qrType))
			return "|" + qrType + "|";
		return "|" + BarType + "|" + qrType + "|";

	}

	public static List<String> getSubCodeType(String codeType) {
		List<String> codeTypeList = new ArrayList<>();
		codeType = codeType.substring(1, codeType.length() - 1);
		if(codeType.contains("\\|")) {
			String[] codeTypeData = codeType.split("\\|");
			for(String data : codeTypeData)
				codeTypeList.add(data);
		} else {
			codeTypeList.add(codeType);
		}

		return codeTypeList;
	}

	public static String decode(String encoded) {
		if(null == encoded || "".equals(encoded.trim())) {
			return "";
		}

		try {
			return URLDecoder.decode(encoded, "UTF-8");
		} catch(Exception e) {
		
		}
		return encoded;
	}

	public static String ListToString(List<String> featureList) {

		if(StringUtil.isListNotNullNEmpty(featureList)) {
			StringBuilder sb = new StringBuilder();
			for(String feature : featureList) {
				sb.append(feature + "\r\n ");
			}
			return sb.toString();
		}
		return "";
	}
	
	public static String getEmailToken(String userId, String email) {
		StringBuffer sb = new StringBuffer();
		sb.append(TOKEN_DELIMITER);
		sb.append(userId);

		sb.append(TOKEN_DELIMITER);
		sb.append(email);
		sb.append(TOKEN_DELIMITER);
		sb.append(System.currentTimeMillis());
		String token = null;
		try {
			token = EncryptionUtil.encrypt(sb.toString());
			token = new String(Base64.encodeBase64(token.getBytes()));
		} catch(Exception e) {
		
		}
		return token;
	}
	
	/**
	 * Method to parse email token
	 * 
	 * @param emailToken
	 * @return
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public static String[] parseEmailToken(String emailToken) throws EmsException {
		if(isNullAndEmpty(emailToken)) {
			throw new IllegalArgumentException();
		}
		String token = null;
		try {
			token = new String(Base64.decodeBase64(emailToken.getBytes()));
			token = EncryptionUtil.decrypt(token);
		} catch(Exception e) {
			throw new IllegalArgumentException();
		}
		if(token.contains(TOKEN_DELIMITER)) {
			String[] emailTokens = token.split(TOKEN_DELIMITER);
			if(emailTokens.length != 4) {
				throw new IllegalArgumentException();
			} else {
				String tokeTime = emailTokens[emailTokens.length - 1];

				int minutes = Integer.parseInt(Properties.getProperty("chatak.admin.email.token.expiry.time"));
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(Long.valueOf(tokeTime));
				cal.add(Calendar.MINUTE, minutes);
				Calendar now = Calendar.getInstance();
				if(now.after(cal)) {
					throw new EmsException(Properties.getProperty("chatak.admin.email.token.expiry.time"));
				}
			}
			return emailTokens;
		} else {
			throw new EmsException(Properties.getProperty("chatak.admin.email.token.expiry.time"));
		}
	}
	
	/**
	 * Method to get Cookie Value
	 * 
	 * @param request
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String cookieValue = "DUMMY_COOKIETEST";
		for(Cookie cookie : cookies) {
			if(Constants.COOKIE_EMS_NAME.equalsIgnoreCase(cookie.getName())) {
				cookieValue = cookie.getValue();
				break;
			}
		}
		return cookieValue;
	}

	/**
	 * Method to replace all injected Scripts
	 * 
	 * @param description
	 * @return
	 */
	private static String replaceXSS(String description) {
		String newDesc = description.replaceAll("\\s{2,}", "");
		if(newDesc.toLowerCase().contains("<script>") 
					|| newDesc.toLowerCase().contains("type='text/javascript'")
					|| newDesc.toLowerCase().contains("javascript")) {
			return "";
		}
		description = description.replaceAll("<", "<").replaceAll(">", ">");
		description = description.replaceAll("eval\\((.*)\\)", "");
		description = description.replaceAll("[\\\"\\\'][\\s]*((?i)javascript):(.*)[\\\"\\\']", "\"\"");
		return description;
	}
	
	/**
	 * Check given input has any scripts
	 * 
	 * @param description
	 * @throws Exception
	 */
	public static void checkScriptData(String description) throws Exception {
		if(!StringUtil.isNullAndEmpty(description)
				&& description.trim().length() > StringUtil.replaceXSS(description.trim()).length()) {
			throw new Exception(Properties.getProperty("ems.script.inject.error"));
		}
	}

}