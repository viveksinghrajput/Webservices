/*
*RoleMaster.java
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
@Table(name = "role_master")
@SuppressWarnings("serial")
public class RoleMaster implements Serializable{
	
	@Id
	@Column(name = "roleMas_id", length = 11 )
	private int roleMas_id;
	
	@Column(name = "roleName")
	private String roleName;	
	
	/*@Column(name = "roleMenu")
	private String roleMenu;		
		*/
	@Column(name = "accessLevel")
	private int accessLevel;	// 0 for view & 1 for Full
	
	@Column(name = "roleId")
	private String roleId;
	
	@Column(name = "role_menu_id")
	private int roleMenuId;

	public int getRoleMas_id() {
		return roleMas_id;
	}

	public void setRoleMas_id(int roleMas_id) {
		this.roleMas_id = roleMas_id;
	}
	
	/*public String getRoleMenu() {
		return roleMenu;
	}

	public void setRoleMenu(String roleMenu) {
		this.roleMenu = roleMenu;
	}*/

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(int roleMenuId) {
		this.roleMenuId = roleMenuId;
	}
}
