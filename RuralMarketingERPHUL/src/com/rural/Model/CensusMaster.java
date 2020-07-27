/*
*CensusMaster.java
*Created By		:Kamal Thappa
*Created Date	:Apr 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "census_master")
@SuppressWarnings("serial")
public class CensusMaster implements Serializable  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "censusMaster_id")
	private int censusMaster_id;
	
	@Column(name = "state",length=255)
	private String state;
	
	@Column(name = "city",length=255)
	private String city;
	
	@Column(name = "tot_pop",length=255)
	private String tot_pop;
	
	public int getCensusMaster_id() {
		return censusMaster_id;
	}

	public void setCensusMaster_id(int censusMaster_id) {
		this.censusMaster_id = censusMaster_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTot_pop() {
		return tot_pop;
	}

	public void setTot_pop(String tot_pop) {
		this.tot_pop = tot_pop;
	}

	

		
}
