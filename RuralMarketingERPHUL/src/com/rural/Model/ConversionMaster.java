/*
*ConversionMaster.java
*Created By		:Kamal Thappa
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conversion_master")
@SuppressWarnings("serial")
public class ConversionMaster implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "conversion_id")
	private int conversion_id;
	
	@Column(name="ipAddress")
	private String ipAddress;
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "isActive")
	private int isActive;
	
	@Column(name = "brandname",length=255)
	private String brandname;
	
	@Column(name = "conpercent",length=255)
	private String conpercent;
	
	@Column(name = "avgsales",length=255)
	private String avgsales;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "lastUpdated")
	private Date lastUpdated;
	
	@Column(name = "updatedBy",length=255)
	private String updatedBy;
	
	public String toString(){
		return getConversion_id()+","+getBrandname()+","+getConpercent()+","+getAvgsales()+","+getLastUpdated()+","+getUpdatedBy();
	}

	public int getConversion_id() {
		return conversion_id;
	}

	public void setConversion_id(int conversion_id) {
		this.conversion_id = conversion_id;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getConpercent() {
		return conpercent;
	}

	public void setConpercent(String conpercent) {
		this.conpercent = conpercent;
	}

	public String getAvgsales() {
		return avgsales;
	}

	public void setAvgsales(String avgsales) {
		this.avgsales = avgsales;
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
