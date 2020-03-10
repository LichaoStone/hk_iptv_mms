package com.haikan.iptv.mapper.advertisement;
import com.haikan.iptv.bean.advertisement.Advertisement;
import com.haikan.iptv.bean.advertisement.query.AdvertisementVoQuery;
import com.haikan.iptv.bean.advertisement.vo.AdvertisementVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertisementMapper{

	/**
	 * 插入对象
	 * @param bean
	 * @return
	 */
	public int insertAdvertisement(Advertisement bean);
	/**
	 * 编辑对象
	 * @param bean
	 * @return
	 */
	public int updateAdvertisement(Advertisement bean);
	/**
	 * 删除对象
	 * @param bean
	 * @return
	 */
	public int deleteAdvertisement(Advertisement bean);
	/**
	 * 只修改需要编辑的字段
	 * @param bean
	 * @return
	 */
	public int updateAdvertisementBySelective(Advertisement bean);
	/**
	 * 根据Id获得对象
	 * @param key
	 * @return
	 */
	public AdvertisementVo getAdvertisementVoById(String key);
	
	/**
	 * 获得对象总数
	 * @param query
	 * @return
	 */
	
	public long getAdvertisementCountByQuery(AdvertisementVoQuery query);
	/**
	 * 获得对象列表
	 * @param query
	 * @return
	 */
	public List<AdvertisementVo> getAdvertisementListByQuery(AdvertisementVoQuery query);
	public List<AdvertisementVo> getVideoTypeListByQuery(AdvertisementVoQuery query);

	/**
	 * 获得广告发布数量
	 * @return
	 */
	public int getAdvReleaseCount();
}