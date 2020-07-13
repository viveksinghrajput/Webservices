package com.accenture.services;

import com.accenture.model.Student;
import com.accenture.response.StudentResponse;

public interface StudentService {

	StudentResponse SaveStudent(Student student);

	StudentResponse GetAllStudent();

	StudentResponse GetStudentByName(String name);

	StudentResponse GetStudentById(int id);

	StudentResponse deleteStudentById(int id);

	StudentResponse updateStudentById(Student student);



}
