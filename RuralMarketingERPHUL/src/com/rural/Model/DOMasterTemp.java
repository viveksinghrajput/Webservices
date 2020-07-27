/*
*AgencyMaster.java
*Created By		:Vivek Rajpoot
*Created Date	:Jul 4, 2018
*/
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
@Table(name="do_master_temp")
@SuppressWarnings("serial")
public class DOMasterTemp implements Serializable {
	@Id
	@Column(name = "do_id")
	private int doId;
	
	@Column(name = "do_number")
	private String doNumber;
	
	@Column(name = "AgencyName")
	private String agencyName;
	
	@Column(name = "do_desc")
	private String doDesc;
	
	@Column(name = "do_value")
	private String doValue;
	
	@Column(name = "valid_from")
	private String validFrom;
	
	@Column(name = "valid_to")
	private String validTo;

	@Column(name = "ERROR")
	private String error;
	
	public int getDoId() {
		return doId;
	}

	public void setDoId(int doId) {
		this.doId = doId;
	}

	public String getDoNumber() {
		return doNumber;
	}

	public void setDoNumber(String doNumber) {
		this.doNumber = doNumber;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getDoDesc() {
		return doDesc;
	}

	public void setDoDesc(String doDesc) {
		this.doDesc = doDesc;
	}

	public String getDoValue() {
		return doValue;
	}

	public void setDoValue(String doValue) {
		this.doValue = doValue;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	
}
