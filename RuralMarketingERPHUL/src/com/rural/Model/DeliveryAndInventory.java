/*
*DeliveryAndInventory.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Delivery_Inventory_Master")
@SuppressWarnings("serial")
public class DeliveryAndInventory implements Serializable {
	
	@Id
	@Column(name = "Req_Id")
	private int req_Id;
	
	@Column(name = "Req_num")
	private String req_num;
	
	public String getReq_num() {
		return req_num;
	}

	public void setReq_num(String req_num) {
		this.req_num = req_num;
	}

	@Column(name = "Brand")
	private String brand;
	
	@Column(name = "Campaign")
	private String campaign;
	
	@Column(name = "State")
	private String state;
	
	@Column(name = "City")
	private String city;
	
	@Column(name = "Warehouse")
	private String warehouse;
	
	@Column(name = "Path_dispatch")
	private String path_dispatch;
	
	@Column(name = "Path_delivery")
	private String path_delivery;
	
	@Column(name = "Business_Req_Date")
	private java.util.Date business_Req_Date;
	
	@Column(name = "status")
	private String status; 

	
	public int getReq_Id() {
		return req_Id;
	}

	public void setReq_Id(int req_Id) {
		this.req_Id = req_Id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
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

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getPath_dispatch() {
		return path_dispatch;
	}

	public void setPath_dispatch(String path_dispatch) {
		this.path_dispatch = path_dispatch;
	}

	public String getPath_delivery() {
		return path_delivery;
	}

	public void setPath_delivery(String path_delivery) {
		this.path_delivery = path_delivery;
	}

	
	public java.util.Date getBusiness_Req_Date() {
		return business_Req_Date;
	}

	public void setBusiness_Req_Date(java.util.Date business_Req_Date) {
		this.business_Req_Date = business_Req_Date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	/*@Column(name = "No_Item_Req")
	private int no_Item_Req;
	
	@Column(name = "Business_Remarks")
	private String business_Remarks;
	
	@Column(name = "Item_Produced")
	private int item_Produced;
	
	@Column(name = "Prod_Remarks")
	private String prod_Remarks;
	
	@Column(name = "Item_Dispach_Prod")
	private int item_Dispach_Prod;
	
	@Column(name = "Item_Received_logi")
	private int item_Received_logi;
	
	@Column(name = "Item_Dispach_logi")
	private int item_Dispach_logi;
	
	public int getItem_Dispach_logi() {
		return item_Dispach_logi;
	}
	

	public void setItem_Dispach_logi(int item_Dispach_logi) {
		this.item_Dispach_logi = item_Dispach_logi;
	}

	@Column(name = "Logist_Remarks")
	private String logist_Remarks;
	
	@Column(name = "Item_Reveived_Vend")
	private int item_Reveived_Vend;
	
	@Column(name = "Vendor_Remarks")
	private String vendor_Remarks;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "Business_Req_Date")
	private java.sql.Date business_Req_Date;
	
	@Column(name = "Item_Produce_Date")
	private java.sql.Date item_Produce_Date;

	@Column(name = "Item_Dispach_date_prod")
	private java.sql.Date item_Dispach_date;
	
	@Column(name = "Item_Reveived_Vend_Dt")
	private java.sql.Date item_Reveived_Vend_Dt;
	
	@Column(name = "Item_Received_Date_logi")
	private java.sql.Date item_Received_Date_logi;
	
	@Column(name = "Item_Dispach_Date_logi")
	private java.sql.Date item_Dispach_Date_logi;
	
	@Column(name = "Path_dispatch")
	private String path_dispatch;
	
	@Column(name = "Path_delivery")
	private String path_delivery;

	public String getPath_dispatch() {
		return path_dispatch;
	}

	public void setPath_dispatch(String path_dispatch) {
		this.path_dispatch = path_dispatch;
	}

	public String getPath_delivery() {
		return path_delivery;
	}

	public void setPath_delivery(String path_delivery) {
		this.path_delivery = path_delivery;
	}

	public java.sql.Date getItem_Received_Date_logi() {
		return item_Received_Date_logi;
	}

	public void setItem_Received_Date_logi(java.sql.Date item_Received_Date_logi) {
		this.item_Received_Date_logi = item_Received_Date_logi;
	}

	public java.sql.Date getItem_Dispach_Date_logi() {
		return item_Dispach_Date_logi;
	}

	public void setItem_Dispach_Date_logi(java.sql.Date item_Dispach_Date_logi) {
		this.item_Dispach_Date_logi = item_Dispach_Date_logi;
	}

	public int getReq_Id() {
		return req_Id;
	}

	public void setReq_Id(int req_Id) {
		this.req_Id = req_Id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
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

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public int getNo_Item_Req() {
		return no_Item_Req;
	}

	public void setNo_Item_Req(int no_Item_Req) {
		this.no_Item_Req = no_Item_Req;
	}

	public String getBusiness_Remarks() {
		return business_Remarks;
	}

	public void setBusiness_Remarks(String business_Remarks) {
		this.business_Remarks = business_Remarks;
	}

	public int getItem_Produced() {
		return item_Produced;
	}

	public void setItem_Produced(int item_Produced) {
		this.item_Produced = item_Produced;
	}

	public String getProd_Remarks() {
		return prod_Remarks;
	}

	public void setProd_Remarks(String prod_Remarks) {
		this.prod_Remarks = prod_Remarks;
	}

	public int getItem_Dispach_Prod() {
		return item_Dispach_Prod;
	}

	public void setItem_Dispach_Prod(int item_Dispach_Prod) {
		this.item_Dispach_Prod = item_Dispach_Prod;
	}


	public int getItem_Received_logi() {
		return item_Received_logi;
	}

	public void setItem_Received_logi(int item_Received_logi) {
		this.item_Received_logi = item_Received_logi;
	}

	public String getLogist_Remarks() {
		return logist_Remarks;
	}

	public void setLogist_Remarks(String logist_Remarks) {
		this.logist_Remarks = logist_Remarks;
	}

	public int getItem_Reveived_Vend() {
		return item_Reveived_Vend;
	}

	public void setItem_Reveived_Vend(int item_Reveived_Vend) {
		this.item_Reveived_Vend = item_Reveived_Vend;
	}

	public String getVendor_Remarks() {
		return vendor_Remarks;
	}

	public void setVendor_Remarks(String vendor_Remarks) {
		this.vendor_Remarks = vendor_Remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Date getBusiness_Req_Date() {
		return business_Req_Date;
	}

	public void setBusiness_Req_Date(java.sql.Date business_Req_Date) {
		this.business_Req_Date = business_Req_Date;
	}

	public java.sql.Date getItem_Produce_Date() {
		return item_Produce_Date;
	}

	public void setItem_Produce_Date(java.sql.Date item_Produce_Date) {
		this.item_Produce_Date = item_Produce_Date;
	}

	public java.sql.Date getItem_Dispach_date() {
		return item_Dispach_date;
	}

	public void setItem_Dispach_date(java.sql.Date item_Dispach_date) {
		this.item_Dispach_date = item_Dispach_date;
	}

	public java.sql.Date getItem_Reveived_Vend_Dt() {
		return item_Reveived_Vend_Dt;
	}

	public void setItem_Reveived_Vend_Dt(java.sql.Date item_Reveived_Vend_Dt) {
		this.item_Reveived_Vend_Dt = item_Reveived_Vend_Dt;
	}
*/	
	}
