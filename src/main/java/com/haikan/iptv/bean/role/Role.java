package com.haikan.iptv.bean.role;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *角色表
 */
public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8018814877640864626L;
	//角色id
	private Integer roleId;  
	//角色名称
	private String roleName;  
	//状态：0禁用，1启用
	private Integer status;  
	//创建时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;  
	//描述
	private String description;  
  	
	public Integer getRoleId(){  
		return roleId;  
	}  
	public void setRoleId(Integer roleId){  
		this.roleId = roleId;  
	}  
	public String getRoleName(){  
		return roleName;  
	}  
	public void setRoleName(String roleName){  
		this.roleName = roleName;  
	}  
	public Integer getStatus(){  
		return status;  
	}  
	public void setStatus(Integer status){  
		this.status = status;  
	}  
	public Date getCreateTime(){  
		return createTime;  
	}  
	public void setCreateTime(Date createTime){  
		this.createTime = createTime;  
	}  
	public String getDescription(){  
		return description;  
	}  
	public void setDescription(String description){  
		this.description = description;  
	}  
}
