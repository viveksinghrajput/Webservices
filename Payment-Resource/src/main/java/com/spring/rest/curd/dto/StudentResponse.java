package com.spring.rest.curd.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.spring.rest.curd.model.Student;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class StudentResponse {

	private String status;
	private String message;
	private List<Student> liststudent;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Student> getListstudent() {
		return liststudent;
	}

	public void setListstudent(List<Student> liststudent) {
		this.liststudent = liststudent;
	}

}
