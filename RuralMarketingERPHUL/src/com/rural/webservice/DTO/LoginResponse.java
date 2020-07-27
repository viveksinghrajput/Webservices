/*
*LoginResponse.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.DTO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LoginResponse")
public class LoginResponse {
	int userId;
	String user;
	int role;
    int supId;
    int agenId;
  /*  String imei;
    String dName;
    String dModel;
    int androidVer;
    String appVerCode;
    String appVerName;*/
    String time;
    String status;
    int apkVer;
    String apkPath;
    int locAcc;
    int locTime;
    int photoRes;
    int photoQly;
    int statesBatch;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getSupId() {
		return supId;
	}
	public void setSupId(int supId) {
		this.supId = supId;
	}
	public int getAgenId() {
		return agenId;
	}
	public void setAgenId(int agenId) {
		this.agenId = agenId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getApkVer() {
		return apkVer;
	}
	public void setApkVer(int apkVer) {
		this.apkVer = apkVer;
	}
	public String getApkPath() {
		return apkPath;
	}
	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}
	public int getLocAcc() {
		return locAcc;
	}
	public void setLocAcc(int locAcc) {
		this.locAcc = locAcc;
	}
	public int getLocTime() {
		return locTime;
	}
	public void setLocTime(int locTime) {
		this.locTime = locTime;
	}
	public int getPhotoRes() {
		return photoRes;
	}
	public void setPhotoRes(int photoRes) {
		this.photoRes = photoRes;
	}
	public int getPhotoQly() {
		return photoQly;
	}
	public void setPhotoQly(int photoQly) {
		this.photoQly = photoQly;
	}
	public int getStatesBatch() {
		return statesBatch;
	}
	public void setStatesBatch(int statesBatch) {
		this.statesBatch = statesBatch;
	}
	
}
