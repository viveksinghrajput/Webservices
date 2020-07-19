package com.accenture.response;

import java.util.List;

import com.accenture.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class EmployeeResponse {
	
	private String status;
	private String message;
	private List<Employee> listemployee;
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
	public List<Employee> getListemployee() {
		return listemployee;
	}
	public void setListemployee(List<Employee> listemployee) {
		this.listemployee = listemployee;
	}

	
	
	

}
