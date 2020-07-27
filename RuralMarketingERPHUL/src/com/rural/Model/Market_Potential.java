/*
*Market_Potential.java
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
@Table(name = "Market_Potential")
@SuppressWarnings("serial")
public class Market_Potential implements Serializable{
	

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

	@Id
	@Column(name = "City")
	private String city;	
	

	@Column(name = "Area")
	private String area;	

	@Column(name = "Total_HH")
	private int total_HH;
	
	@Column(name = "Pentrtn_H2H_perc")
	private int pentrtn_H2H_perc;	
	
	@Column(name = "LSM_0_3")
	private int lSM_0_3;	
	
	@Column(name = "LSM_4_6")
	private int lSM_4_6;	
	
	@Column(name = "LSM_7_9")
	private int lSM_7_9;	
	
	@Column(name = "HUL_Retail_cvg")
	private int hUL_Retail_cvg;	
	
	@Column(name = "LSM_10_12")
	private int lSM_10_12;	
	
	public int getTotal_HH() {
		return total_HH;
	}

	public void setTotal_HH(int total_HH) {
		this.total_HH = total_HH;
	}

	public int getPentrtn_H2H_perc() {
		return pentrtn_H2H_perc;
	}

	public void setPentrtn_H2H_perc(int pentrtn_H2H_perc) {
		this.pentrtn_H2H_perc = pentrtn_H2H_perc;
	}

	public int getlSM_0_3() {
		return lSM_0_3;
	}

	public void setlSM_0_3(int lSM_0_3) {
		this.lSM_0_3 = lSM_0_3;
	}

	public int getlSM_4_6() {
		return lSM_4_6;
	}

	public void setlSM_4_6(int lSM_4_6) {
		this.lSM_4_6 = lSM_4_6;
	}

	public int getlSM_7_9() {
		return lSM_7_9;
	}

	public void setlSM_7_9(int lSM_7_9) {
		this.lSM_7_9 = lSM_7_9;
	}

	public int gethUL_Retail_cvg() {
		return hUL_Retail_cvg;
	}

	public void sethUL_Retail_cvg(int hUL_Retail_cvg) {
		this.hUL_Retail_cvg = hUL_Retail_cvg;
	}

	public int getlSM_10_12() {
		return lSM_10_12;
	}

	public void setlSM_10_12(int lSM_10_12) {
		this.lSM_10_12 = lSM_10_12;
	}

	public int getlSM_13_15() {
		return lSM_13_15;
	}

	public void setlSM_13_15(int lSM_13_15) {
		this.lSM_13_15 = lSM_13_15;
	}

	public int getLSM16_18() {
		return LSM16_18;
	}

	public void setLSM16_18(int lSM16_18) {
		LSM16_18 = lSM16_18;
	}

	@Column(name = "LSM_13_15")
	private int lSM_13_15;	
	
	@Column(name = "LSM16_18")
	private int LSM16_18;	

}
