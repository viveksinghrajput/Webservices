package com.rural.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="invoice_master")
@SuppressWarnings("serial")
public class Stock implements Serializable {

	@Id
	@Column(name="invoice_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String invoice_id;
	
	@Column(name="Non_RS")
	private String non_rs;

	public String getNon_rs() {
		return non_rs;
	}

	public void setNon_rs(String non_rs) {
		this.non_rs = non_rs;
	}

	@Column(name="Bill_number")
	private String bill_number;
	
	@Column(name="venId")
	private Integer venId;
	
	@Column(name="RS_code")
	private String rs_code;
	
	@Column(name="RS_name")
	private String rs_name;
	
	@Column(name="State")
	private String state;
	
	@Column(name="City")
	private String city;
	
	@Column(name="Bill_date")
	private String bill_date;
	
	@Column(name="Total_Amount")
	private String total_amount;
	
	@Column(name="Invoice_path")
	private String invoice_path;
	
	@Column(name="mail_path")
	private String mail_path;
	
	
	public String getMail_path() {
		return mail_path;
	}

	public void setMail_path(String mail_path) {
		this.mail_path = mail_path;
	}

	@Column(name="status")
	private int status;
	
	@Column(name="comments")
	private String  comments;
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@OneToMany(mappedBy="stock")
	private List<CreateInvoices> invoices;
	
	

	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getBill_number() {
		return bill_number;
	}

	public void setBill_number(String bill_number) {
		this.bill_number = bill_number;
	}

	public Integer getVenId() {
		return venId;
	}

	public void setVenId(Integer venId) {
		this.venId = venId;
	}

	public String getRs_code() {
		return rs_code;
	}

	public void setRs_code(String rs_code) {
		this.rs_code = rs_code;
	}

	public String getRs_name() {
		return rs_name;
	}

	public void setRs_name(String rs_name) {
		this.rs_name = rs_name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getInvoice_path() {
		return invoice_path;
	}

	public void setInvoice_path(String invoice_path) {
		this.invoice_path = invoice_path;
	}

	public List<CreateInvoices> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<CreateInvoices> invoices) {
		this.invoices = invoices;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	

}
