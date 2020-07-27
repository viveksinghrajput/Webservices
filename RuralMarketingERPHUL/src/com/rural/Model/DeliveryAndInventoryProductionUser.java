/*
*DeliveryAndInventoryProductionUser.java
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
@Table(name = "delivery_inventory_Production_users")
@SuppressWarnings("serial")
public class DeliveryAndInventoryProductionUser implements Serializable {
	
	@Id
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Req_Id")
	private String req_Id;
	
	@Column(name = "Prod_users")
	private String prod_users;
	
	@Column(name = "status")
	private String status;

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

	public String getProd_users() {
		return prod_users;
	}

	public void setProd_users(String prod_users) {
		this.prod_users = prod_users;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	}
