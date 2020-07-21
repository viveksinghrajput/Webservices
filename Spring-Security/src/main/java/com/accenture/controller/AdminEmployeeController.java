package com.accenture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/rest")
public class AdminEmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private BCryptPasswordEncoder BCryptPasswordEncoder;

	
	
	@PostMapping("/addUser")
	public EmployeeResponse addUser(@RequestBody UserInfo userInfo) {
		String password = userInfo.getPassword();
		String encryptpwd = BCryptPasswordEncoder.encode(password);
		userInfo.setPassword(encryptpwd);
		return employeeService.adduser(userInfo);

	}

	@PostMapping("/saveEmployee")
	public EmployeeResponse addEmployee(@RequestBody Employee employee) {
		return employeeService.SaveEmployee(employee);

	}

	

}
