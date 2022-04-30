package com.muntu.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StreamingController {

	@Autowired
	private Logger logger;

	@RequestMapping(value = "/loadVideoFile/{title}", method = RequestMethod.GET)
	@ResponseBody
	public void loadVideoFile(HttpServletResponse response, @PathVariable String title) {
		logger.debug("StreamingController class  loadVideoFile method");
		logger.info("StreamingController class  loadVideoFile method");
		try {
			String filePath = "E:\\Mr.ViewerProject\\Mr.Viewer\\MrViewer\\src\\main\\webapp\\resources\\videos\\"
					+ title;
			int fileSize = (int) new File(filePath).length();
			response.setContentLength(fileSize);
			response.setContentType("video/mp4");
			FileInputStream inputStream = new FileInputStream(filePath);
			ServletOutputStream outputStream = response.getOutputStream();
			int value = IOUtils.copy(inputStream, outputStream);
			System.out.println("File Size :: " + fileSize);
			System.out.println("Copied Bytes :: " + value);

			IOUtils.close(inputStream);
			IOUtils.close(outputStream);

			response.setStatus(HttpServletResponse.SC_OK);
		} catch (java.io.FileNotFoundException e) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

}
