package com.rural.Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MenuTemp implements Serializable {
	private Integer roleId;
	private String menu;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	
	

}
