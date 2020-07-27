/*
*H2HCoveredMaster.java
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
@Table(name = "h2hcovered")
@SuppressWarnings("serial")
public class H2HCoveredMaster implements Serializable{
	
	@Id
	@Column(name = "City")
	private String City;	
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Column(name = "Brand")
	private String brand;	
	
	@Column(name = "Area")
	private String Area;	
	@Column(name = "H2H_Covered")
	private String H2H_Covered;
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getH2H_Covered() {
		return H2H_Covered;
	}
	public void setH2H_Covered(String h2h_Covered) {
		H2H_Covered = h2h_Covered;
	}	
	
	
	

}
