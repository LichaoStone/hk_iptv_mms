package com.haikan.iptv.service.platformUser;

import com.haikan.iptv.bean.platformUser.PlatformUser;
import com.haikan.iptv.bean.platformUser.query.PlatformUserVoQuery;
import com.haikan.iptv.bean.platformUser.vo.PlatformUserVo;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.common.util.bean.ServiceResult;

import java.util.Map;

public interface PlatformUserServiceApi {


//
//	/**
//	 * 删除用户
//	 * @param userKey
//	 * @return
//	 */
//	ServiceResult<Boolean> deleteUserByUserId(String userKey);
	/**
	 *
	 * @Title: getUserByLoginName
	 * @Description: 根据登录名获得用户信息
	 * @param: @param loginName
	 * @param: @return
	 * @return: ServiceResult<UserVo>
	 * @throws
	 * @author: banbu
	 */
	ServiceResult<PlatformUserVo> getUserByLoginName(String loginName);
//
//	void updateUserBySelective(PlatformUserVo userVo);

	/**
	 * 查询用户
	 * @param query
	 * @return
	 */
	 Result getUserPageResult(PlatformUserVoQuery query);

	 /**
	 * 查询用户详情
	 * @param query
	 * @return
	 */
	Result getUserInfoResult(PlatformUserVoQuery query);

	/**
	 * 保存用户
	 * @return
	 */
	Result saveUserVo(PlatformUser user);

	/**
	 * 检查登录用户
	 * @param userVo
	 * @return
	 */
	ServiceResult<PlatformUserVo> checkLoginUser(PlatformUserVo userVo);

	/**
	 * 查询总的账号数
	 * @return
	 */
	Result  getUserCountByQuery(PlatformUserVoQuery query);
	/**
	 * 更新用户密码
	 * @param userKey
	 * @return
	 */
	Result updateUserPwd(String userKey, String loginName);
	/**
	 * 更新用户状态
	 * @param userVo
	 * @return
	 */
	Result updateUserStatus(PlatformUserVo userVo);

}
