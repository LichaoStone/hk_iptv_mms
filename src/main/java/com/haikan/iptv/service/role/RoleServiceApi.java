package com.haikan.iptv.service.role;

import com.haikan.iptv.bean.permissions.Permission;
import com.haikan.iptv.bean.permissions.vo.RolePermissionRelationshipVo;
import com.haikan.iptv.bean.role.query.RoleVoQuery;
import com.haikan.iptv.bean.role.vo.RoleVo;
import com.haikan.iptv.common.util.bean.PageVo;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.common.util.bean.ServiceResult;

import java.util.List;

public interface RoleServiceApi {

	/**
	 * 分页获得角色列表
	 * @param query
	 * @return
	 */
	Result getRolePageByQuery(RoleVoQuery query);
	/**
	 * 获得角色列表
	 * @param query
	 * @return
	 */
	 List<RoleVo> getRoleListByQuery(RoleVoQuery query);
	/**
	 * 保存用户角色
	 * @param roleVo
	 * @return
	 */
	Result saveRole(RoleVo roleVo);
	/**
	 * 删除用户角色
	 * @param roleId
	 * @return
	 */
	Result deleteRoleById(Integer roleId);

	/**
	 * 保存角色权限
	 * @param roleVo
	 * @return
	 */
	Result saveRolePermission(RoleVo roleVo);


	/**
	 * 根据用户逐渐获得用户权限
	 * @param roleKey
	 * @return
	 */
	List<Permission> getPermissionBeanVosByRoleKey(Integer roleKey);

	/**
	 * 获取所有权限
	 * @return
	 */
	List<Permission> getPermissions();

	/**
	 * 获取角色对应的权限列表IDs
	 * @return
	 */
	List<String> getPermissionsIdByRoldId(Integer roleId);

	/**
	 * 判定拥有该角色的用户数
	 * @param query
	 * @return
	 */
	Result checkRoleUser(RoleVoQuery query);

	/**
	 * 更新角色状态
	 * @param query
	 * @return
	 */
	Result updateRoleStatus(RoleVo vo);

	/**
	 * 更新角色状态
	 * @param query
	 * @return
	 */
	Result getRoleByKey(RoleVo vo);

}
