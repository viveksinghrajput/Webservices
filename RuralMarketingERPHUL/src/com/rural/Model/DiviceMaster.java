/*
*DiviceMaster.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "diviceMaster")
public class DiviceMaster {
	String user;
    String pass;
    String imei;
    String dName;
    String dModel;
    int androidVer;
    String appVerCode;
    String appVerName;
    String time;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getdModel() {
		return dModel;
	}
	public void setdModel(String dModel) {
		this.dModel = dModel;
	}
	public int getAndroidVer() {
		return androidVer;
	}
	public void setAndroidVer(int androidVer) {
		this.androidVer = androidVer;
	}
	public String getAppVerCode() {
		return appVerCode;
	}
	public void setAppVerCode(String appVerCode) {
		this.appVerCode = appVerCode;
	}
	public String getAppVerName() {
		return appVerName;
	}
	public void setAppVerName(String appVerName) {
		this.appVerName = appVerName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
