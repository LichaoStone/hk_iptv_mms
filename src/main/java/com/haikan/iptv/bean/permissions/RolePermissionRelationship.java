package com.haikan.iptv.bean.permissions;

import java.io.Serializable;
import java.util.Date;

/**
 *角色权限信息表
 */
public class RolePermissionRelationship implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4565320739058995625L;
	//主键
	private Integer relationshipKey;  
	//权限Key
	private String permissionKey;  
	//角色Key
	private Integer roleKey;  
	//创建者
	private String creator;  
	//创建时间
	private Date createTime;  
  	
	public Integer getRelationshipKey() {
		return relationshipKey;
	}
	public void setRelationshipKey(Integer relationshipKey) {
		this.relationshipKey = relationshipKey;
	}
	public Integer getRoleKey() {
		return roleKey;
	}
	public void setRoleKey(Integer roleKey) {
		this.roleKey = roleKey;
	}
	public String getPermissionKey(){  
		return permissionKey;  
	}  
	public void setPermissionKey(String permissionKey){  
		this.permissionKey = permissionKey;  
	}  
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime(){  
		return createTime;  
	}  
	public void setCreateTime(Date createTime){  
		this.createTime = createTime;  
	}  
}
