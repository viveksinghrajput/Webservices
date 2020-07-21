package com.accenture.repository;

import java.util.List;

import com.accenture.entity.Employee;
import com.accenture.entity.UserInfo;

public interface EmployeeDao {

	String saveEmployee(Employee employee);

	List<Employee> getAllEmp();

	List<Employee> getEmpById(int id);

	String addUser(UserInfo userInfo);

	

}
