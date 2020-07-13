package com.accenture.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_table")
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2200779629845135952L;
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String emailid;
	private String gender;
	private String hobbies;
	private String city;
	private String state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Student() {
		super();
	}

	public Student(String name, String emailid, String gender, String hobbies, String city, String state) {
		super();

		this.name = name;
		this.emailid = emailid;
		this.gender = gender;
		this.hobbies = hobbies;
		this.city = city;
		this.state = state;
	}

}