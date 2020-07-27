/*
*CollateralMaster.java
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
@Table(name = "Collateral_Master")
@SuppressWarnings("serial")
public class CollateralMaster implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Collateral_ID")
	private int coll_Id;
	
	@Column(name = "Collateral_Desc")
	private String coll_desc;

	public int getColl_Id() {
		return coll_Id;
	}

	public void setColl_Id(int coll_Id) {
		this.coll_Id = coll_Id;
	}

	public String getColl_desc() {
		return coll_desc;
	}

	public void setColl_desc(String coll_desc) {
		this.coll_desc = coll_desc;
	}
	
	
	
}
