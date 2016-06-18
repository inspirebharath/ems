/**
 * 
 */
package com.ems.user.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bharath Arya
 *
 */
public final class CryptographHandler {

	private static String ALPHA_CHARS = "CK8A3BNEFV3AIC5K13RFKA3EJF6F9486M48507B5P5DFRK8A3BEF6F9486M48507B5P5DFK";

	private static String PREFIX = "_N20G04U0K06T_";

	private static String SUFFIX = "I01L02U20S40M==";

	/**
	 * Hex decimal character array. Used in selecting the mapping for each four binary bits.
	 */
	private static final char HEX[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static final int HEX_CHAR_LENGTH = 4;

	private static final String TEMP_SECURE_WORD = "@@GOOD_NATURE@@";

	private Pattern pattern;

	private Matcher matcher;

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@,Â£^*?â‚¬\"!$]).{8,16})";

	public CryptographHandler() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	/**
	 * Encrypt the password string using MD5 encryption and return the Hex decimal format of it.
	 * 
	 * @param password
	 * @return: MD5 encrypted password in Hex decimal format.
	 * @throws Exception
	 */
	public static String encodePassword(String password) {
		try {

			String tempPassword = TEMP_SECURE_WORD + password + TEMP_SECURE_WORD;
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] md5Binary = messageDigest.digest(tempPassword.getBytes());
			String hexParam = encodeHex(md5Binary);
			return hexParam.toUpperCase();
		} catch(NoSuchAlgorithmException e) {
			
		}
		return null;
	}
	
	/**
	 * Encode the byte array of binary data to hex decimal format.
	 * 
	 * @param data: byte array
	 * @return: Hex decimal string representing the bytes
	 */
	private static String encodeHex(byte data[]) {
		int datalength = data.length;
		// multiply by 2 as every byte will be represented by two characters
		char out[] = new char[datalength * 2];
		int j = 0;
		for(int i = 0; i < datalength; i++) {
			out[j++] = HEX[(0xf0 & data[i]) >>> HEX_CHAR_LENGTH];
			out[j++] = HEX[0xf & data[i]];
		}
		return new String(out);
	}

	/**
	 * This method used for generate the alpha numeric password based on length.
	 * 
	 * @return password
	 */
	public static String getSystemGeneratedPassword(int length) {
		
		final String charString = "0123456789abcdefghijklmnopqrstuvwxyz!@#$%^&*ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(length);
		
		for(int i = 0; i < length; i++) {
			sb.append(charString.charAt(rnd.nextInt(charString.length())));
		}
		
		return sb.toString();
		
	}

	/**
	 * Generate Random numbers for given length.
	 * 
	 * @param length
	 * @return
	 */
	public static String generateRandomNumber(int length) {
		
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for(int n = 0; n < length; n++) {
			int j = (Math.abs(random.nextInt()) % 10);
			// If First digit is "0", skip that and get next random
			if(n == 0 && j == 0) {
				n--;
				continue;
			}
			sb.append(Integer.toString(j));
		}
		return sb.toString();
		
	}

	/**
	 * Validate password with regular expression.
	 * 
	 * @param password for validation
	 * @return true valid password, false invalid password
	 */
	public boolean validate(final String password) {

		matcher = pattern.matcher(password);
		return matcher.matches();

	}

	/**
	 * Method used to encrypt raw string format to encrypted format.
	 * 
	 * @param str: raw string
	 * @return encrypted string
	 */
	public static String encrypt(String str) {
		String encrypted = PREFIX;

		for(int k = 0; k < str.length(); k++) {
			encrypted += Character.toString(ALPHA_CHARS.charAt(k)) + Character.toString(str.charAt(k));
		}
		encrypted += SUFFIX;
		return encrypted;
	}

	/**
	 * Method used to decrypt encrypted string to raw string.
	 * 
	 * @param value
	 * @return
	 */
	public static String decrypt(String str) {
		String decrypted = "";
		str = str.substring(PREFIX.length());

		str = str.substring(0, str.length() - SUFFIX.length());

		for(int k = 1; k < str.length(); k = k + 2) {
			decrypted += Character.toString(str.charAt(k));
		}
		return decrypted;
	}
	
	public static void main(String[] args) {
		System.out.println(getSystemGeneratedPassword(10));
		System.out.println("EN:"+encrypt("1234532328@@10@@bharath@@admin@@"));
		System.out.println("EN:"+decrypt(encrypt("1234532328@@10@@bharath@@admin@@")));
	}

}