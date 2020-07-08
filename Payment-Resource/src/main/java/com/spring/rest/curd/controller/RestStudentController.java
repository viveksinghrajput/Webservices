package com.spring.rest.curd.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.curd.dto.StudentResponse;
import com.spring.rest.curd.model.Student;
import com.spring.rest.curd.service.StudentService;


@RestController
@RequestMapping("/studentMgmt")
public class RestStudentController {
	
	@Autowired
	private StudentService StudentService;
	
	
	@PostMapping("/saveStudent")
	public StudentResponse saveStudent(@RequestBody Student student) {
		return StudentService.SaveStudent(student);
	}
	
	@GetMapping("/getAllStudent")
	public StudentResponse listStudent() {
		return  StudentService.GetAllStudent();
	}
	
	@GetMapping("/getAllStudent/{name}")
	public StudentResponse getStudentByName(@PathVariable("name") String name) {
		return StudentService.GetStudentByName(name);
		
	}
	@GetMapping("/getStudent/{id}")
	public StudentResponse getstudentById(@PathVariable("id") int id) {
		return StudentService.GetStudentById(id);
		
	}
	@DeleteMapping(value="/deleteStudent/{id}")
	 public StudentResponse deleteStudentById(@PathVariable("id") int id) {
		 return StudentService.deleteStudentById(id);
	 }
	//@RequestMapping(value="/updateStudent/{id}",method=RequestMethod.PUT)
	@PostMapping("/updateStudent/{id}")
	public StudentResponse updateStudentById(@RequestBody Student student) {
		return StudentService.updateStudentById(student);
	}
	
}
