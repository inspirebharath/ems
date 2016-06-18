/**
 * 
 */
package com.ems.user.rest.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ems.user.beans.FileInfoBean;
import com.ems.user.beans.Response;
import com.ems.user.constants.RestServiceURLs;
import com.ems.user.rest.handler.FileManagerServiceHandler;

/**
 * @author Bharath Arya
 *
 */
@RestController
@RequestMapping(value = "/fileService", consumes =  {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class FileManagerService implements RestServiceURLs {
	
	@Autowired
	private FileManagerServiceHandler fileManagerServiceHandler;
	
	@RequestMapping(value = EMS_FILE_UPLOAD, method = RequestMethod.POST)
	public Response uploadFiles(HttpSession session, @RequestPart(value = "fileDto") FileInfoBean fileDto, @RequestPart("files") MultipartFile[] files) {
		
		String sessionId = "";
		if(null != session) {
			sessionId = session.getId();
		}
		return fileManagerServiceHandler.uploadFiles(sessionId, files, fileDto);
		
	}
	
}