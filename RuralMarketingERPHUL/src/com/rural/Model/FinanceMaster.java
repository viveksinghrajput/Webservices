/*
*FinanceMaster.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.format.annotation.DateTimeFormat;

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "saveFinrequest",
	query = "CALL saveFinrequest(:completionDesc,:agency,:month,:year,:remarks,:path)",
	resultClass = FinanceMaster.class
	)
})
@Entity
@Table(name = "finance_master")
@SuppressWarnings("serial")
public class FinanceMaster implements Serializable{
	
	@Id
	@Column(name = "fin_id")
	private Integer finId;
	
	@Column(name = "req_num")
	private String reqNum;
	
	@Column(name = "completion_Rpt_desc")
	private String completionRptDesc;
	
	@Column(name = "agency_id")
	private int agencyId;
	
	@Column(name = "_month")
	private String month;
	
	@Column(name = "_year")
	private String year;
	
	@Column(name = "created_date")
	private java.util.Date createdDate;
	
	@Column(name = "invoice_path_audit")
	private String invoicePathAudit;
	
	@Column(name = "invoice_path_agency")
	private String invoicePathAgency;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "total_amount")
	private Integer totalAmount;
	
	@OneToMany(mappedBy="financeMaster")
	private List<FinanceHistory> history;
	
	public Integer getFinId() {
		return finId;
	}

	public void setFinId(Integer finId) {
		this.finId = finId;
	}

	public String getReqNum() {
		return reqNum;
	}

	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}

	public String getCompletionRptDesc() {
		return completionRptDesc;
	}

	public void setCompletionRptDesc(String completionRptDesc) {
		this.completionRptDesc = completionRptDesc;
	}

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getInvoicePathAudit() {
		return invoicePathAudit;
	}

	public void setInvoicePathAudit(String invoicePathAudit) {
		this.invoicePathAudit = invoicePathAudit;
	}

	public String getInvoicePathAgency() {
		return invoicePathAgency;
	}

	public void setInvoicePathAgency(String invoicePathAgency) {
		this.invoicePathAgency = invoicePathAgency;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<FinanceHistory> getHistory() {
		return history;
	}

	public void setHistory(List<FinanceHistory> history) {
		this.history = history;
	}

			
}
