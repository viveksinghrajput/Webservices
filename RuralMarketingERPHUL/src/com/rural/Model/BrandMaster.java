/*
*CensusMaster.java
*Created By		:Kamal Thappa
*Created Date	:Apr 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brand_master")
@SuppressWarnings("serial")
public class BrandMaster implements Serializable{
	
	@Id
	@Column(name = "brandMaster_id", length = 11 )
	private Integer brand_id;
	
	@Column(name = "brandName")
	private String brandName;	
	
	@Column(name = "isActive")
	private String isActive;

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
}
