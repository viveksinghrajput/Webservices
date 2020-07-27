/*
*VendorMaster.java
*Created By		:Kamal Thapa
*Created Date	:Mar 6, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="vendorwarehouse_master")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "warehouse")
public class VendorWareHouseMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "trackId",nullable = false)
	private String trackId;
	
	@Column(name = "userId",nullable = false)
	private String userId;
	
	
	@Column(name = "vendorMaster_id",nullable = false)
	private String id;
	
	//@Value(value="Submitted")
	@Column(name = "status")
	private String status;
		
	@Column(name = "vendorId",length=100)
	private Integer vendorId;
	
	@Column(name = "vendorContact")
	private String vendorContact;
	
	@Column(name = "vendorEmail",length=45)
	private String vendorEmail;
	
	@Column(name = "warehouseName",length=45)
	private String name;

	
	@Column(name = "wareHouseAddress01",length=255)
	private String addr1;
	
	@Column(name = "wareHouseAddress02",length=255)
	private String addr2;
	
	@Column(name = "pinCode",length=06)
	private String pin;
	
	
	@Column(name = "createdDate")
	private String time;;
	

	
	@Column(name = "lastEditedDate")
	private Date lastEditedDate;
	
	@Column(name = "comment")
	private String comment;
	
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "stateId",nullable = false)
	private int stateId;
	
	@Column(name = "cityId",nullable = false)
	private int cityId;
	
	@Column(name = "wareHouseAreainSqft")
	private int area;
	
	//@OneToMany(fetch = FetchType.LAZY,mappedBy = "warehouseMaster")
	
	
	@OneToMany(
	        cascade = CascadeType.ALL,  fetch = FetchType.LAZY,
	        orphanRemoval = true
	    )
    @JoinColumn(name = "trackId",referencedColumnName="trackId", nullable = false)
	private List<Photo> photos;

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getLastEditedDate() {
		return lastEditedDate;
	}

	public void setLastEditedDate(Date lastEditedDate) {
		this.lastEditedDate = lastEditedDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
