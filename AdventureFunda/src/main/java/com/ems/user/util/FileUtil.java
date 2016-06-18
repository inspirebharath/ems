/**
 * 
 */
package com.ems.user.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ems.user.beans.FileInfoBean;
import com.ems.user.constants.AppEntityEnum;
import com.ems.user.constants.FileConstants;

/**
 * @author Bharath Arya
 *
 */
public class FileUtil implements FileConstants {

	/*private static final String LOCATION = "C:/temp/"; // Temporary location where files will be stored

    private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
                                                        // Beyond that size spring will throw exception.
    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.

    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
	 */ 

	private static final String BASEFILEPATH = ApplicationUtil.CONTEXT_PATH + File.separator + APP_INFO + File.separator;

	private static final String JSON_EXTENSION = ".json";  

	public boolean isValidFileSize(File file) {
		return false;
	}	

	public void uploadFile(String sessionId, FileInfoBean fileInfoBean, MultipartFile[] files) throws IOException {

		String filePath = new StringBuilder()
							.append(ApplicationUtil.CONTEXT_PATH).append(File.separator)
							.append(getFilePath(fileInfoBean.getEntityType(), fileInfoBean.getImageType()))
							.toString();

		String fileName = "";

		MultipartFile file = null;

		File directory = new File(filePath.substring(0,filePath.lastIndexOf(File.separator)));
		if (!directory.exists()) {
			directory.mkdirs();
		}

		for(int i = 0; i < files.length; i++) {
			file = files[i];

			fileName = getTempFileName(sessionId, fileInfoBean.getEntityType(), fileInfoBean.getImageType(), getFileExtension(file));

			FileCopyUtils.copy(file.getBytes(), new File(directory.getAbsolutePath() + File.separator + fileName));
		}
	}

	public void moveFile(String sessionId, FileInfoBean fileInfoBean, String entityIdForFolderName) throws IOException {

		List<String> filePaths = getEntityFilePaths(fileInfoBean);
		//String filePath = ApplicationUtil.CONTEXT_PATH + File.separator + getFilePath(fileInfoBean.getEntityType(), fileInfoBean.getImageType());

		String fileName = "";

		File file = null;
		File[] files = null;
		File directory = null;
		String tempFilePath = "";
		int filePathsSize = filePaths.size();

		for(int i = 0; i < filePathsSize; i++) {
			tempFilePath = filePaths.get(i);
			directory = new File(tempFilePath);

			if(!CommonUtil.isNull(directory)) {
				files = directory.listFiles();

				if(!CommonUtil.isNull(files)) {
					directory = new File(tempFilePath + entityIdForFolderName);
					if (!directory.exists()) {
						directory.mkdirs();
					}

					int fileNameIndex = 0;
					for(int j = 0; j < files.length; j++) {
						file = files[j];

						fileName = file.getName(); 
						fileNameIndex = fileName.indexOf("_" + sessionId); 

						if(fileNameIndex > 0) {
							fileName = fileName.substring(0, fileNameIndex);
							file.renameTo(new File(new StringBuilder().append(directory.getAbsolutePath()).append(File.separator).append(fileName).append(getFileExtension(file)).toString()));
							break;
						}

						// fileName = getCustomFileName(fileInfoBean.getEntityType(), fileInfoBean.getImageType(), getFileExtension(file));
						//FileCopyUtils.copy(file.getBytes(), new File(directory.getAbsolutePath() + File.separator + fileName));
					}
				}

			}

		}

	}

	private String getFilePath(String entityType, String imageType) {

		if(imageType.equals(PROFILE_PICS)) {
			return getEntityProfilePicPath(entityType);
		}
		
		StringBuilder sb = new StringBuilder();
			
		sb.append(APP_INFO).append(File.separator);

		switch (entityType) {
			case PARTNERS:
				sb.append(PARTNERS).append(File.separator);
				switch (imageType) {
					case ORGANIZATION_LOGOS:
						sb.append(ORGANIZATION_LOGOS).append(File.separator);
						break;
	
					case INDIVIDUAL_PHOTOS:
						sb.append(INDIVIDUAL_PHOTOS).append(File.separator);
						break;
	
					case PASSPORT_PHOTOCOPIES:
						sb.append(PASSPORT_PHOTOCOPIES).append(File.separator);
						break;
	
					default:
						break;
				}
	
				break;

		case EVENTS:

			break;

		case USERS:				

			break;
		default:
			break;
		}

		return sb.toString();

	}

	private List<String> getEntityFilePaths(FileInfoBean fileInfoBean) {

		String entityType = fileInfoBean.getEntityType();
		String uploadedFilesFlag = fileInfoBean.getUploadedFilesFlag();

		// uploadedFilesFlag file types order
		// partner = passport, photo, logo
		// events = mainpic, supporting pics

		StringBuilder sb = new StringBuilder();
		List<String> filePathsList = new ArrayList<>(); 

		sb.append(BASEFILEPATH);
		
		switch (entityType) {
		case ADMINS:
			sb.append(ADMINS).append(File.separator);
			break;

		case PARTNERS:
			sb.append(PARTNERS).append(File.separator);

			if(uploadedFilesFlag.charAt(0) == 1) {
				filePathsList.add(sb.append(PASSPORT_PHOTOCOPIES).append(File.separator).toString());
			}
			if(uploadedFilesFlag.charAt(1) == 1) {
				filePathsList.add(sb.append(INDIVIDUAL_PHOTOS).append(File.separator).toString());
			}
			if(uploadedFilesFlag.charAt(2) == 1) {
				filePathsList.add(sb.append(ORGANIZATION_LOGOS).append(File.separator).toString());
			}

			break;

		case EVENTS:
			sb.append(ADMINS).append(File.separator);

			break;

		case USERS:				
			sb.append(USERS).append(File.separator);

			break;

		default:
			break;
		}

		return filePathsList;

	}

