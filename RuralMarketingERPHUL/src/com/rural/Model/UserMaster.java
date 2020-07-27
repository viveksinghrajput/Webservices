/*
*UserMaster.java
*Created By		:Kamal Thapa
*Created Date	:Apr 25, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_master")
@SuppressWarnings("serial")
public class UserMaster implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	

	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "contactNum")
	private String contactNum;

	@Column(name = "password")
	private String password;
	
	@Column(name = "stateId")
	private int state;
	
	@Column(name = "cityId")
	private int city;
	
	@Column(name="vendor_id")
	private int vendorId;
	

	@Column(name="roleId")
	private int roleId;
	
	@Column(name="Agency_id")
	private int agencyId;
	
	@Column(name = "isActive")
	private String isActive;
	
	@Column(name = "mobileAccess")
	private String mobileAccess;
	
	@Column(name = "createDate")
	private Date createDate;

	@Column(name = "lastModified")
	private Timestamp lastModifiedDate;
	
	
	@Column(name="brand_id")
	private Integer brandId;
	
	@Column(name="hitCount")
	private int counterFlag;
	
	@Column(name = "isLogin")
	private String islogin;
	
	@Column(name = "app_type")
	private String appType;

	
	@Override
	public String toString() {
		String returnString="UserMaster Id="+getId()+"; Username="+getUsername()+"; Password="+getPassword()
							+"; FirstName="+getFirstName()+"; LastName="+getLastName()+"; Email="+getEmail()
							+"; Contact="+getContactNum()+"; isActive="+getIsActive()
							+"; VendorName="+getVendorId()+"; AgencyName="+getAgencyId();
		
		return returnString;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContactNum() {
		return contactNum;
	}


	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public int getCity() {
		return city;
	}


	public void setCity(int city) {
		this.city = city;
	}


	public int getVendorId() {
		return vendorId;
	}


	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public int getAgencyId() {
		return agencyId;
	}


	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getMobileAccess() {
		return mobileAccess;
	}


	public void setMobileAccess(String mobileAccess) {
		this.mobileAccess = mobileAccess;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}


	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public Integer getBrandId() {
		return brandId;
	}


	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}


	public int getCounterFlag() {
		return counterFlag;
	}


	public void setCounterFlag(int counterFlag) {
		this.counterFlag = counterFlag;
	}


	public String getIslogin() {
		return islogin;
	}


	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	
}
