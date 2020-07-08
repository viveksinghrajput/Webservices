package com.spring.rest.curd.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.curd.dao.StudentDao;
import com.spring.rest.curd.dto.StudentResponse;
import com.spring.rest.curd.model.Student;


@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	
	StudentResponse response=new StudentResponse();

	@Override
	public StudentResponse SaveStudent(Student student) {
		
		String msg=studentDao.SaveStudent(student);
		response.setMessage(msg);
		response.setStatus("success");
		return response;
		
	}

	@Override
	public StudentResponse GetAllStudent() {
		List<Student> liststudent=studentDao.GetAllStudent();
		response.setListstudent(liststudent);
		response.setStatus("success");
		return response;
		
	}

	public StudentResponse GetStudentByName(String name) {
		List<Student> listStudent=studentDao.GetStudentByName(name);
		response.setStatus("success");
		response.setListstudent(listStudent);
		return response;
		
	}

	@Override
	public StudentResponse GetStudentById(int id) {
		List<Student> listStudents=studentDao.GetStudentById(id);
		response.setStatus("success");
		response.setListstudent(listStudents);
		return response;
	}

	@Override
	public StudentResponse deleteStudentById(int id) {
		String msg=studentDao.deleteStudentById(id);
		response.setMessage(msg);
		response.setStatus("Success");
		return response;
		
	}

	@Override
	public StudentResponse updateStudentById(Student student) {
		List<Student> listStudent=studentDao.updateStudentById(student);
		response.setStatus("Success");
		response.setListstudent(listStudent);
		return response;
	}

	
}
