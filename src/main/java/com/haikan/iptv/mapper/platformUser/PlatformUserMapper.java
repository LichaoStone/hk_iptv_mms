package com.haikan.iptv.mapper.platformUser;

import com.haikan.iptv.bean.platformUser.PlatformUser;
import com.haikan.iptv.bean.platformUser.query.PlatformUserVoQuery;
import com.haikan.iptv.bean.platformUser.vo.PlatformUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlatformUserMapper{

	/**
	 * 插入对象
	 * @return
	 */
	public int insertUser(PlatformUser bean);
	/**
	 * 编辑对象
	 * @param bean
	 * @return
	 */
	public int updateUser(PlatformUser bean);
	/**
	 * 删除对象
	 * @param bean
	 * @return
	 */
	public int deleteUser(PlatformUser bean);
	/**
	 * 只修改需要编辑的字段
	 * @param bean
	 * @return
	 */
	public int updateUserBySelective(PlatformUser bean);
	/**
	 * 根据Id获得对象
	 * @param key
	 * @return
	 */
	public PlatformUserVo getUserVoById(String key);

	/**
	 * 根据Id获得对象
	 * @param key
	 * @return
	 */
	public PlatformUserVo getUserVoByKey(String userKey);
	
	/**
	 * 获得对象总数
	 * @param query
	 * @return
	 */
	
	public long getUserCountByQuery(PlatformUserVoQuery query);
	/**
	 * 获得对象列表
	 * @param query
	 * @return
	 */
	public List<PlatformUserVo> getUserListByQuery(PlatformUserVoQuery query);

	/**
	 * 检查用户登录名
	 * @param user
	 * @return
	 */
	public List<PlatformUserVo> checkUserLoginName(PlatformUser user);
	/**
	 * 更新用户状态
	 * @param bean
	 * @return
			 */
	public int updateUserStatus(PlatformUserVo bean);
}