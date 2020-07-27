/*
*FinanceDOMapping.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 27, 2018
*/
package com.rural.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "finance_do_mapping")
@SuppressWarnings("serial")
public class FinanceDOMapping implements Serializable{
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "fin_id")
	private int finId;
	
	@Column(name = "do_number")
	private String doNumber;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "valid_from")
	private String validFrom;
	
	@Column(name = "valid_to")
	private String validTo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFinId() {
		return finId;
	}

	public void setFinId(int finId) {
		this.finId = finId;
	}

	public String getDoNumber() {
		return doNumber;
	}

	public void setDoNumber(String doNumber) {
		this.doNumber = doNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	
	
}
