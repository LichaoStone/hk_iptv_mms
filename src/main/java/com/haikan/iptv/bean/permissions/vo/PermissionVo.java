package com.haikan.iptv.bean.permissions.vo;

import com.haikan.iptv.bean.permissions.Permission;

/**
 * 版本: 1.0 
 * 
 * 作者: 
 *
 * 作用: 
 *
 * 包名:cn.qingk.bean.user
 * 
 * 表名:t_permission
 * 
 **/
public class PermissionVo extends Permission {

	/**
	 * 
	 */
	private static final long serialVersionUID = -957178056859900621L;
	
	private Integer userKey ;
	
	private Integer roleKey ;

	public Integer getUserKey() {
		return userKey;
	}

	public void setUserKey(Integer userKey) {
		this.userKey = userKey;
	}

	public Integer getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(Integer roleKey) {
		this.roleKey = roleKey;
	}
	
	
}
