package com.spring.rest.curd.dao;

import java.util.List;

import com.spring.rest.curd.model.Student;

public interface StudentDao {

	String SaveStudent(Student student);

	List<Student> GetAllStudent();

	List<Student> GetStudentByName(String name);

	List<Student> GetStudentById(int id);

	String deleteStudentById(int id);

	List<Student> updateStudentById(Student student);

}
