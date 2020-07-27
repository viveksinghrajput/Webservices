/*
*SLAMaster.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SLA_Master")
@SuppressWarnings("serial")
public class SLAMaster implements Serializable {
	
	@Id
	@Column(name = "city")
	private String city;
	
	@Column(name = "days")
	private int days;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
	
}
