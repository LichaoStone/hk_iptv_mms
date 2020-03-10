package com.haikan.iptv.bean.platformUser.query;

import com.haikan.iptv.bean.platformUser.vo.PlatformUserVo;

/**
 *用户表
 */
public class PlatformUserVoQuery extends PlatformUserVo {


	private static final long serialVersionUID = 8410426717886344485L;
	/**
	 * 每页数据条数
	 */
	private  Integer pageSize ;

	/**
	 * 当前页数 
	 */
	private Integer offset ;

	private String _timestamp;
	private String _sign;
	private String v;
	
	public Integer getPageSize() {
		if (pageSize == null) {
			pageSize = 10 ;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getOffset() {
		if (offset == null) {
			offset = 1 ;
		}
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String get_timestamp() {
		return _timestamp;
	}

	public void set_timestamp(String _timestamp) {
		this._timestamp = _timestamp;
	}

	public String get_sign() {
		return _sign;
	}

	public void set_sign(String _sign) {
		this._sign = _sign;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}
}
