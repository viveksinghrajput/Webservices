package com.accenture.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_info")
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8256605980213373822L;
	@Id
	@GeneratedValue
	private int employee_Id;

	private String employee_Name;
	private String employee_Role;
	private String employee_Emailid;
	private String employee_Gender;
	private String employee_Hobbies;
	private String employee_City;
	private String employee_State;

	public int getEmployee_Id() {
		return employee_Id;
	}

	public void setEmployee_Id(int employee_Id) {
		this.employee_Id = employee_Id;
	}

	public String getEmployee_Name() {
		return employee_Name;
	}

	public void setEmployee_Name(String employee_Name) {
		this.employee_Name = employee_Name;
	}

	public String getEmployee_Role() {
		return employee_Role;
	}

	public void setEmployee_Role(String employee_Role) {
		this.employee_Role = employee_Role;
	}

	public String getEmployee_Emailid() {
		return employee_Emailid;
	}

	public void setEmployee_Emailid(String employee_Emailid) {
		this.employee_Emailid = employee_Emailid;
	}

	public String getEmployee_Gender() {
		return employee_Gender;
	}

	public void setEmployee_Gender(String employee_Gender) {
		this.employee_Gender = employee_Gender;
	}

	public String getEmployee_Hobbies() {
		return employee_Hobbies;
	}

	public void setEmployee_Hobbies(String employee_Hobbies) {
		this.employee_Hobbies = employee_Hobbies;
	}

	public String getEmployee_City() {
		return employee_City;
	}

	public void setEmployee_City(String employee_City) {
		this.employee_City = employee_City;
	}

	public String getEmployee_State() {
		return employee_State;
	}

	public void setEmployee_State(String employee_State) {
		this.employee_State = employee_State;
	}

}