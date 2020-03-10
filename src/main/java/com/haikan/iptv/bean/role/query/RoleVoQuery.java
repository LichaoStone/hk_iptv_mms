package com.haikan.iptv.bean.role.query;

import com.haikan.iptv.bean.role.vo.RoleVo;

/**
 *角色表
 */
public class RoleVoQuery extends RoleVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1701305254842681340L;

	/**
	 * 每页数据条数
	 */
	private Integer pageSize ;

	/**
	 * 当前页数 
	 */
	private Integer offset ;
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
}
