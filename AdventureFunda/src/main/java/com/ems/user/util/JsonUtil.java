/**
 * 
 */
package com.ems.user.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.ems.user.exception.EmsException;

/**
 * @author Bharath Arya
 * 
 * This is a utility class which contains methods to convert object to Json string & vice-versa using Jackson API.
 *
 */
public class JsonUtil {

	public static ObjectWriter objectWriter = new ObjectMapper().writer();

	/**
	 * 
	 * @param object
	 * @return
	 * @throws EmsException
	 */
	public static String convertObjectToJSON(Object object) throws EmsException {
		
		String input = "";
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		try {
			input = objectWriter.writeValueAsString(object);
			return input;
		} catch(JsonGenerationException e) {
			throw new EmsException(e.getMessage());
		} catch(JsonMappingException e) {
			throw new EmsException(e.getMessage());
		} catch(IOException e) {
			throw new EmsException(e.getMessage());
		}
		
	}

	/**
	 * 
	 * @param jsonData
	 * @param c
	 * @return
	 * @throws EmsException
	 */
	public static Object convertJSONToObject(String jsonData, Class<?> c) throws EmsException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return mapper.readValue(jsonData, c);
		} catch(JsonParseException e) {
			throw new EmsException(e.getMessage());
		} catch(JsonMappingException e) {
			throw new EmsException(e.getMessage());
		} catch(IOException e) {
			throw new EmsException(e.getMessage());
		}
		
	}
}