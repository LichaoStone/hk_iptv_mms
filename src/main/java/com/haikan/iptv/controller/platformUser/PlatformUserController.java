package com.haikan.iptv.controller.platformUser;

import com.haikan.iptv.bean.platformUser.PlatformUser;
import com.haikan.iptv.bean.platformUser.query.PlatformUserVoQuery;
import com.haikan.iptv.bean.platformUser.vo.PlatformUserVo;
import com.haikan.iptv.common.util.bean.BaseResult;
import com.haikan.iptv.common.util.bean.JsonResult;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.common.util.bean.ServiceResult;
import com.haikan.iptv.common.util.constant.Constants;
import com.haikan.iptv.controller.WebBaseController;
import com.haikan.iptv.service.platformUser.PlatformUserServiceApi;
import com.haikan.iptv.service.role.RoleServiceApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/platform_user")
public class PlatformUserController extends WebBaseController {

	@Resource
	private PlatformUserServiceApi userServiceImpl ;
//	@Resource
//	private PlatformServiceApi platformServiceImpl;
	@Resource
	private RoleServiceApi roleServiceImpl;


	/**分页展示用户列表
	 *
	 * @return
	 */
	@RequestMapping(value="/get_user_list")
	@ResponseBody
	public Result getUserList(PlatformUserVoQuery query) {
			Result result = userServiceImpl.getUserPageResult(query);
			return result ;
	}

	/**获取用户信息
	 *
	 * @return
	 */
	@RequestMapping(value="/get_user_info")
	@ResponseBody
	public Result getUserInfo(PlatformUserVoQuery query) {
		Result result = userServiceImpl.getUserInfoResult(query);
		return result ;
	}

	@RequestMapping("/get_user_total")
	@ResponseBody
	public Result getUserTotal(PlatformUserVoQuery vo) {
		Result result = userServiceImpl.getUserCountByQuery(vo);
		return result ;
	}


	/**
	 *@Description: add_user
	 *@Param:
	 *@return:  保存用户信息
	 *@Author: liuzhaokun
	 *@date: 2020/2/19/019
	 */
	@PostMapping("/add_user")
	@ResponseBody
	public Result addUser(PlatformUser vo) {
		Result result = userServiceImpl.saveUserVo(vo);
		return result ;
	}

	/**
	 *
	 * @param userKey
	 * @param loginName
	 * @return
	 */
	@PostMapping("/updatePwd")
	@ResponseBody
	public Result updatePwd(String userKey, String loginName) {
		Result result = userServiceImpl.updateUserPwd(userKey, loginName);
		return result;
	}

	/**
	 * 更新用户状态
	 * @param userVo
	 * @return
	 */
	@PostMapping("/update_user_status")
	@ResponseBody
	public Result updateUserStatus(PlatformUserVo userVo) {
		Result result = userServiceImpl.updateUserStatus(userVo);
		return result ;
	}


//	/**
//	 * 跳转用户添加编辑页面
//	 * @param mav
//	 * @return
//	 */
//	@RequestMapping("/show_save_user")
//	public ModelAndView showSaveUser(ModelAndView mav, String loginName) {
////		ServiceResult<List<PlatformVo>> platformResult = platformServiceImpl.fetchPlatformList(new PlatformVoQuery());
////		if(platformResult.isOk()) {
////			List<PlatformVo> pvList = platformResult.getData();
////			mav.addObject("platformList", pvList);
////		}
//		RoleVoQuery roleVoQuery = new RoleVoQuery();
//		roleVoQuery.setStatus(Constants.ROLE.ROLE_ENABLE);
//		ServiceResult<List<RoleVo>> serviceResult = roleServiceImpl.getRoleListByQuery(roleVoQuery);
//		if(serviceResult.isOk()) {
//			List<RoleVo> roleList = serviceResult.getData();
//			mav.addObject("roleList", roleList);
//		}
//		if(StringUtils.isNotEmpty(loginName)) {
//			ServiceResult<PlatformUserVo> userBeanResult = userServiceImpl.getUserByLoginName(loginName);
//			PlatformUserVo userVo = userBeanResult.getData();
//			mav.addObject("userVo", userVo);
//		}
//		mav.setViewName("/user/showSaveUser");
//		return mav ;
//	}



//	/**
//	 * 保存用户数据
//	 * @param userVo
//	 * @return
//	 */
//	@PostMapping("/save_user")
//	@ResponseBody
//	public JsonResult saveUser(PlatformUserVo userVo) {
//		JsonResult jsonResult = new JsonResult() ;
//		ServiceResult<PlatformUserVo> serviceResult = userServiceImpl.saveUserVo(userVo);
//		jsonResult.setData(serviceResult.getData());
//		jsonResult.setOk(serviceResult.isOk());
//		jsonResult.setComment(serviceResult.getComment());
//		return jsonResult ;
//	}



//	/**
//	 * 删除用户数据
//	 * @param userKey
//	 * @return
//	 */
//	@RequestMapping("/delete_user")
//	@ResponseBody
//	public JsonResult deleteUser(String userKey) {
//		JsonResult jsonResult = new JsonResult() ;
//		ServiceResult<Boolean> serviceResult = userServiceImpl.deleteUserByUserId(userKey);
//		jsonResult.setData(serviceResult.getData());
//		jsonResult.setOk(serviceResult.isOk());
//		jsonResult.setComment(serviceResult.getComment());
//		return jsonResult ;
//	}
//

//
//	/**
//	 *
//	 * 延长seesion中的登录过期时间，空方法
//	 * @author pengyu
//	 * @since 1.0.0
//	 */
//	@RequestMapping("/check_login_user")
//	public void check_login_user() {
//
//	}
//
//	@PostMapping("/modifyPwd")
//	@ResponseBody
//	public JsonResult modifyPwd(String oldPwd, String newPwd) {
//		JsonResult jsonResult = new JsonResult() ;
//		try {
//			String loginName = SecurityUtils.getSubject().getPrincipal().toString();
//			PlatformUserVo userVo = userServiceImpl.getUserByLoginName(loginName).getData();
//
//			if (userVo == null) {
//				jsonResult.setMessage("系统不存在当前用户");
//				jsonResult.setOk(false);
//				return jsonResult;
//			}
//			if (!userVo.getPassword().equals(MD5Util.MD5(oldPwd+ Constants.USER.PASSWORD_ADD_WORD))) {
//				jsonResult.setMessage("旧密码错误！");
//				jsonResult.setOk(false);
//				return jsonResult;
//			}
//
//			PlatformUserVo userVo1 = new PlatformUserVo();
//			userVo1.setUserKey(userVo.getUserKey());
//			userVo1.setPassword(MD5Util.MD5(newPwd+ Constants.USER.PASSWORD_ADD_WORD));
//
//			userServiceImpl.updateUserBySelective(userVo1);
//			jsonResult.setOk(true);
//			jsonResult.setMessage("密码修改成功！");
//			return jsonResult;
//		}catch (Exception e){
//			e.printStackTrace();
//			jsonResult.setOk(false);
//			jsonResult.setMessage("密码修改失败！");
//			return jsonResult;
//		}
//	}
}
