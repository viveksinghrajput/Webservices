package com.accenture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.entity.Employee;
import com.accenture.entity.UserInfo;
import com.accenture.response.EmployeeResponse;
import com.accenture.services.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private BCryptPasswordEncoder BCryptPasswordEncoder;

	

	@GetMapping("/getAllEmployee")
	public EmployeeResponse getAllEmployee() {
		return employeeService.getAllEmp();
	}

	@GetMapping("/getAllEmployee/{id}")
	public EmployeeResponse getEmployeeById(@PathVariable("id") int id) {
		return employeeService.getEmployeeById(id);

	}

}
