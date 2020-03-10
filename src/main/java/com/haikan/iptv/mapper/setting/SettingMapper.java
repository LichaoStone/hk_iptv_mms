package com.haikan.iptv.mapper.setting;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.haikan.iptv.bean.setting.Setting;
import com.haikan.iptv.bean.setting.vo.SettingVo;
import com.haikan.iptv.bean.setting.query.SettingVoQuery;
@Mapper
public interface SettingMapper{

	/**
	 * 插入对象
	 * @param bean
	 * @return
	 */
	public int insert(Setting bean);
	/**
	 * 编辑对象
	 * @param bean
	 * @return
	 */
	public int update(Setting bean);
	/**
	 * 删除对象
	 * @param bean
	 * @return
	 */
	public int delete(Setting bean);
	/**
	 * 只修改需要编辑的字段
	 * @param bean
	 * @return
	 */
	public int updateBySelective(Setting bean);
	/**
	 * 根据Id获得对象
	 * @param key
	 * @return
	 */
	public SettingVo getSettingVoById(String key);
	
	/**
	 * 获得对象总数
	 * @param query
	 * @return
	 */
	
	public long getCountByQuery(SettingVoQuery query);
	/**
	 * 获得对象列表
	 * @param query
	 * @return
	 */
	public List<SettingVo> getListByQuery(SettingVoQuery query);
}