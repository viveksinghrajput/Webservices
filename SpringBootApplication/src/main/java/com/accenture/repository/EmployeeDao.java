package com.accenture.repository;

import java.util.List;

import com.accenture.entity.Employee;

public interface EmployeeDao {

	String saveEmployee(Employee employee);

	List<Employee> getAllEmp();

	

}
