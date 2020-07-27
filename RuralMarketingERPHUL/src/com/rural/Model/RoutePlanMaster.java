/*
*RoutePlanMaster.java
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
@Table(name = "routePlan_Master")
@SuppressWarnings("serial")
public class RoutePlanMaster  implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "routePlan_id")
	private int routePlan_id;
	
	@Column(name = "routePlan_Name")
	private String routePlan_Name;
	
	@Column(name = "Brand")
	private String brand;
	
	@Column(name = "City")
	private String city;
	
	@Column(name = "Area")
	private String area;
	
	@Column(name = "Noof_Retailors")
	private String noof_Retailors;
	
	@Column(name = "Actual_Sales_Value")
	private String actual_Sales_Value;
	
	@Column(name = "Market_Potential_Sales")
	private String market_Potential_Sales;

	@Column(name = "Market_Share")
	private String market_Share;
	
	@Column(name = "Contribution")
	private String contribution;
	
	@Column(name = "Market_Share_HiLo")
	private String market_Share_HiLo;
	
	@Column(name = "Contribution_HiLo")
	private String contribution_HiLo;
	
	@Column(name = "X_Y")
	private String x_Y;
	
	@Column(name = "Quadrant")
	private String quadrant;
	
	@Column(name = "Total_HH")
	private String total_HH;
	
	@Column(name = "LSM_0_3")
	private String lSM_0_3;
	
	@Column(name = "LSM_4_6")
	private String lSM_4_6;
	
	@Column(name = "LSM_7_9")
	private String lSM_7_9;
	
	@Column(name = "LSM_10_12")
	private String lSM_10_12;
	
	@Column(name = "LSM_13_15")
	private String lSM_13_15;
	
	@Column(name = "LSM16_18")
	private String lSM_16_18;
	
	@Column(name = "Conversions")
	private String conversions;
	
	@Column(name = "TG_HH")
	private String tG_HH;
	
	@Column(name = "saturation")
	private String saturation;
	
	@Column(name = "Balance_TG_HH")
	private String balance_TG_HH;
	
	@Column(name = "TG_HH_30_percent")
	private String tG_HH_30_percent;
	
	@Column(name = "Balance_TG_HH_Potential")
	private String balance_TG_HH_Potential;

	@Column(name = "TG_HH_Potental")
	private String tG_HH_Potental;
	
	@Column(name = "isActive")
	private String isActive;

	public int getRoutePlan_id() {
		return routePlan_id;
	}

	public void setRoutePlan_id(int routePlan_id) {
		this.routePlan_id = routePlan_id;
	}

	public String getRoutePlan_Name() {
		return routePlan_Name;
	}

	public void setRoutePlan_Name(String routePlan_Name) {
		this.routePlan_Name = routePlan_Name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNoof_Retailors() {
		return noof_Retailors;
	}

	public void setNoof_Retailors(String noof_Retailors) {
		this.noof_Retailors = noof_Retailors;
	}

	public String getActual_Sales_Value() {
		return actual_Sales_Value;
	}

	public void setActual_Sales_Value(String actual_Sales_Value) {
		this.actual_Sales_Value = actual_Sales_Value;
	}

	public String getMarket_Potential_Sales() {
		return market_Potential_Sales;
	}

	public void setMarket_Potential_Sales(String market_Potential_Sales) {
		this.market_Potential_Sales = market_Potential_Sales;
	}

	public String getMarket_Share() {
		return market_Share;
	}

	public void setMarket_Share(String market_Share) {
		this.market_Share = market_Share;
	}

	public String getContribution() {
		return contribution;
	}

	public void setContribution(String contribution) {
		this.contribution = contribution;
	}

	public String getMarket_Share_HiLo() {
		return market_Share_HiLo;
	}

	public void setMarket_Share_HiLo(String market_Share_HiLo) {
		this.market_Share_HiLo = market_Share_HiLo;
	}

	public String getContribution_HiLo() {
		return contribution_HiLo;
	}

	public void setContribution_HiLo(String contribution_HiLo) {
		this.contribution_HiLo = contribution_HiLo;
	}

	public String getX_Y() {
		return x_Y;
	}

	public void setX_Y(String x_Y) {
		this.x_Y = x_Y;
	}

	public String getQuadrant() {
		return quadrant;
	}

	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}

	public String getTotal_HH() {
		return total_HH;
	}

	public void setTotal_HH(String total_HH) {
		this.total_HH = total_HH;
	}

	public String getlSM_0_3() {
		return lSM_0_3;
	}

	public void setlSM_0_3(String lSM_0_3) {
		this.lSM_0_3 = lSM_0_3;
	}

	public String getlSM_4_6() {
		return lSM_4_6;
	}

	public void setlSM_4_6(String lSM_4_6) {
		this.lSM_4_6 = lSM_4_6;
	}

	public String getlSM_7_9() {
		return lSM_7_9;
	}

	public void setlSM_7_9(String lSM_7_9) {
		this.lSM_7_9 = lSM_7_9;
	}

	public String getlSM_10_12() {
		return lSM_10_12;
	}

	public void setlSM_10_12(String lSM_10_12) {
		this.lSM_10_12 = lSM_10_12;
	}

	public String getlSM_13_15() {
		return lSM_13_15;
	}

	public void setlSM_13_15(String lSM_13_15) {
		this.lSM_13_15 = lSM_13_15;
	}

	public String getlSM_16_18() {
		return lSM_16_18;
	}

	public void setlSM_16_18(String lSM_16_18) {
		this.lSM_16_18 = lSM_16_18;
	}

	public String getConversions() {
		return conversions;
	}

	public void setConversions(String conversions) {
		this.conversions = conversions;
	}

	public String gettG_HH() {
		return tG_HH;
	}

	public void settG_HH(String tG_HH) {
		this.tG_HH = tG_HH;
	}

	public String getSaturation() {
		return saturation;
	}

	public void setSaturation(String saturation) {
		this.saturation = saturation;
	}

	public String getBalance_TG_HH() {
		return balance_TG_HH;
	}

	public void setBalance_TG_HH(String balance_TG_HH) {
		this.balance_TG_HH = balance_TG_HH;
	}

	public String gettG_HH_30_percent() {
		return tG_HH_30_percent;
	}

	public void settG_HH_30_percent(String tG_HH_30_percent) {
		this.tG_HH_30_percent = tG_HH_30_percent;
	}

	public String getBalance_TG_HH_Potential() {
		return balance_TG_HH_Potential;
	}

	public void setBalance_TG_HH_Potential(String balance_TG_HH_Potential) {
		this.balance_TG_HH_Potential = balance_TG_HH_Potential;
	}

	public String gettG_HH_Potental() {
		return tG_HH_Potental;
	}

	public void settG_HH_Potental(String tG_HH_Potental) {
		this.tG_HH_Potental = tG_HH_Potental;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	
}