	private String getEntityProfilePicPath(String entityType) {

		StringBuilder sb = new StringBuilder();
		sb.append(BASEFILEPATH).append(PROFILE_PICS).append(File.separator);

		switch (entityType) {
		case ADMINS:
			sb.append(ADMINS);
			break;

		case PARTNERS:
			sb.append(PARTNERS);
			break;

		case USERS:				
			sb.append(USERS);
			break;

		default:
			return null;
		}

		return sb.append(File.separator).toString();

	}

	private String getTempFileName(String sessionId, String entityType, String imageType, String fileExtension) {
		StringBuilder sb = new StringBuilder();
		
		if(imageType.equals(PROFILE_PICS)) {
			return sb.append(getEntityProfilePicPath(entityType)).append("_").append(sessionId).append(fileExtension).toString();
		}

		switch (entityType) {
		case ADMINS:

			break;

		case PARTNERS:
			switch (imageType) {
				case ORGANIZATION_LOGOS:
					sb.append(ORG_LOGO);
					break;
	
				case INDIVIDUAL_PHOTOS:
					sb.append(PARTNER_PHOTO);
					break;
	
				case PASSPORT_PHOTOCOPIES:
					sb.append(PASSPORT);
					break;
	
				default:
					break;
			}

			break;

		case EVENTS:

			break;

		case USERS:				

			break;
		default:
			break;
		}

		return sb.append("_").append(sessionId).append(fileExtension).toString();
	}

	public void createEntityDataFile(String userType, String folderName, String dataToWrite) throws IOException {

		if(!StringUtil.isNullAndEmpty(userType)) {

			String fileNameWithPath = getEntityDataFilePathWithName(userType, folderName, true);
			File file = new File(fileNameWithPath.toString());

			FileCopyUtils.copy(dataToWrite.getBytes(), file);
		}

	}

	private String getEntityDataFilePathWithName(String userType, String folderName, boolean isToCreateFile) {

		String fileName = ""; 

		StringBuilder fileNameWithPath = new StringBuilder();
		fileNameWithPath.append(BASEFILEPATH);

		if(userType.equals(AppEntityEnum.PARTNER.name())) {
			fileNameWithPath.append(PARTNERS)
			.append(File.separator)
			.append(ADDITIONAL_DATA);
			fileName = PARTNER_DATA;
		} else if(userType.equals(AppEntityEnum.PARTNER.name())) {
			fileName = EVENT_INFO;
			fileNameWithPath.append(EVENTS)
			.append(File.separator)
			.append(EVENT_DETAILS);
		} 

		fileNameWithPath.append(File.separator).append(folderName);

		if(isToCreateFile) {
			File file = new File(fileNameWithPath.toString());
			if (!file.exists()) {
				file.mkdirs();
			}
		}

		fileNameWithPath.append(File.separator).append(fileName).append(JSON_EXTENSION);

		return fileNameWithPath.toString();
	}

	public String getEntityDataFileContent(String userType, String folderName, String dataToWrite) throws IOException {

		String fileContent = "";
		if(!StringUtil.isNullAndEmpty(userType)) {

			String fileNameWithPath = getEntityDataFilePathWithName(userType, folderName, false);
			File file = new File(fileNameWithPath);

			fileContent = getFileContent(file);

		}

		return fileContent;

	}
	
	private String getFileContent(String filePath) throws IOException {

		File file = new File(filePath);
		return getFileContent(file);

	}

	private String getFileContent(File file) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));

		StringBuilder sb = new StringBuilder();

		String line = "";
		while((line = reader.readLine()) != null){
			sb.append(line);
		}

		reader.close();

		return sb.toString();

	}
	
	private String getFileExtension(MultipartFile file) throws UnsupportedEncodingException {

		StringBuilder fileExtension = new StringBuilder();

		String fileName = new String(file.getOriginalFilename().getBytes("iso-8859-1"),"UTF-8");

		int dotIndex = fileName.lastIndexOf('.');

		if (dotIndex > 0) {
			fileExtension.append(fileName.substring(dotIndex));
		}

		return fileExtension.toString();
	}
	
	private String getFileExtension(File file) throws UnsupportedEncodingException {

		StringBuilder fileExtension = new StringBuilder();

		String fileName = new String(file.getName().getBytes("iso-8859-1"),"UTF-8");

		int dotIndex = fileName.lastIndexOf('.');

		if (dotIndex > 0) {
			fileExtension.append(fileName.substring(dotIndex));
		}

		return fileExtension.toString();
	}

}