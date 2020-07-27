/*
*BatchTemp.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.util.Set;

public class BatchTemp {
	private int batch;
	private String status;
	private Set<StateTemp> states;
	
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
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
