/*
*VendorMaster.java
*Created By		:Kamal Thapa
*Created Date	:Apr 25, 2018
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
@Table(name = "vendor_master")
@SuppressWarnings("serial")
public class VendorMaster implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="agency_id")
	private int agencyId;
	
	@Column(name="city")
	private int city;
	
	@Column(name="state")
	private int state;
	
	@Column(name="vendorName")
	private String vendorName;
	
	@Column(name="hul_outlet_code")
	private String hul_outlet_code;
	
	@Column(name="gstn_no")
	private String gstn_no;

	
	@Column(name= "isActive")
	private int isActive;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getHul_outlet_code() {
		return hul_outlet_code;
	}
	public void setHul_outlet_code(String hul_outlet_code) {
		this.hul_outlet_code = hul_outlet_code;
	}
	public String getGstn_no() {
		return gstn_no;
	}
	public void setGstn_no(String gstn_no) {
		this.gstn_no = gstn_no;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		
		return "Vendor Name: "+getVendorName()+" ;State :"+getState()+" ; City :"+getCity();
	}

	


}
