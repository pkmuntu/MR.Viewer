package com.virtusa.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static String saveFile(String uploadDir, MultipartFile multipartFile) throws IOException {
		File file = null;
		// create folder in server machine file System having uploaded location
		if (uploadDir.equalsIgnoreCase("poster"))
			file = new File("E:\\Mr.ViewerProject\\Mr.Viewer\\MrViewer\\src\\main\\webapp\\resources\\images");
		if (uploadDir.equalsIgnoreCase("movie"))
			file = new File("E:\\Mr.ViewerProject\\Mr.Viewer\\MrViewer\\src\\main\\webapp\\resources\\videos");
		if (!file.exists())
			file.mkdir();

		// get names of the upload files from model object
		String fileUrl = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		// create Input streams representing uploaded file
		try (InputStream IS = multipartFile.getInputStream()) {
			// create OutputStream pointing to dest files
			try (OutputStream OS = new FileOutputStream(file.getAbsolutePath().replace('\\', '/') + "/" + fileUrl)) {
				// Copy the content
				IOUtils.copy(IS, OS);
				// return the url
				return fileUrl;
			} // outputStream try block
		} // input stream try block
		catch (IOException ioe) {
			throw ioe;
		}

	}
}
