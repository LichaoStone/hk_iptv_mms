package com.haikan.iptv.bean.permissions.query;

import com.haikan.iptv.bean.permissions.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 版本: 1.0 
 * 
 * 作者: 
 *
 * 作用: 
 *
 * 包名:cn.qingk.bean.user
 * 
 * 表名:t_permission
 * 
 **/
public class PermissionVoQuery extends PermissionVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2860227873507289721L;

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
	/**
	 * 默认当前页
	 */
	private int pageNumberTmp = 1;
	
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
		try {
			pageNumberTmp = Integer.parseInt(pageNumber);
			if (pageNumberTmp != 0) {
				pageNumberTmp = pageNumberTmp / pageSizeTmp;
			}
		} catch (Exception e) {
			pageNumberTmp = 1;
		}
		return pageNumberTmp+1;
	}
	
	/**
	 * 查询时的时间区间
	 */
	private String searchTime ;
	
	/**
	 * 开始时间
	 */
	private Date beginTime ;
	
	/**
	 * 结束时间
	 */
	private Date endTime ;
	
	/**
	 * 搜索框关键字
	 */
	private String searchWords ;
	
	public String getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}

	public Date getBeginTime() {
		if (StringUtils.isNotBlank(searchTime)) {
			String begin = searchTime.split("-")[0];
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			try {
				beginTime = format.parse(begin);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return beginTime;
	}

	public Date getEndTime() {
		if (StringUtils.isNotBlank(searchTime)) {
			String end = searchTime.split("-")[1];
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			try {
				endTime = format.parse(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return endTime;
	}

	public String getSearchWords() {
		return searchWords;
	}

	public void setSearchWords(String searchWords) {
		this.searchWords = searchWords;
	}

}
