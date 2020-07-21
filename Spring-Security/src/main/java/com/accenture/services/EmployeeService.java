package com.accenture.services;

import org.springframework.security.access.annotation.Secured;

import com.accenture.entity.Employee;
import com.accenture.entity.UserInfo;
import com.accenture.response.EmployeeResponse;

public interface EmployeeService {

	@Secured({ "ROLE_ADMIN" })
	EmployeeResponse SaveEmployee(Employee employee);

	@Secured(value = { "ROLE_ADMIN" })
	EmployeeResponse adduser(UserInfo userInfo);

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	EmployeeResponse getAllEmp();

	@Secured(value = { "ROLE_ADMIN", "ROLE_USER" })
	EmployeeResponse getEmployeeById(int id);

}
