/*
*ItemDescriptionMaster.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="item_description_master")
public class ItemDescriptionMaster {
	
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ItemId;

	@Column(name="item_description")
	private String ItemDescription;
	
	@Column(name="units_per_case")
	private String unitspercase;
	
	@Column(name="price_per_unit")
	private String priceperunit;
	
	public int getItemId() {
		return ItemId;
	}

	public void setItemId(int itemId) {
		ItemId = itemId;
	}
	
	public String getItemDescription() {
		return ItemDescription;
	}

	public void setItemDescription(String itemDescription) {
		ItemDescription = itemDescription;
	}

	public String getUnitspercase() {
		return unitspercase;
	}

	public void setUnitspercase(String unitspercase) {
		this.unitspercase = unitspercase;
	}

	public String getPriceperunit() {
		return priceperunit;
	}

	public void setPriceperunit(String priceperunit) {
		this.priceperunit = priceperunit;
	}

	
	

}
