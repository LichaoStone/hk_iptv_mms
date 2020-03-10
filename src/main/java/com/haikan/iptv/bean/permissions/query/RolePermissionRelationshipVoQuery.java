package com.haikan.iptv.bean.permissions.query;

import com.haikan.iptv.bean.permissions.vo.RolePermissionRelationshipVo;
import org.apache.commons.lang3.StringUtils;

/**
 *角色权限信息表
 */
public class RolePermissionRelationshipVoQuery extends RolePermissionRelationshipVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1581825113152413004L;

	/**
	 * 每页数据条数
	 */
	private String pageSize ;
	
	/**
	 * 当前页数 
	 */
	private String pageNumber ;
	
	/**
	 * 默认每页数据
	 */
	private int pageSizeTmp = 10;
	
	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSizeTmp() {
		try {
			pageSizeTmp = Integer.parseInt(pageSize);
		} catch (Exception e) {
			pageSizeTmp = 10;
		}
		return pageSizeTmp;
	}

	public int getPageNumberTmp() {
		if (StringUtils.isBlank(pageNumber)) {
			pageNumber = "1" ;
		}
		return Integer.parseInt(pageNumber);
	}
	
}
