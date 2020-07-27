/*
*BatchTemp.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.DTO;

import java.util.Set;

public class BatchTemp {
	private int batchId;
	private String status;
	private Set<StateTemp> states;
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<StateTemp> getStates() {
		return states;
	}
	public void setStates(Set<StateTemp> states) {
		this.states = states;
	}
	
	
	

}
