package com.haikan.iptv.service.advertisement;

import com.haikan.iptv.bean.advertisement.query.AdvertisementVoQuery;
import com.haikan.iptv.bean.advertisement.vo.AdvertisementVo;
import com.haikan.iptv.bean.role.query.RoleVoQuery;
import com.haikan.iptv.bean.role.vo.RoleVo;
import com.haikan.iptv.common.util.bean.PageVo;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.common.util.bean.ServiceResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AdvertisementServiceApi {
	
	//获取广告列表
	Result getAdvertisementPage(AdvertisementVoQuery query) ;
	//更具ID获取广告信息
	AdvertisementVo getAdvertisementById(AdvertisementVoQuery query) ;

	/**
	 * 保存广告
	 * @param advertisementVo
	 * @return
	 */
	Result saveAdvertisementVo(AdvertisementVo advertisementVo);

	/**
	 * 修改广告状态
	 * @param advertisementVo
	 * @return
	 */
	ServiceResult<AdvertisementVo> updateStatus(AdvertisementVo advertisementVo);
	/**
	 * 获得分类列表
	 * @param query
	 * @return
	 */
	List<AdvertisementVo> getVideoTypeListByQuery(AdvertisementVoQuery query);
	/**
	 * 修改排序
	 * @param list
	 * @return
	 */
	boolean modifyOrderNum(List<AdvertisementVo> list);



	boolean checkReleaseCount();
	boolean checkGoodsExist(AdvertisementVo advertisementVo);

}
