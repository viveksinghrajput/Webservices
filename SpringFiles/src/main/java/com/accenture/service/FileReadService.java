package com.accenture.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.accenture.model.User;

public interface FileReadService {
	
	List<User> findAll();

	boolean saveDataFromFileUpload(MultipartFile file);

	boolean createPdf(List<User> listuser, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

	boolean createExcel(List<User> listuser, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

	boolean createJson(List<User> listuser, ServletContext context);

	boolean createCSV(List<User> listuser, ServletContext context);

	

}
