/*
*UserReq.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.DTO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class UserReq {
	
	private int userId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	

}
