package com.haikan.iptv.bean.advertisement;

import java.io.Serializable;
import java.util.Date;

/**
 *广告表
 */
public class Advertisement implements Serializable {
	
	//自增ID
	private Integer id;  
	//主键key
	private String advKey;  
	//广告标题
	private String advName;
	//广告类型
	private Integer advType;
	//点播分类
	private String videoType;
	//广告图片
	private String advImg;  
	//指向类型：1站内;2站外;3自定义类型;4无指向
	private Integer targetType;
	//目标url
	private String targetUrl;  
	//广告归属：2移动,4联通,8电信,6移动联通,10移动电信,12联通电信,14全部
	private Integer advBelong;
	//排序
	private Integer ordernum;
	//开始时间
	private Date beginTime;  
	//结束时间
	private Date endTime;  
	//状态：0启用，1禁用，2已过期，-1删除
	private Integer status;
	//自定义广告内容
	private String content;  
	//创建时间
	private Date createTime;  
	//创建者
	private String creator;  
	//更新时间
	private Date updateTime;



  	
	public Integer getId(){  
		return id;  
	}  
	public void setId(Integer id){  
		this.id = id;  
	}  
	public String getAdvKey(){  
		return advKey;  
	}  
	public void setAdvKey(String advKey){  
		this.advKey = advKey;  
	}  
	public String getAdvName(){  
		return advName;  
	}  
	public void setAdvName(String advName){  
		this.advName = advName;  
	}  
	public String getAdvImg(){  
		return advImg;  
	}  
	public void setAdvImg(String advImg){  
		this.advImg = advImg;  
	}  
	public Integer getTargetType(){
		return targetType;  
	}  
	public void setTargetType(Integer targetType){
		this.targetType = targetType;  
	}  
	public String getTargetUrl(){  
		return targetUrl;  
	}  
	public void setTargetUrl(String targetUrl){  
		this.targetUrl = targetUrl;  
	}  
	public Integer getAdvBelong(){
		return advBelong;  
	}  
	public void setAdvBelong(Integer advBelong){
		this.advBelong = advBelong;  
	}  
	public Integer getOrdernum(){
		return ordernum;  
	}  
	public void setOrdernum(Integer ordernum){
		this.ordernum = ordernum;  
	}  
	public Date getBeginTime(){  
		return beginTime;  
	}  
	public void setBeginTime(Date beginTime){  
		this.beginTime = beginTime;  
	}  
	public Date getEndTime(){  
		return endTime;  
	}  
	public void setEndTime(Date endTime){  
		this.endTime = endTime;  
	}  
	public Integer getStatus(){
		return status;  
	}  
	public void setStatus(Integer status){
		this.status = status;  
	}  
	public String getContent(){  
		return content;  
	}  
	public void setContent(String content){  
		this.content = content;  
	}  
	public Date getCreateTime(){  
		return createTime;  
	}  
	public void setCreateTime(Date createTime){  
		this.createTime = createTime;  
	}  
	public String getCreator(){  
		return creator;  
	}  
	public void setCreator(String creator){  
		this.creator = creator;  
	}  
	public Date getUpdateTime(){  
		return updateTime;  
	}  
	public void setUpdateTime(Date updateTime){  
		this.updateTime = updateTime;  
	}

	public Integer getAdvType() {
		return advType;
	}

	public void setAdvType(Integer advType) {
		this.advType = advType;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

}
