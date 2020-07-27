package com.rural.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "question_master")
public class SecurityQuestions implements Serializable{
	
	@Id
	@Column(name = "qus_id")
	private int qus_id;
	
	@Column(name = "qus_name")
	private String qus_name;
	
	@Column(name = "isActive")
	private String isActive;
	
	@Column(name = "updatedon")
	private String updatedOn;
	
	@Column(name = "updatedby")
	private String updatedBy;
	
	public int getQus_id() {
		return qus_id;
	}

	public void setQus_id(int qus_id) {
		this.qus_id = qus_id;
	}

	public String getQus_name() {
		return qus_name;
	}

	public void setQus_name(String qus_name) {
		this.qus_name = qus_name;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	
	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
