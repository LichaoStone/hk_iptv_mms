/**
* @Title: SettingServiceImpl.java
* @Package com.haikan.iptv.service.Setting.impl
* @Description: TODO
* @author mayi
* @date 2020年2月18日
* @version V1.0
*/
package com.haikan.iptv.service.Setting.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haikan.iptv.bean.setting.query.SettingVoQuery;
import com.haikan.iptv.bean.setting.vo.SettingVo;
import com.haikan.iptv.common.util.PkCreat;
import com.haikan.iptv.common.util.bean.ServiceResult;
import com.haikan.iptv.mapper.setting.SettingMapper;
import com.haikan.iptv.service.Setting.SettingServiceApi;

/**
* @ClassName: SettingServiceImpl
* @Description: TODO
* @author mayi
* @date 2020年2月18日
*
*/
@Service
public class SettingServiceImpl implements SettingServiceApi {
	
	@Resource
	private SettingMapper settingMapper ;

	@Override
	@Transactional
	public ServiceResult<SettingVo> saveSetting(SettingVo vo) {
		ServiceResult<SettingVo> serviceResult = new ServiceResult<SettingVo>(true);
		int count  = 0 ;
		SettingVoQuery query = new SettingVoQuery();
		query.setName(vo.getName());
		List<SettingVo> list = settingMapper.getListByQuery(query);
		if (list != null && list.size() > 0) {
			vo.setSettingKey(list.get(0).getSettingKey());
			count = settingMapper.updateBySelective(vo);
		}else {
			vo.setSettingKey(PkCreat.getTablePk());
			vo.setCreateTime(new Date());
			vo.setUpdateTime(new Date());
			count = settingMapper.insert(vo);
		}
		if (count > 0) {
			serviceResult.setComment("保存成功");
			serviceResult.setData(vo);
			return serviceResult ;
		}
		serviceResult.setComment("保存失败");
		serviceResult.setData(vo);
		serviceResult.setOk(false);
		return serviceResult;
	}

	@Override
	public ServiceResult<SettingVo> getSettingByName(String name) {
		ServiceResult<SettingVo> serviceResult = new ServiceResult<SettingVo>(true);
		SettingVoQuery query = new SettingVoQuery();
		query.setName(name);
		List<SettingVo> list = settingMapper.getListByQuery(query);
		SettingVo vo = null ;
		if (list != null && list.size() > 0) {
			vo =  list.get(0);
		}
		serviceResult.setData(vo);
		return serviceResult;
	}

	
}
