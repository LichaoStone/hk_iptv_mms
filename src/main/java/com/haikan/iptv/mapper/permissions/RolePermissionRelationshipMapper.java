package com.haikan.iptv.mapper.permissions;

import com.haikan.iptv.bean.permissions.RolePermissionRelationship;
import com.haikan.iptv.bean.permissions.query.RolePermissionRelationshipVoQuery;
import com.haikan.iptv.bean.permissions.vo.RolePermissionRelationshipVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionRelationshipMapper{

	/**
	 * 插入对象
	 * @param bean
	 * @return
	 */
	public int insertRolePermissionRelationship(RolePermissionRelationship bean);
	/**
	 * 编辑对象
	 * @param bean
	 * @return
	 */
	public int updateRolePermissionRelationship(RolePermissionRelationship bean);
	/**
	 * 删除对象
	 * @param bean
	 * @return
	 */
	public int deleteRolePermissionRelationship(RolePermissionRelationship bean);

	/**
	 *
	 * @param roleKey
	 * @return
	 */
	public int deletePermissionByRoleId(Integer roleKey);
	/**
	 * 只修改需要编辑的字段
	 * @param bean
	 * @return
	 */
	public int updateRolePermissionRelationshipBySelective(RolePermissionRelationship bean);
	/**
	 * 根据Id获得对象
	 * @param key
	 * @return
	 */
	public RolePermissionRelationshipVo getRolePermissionRelationshipVoById(String key);
	
	/**
	 * 获得对象总数
	 * @param query
	 * @return
	 */
	
	public long getRolePermissionRelationshipCountByQuery(RolePermissionRelationshipVoQuery query);
	/**
	 * 获得对象列表
	 * @param query
	 * @return
	 */
	public List<RolePermissionRelationshipVo> getRolePermissionRelationshipListByQuery(RolePermissionRelationshipVoQuery query);
	/**
	 * 
	 * @Title: deleteRelationshipByRoleKey   
	 * @Description: 根据角色Key删除角色权限  
	 * @param: @param roleKey
	 * @param: @return      
	 * @return: int      
	 * @throws 
	 * @author: banbu
	 */
	public int deleteRelationshipByRoleKey(Integer roleKey);
	/**
	 * 
	 * @Title: batchInsert   
	 * @Description: 批量插入权限  
	 * @param: @param relationshipVos
	 * @param: @return      
	 * @return: int      
	 * @throws 
	 * @author: banbu
	 */
	public int batchInsert(@Param("relationshipList") List<RolePermissionRelationshipVo> relationshipList);
}