/*
*DOMaster.java
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
@Table(name = "do_master")
@SuppressWarnings("serial")
public class DOMaster implements Serializable{
	@Id
	@Column(name = "do_id")
	private int doId;
	
	@Column(name = "do_number")
	private String doNumber;
	/*
	@Column(name = "do_desc")
	private String doDesc;*/
	
	@Column(name = "do_value")
	private String doValue;
	
	@Column(name = "do_balance")
	private String doBalance;
	
	@Column(name = "valid_from")
	private java.util.Date validFrom;
	
	@Column(name = "valid_to")
	private java.util.Date validTo;

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

	/*public String getDoDesc() {
		return doDesc;
	}

	public void setDoDesc(String doDesc) {
		this.doDesc = doDesc;
	}
*/
	public String getDoValue() {
		return doValue;
	}

	public void setDoValue(String doValue) {
		this.doValue = doValue;
	}

	public String getDoBalance() {
		return doBalance;
	}

	public void setDoBalance(String doBalance) {
		this.doBalance = doBalance;
	}

	public java.util.Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(java.util.Date validFrom) {
		this.validFrom = validFrom;
	}

	public java.util.Date getValidTo() {
		return validTo;
	}

	public void setValidTo(java.util.Date validTo) {
		this.validTo = validTo;
	}
	
	
}
