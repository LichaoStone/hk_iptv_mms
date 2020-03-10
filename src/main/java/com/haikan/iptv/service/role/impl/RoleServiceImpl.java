package com.haikan.iptv.service.role.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haikan.iptv.bean.permissions.Permission;
import com.haikan.iptv.bean.permissions.query.PermissionVoQuery;
import com.haikan.iptv.bean.permissions.vo.RolePermissionRelationshipVo;
import com.haikan.iptv.bean.platformUser.query.PlatformUserVoQuery;
import com.haikan.iptv.bean.role.Role;
import com.haikan.iptv.bean.role.query.RoleVoQuery;
import com.haikan.iptv.bean.role.vo.RoleVo;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.common.util.bean.ServiceResult;
import com.haikan.iptv.common.util.constant.Constants;
import com.haikan.iptv.common.util.constant.HttpConstant;
import com.haikan.iptv.mapper.permissions.PermissionMapper;
import com.haikan.iptv.mapper.permissions.RolePermissionRelationshipMapper;
import com.haikan.iptv.mapper.platformUser.PlatformUserMapper;
import com.haikan.iptv.mapper.role.RoleMapper;
import com.haikan.iptv.service.role.RoleServiceApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleServiceApi {
	
	@Resource
	private RoleMapper roleMapper ;
	@Resource
	private PlatformUserMapper platformUserMapper ;
	@Resource
	private PermissionMapper permissionMapper ;
	@Resource
	private RolePermissionRelationshipMapper rolePermissionRelationshipMapper;
	
	@Override
	public Result getRolePageByQuery(RoleVoQuery query) {
		PageHelper.startPage(query.getOffset(), query.getPageSize());
		List<RoleVo> list = roleMapper.getRoleListByQuery(query);
		PageInfo<RoleVo> pageInfo = new PageInfo<>(list);
		Result result = new Result(pageInfo,list) ;
		return result;
	}

	@Override
	public List<RoleVo> getRoleListByQuery(RoleVoQuery query) {
		List<RoleVo> list = roleMapper.getRoleListByQuery(query);
		if (list == null) {
			list = new ArrayList<RoleVo>();
		}
		return list;
	}

	@Override
	@Transactional
	public Result saveRole(RoleVo roleVo) {
		Result result = new Result();
		List<RoleVo> list = roleMapper.checkRoleName(roleVo);
		if (list != null && list.size() > 0) {
			result.setRet(HttpConstant.ROLE_RET.ADD_DUPLICATE_ROLE_NAME);
			result.setMsg(HttpConstant.ROLE_MESSAGES.ADD_DUPLICATE_ROLE_NAME_INFO);
			return result ;
		}
		int count = 0 ;
		// 新增或者修改标志  flag:true 新增 flag:flase 修改
//		boolean flag=false;
		if (roleVo.getRoleId() == null) {
//			flag =true;
			roleVo.setStatus(Constants.ROLE.ROLE_ENABLE);
			count = roleMapper.insertRole(roleVo);
			result.setMsg(HttpConstant.ROLE_MESSAGES.ADD_ROLE_SUCCESS_INFO);
			return  result;
		}else {
			count = roleMapper.updateRoleBySelective(roleVo);
			result.setMsg(HttpConstant.ROLE_MESSAGES.MODIFY_ROLE_SUCCESS_INFO);
			return  result;
		}
//		if (flag){
//			result.setRet(HttpConstant.ROLE_RET.ADD_ROLE_ERROR);;
//			result.setMsg(HttpConstant.ROLE_MESSAGES.ADD_ROLE_ERROR_INFO);
//		}else{
//			result.setRet(HttpConstant.ROLE_RET.MODIFY_ROLE_ERROR);
//			result.setMsg(HttpConstant.ROLE_MESSAGES.MODIFY_ROLE_ERROR_INFO);
//		}
//
//		return result;
	}

	@Override
	public Result checkRoleUser(RoleVoQuery query) {
		Result result = new Result();
		PlatformUserVoQuery userVoQuery = new PlatformUserVoQuery();
		userVoQuery.setRoleId(query.getRoleId());
		long count = platformUserMapper.getUserCountByQuery(userVoQuery);
		if (count != 0) {
			result.setRet(HttpConstant.ROLE_RET.USER_ROLE_EXITS);
			result.setMsg(HttpConstant.ROLE_MESSAGES.USER_ROLE_EXITS_INFO);
		}
		return result;
	}

	@Override
	@Transactional
	public Result deleteRoleById(Integer roleId) {
		Result result = new Result();
		Role role = new Role();
		role.setRoleId(roleId);
		rolePermissionRelationshipMapper.deletePermissionByRoleId(roleId);
		int count = roleMapper.deleteRole(role);
		if (count > 0) {
			result.setMsg(HttpConstant.ROLE_MESSAGES.DELETE_ROLE_SUCCESS_INFO);
			return result ;
		}
		result.setRet(HttpConstant.ROLE_RET.DELETE_ROLE_ERROR);
		result.setMsg(HttpConstant.ROLE_MESSAGES.DELETE_ROLE_ERROR_INFO);
		return result;
	}

	@Override
	public Result updateRoleStatus(RoleVo vo) {
		int cnt = roleMapper.updateRoleStatus(vo);
		Result result = new Result() ;
		// 用户禁用成功
		if(Constants.ROLE.ROLE_FORBIDDEN == vo.getStatus()){
			result.setMsg(HttpConstant.ROLE_MESSAGES.USER_STATUS_UNENABLE_SUCCESS_INFO);
		}else if(Constants.USER.STATUS_ENABLE == vo.getStatus()){
			// 用户启用成功
			result.setMsg(HttpConstant.ROLE_MESSAGES.USER_STATUS_ENABLE_SUCCESS_INFO);
		}
		return result;
	}

	@Override
	public Result getRoleByKey(RoleVo vo) {
		Role role = roleMapper.getRoleVoById(String.valueOf(vo.getRoleId()));
		Result result = new Result() ;
		result.setData(role);
		return result;
	}

	@Override
	@Transactional
	public Result saveRolePermission(RoleVo roleVo) {
		Result result = new Result();
		String [] keys = roleVo.getPermissions();
		RolePermissionRelationshipVo vo = null ;
		List<RolePermissionRelationshipVo> list = new ArrayList<RolePermissionRelationshipVo>();
		if (keys.length > 0) {
			for (String key : keys) {
				if (StringUtils.isNotBlank(key)) {
					vo = new RolePermissionRelationshipVo();
					vo.setCreateTime(new Date());
					//TODO  获取用户 根据token
					//vo.setCreator(userName);
					vo.setPermissionKey(key);
					vo.setRoleKey(roleVo.getRoleId());
					list.add(vo);
				}
			}
		}
		rolePermissionRelationshipMapper.deleteRelationshipByRoleKey(roleVo.getRoleId());
		int count  = rolePermissionRelationshipMapper.batchInsert(list);
		result.setMsg(HttpConstant.ROLE_MESSAGES.ADD_ROLE_PERMISSIONS_SUCCESS_INFO);
		return result;
	}

	@Override
	public List<Permission> getPermissionBeanVosByRoleKey(Integer roleKey) {
		PermissionVoQuery query = new PermissionVoQuery();
		query.setRoleKey(roleKey);
		List<Permission> permissionlist = permissionMapper.getListByQuery(query);
		if (permissionlist == null) {
			permissionlist = new ArrayList<Permission>(0);
		}
		return permissionlist;
	}

	@Override
	public List<Permission> getPermissions() {
		PermissionVoQuery query = new PermissionVoQuery();
		List<Permission> permissionlist = permissionMapper.getListByQuery(query);
		if (permissionlist == null) {
			permissionlist = new ArrayList<Permission>(0);
		}
		return permissionlist;
	}

	@Override
	public List<String> getPermissionsIdByRoldId(Integer roleId) {
		PermissionVoQuery query = new PermissionVoQuery();
		query.setRoleKey(roleId);
		List<String> permissionlist = permissionMapper.getRolePerssionIdListByRoleId(query);
		if (permissionlist == null) {
			permissionlist = new ArrayList<String>(0);
		}
		return permissionlist;
	}

}
