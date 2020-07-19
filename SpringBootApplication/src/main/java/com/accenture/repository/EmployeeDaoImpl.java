package com.accenture.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.entity.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

	
	@PersistenceContext
    private EntityManager entityManager;

    

	@Override
	public String saveEmployee(Employee employee) {
		
		entityManager.persist(employee);
		return "Record Submitted Successfully with :" + employee.getEmployee_Name();
	}



	@Override
	public List<Employee> getAllEmp() {
		String qlString="from Employee";
		return (List<Employee>) entityManager.createQuery(qlString).getResultList();
	}

	

}
