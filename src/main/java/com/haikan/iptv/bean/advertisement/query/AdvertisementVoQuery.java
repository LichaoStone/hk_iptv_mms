package com.haikan.iptv.bean.advertisement.query;


import com.haikan.iptv.bean.advertisement.vo.AdvertisementVo;

import java.util.ArrayList;
import java.util.List;

/**
 *广告表
 */
public class AdvertisementVoQuery extends AdvertisementVo {

	/**
	 * 每页数据条数
	 */
	private Integer pageSize ;

	/**
	 * 当前页数 
	 */
	private Integer offset ;



	private List<Integer> advBelongList = new ArrayList<Integer>();



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


	public List<Integer> getAdvBelongList() {
		return advBelongList;
	}

	public void setAdvBelongList(List<Integer> advBelongList) {
		this.advBelongList = advBelongList;
	}


}
