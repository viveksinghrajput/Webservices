/*
*FinanceHistory.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "finance_history")
@SuppressWarnings("serial")
public class FinanceHistory implements Serializable{
	@Id
	@Column(name = "his_id")
	private int hisId;
	
	@Column(name = "team")
	private String team;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "lastupdated_date")
	private Date lastupdated_date;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "status")
	private int status;


	@ManyToOne
	@JoinColumn(name="req_no", nullable=false)
	private FinanceMaster financeMaster;
	
	
	public int getHisId() {
		return hisId;
	}

	public void setHisId(int hisId) {
		this.hisId = hisId;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getLastupdated_date() {
		return lastupdated_date;
	}

	public void setLastupdated_date(Date lastupdated_date) {
		this.lastupdated_date = lastupdated_date;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public FinanceMaster getFinanceMaster() {
		return financeMaster;
	}

	public void setFinanceMaster(FinanceMaster financeMaster) {
		this.financeMaster = financeMaster;
	}
	
	
		
}
