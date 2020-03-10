package com.haikan.iptv.bean.platformUser.vo;

import com.haikan.iptv.bean.platformUser.PlatformUser;

import java.util.List;


/**
 *用户表
 */
public class PlatformUserVo extends PlatformUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1271207357143016800L;
	private String roleName;
	private String sexName;
	private String statusName;
	private List<String> permissionUrlList ;
	
	private List<String> permissionKeyList ;
	

	public List<String> getPermissionUrlList() {
		return permissionUrlList;
	}

	public void setPermissionUrlList(List<String> permissionUrlList) {
		this.permissionUrlList = permissionUrlList;
	}

	public List<String> getPermissionKeyList() {
		return permissionKeyList;
	}

	public void setPermissionKeyList(List<String> permissionKeyList) {
		this.permissionKeyList = permissionKeyList;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
