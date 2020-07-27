/*
*PrePlanItemList.java
*Created By		:Kamal Thappa
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class PrePlanItemList implements Serializable{

	private String brand;

	private String state;

	private String city;

	private int tot_pop;

	private int tot_hh;

	private int conversion;

	private String contactDone;

	private int avgSales;

	private int span;

	private int promotorNum;

	private int teamNum;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public int getConversion() {
		return conversion;
	}

	public void setConversion(int conversion) {
		this.conversion = conversion;
	}

	public String getContactDone() {
		return contactDone;
	}

	public void setContactDone(String contactDone) {
		this.contactDone = contactDone;
	}

	public int getAvgSales() {
		return avgSales;
	}

	public void setAvgSales(int avgSales) {
		this.avgSales = avgSales;
	}

	public int getSpan() {
		return span;
	}

	public void setSpan(int span) {
		this.span = span;
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

	
}
