package com.accenture.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.entity.Employee;
import com.accenture.response.EmployeeResponse;
import com.accenture.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/saveEmployee")
	public EmployeeResponse addEmployee(@RequestBody Employee employee) {
		return employeeService.SaveEmployee(employee);
		
	}
	@GetMapping("/getAllEmployee")
	public EmployeeResponse getAllEmployee() {
		return employeeService.getAllEmp();
	}
	
	@GetMapping("/getAllEmployee/{id}")
	public EmployeeResponse getEmployeeById(@PathVariable("id") int id) {
		return employeeService.getEmployeeById(id);
		
	}
	
	
	

}
