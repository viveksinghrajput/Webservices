package com.rural.Model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "answer_master")
@SuppressWarnings("serial")
public class SecurityAnswers implements Serializable{

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "qus_id")
	private Integer qus_id;
	
	@Column(name = "ans_desc")
	private String ans_desc;
	
	@Column(name = "isActive")
	private String isActive;
	
	@Column(name = "updatedon")
	private Timestamp updatedOn;
	
	@Column(name = "updatedby")
	private String updatedBy;
	
	@Column(name = "user_id")
	private Integer user_id;
	
	/*
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "qus_id")
	private SecurityQuestions securityQuestions;
*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Integer getQus_id() {
		return qus_id;
	}

	public void setQus_id(Integer qus_id) {
		this.qus_id = qus_id;
	}

	public String getAns_desc() {
		return ans_desc;
	}

	public void setAns_desc(String ans_desc) {
		this.ans_desc = ans_desc;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
	/*public SecurityQuestions getSecurityQuestions() {
		return securityQuestions;
	}

	public void setSecurityQuestions(SecurityQuestions securityQuestions) {
		this.securityQuestions = securityQuestions;
	}
	*/
	
}
