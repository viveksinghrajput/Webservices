/*
*MenuMaster.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu_master")
@SuppressWarnings("serial")
public class MenuMaster implements Serializable{

	@Id
	@Column(name = "id_menuMaster")
	private int id_MenuMaster;
	
	@Column(name = "menuGroup")
	private String menuGroup;
	
	@Column(name = "menuItem")
	private String menuItem;
	
	@Column(name = "menuLink")
	private String menuLink;
	
	@Column(name = "menuOrder")
	private int menuOrder;
	
	@Column(name = "menuId")
	private int menuId;
	
	@Column(name = "app_type")
	private String appType;

	public int getId_MenuMaster() {
		return id_MenuMaster;
	}

	public void setId_MenuMaster(int id_MenuMaster) {
		this.id_MenuMaster = id_MenuMaster;
	}

	public String getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(String menuGroup) {
		this.menuGroup = menuGroup;
	}

	public String getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}

	public String getMenuLink() {
		return menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}
}
