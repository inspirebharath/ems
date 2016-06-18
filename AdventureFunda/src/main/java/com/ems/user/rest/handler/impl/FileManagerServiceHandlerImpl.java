/**
 * 
 */
package com.ems.user.rest.handler.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ems.user.beans.FileInfoBean;
import com.ems.user.beans.Response;
import com.ems.user.constants.AppCodes;
import com.ems.user.rest.handler.FileManagerServiceHandler;
import com.ems.user.util.CommonUtil;
import com.ems.user.util.FileUtil;

/**
 * @author Bharath Arya
 *
 */
@Service
public class FileManagerServiceHandlerImpl implements FileManagerServiceHandler {

	@Override
	public Response uploadFiles(String sessionId, MultipartFile[] files, FileInfoBean fileDto) {
		
		Response response = new Response();
		
		FileUtil fileUtil = new FileUtil();
		try {
			fileUtil.uploadFile(sessionId, fileDto, files);
			CommonUtil.populateResponseSuccessData(response, AppCodes.EAS13);
		} catch (IOException e) {
			CommonUtil.populateResponseErrorData(response, AppCodes.EAS14, e);
		}
		
		return response;
	}
	
	/*public static String getContextPath(ServletContext context) {

		String contextPath = context.getRealPath("/");
		
		if(contextPath.endsWith(File.separator)) {
			contextPath = contextPath.substring(0, (contextPath.length() - 1));
		}
		
		return contextPath;
	}*/

}