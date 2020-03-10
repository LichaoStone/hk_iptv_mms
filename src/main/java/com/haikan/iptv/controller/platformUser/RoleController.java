package com.haikan.iptv.controller.platformUser;

import com.haikan.iptv.bean.permissions.Permission;
import com.haikan.iptv.bean.permissions.vo.RolePermissionRelationshipVo;
import com.haikan.iptv.bean.role.query.RoleVoQuery;
import com.haikan.iptv.bean.role.vo.RoleVo;
import com.haikan.iptv.common.util.bean.BaseResult;
import com.haikan.iptv.common.util.bean.JsonResult;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.common.util.bean.ServiceResult;
import com.haikan.iptv.common.util.constant.Constants;
import com.haikan.iptv.common.util.constant.HttpConstant;
import com.haikan.iptv.common.util.tree.Tree;
import com.haikan.iptv.common.util.tree.TreeUtil;
import com.haikan.iptv.controller.WebBaseController;
import com.haikan.iptv.service.role.RoleServiceApi;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/role")
public class RoleController extends WebBaseController {

	@Resource
	private RoleServiceApi roleServiceImpl ;

	/**
	*@Description: 获取角色列表
	*@Param: 
	*@return: 
	*@Author: liuzhaokun
	*@date: 2020/2/18/018
	*/
	@RequestMapping("/get_role_select_list")
	@ResponseBody
	public Result getRoleSelectList() {
		RoleVoQuery query = new RoleVoQuery();
		query.setStatus(Constants.ROLE.ROLE_ENABLE);
		List<RoleVo> dataList = roleServiceImpl.getRoleListByQuery(query);
		Map<String,Object> roleMap = new HashMap<String,Object>();
		List<Object> roleList = new ArrayList<Object>();
		for(RoleVo vo:dataList){
			roleMap = new HashMap<String,Object>();
			roleMap.put("value",vo.getRoleId());
			roleMap.put("label",vo.getRoleName());
			roleList.add(roleMap);
		}
		Result result =  new Result(roleList);
		return result ;
	}

	/**
	 *@Description: 获取角色分页列表
	 *@Param:
	 *@return:
	 *@Author: liuzhaokun
	 *@date: 2020/2/18/018
	 */
	@RequestMapping("/get_role_list")
	@ResponseBody
	public Result getRoleList(RoleVoQuery query) {
		Result result  = roleServiceImpl.getRolePageByQuery(query);
		return result ;
	}


	/**
	 * 保存角色
	 * @param roleVo
	 * @return
	 */
	@RequestMapping("/save_role")
	@ResponseBody
	public Result saveRole(RoleVo roleVo) {
		Result result = roleServiceImpl.saveRole(roleVo);
		return result ;
	}
	/**
	 * 更改角色状态
	 * @param roleVo
	 * @return
	 */
	@RequestMapping("/update_role_status")
	@ResponseBody
	public Result updateRoleStatus(RoleVo roleVo) {
		Result result = roleServiceImpl.updateRoleStatus(roleVo);
		return result ;
	}

	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/delete_role")
	@ResponseBody
	public Result deleteRole(Integer roleId) {
		Result result = roleServiceImpl.deleteRoleById(roleId);
		return result ;
	}

	/**
	 * 判定是否存在拥有该角色的用户
	 * @param query
	 * @return
	 */
	@RequestMapping("/check_role_user")
	@ResponseBody
	public Result checkRoleUser(RoleVoQuery query) {
		Result result = roleServiceImpl.checkRoleUser(query);
		return result ;
	}

	/**
	 * 获取角色信息
	 * @param vo
	 * @return
	 */
	@RequestMapping("/get_role_info")
	@ResponseBody
	public Result getRoleInfo(RoleVo vo) {
		Result result = roleServiceImpl.getRoleByKey(vo);
		return result ;
	}

	/**
	 * 用户权限
	 * @param
	 * @return
	 */
	@RequestMapping("/get_role_permission")
	@ResponseBody
	public Result getRolePermission(Integer roleId) {
		Result result = new Result();
		// 获取所有权限列表
		List<Permission>  permissionList = roleServiceImpl.getPermissionBeanVosByRoleKey(null);
		Map<String,Object> dataMap = new HashMap<>();
		List<Tree<Permission>> trees = new ArrayList<>();
		// 所有权限的ids列表
		List<String> ids = new ArrayList<>();
		buildTrees(trees, permissionList,ids);
		List<Tree<Permission>> roleTree = TreeUtil.getTreeList(trees);
		//数据结构数据源
		dataMap.put("roleTree",roleTree);
		// 树形结构所有的Ids
		dataMap.put("roleTreeIds",ids);
		// 该角色所拥有的权限
		List<String> roleIds = roleServiceImpl.getPermissionsIdByRoldId(roleId);
		//角色所拥有的权限
		dataMap.put("roleIds",roleIds);
		result.setData(dataMap);
		return result ;
	}

	@RequestMapping("/save_role_permission")
	@ResponseBody
	public Result saveUserPermission(RoleVo roleVo) {
		Result result = roleServiceImpl.saveRolePermission(roleVo);
		return result ;
	}

	private void buildTrees(List<Tree<Permission>> trees, List<Permission> permissions, List<String> ids) {
		permissions.forEach(permission -> {
			ids.add(permission.getPermissionKey().toString());
			Tree<Permission> tree = new Tree<>();
			tree.setId(permission.getPermissionKey().toString());
			tree.setKey(permission.getPermissionKey().toString());
			tree.setParentId(permission.getParentKey().toString());
			tree.setText(permission.getPermissionName());
			tree.setOrder(permission.getOrderNum());
			tree.setLabel(tree.getText());
			tree.setValue(tree.getId());
			trees.add(tree);
		});
	}





}
