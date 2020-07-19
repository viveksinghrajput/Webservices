package com.accenture.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.entity.Employee;
import com.accenture.repository.EmployeeDao;
import com.accenture.response.EmployeeResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeedao;
	
	EmployeeResponse response=new EmployeeResponse();

	@Override
	public EmployeeResponse SaveEmployee(Employee employee) {
		String msg=employeedao.saveEmployee(employee);
		response.setMessage(msg);
		response.setStatus("Success");
		return response;
	}

	@Override
	public EmployeeResponse getAllEmp() {
		List<Employee> listemp=employeedao.getAllEmp();
		response.setStatus("Success");
		response.setListemployee(listemp);
		return response;
	}

	@Override
	public EmployeeResponse getEmployeeById(int id) {
		List<Employee> listemp=employeedao.getEmpById(id);
		response.setListemployee(listemp);
		response.setStatus("Success");
		return response;
	}

	
}
