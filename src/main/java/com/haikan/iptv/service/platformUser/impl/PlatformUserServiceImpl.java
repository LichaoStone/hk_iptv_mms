package com.haikan.iptv.service.platformUser.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haikan.iptv.bean.platformUser.PlatformUser;
import com.haikan.iptv.bean.platformUser.query.PlatformUserVoQuery;
import com.haikan.iptv.bean.platformUser.vo.PlatformUserVo;
import com.haikan.iptv.bean.role.query.RoleVoQuery;
import com.haikan.iptv.common.util.DateUtil;
import com.haikan.iptv.common.util.MD5Util;
import com.haikan.iptv.common.util.PkCreat;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.common.util.bean.ServiceResult;
import com.haikan.iptv.common.util.constant.Constants;
import com.haikan.iptv.common.util.constant.HttpConstant;
import com.haikan.iptv.mapper.permissions.PermissionMapper;
import com.haikan.iptv.mapper.platformUser.PlatformUserMapper;
import com.haikan.iptv.service.platformUser.PlatformUserServiceApi;
import com.haikan.iptv.service.role.RoleServiceApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PlatformUserServiceImpl implements PlatformUserServiceApi {
	
	@Resource
	private PlatformUserMapper userMapper ;
	@Resource
	private PermissionMapper permissionMapper ;
	@Resource
	private RoleServiceApi roleServiceImpl;


	@Override
	public Result getUserPageResult(PlatformUserVoQuery query) {
		PageHelper.startPage(query.getOffset(), query.getPageSize());
		List<PlatformUserVo> list = userMapper.getUserListByQuery(query);
		PageInfo<PlatformUserVo> pageInfo = new PageInfo<>(list);
		Result result = new Result(pageInfo,list) ;
		return result;
	}

	@Override
	public Result getUserInfoResult(PlatformUserVoQuery query) {
		PlatformUserVo vo = userMapper.getUserVoByKey(query.getUserKey());
		Result result = new Result(vo) ;
		return result;
	}

	@Override
	@Transactional
	public Result saveUserVo(PlatformUser user) {

		List<PlatformUserVo> list = userMapper.checkUserLoginName(user);
		if (list != null && list.size() >0) {
			Result result = new Result(
					HttpConstant.CODE.SUCCESS,
					HttpConstant.RET.ADD_DUPLICATE_NAME,
					HttpConstant.MESSAGES.ADD_DUPLICATE_NAME_INFO,
					user);
			return result;
		}
		int count = 0 ;
		String pw = PkCreat.createUserPass();
		String userKey = PkCreat.getTablePk();
		//新增用户
		if (user.getUserKey()==null || StringUtils.isEmpty(user.getUserKey())){
			user.setUserKey(userKey);
			//新建默认是启用状态
			user.setStatus(Constants.USER.STATUS_ENABLE);
			//创建时间
			user.setCreateTime(new Date());
			//密码
			user.setPassword(MD5Util.MD5(pw+Constants.USER.PASSWORD_ADD_WORD));
			count = userMapper.insertUser(user);
		}else {
			count = userMapper.updateUserBySelective(user);
		}
		if (count > 0) {
			Result result = new Result(
					HttpConstant.CODE.SUCCESS,
					HttpConstant.RET.ADD_USER_SUCCESS,
					HttpConstant.MESSAGES.ADD_USER_SUCCESS_INFO,
					user);
			return result;
		}
		//操作失败
		Result result = new Result(
				HttpConstant.CODE.SUCCESS,
				HttpConstant.RET.ADD_USER_ERROR,
				HttpConstant.MESSAGES.ADD_USER_ERROR_INFO,
				user);
		return result;
	}

	@Override
	public ServiceResult<PlatformUserVo> checkLoginUser(PlatformUserVo userVo) {
		ServiceResult<PlatformUserVo> serviceResult = new ServiceResult<PlatformUserVo>(false);
		PlatformUserVoQuery query = new PlatformUserVoQuery();
		query.setLoginName(userVo.getLoginName());
		List<PlatformUserVo> list = userMapper.getUserListByQuery(query);
		if (list == null || list.isEmpty()) {
			serviceResult.setComment("系统不存在当前用户");
			serviceResult.setOk(false);
			serviceResult.setMsgCode("4003");
			return serviceResult ;
		}
		PlatformUserVo loginUser = list.get(0);
		if (loginUser.getStatus() == Constants.USER.STATUS_FORBIDDEN) {
			serviceResult.setComment("当前用户被禁用");
			serviceResult.setMsgCode("4004");
			serviceResult.setOk(false);
			return serviceResult ;
		}
		if (!MD5Util.MD5(userVo.getPassword()+Constants.USER.PASSWORD_ADD_WORD).equals(loginUser.getPassword())) {
			serviceResult.setComment("用户密码不正确");
			serviceResult.setMsgCode("4005");
			serviceResult.setOk(false);
			return serviceResult ;
		}

		RoleVoQuery roleQuery = new RoleVoQuery();
		roleQuery.setRoleId(loginUser.getRoleId());
		//TODO
//		ServiceResult<List<RoleVo>> roleQ = roleServiceImpl.getRoleListByQuery(roleQuery);
//		RoleVo role = roleQ.getData().get(0);
//		if(role.getStatus() == Constants.USER.STATUS_FORBIDDEN) {
//			serviceResult.setComment("当前用户所在角色被禁用");
//			serviceResult.setMsgCode("4006");
//			serviceResult.setOk(false);
//			return serviceResult ;
//		}
		serviceResult.setOk(true);
		serviceResult.setData(loginUser);
		return serviceResult;

	}

	@Override
	public Result getUserCountByQuery(PlatformUserVoQuery query) {
		query.setStatus(Constants.USER.STATUS_ENABLE);
		Result result = new Result();
		long cnt = userMapper.getUserCountByQuery(query);
		// 最多添加100个有效账号
		if((int)cnt >=100){
			result.setRet(HttpConstant.RET.ADD_USER_TOTAL_ERROR);
			result.setMsg(HttpConstant.MESSAGES.ADD_USER_TOTAL_ERROR_INFO);
		}
		return result;
	}

	//
//
//	@Override
//	@Transactional
//	public ServiceResult<Boolean> deleteUserByUserId(String userKey) {
//		ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>(false);
//		PlatformUser user = new PlatformUser();
//		user.setUserKey(userKey);
//		int count = userMapper.deleteUser(user);
//		if (count > 0) {
//			serviceResult.setOk(true);
//			serviceResult.setData(true);
//			serviceResult.setComment("删除成功");
//			return serviceResult ;
//		}
//		serviceResult.setData(false);
//		serviceResult.setComment("删除失败");
//		return serviceResult ;
//	}
//
	@Override
	public ServiceResult<PlatformUserVo> getUserByLoginName(String loginName) {
		ServiceResult<PlatformUserVo> serviceResult = new ServiceResult<PlatformUserVo>(false);
		PlatformUserVoQuery query = new PlatformUserVoQuery() ;
		query.setLoginName(loginName);
		List<PlatformUserVo> list = userMapper.getUserListByQuery(query);
		if (list.size() > 0) {
			serviceResult.setData(list.get(0));
			serviceResult.setOk(true);
			return serviceResult  ;
		}
		serviceResult.setComment("没有获得用户登录信息");
		return serviceResult;
	}

	@Override
	public Result updateUserPwd(String userKey, String loginName) {
		Result result = new Result();
		PlatformUserVoQuery query = new PlatformUserVoQuery();
		query.setLoginName(loginName);
		List<PlatformUserVo> list = userMapper.getUserListByQuery(query);
		if (list == null || list.isEmpty()) {
			result.setRet(HttpConstant.RET.USER_NOT_EXITS);
			result.setMsg(HttpConstant.MESSAGES.USER_NOT_EXITS_INFO);
			return result ;
		}
		String pw = PkCreat.createUserPass();
		query.setPassword(MD5Util.MD5(pw+Constants.USER.PASSWORD_ADD_WORD));
		query.setUserKey(userKey);
		int count = userMapper.updateUserBySelective(query);
		if(count > 0) {
			result.setRet(HttpConstant.RET.SUCCESS);
			result.setMsg(HttpConstant.MESSAGES.USER_PWD_UPDATE_SUCCESS_INFO);
			result.setData(query);
			return result;
		}
		result.setRet(HttpConstant.RET.USER_PWD_UPDATE_ERROR);
		result.setMsg(HttpConstant.MESSAGES.USER_PWD_UPDATE_ERROR_INFO);
		return result;
	}

	@Override
	public Result updateUserStatus(PlatformUserVo userVo) {
		int cnt = userMapper.updateUserStatus(userVo);
		Result result = new Result() ;
		if(cnt > 0) {
			result.setRet(HttpConstant.RET.SUCCESS);
			// 用户禁用成功
			if(Constants.USER.STATUS_FORBIDDEN == userVo.getStatus()){
				result.setMsg(HttpConstant.MESSAGES.USER_STATUS_UNENABLE_SUCCESS_INFO);
			}else if(Constants.USER.STATUS_ENABLE == userVo.getStatus()){
				// 用户启用成功
				result.setMsg(HttpConstant.MESSAGES.USER_STATUS_ENABLE_SUCCESS_INFO);
			}
			return result;
		}
		result.setRet(HttpConstant.RET.USER_STATUS_UPDATE_ERROR);
		result.setMsg(HttpConstant.MESSAGES.USER_STATUS_UPDATE_ERROR_INFO);
		return result;
	}
//
//	@Override
//	public void updateUserBySelective(PlatformUserVo userVo) {
//		userMapper.updateUserBySelective(userVo);
//	}
}
