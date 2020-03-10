package com.haikan.iptv.bean.platformUser;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *用户表
 */
public class PlatformUser implements Serializable {

	private static final long serialVersionUID = 1548784671916206912L;
	//用户id
	private Integer userId;  
	//主键
	private String userKey;  
	//登录名称
	private String loginName;  
	//用户名称
	private String userName;  
	//密码
	private String password;  
	//所属角色
	private Integer roleId;  
	//状态：0禁用，1启用
	private Integer status;  
	//创建时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;  
	//性别：0未知，1男，2女
	private Byte sex;  
	//手机号
	private String mobile;  
	//邮箱
	private String email;  
  	
	public Integer getUserId(){  
		return userId;  
	}  
	public void setUserId(Integer userId){  
		this.userId = userId;  
	}  
	public String getUserKey(){  
		return userKey;  
	}  
	public void setUserKey(String userKey){  
		this.userKey = userKey;  
	}  
	public String getLoginName(){  
		return loginName;  
	}  
	public void setLoginName(String loginName){  
		this.loginName = loginName;  
	}  
	public String getUserName(){  
		return userName;  
	}  
	public void setUserName(String userName){  
		this.userName = userName;  
	}  
	public String getPassword(){  
		return password;  
	}  
	public void setPassword(String password){  
		this.password = password;  
	}  
	public Integer getRoleId(){  
		return roleId;  
	}  
	public void setRoleId(Integer roleId){  
		this.roleId = roleId;  
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
	public Byte getSex(){  
		return sex;  
	}  
	public void setSex(Byte sex){  
		this.sex = sex;  
	}  
	public String getMobile(){  
		return mobile;  
	}  
	public void setMobile(String mobile){  
		this.mobile = mobile;  
	}  
	public String getEmail(){  
		return email;  
	}  
	public void setEmail(String email){  
		this.email = email;  
	}  
}
