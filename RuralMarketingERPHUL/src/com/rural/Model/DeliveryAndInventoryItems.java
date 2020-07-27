/*
*DeliveryAndInventoryItems.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Delivery_Inventory_Items")
@SuppressWarnings("serial")
public class DeliveryAndInventoryItems implements Serializable {
	
	@Id
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Req_Id")
	private String req_Id;
	
	@Column(name = "Brand")
	private String brand;
	
	@Column(name = "Team")
	private String team;
	
	@Column(name = "Collateral_desc")
	private String collateral;
	
	@Column(name = "Prod_users")
	private String prod_users;
	
	@Column(name = "No_Item_Req")
	private int no_Item_Req;
	
	@Column(name = "Business_Req_Date")
	private java.sql.Date business_Req_Date;
	
	@Column(name = "Business_Remarks")
	private String business_Remarks;
	
	@Column(name = "Item_Produced")
	private String item_Produced;
	
	@Column(name = "Prod_Remarks_Produced")
	private String prod_Remarks_produced;
	
	@Column(name = "Item_Dispatch_Prod")
	private String item_Dispatch_Prod;
	
	@Column(name = "Prod_Remarks_Dispatched")
	private String prod_Remarks_dispatched;
	
	@Column(name = "Item_Received_logi")
	private String item_Received_logi;
	
	@Column(name = "Item_Dispatch_logi")
	private String item_Dispatch_logi;
	
	@Column(name = "Logist_Remarks_Received")
	private String logist_Remarks_received;
	
	@Column(name = "Logist_Remarks_Dispatched")
	private String logist_Remarks_dispatched;
	
	@Column(name = "Item_Reveived_Vend")
	private String item_Reveived_Vend;
	
	@Column(name = "Vendor_Remarks")
	private String vendor_Remarks;
	
	@Column(name = "Item_Produce_Date")
	private java.sql.Date item_Produce_Date;

	@Column(name = "Item_Dispatch_date_prod")
	private java.sql.Date item_Dispatch_date;
	
	@Column(name = "Item_Reveived_Vend_Dt")
	private java.sql.Date item_Reveived_Vend_Dt;
	
	@Column(name = "Item_Received_Date_logi")
	private java.sql.Date item_Received_Date_logi;
	
	@Column(name = "Item_Dispatch_Date_logi")
	private java.sql.Date item_Dispatch_Date_logi;
	
	@Column(name = "reachedLogi")
	private String reachedLogi;
	
	@Column(name = "Checked")
	private String checked;

	@Column(name = "status")
	private String status;
	
	@Column(name = "Req_order")
	private String req_order;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReq_Id() {
		return req_Id;
	}

	public void setReq_Id(String req_Id) {
		this.req_Id = req_Id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getCollateral() {
		return collateral;
	}

	public void setCollateral(String collateral) {
		this.collateral = collateral;
	}

	public String getProd_users() {
		return prod_users;
	}

	public void setProd_users(String prod_users) {
		this.prod_users = prod_users;
	}

	public int getNo_Item_Req() {
		return no_Item_Req;
	}

	public void setNo_Item_Req(int no_Item_Req) {
		this.no_Item_Req = no_Item_Req;
	}

	public java.sql.Date getBusiness_Req_Date() {
		return business_Req_Date;
	}

	public void setBusiness_Req_Date(java.sql.Date business_Req_Date) {
		this.business_Req_Date = business_Req_Date;
	}

	public String getBusiness_Remarks() {
		return business_Remarks;
	}

	public void setBusiness_Remarks(String business_Remarks) {
		this.business_Remarks = business_Remarks;
	}

	public String getItem_Produced() {
		return item_Produced;
	}

	public void setItem_Produced(String item_Produced) {
		this.item_Produced = item_Produced;
	}

	public String getProd_Remarks_produced() {
		return prod_Remarks_produced;
	}

	public void setProd_Remarks_produced(String prod_Remarks_produced) {
		this.prod_Remarks_produced = prod_Remarks_produced;
	}

	public String getItem_Dispatch_Prod() {
		return item_Dispatch_Prod;
	}

	public void setItem_Dispatch_Prod(String item_Dispatch_Prod) {
		this.item_Dispatch_Prod = item_Dispatch_Prod;
	}

	public String getProd_Remarks_dispatched() {
		return prod_Remarks_dispatched;
	}

	public void setProd_Remarks_dispatched(String prod_Remarks_dispatched) {
		this.prod_Remarks_dispatched = prod_Remarks_dispatched;
	}

	public String getItem_Received_logi() {
		return item_Received_logi;
	}

	public void setItem_Received_logi(String item_Received_logi) {
		this.item_Received_logi = item_Received_logi;
	}

	public String getItem_Dispatch_logi() {
		return item_Dispatch_logi;
	}

	public void setItem_Dispatch_logi(String item_Dispatch_logi) {
		this.item_Dispatch_logi = item_Dispatch_logi;
	}

	public String getLogist_Remarks_received() {
		return logist_Remarks_received;
	}

	public void setLogist_Remarks_received(String logist_Remarks_received) {
		this.logist_Remarks_received = logist_Remarks_received;
	}

	public String getLogist_Remarks_dispatched() {
		return logist_Remarks_dispatched;
	}

	public void setLogist_Remarks_dispatched(String logist_Remarks_dispatched) {
		this.logist_Remarks_dispatched = logist_Remarks_dispatched;
	}

	public String getItem_Reveived_Vend() {
		return item_Reveived_Vend;
	}

	public void setItem_Reveived_Vend(String item_Reveived_Vend) {
		this.item_Reveived_Vend = item_Reveived_Vend;
	}

	public String getVendor_Remarks() {
		return vendor_Remarks;
	}

	public void setVendor_Remarks(String vendor_Remarks) {
		this.vendor_Remarks = vendor_Remarks;
	}

	public java.sql.Date getItem_Produce_Date() {
		return item_Produce_Date;
	}

	public void setItem_Produce_Date(java.sql.Date item_Produce_Date) {
		this.item_Produce_Date = item_Produce_Date;
	}

	public java.sql.Date getItem_Dispatch_date() {
		return item_Dispatch_date;
	}

	public void setItem_Dispatch_date(java.sql.Date item_Dispatch_date) {
		this.item_Dispatch_date = item_Dispatch_date;
	}

	public java.sql.Date getItem_Reveived_Vend_Dt() {
		return item_Reveived_Vend_Dt;
	}

	public void setItem_Reveived_Vend_Dt(java.sql.Date item_Reveived_Vend_Dt) {
		this.item_Reveived_Vend_Dt = item_Reveived_Vend_Dt;
	}

	public java.sql.Date getItem_Received_Date_logi() {
		return item_Received_Date_logi;
	}

	public void setItem_Received_Date_logi(java.sql.Date item_Received_Date_logi) {
		this.item_Received_Date_logi = item_Received_Date_logi;
	}

	public java.sql.Date getItem_Dispatch_Date_logi() {
		return item_Dispatch_Date_logi;
	}

	public void setItem_Dispatch_Date_logi(java.sql.Date item_Dispatch_Date_logi) {
		this.item_Dispatch_Date_logi = item_Dispatch_Date_logi;
	}

	
	public String getReachedLogi() {
		return reachedLogi;
	}

	public void setReachedLogi(String reachedLogi) {
		this.reachedLogi = reachedLogi;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReq_order() {
		return req_order;
	}

	public void setReq_order(String req_order) {
		this.req_order = req_order;
	}

	
	/*private String path_dispatch;
	
	private String path_delivery;*/
	
	
	}
