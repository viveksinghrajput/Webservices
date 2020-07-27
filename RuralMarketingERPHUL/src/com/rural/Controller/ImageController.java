/*
*ImageController.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {
	static Logger logger =Logger.getLogger(ImageController.class);
	@ResponseBody
	@RequestMapping(value="/get_image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] fetchImage(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException{
		InputStream in =null;
		try{
		 in = new FileInputStream(request.getParameter("path"));
		 } catch (IOException io) {
				logger.error("Problem Occured while loading the file "+io.getMessage());
			}
		 catch (Exception e) {
			 logger.error("Problem Occured while loading the file"+e.getMessage());
		}
		return IOUtils.toByteArray(in);
}
}