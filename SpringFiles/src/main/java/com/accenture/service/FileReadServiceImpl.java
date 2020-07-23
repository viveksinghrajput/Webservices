package com.accenture.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accenture.model.User;
import com.accenture.repository.FileReadDao;

@Service
public class FileReadServiceImpl implements FileReadService {
	
	@Autowired
	private FileReadDao fileReadDao;

	@Override
	public boolean saveDataFromFileUpload(MultipartFile file) {
		
		return fileReadDao.saveDataFromFileUpload(file);
	}

	@Override
	public List<User> findAll() {
		
		return fileReadDao.findAll();
	}

	@Override
	public boolean createPdf(List<User> listuser, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		
		return fileReadDao.createPdf(listuser,context,request,response);
	}

	@Override
	public boolean createExcel(List<User> listuser, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		
		return fileReadDao.createExcel(listuser,context,request,response);
	}

	@Override
	public boolean createJson(List<User> listuser, ServletContext context) {
		
		return fileReadDao.createJson(listuser,context);
	}

	@Override
	public boolean createCSV(List<User> listuser, ServletContext context) {
		
		return fileReadDao.createCSV(listuser,context);
	}
	
	

}
