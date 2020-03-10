package com.haikan.iptv.bean.role.vo;

import com.haikan.iptv.bean.role.Role;

/**
 *角色表
 */
public class RoleVo extends Role{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4453744888317244528L;

	private String statusName;

	private String[] permissions;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String[] getPermissions() {
		return permissions;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}
}
