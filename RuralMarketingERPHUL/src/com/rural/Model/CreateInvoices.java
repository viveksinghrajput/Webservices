/*
*ConversionMaster.java
*Created By		:SaiTej Deep
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="invoice_items")
@SuppressWarnings("serial")
public class CreateInvoices implements Serializable{
	
	@Id
	@Column(name="Item_number")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int item_number;
	
	@Column(name="item_id")
	private String itemId;
	
	
	private String cases;
	
	@Column(name="units")
	private String units;
	
	@Column(name="totalunits")
	private String totalunits;
	
	@Column(name="Net_amount")
	private String net_amount;
	
	@Column(name="priceperunits")
	private String priceperunits;
	
	

	@ManyToOne
	@JoinColumn(name="invoice_id", nullable=false)
	private Stock stock;
	

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public int getItem_number() {
		return item_number;
	}

	public void setItem_number(int item_number) {
		this.item_number = item_number;
	}

	



	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getCases() {
		return cases;
	}

	@Column(name="case")
	public void setCases(String cases) {
		this.cases = cases;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getTotalunits() {
		return totalunits;
	}

	public void setTotalunits(String totalunits) {
		this.totalunits = totalunits;
	}

	public String getNet_amount() {
		return net_amount;
	}

	public void setNet_amount(String net_amount) {
		this.net_amount = net_amount;
	}

	public String getPriceperunits() {
		return priceperunits;
	}

	public void setPriceperunits(String priceperunits) {
		this.priceperunits = priceperunits;
	}
	
}
