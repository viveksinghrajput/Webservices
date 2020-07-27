package com.rural.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;


@Entity
@Table(name="agency_master")
@SuppressWarnings("serial")
public class AgencyMaster implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int Id;
	
	@Column(name="agencyName")//this name came from back-end
	private String agencyname;//this name should be same as jsp name
	
	@Column(name="stateId")
	private int stateId;
	
	@Column(name="cityId")
	private int cityId;
	
	@Column(name="isActive")
	private int isActive;
	
	
	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getAgencyname() {
		return agencyname;
	}


	public void setAgencyname(String agencyname) {
		this.agencyname = agencyname;
	}


	


	public int getStateId() {
		return stateId;
	}


	public void setStateId(int stateId) {
		this.stateId = stateId;
	}


	public int getCityId() {
		return cityId;
	}


	public void setCityId(int cityId) {
		this.cityId = cityId;
	}


	public int getIsActive() {
		return isActive;
	}


	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}


	@Override
	public String toString() {
		
		return agencyname;
		//return "Agency Name: "+getAgencyname()+" ;State :"+getStatename()+" ; City :"+getCityname();
	
	}

}
