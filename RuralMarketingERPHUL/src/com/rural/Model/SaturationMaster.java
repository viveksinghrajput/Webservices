/*
*SaturationMaster.java
*Created By		:Kamal Thappa
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "saturation_master")
@SuppressWarnings("serial")
public class SaturationMaster  implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "saturation_id")
	private int saturation_id;
	
	@Column(name ="ipAddress")
	private String ipAddress;
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "state",length=255)
	private String state;
	
	@Column(name = "city",length=255)
	private String city;
	
	@Column(name = "brand",length=255)
	private String brand;
	
	@Column(name = "satpercent",length=255)
	private String satpercent;

	@Column(name = "createdDate")
	private Date createdDate=new Date();
	
	@Column(name = "lastUpdated")
	private Date lastUpdated=new Date();
	
	@Column(name = "updatedBy",length=255)
	private String updatedBy;
	
	public String toString(){
		return getSaturation_id()+","+getState()+","+getCity()+","+getBrand()+","+getSatpercent()+","+getLastUpdated()+","+getUpdatedBy();
	}
   
	public int getSaturation_id() {
		return saturation_id;
	}

	public void setSaturation_id(int saturation_id) {
		this.saturation_id = saturation_id;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSatpercent() {
		return satpercent;
	}

	public void setSatpercent(String satpercent) {
		this.satpercent = satpercent;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
	
}
