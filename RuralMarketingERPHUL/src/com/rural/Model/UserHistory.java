/*
*LoginHistory.java
*Created By		:Kamal Thapa
*Created Date	:Apr 26, 2018
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
@Table(name = "user_history")
@SuppressWarnings("serial")
public class UserHistory implements Serializable{
	
	/**
	 * 
	 */
	public UserHistory(String username) {
		UserHistory user=new UserHistory();
		user.setUserName(username);
	}

	/**
	 * 
	 */
	public UserHistory() {
	}

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "activity")
	private String activity;
	
	@Column(name = "logDate")
	private Date logDate=new Date();
	
	@Column(name = "ipAddress")
	private String ipAddress;
	
	@Column(name = "browser")
	private String browser;
	
	@Column(name = "sessionCookie")
	private String sessionCookie;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getSessionCookie() {
		return sessionCookie;
	}

	public void setSessionCookie(String sessionCookie) {
		this.sessionCookie = sessionCookie;
	}
	
	
	
}
