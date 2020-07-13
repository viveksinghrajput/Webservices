package com.accenture.dao;

import java.util.List;

import com.accenture.model.Student;



public interface StudentDao {

	String SaveStudent(Student student);

	List<Student> GetAllStudent();

	List<Student> GetStudentByName(String name);

	List<Student> GetStudentById(int id);

	String deleteStudentById(int id);

	List<Student> updateStudentById(Student student);

}