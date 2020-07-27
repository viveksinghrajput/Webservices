/*
*StateMaster.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="state_master")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "states")
public class StateMaster implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StateMaster(){
	    }
	    
	    public StateMaster(int stateId,int batchId,String stateName, Set<CityMaster> cities){
	    	this.stateId = stateId;
	    	this.batchId = batchId;
	    	this.states=stateName;
	    	this.cities=cities;
	    }
	    
	@JsonIgnore 
	@Id
	@Column(name="stateId")
	private int stateId;
	
	@Column(name="batch_id")
	private int batchId;
	 
	@Column(name="stateName")
	@OrderBy("stateName")
	private String states;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "stateMaster")
	Set<CityMaster> cities;
	
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}

	public Set<CityMaster> getCities() {
		return cities;
	}

	public void setCities(Set<CityMaster> cities) {
		this.cities = cities;
	}

	

}
