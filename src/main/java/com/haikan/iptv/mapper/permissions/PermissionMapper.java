package com.haikan.iptv.mapper.permissions;

import com.haikan.iptv.bean.permissions.Permission;
import com.haikan.iptv.bean.permissions.query.PermissionVoQuery;
import com.haikan.iptv.bean.permissions.vo.PermissionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper{

	/**
	 * 插入对象
	 * @param bean
	 * @return
	 */
	public int insertPermission(Permission bean);
	/**
	 * 编辑对象
	 * @param bean
	 * @return
	 */
	public int updatePermission(Permission bean);
	/**
	 * 删除对象
	 * @param bean
	 * @return
	 */
	public int deletePermission(Permission bean);
	/**
	 * 只修改需要编辑的字段
	 * @param bean
	 * @return
	 */
	public int updatePermissionBySelective(Permission bean);
	/**
	 * 根据Id获得对象
	 * @param key
	 * @return
	 */
	public PermissionVo getPermissionVoById(String key);
	
	/**
	 * 获得对象总数
	 * @param query
	 * @return
	 */
	
	public long getPermissionCountByQuery(PermissionVoQuery query);
	/**
	 * 获得对象列表
	 * @param query
	 * @return
	 */
	public List<PermissionVo> getPermissionListByQuery(PermissionVoQuery query);
	/**
	 * 根据角色Id删除角色权限
	 * @param roleId
	 * @return
	 */
	public int deletePermissionByRoleId(@Param("roleId") Integer roleId);
	/**
	 * 批量插入角色权限
	 * @param list
	 * @return
	 */
	public int batchInsertPermission(@Param("list") List<Permission> list);
	
	/**
	 * 根据查询条件获得权限
	 * @param query
	 * @return
	 */
	public List<Permission> getListByQuery(PermissionVoQuery query);
	/**
	 * 根据查询条件获得角色所拥有的权限
	 * @param query
	 * @return
	 */
	public List<String> getRolePerssionIdListByRoleId(PermissionVoQuery query);
}