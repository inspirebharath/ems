/**
 * 
 */
package com.ems.user.rest.handler;

import org.springframework.web.multipart.MultipartFile;

import com.ems.user.beans.FileInfoBean;
import com.ems.user.beans.Response;

/**
 * @author Bharath Arya
 *
 */
public interface FileManagerServiceHandler {

	Response uploadFiles(String sessionId, MultipartFile[] files, FileInfoBean fileDto);
	
}