package com.accenture.services;

import com.accenture.entity.Employee;
import com.accenture.response.EmployeeResponse;

public interface EmployeeService {

	EmployeeResponse SaveEmployee(Employee employee);

	EmployeeResponse getAllEmp();

}
