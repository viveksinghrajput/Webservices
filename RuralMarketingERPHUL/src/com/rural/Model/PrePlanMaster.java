/*
*PrePlanMaster.java
*Created By		:Kamal Thappa
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "preplan_master")
@SuppressWarnings("serial")
public class PrePlanMaster implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "prePlan_id")
	private int prePlan_id;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "isActive")
	private int isActive;
	
	@Column(name = "prePlanName",length=255)
	private String prePlanName;
	
	@Column(name = "createdBy",length=255)
	private String createdBy;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "spanDays")
	private int spanDays;
	
	@Column(name = "state",length=255)
	private String state;
	
	@Column(name = "citytown",length=255)
	private String citytown;
	
	@Column(name = "tot_pop")
	private int tot_pop;
	
	@Column(name = "tot_hh")
	private int tot_hh;
	
	@Column(name = "conversion")
	private String conversion;
	
	@Column(name = "contactDone")
	private String contactDone;
	
	@Column(name = "avgSales")
	private int avgSales;
	
	@Column(name = "promotorNum")
	private int promotorNum;
	
	public String getRouteCreated() {
		return routeCreated;
	}

	public void setRouteCreated(String routeCreated) {
		this.routeCreated = routeCreated;
	}

	@Column(name = "teamNum")
	private int teamNum;
	
	@Column(name = "isRouteCreated")
	private String routeCreated;
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrePlan_id() {
		return prePlan_id;
	}

	public void setPrePlan_id(int prePlan_id) {
		this.prePlan_id = prePlan_id;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getPrePlanName() {
		return prePlanName;
	}

	public void setPrePlanName(String prePlanName) {
		this.prePlanName = prePlanName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getSpanDays() {
		return spanDays;
	}

	public void setSpanDays(int spanDays) {
		this.spanDays = spanDays;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCitytown() {
		return citytown;
	}

	public void setCitytown(String citytown) {
		this.citytown = citytown;
	}

	public int getTot_pop() {
		return tot_pop;
	}

	public void setTot_pop(int tot_pop) {
		this.tot_pop = tot_pop;
	}

	public int getTot_hh() {
		return tot_hh;
	}

	public void setTot_hh(int tot_hh) {
		this.tot_hh = tot_hh;
	}

	public int getAvgSales() {
		return avgSales;
	}

	public void setAvgSales(int avgSales) {
		this.avgSales = avgSales;
	}

	public int getPromotorNum() {
		return promotorNum;
	}

	public void setPromotorNum(int promotorNum) {
		this.promotorNum = promotorNum;
	}

	public int getTeamNum() {
		return teamNum;
	}

	public void setTeamNum(int teamNum) {
		this.teamNum = teamNum;
	}

	public String getConversion() {
		return conversion;
	}

	public void setConversion(String conversion) {
		this.conversion = conversion;
	}

	public String getContactDone() {
		return contactDone;
	}

	public void setContactDone(String contactDone) {
		this.contactDone = contactDone;
	}

}