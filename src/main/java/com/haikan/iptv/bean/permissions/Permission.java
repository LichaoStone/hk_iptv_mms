package com.haikan.iptv.bean.permissions;

import java.io.Serializable;
import java.util.Date;
/**
 *权限信息表
 */
public class Permission implements Serializable {

	private static final long serialVersionUID = -4275003672171636097L;
	//主键
	private String permissionKey;  
	//权限名称
	private String permissionName;  
	//权限描述
	private String permissionDescript;  
	//展示路径
	private String showUrl;  
	//获取数据路径
	private String getDataUrl;  
	//上级权限Key
	private Byte parentKey;  
	//排序序号
	private Integer orderNum;  
	//创建者
	private String creator;  
	//显示状态（0:隐藏；1：显示）
	private Byte showStatus;  
	//创建时间
	private Date createTime;  
  	
	public String getPermissionKey(){  
		return permissionKey;  
	}  
	public void setPermissionKey(String permissionKey){  
		this.permissionKey = permissionKey;  
	}  
	public String getPermissionName(){  
		return permissionName;  
	}  
	public void setPermissionName(String permissionName){  
		this.permissionName = permissionName;  
	}  
	public String getPermissionDescript(){  
		return permissionDescript;  
	}  
	public void setPermissionDescript(String permissionDescript){  
		this.permissionDescript = permissionDescript;  
	}  
	public String getShowUrl(){  
		return showUrl;  
	}  
	public void setShowUrl(String showUrl){  
		this.showUrl = showUrl;  
	}  
	public String getGetDataUrl(){  
		return getDataUrl;  
	}  
	public void setGetDataUrl(String getDataUrl){  
		this.getDataUrl = getDataUrl;  
	}  
	public Byte getParentKey(){  
		return parentKey;  
	}  
	public void setParentKey(Byte parentKey){  
		this.parentKey = parentKey;  
	}  
	public Integer getOrderNum(){  
		return orderNum;  
	}  
	public void setOrderNum(Integer orderNum){  
		this.orderNum = orderNum;  
	}  
	public String getCreator(){  
		return creator;  
	}  
	public void setCreator(String creator){  
		this.creator = creator;  
	}  
	public Byte getShowStatus(){  
		return showStatus;  
	}  
	public void setShowStatus(Byte showStatus){  
		this.showStatus = showStatus;  
	}  
	public Date getCreateTime(){  
		return createTime;  
	}  
	public void setCreateTime(Date createTime){  
		this.createTime = createTime;  
	}  
}
