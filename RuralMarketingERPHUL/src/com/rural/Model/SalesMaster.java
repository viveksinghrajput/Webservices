/*
*SalesMaster.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*
@Entity
@Table(name = "Sales_Master")
@SuppressWarnings("serial")*/
public class SalesMaster implements Serializable{
/*	@Id
		@Column(name = "id")*/
		private String id;
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//@Column(name = "City")
	private String city;
	
	//@Column(name = "Ward")
	private String ward;
	
	//@Column(name = "Brand")
	private String brand;
	
	//@Column(name = "Period")
	private String period;
	
	//@Column(name = "Noof_Retailors")
	private int noof_Retailors;

	//@Column(name = "Total_HH")
	private String total_HH;
	
	//@Column(name = "NoofHH")
	private String noHH;
	
	//@Column(name = "Sales_Value")
	private String sales;
	
	//@Column(name = "contribution_share")
	private String contribution_share;
	
	@Column(name = "Penetration")
	private int penetration;
	
	@Column(name = "Done")
	private String done;
	
	@Column(name = "slab_saturation")
	private int slab_saturation;

	private Map<String, Double> salesMap;
	private Map<String, Double> totSalesMap;
	private String total_sales;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getNoof_Retailors() {
		return noof_Retailors;
	}

	public void setNoof_Retailors(int noof_Retailors) {
		this.noof_Retailors = noof_Retailors;
	}

	public String getTotal_HH() {
		return total_HH;
	}

	public void setTotal_HH(String total_HH) {
		this.total_HH = total_HH;
	}

	public String getNoHH() {
		return noHH;
	}

	public void setNoHH(String noHH) {
		this.noHH = noHH;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getContribution_share() {
		return contribution_share;
	}

	public void setContribution_share(String contribution_share) {
		this.contribution_share = contribution_share;
	}

	public int getPenetration() {
		return penetration;
	}

	public void setPenetration(int penetration) {
		this.penetration = penetration;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public int getSlab_saturation() {
		return slab_saturation;
	}

	public void setSlab_saturation(int slab_saturation) {
		this.slab_saturation = slab_saturation;
	}

	public Map<String, Double> getSalesMap() {
		return salesMap;
	}

	public void setSalesMap(Map<String, Double> salesMap) {
		this.salesMap = salesMap;
	}

	public Map<String, Double> getTotSalesMap() {
		return totSalesMap;
	}

	public void setTotSalesMap(Map<String, Double> totSalesMap) {
		this.totSalesMap = totSalesMap;
	}

	public String getTotal_sales() {
		return total_sales;
	}

	public void setTotal_sales(String total_sales) {
		this.total_sales = total_sales;
	}


	
}
