package com.rural.Model;

import java.io.Serializable;

public class SessionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  userId;
	private String sessionId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
}
