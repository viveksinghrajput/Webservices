/*
*WareHouseHistory.java
*Created By		:Kamal Thapa
*Created Date	:Mar 7, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "warehouse_history")
@SuppressWarnings("serial")
public class WareHouseHistory implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "WareHouseHistory_id")
	private int WareHouseHistory_id;
	
	@Column(name="trackId")
	private String trackId;
	
	@Column(name = "vendorId")
	private Integer vendorId;
	
	@Column(name = "vendorContact")
	private String vendorContact;
	
	@Column(name = "vendorEmail",length=45)
	private String vendorEmail;
	
	@Column(name = "updateCount")
	private int updateCount;
	
	@Column(name = "warehouseName",length=45)
	private String warehouseName;
	
	@Column(name = "warehouseStatus",length=45)
	private String warehouseStatus;
	
	@Column(name = "wareHouseAddress01",length=255)
	private String wareHouseAddress01;
	
	@Column(name = "wareHouseAddress02",length=255)
	private String wareHouseAddress02;
	
	@Column(name = "stateId")
	private int stateId;
	
	@Column(name = "cityId")
	private int cityId;
	
	@Column(name = "pinCode",length=06)
	private String pinCode;
	
	@Column(name = "wareHouseOuterImage")
	private String wareHouseOuterImage;
	
	@Column(name = "wareHouseInnerImage01")
	private String wareHouseInnerImage01;
	
	@Column(name = "wareHouseInnerImage02")
	private String wareHouseInnerImage02;
	
	@Column(name = "wareHouseAreainSqft",length=06)
	private int wareHouseAreainSqft;
	
	@Column(name="latOuter")
	private String latOuter;
	
	@Column(name="lonOuter")
	private String lonOuter;
	
	@Column(name="latInner1")
	private String latInner1;
	
	@Column(name="lonInner1")
	private String lonInner1;
	
	@Column(name="latInner2")
	private String latInner2;
	
	@Column(name="lonInner2")
	private String lonInner2;
	
	@Column(name = "createdDate")
	private String  createdDate;
	
	@Column(name = "approvedBy",length=45)
	private String approvedBy;
	
	@Column(name = "approvalDate")
	private String approvalDate;
	
	@Column(name = "comment",length=255)
	private String comment;
	
	@Column(name="remarks")
	private String remarks;
	
	

	@Column(name = "billingStartDate",length=45)
	private Date billingStartDate;
	
	@Column(name = "billingStopDate",length=45)
	private Date billingStopDate;

	
	public String toString() {
		return "id: " + getWareHouseHistory_id() +" ; Created On: "+getCreatedDate() 
				+" ;WareHouseName: "+getWarehouseName();
	}


	public int getWareHouseHistory_id() {
		return WareHouseHistory_id;
	}


	public void setWareHouseHistory_id(int wareHouseHistory_id) {
		WareHouseHistory_id = wareHouseHistory_id;
	}


	public String getVendorContact() {
		return vendorContact;
	}


	public void setVendorContact(String vendorContact) {
		this.vendorContact = vendorContact;
	}


	public String getVendorEmail() {
		return vendorEmail;
	}


	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}


	public int getUpdateCount() {
		return updateCount;
	}


	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}


	public String getWarehouseName() {
		return warehouseName;
	}


	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}


	public String getWarehouseStatus() {
		return warehouseStatus;
	}


	public void setWarehouseStatus(String warehouseStatus) {
		this.warehouseStatus = warehouseStatus;
	}


	public String getWareHouseAddress01() {
		return wareHouseAddress01;
	}


	public void setWareHouseAddress01(String wareHouseAddress01) {
		this.wareHouseAddress01 = wareHouseAddress01;
	}


	public String getWareHouseAddress02() {
		return wareHouseAddress02;
	}


	public void setWareHouseAddress02(String wareHouseAddress02) {
		this.wareHouseAddress02 = wareHouseAddress02;
	}

	public int getStateId() {
		return stateId;
	}


	public void setStateId(int stateId) {
		this.stateId = stateId;
	}


	public int getCityId() {
		return cityId;
	}


	public void setCityId(int cityId) {
		this.cityId = cityId;
	}


	public String getPinCode() {
		return pinCode;
	}


	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}


	public String getWareHouseOuterImage() {
		return wareHouseOuterImage;
	}


	public void setWareHouseOuterImage(String wareHouseOuterImage) {
		this.wareHouseOuterImage = wareHouseOuterImage;
	}


	public String getWareHouseInnerImage01() {
		return wareHouseInnerImage01;
	}


	public void setWareHouseInnerImage01(String wareHouseInnerImage01) {
		this.wareHouseInnerImage01 = wareHouseInnerImage01;
	}


	public String getWareHouseInnerImage02() {
		return wareHouseInnerImage02;
	}


	public void setWareHouseInnerImage02(String wareHouseInnerImage02) {
		this.wareHouseInnerImage02 = wareHouseInnerImage02;
	}


	public int getWareHouseAreainSqft() {
		return wareHouseAreainSqft;
	}


	public void setWareHouseAreainSqft(int wareHouseAreainSqft) {
		this.wareHouseAreainSqft = wareHouseAreainSqft;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public String getApprovedBy() {
		return approvedBy;
	}


	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}


	


	public String getApprovalDate() {
		return approvalDate;
	}


	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}


	public Date getBillingStartDate() {
		return billingStartDate;
	}


	public void setBillingStartDate(Date billingStartDate) {
		this.billingStartDate = billingStartDate;
	}


	public Date getBillingStopDate() {
		return billingStopDate;
	}


	public void setBillingStopDate(Date billingStopDate) {
		this.billingStopDate = billingStopDate;
	}

	public Integer getVendorId() {
		return vendorId;
	}


	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getTrackId() {
		return trackId;
	}


	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}


	public String getLatOuter() {
		return latOuter;
	}


	public void setLatOuter(String latOuter) {
		this.latOuter = latOuter;
	}


	public String getLonOuter() {
		return lonOuter;
	}


	public void setLonOuter(String lonOuter) {
		this.lonOuter = lonOuter;
	}


	public String getLatInner1() {
		return latInner1;
	}


	public void setLatInner1(String latInner1) {
		this.latInner1 = latInner1;
	}


	public String getLonInner1() {
		return lonInner1;
	}


	public void setLonInner1(String lonInner1) {
		this.lonInner1 = lonInner1;
	}


	public String getLatInner2() {
		return latInner2;
	}


	public void setLatInner2(String latInner2) {
		this.latInner2 = latInner2;
	}


	public String getLonInner2() {
		return lonInner2;
	}


	public void setLonInner2(String lonInner2) {
		this.lonInner2 = lonInner2;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
