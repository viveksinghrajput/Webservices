package com.accenture.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.accenture.entity.Employee;
import com.accenture.entity.UserInfo;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmp() {
		String qlString="from Employee";
		return (List<Employee>) entityManager.createQuery(qlString).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmpById(int id) {
		System.out.println("id................................"+id);
		List<Employee> list=entityManager.createQuery("from Employee where employee_Id=?")
				.setParameter(1, id).getResultList();
		return list;
	}

	@Override
	public String addUser(UserInfo userInfo) {
		entityManager.persist(userInfo);
	
		return "User has successFully saved ::- "+userInfo.getFullName();
	}

	

}
