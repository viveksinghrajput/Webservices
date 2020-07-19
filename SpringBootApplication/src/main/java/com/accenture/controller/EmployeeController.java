package com.accenture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.entity.Employee;
import com.accenture.response.EmployeeResponse;
import com.accenture.services.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/admin/saveEmployee")
	public EmployeeResponse saveEmployee(@RequestBody Employee employee) {
		return employeeService.SaveEmployee(employee);
		
	}
	@GetMapping("/employee/getAllEmployee")
	public EmployeeResponse getAllEmp() {
		return employeeService.getAllEmp();
	}
	
	
	

}
