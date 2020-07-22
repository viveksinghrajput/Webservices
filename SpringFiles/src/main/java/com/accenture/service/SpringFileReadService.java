package com.accenture.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.accenture.model.User;

public interface SpringFileReadService {

	List<User> findAll();

	boolean saveDataFromFileUpload(MultipartFile file);

}
