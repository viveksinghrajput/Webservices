package com.spring.rest.curd.service;


import com.spring.rest.curd.dto.StudentResponse;
import com.spring.rest.curd.model.Student;

public interface StudentService {

	StudentResponse SaveStudent(Student student);

	StudentResponse GetAllStudent();

	StudentResponse GetStudentByName(String name);

	StudentResponse GetStudentById(int id);

	StudentResponse deleteStudentById(int id);

	StudentResponse updateStudentById(Student student);



}
