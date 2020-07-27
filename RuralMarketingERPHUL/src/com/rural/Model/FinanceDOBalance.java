/*
*FinanceDOBalance.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Aug 2, 2018
*/
package com.rural.Model;

import java.io.Serializable;
@SuppressWarnings("serial")
public class FinanceDOBalance implements Serializable{
	private String reqNum;
	private String agencyName;
	private java.util.Date createdDate;
	private Integer totalAmount;
	public String getReqNum() {
		return reqNum;
	}
	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public java.util.Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
}
