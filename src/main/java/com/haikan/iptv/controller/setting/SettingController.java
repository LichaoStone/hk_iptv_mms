/**
* @Title: SettingController.java
* @Package com.haikan.iptv.controller.setting
* @Description: TODO
* @author mayi
* @date 2020年2月18日
* @version V1.0
*/
package com.haikan.iptv.controller.setting;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haikan.iptv.bean.setting.vo.SettingVo;
import com.haikan.iptv.common.util.StringUtils;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.common.util.bean.ServiceResult;

import com.haikan.iptv.common.util.constant.HttpConstant;
import com.haikan.iptv.config.Exception.RedisConnectException;
import com.haikan.iptv.config.redis.RedisServiceImpl;
import com.haikan.iptv.service.Setting.SettingServiceApi;
import org.springframework.beans.factory.annotation.Autowired;


/**
* @ClassName: SettingController
* @Description: TODO
* @author mayi
* @date 2020年2月18日
*
*/
@Controller
@RequestMapping("/setting")
public class SettingController {

	@Autowired
	private RedisServiceImpl redisService;
	@Resource
	private SettingServiceApi settingServiceImpl ;
	/**
	 * 
	* @Title: getSetting
	* @Description: 获得设置信息
	* @param @param vo
	* @param @param request
	* @param @return    参数
	* @return JsonResult    返回类型
	* @throws
	* @author mayi
	* @date 2020年2月18日
	 */
	@RequestMapping("/get_setting")
	@ResponseBody
	public Result getSetting(SettingVo vo,HttpServletRequest request) {
		Result result = new Result();
		//判断传入名称
		if (StringUtils.isBlank(vo.getName())) {
			result.setCode(HttpConstant.SETTING_RET.SETTING_NAME_NULL);;
			result.setMsg(HttpConstant.SETTING_MESSAGES.SETTING_NAME_NULL_INFO);
			return result ;
		}
		//从redis中获得设置信息
		String content = "";
		try {
			content = redisService.get("setting:"+vo.getName());
		} catch (RedisConnectException e) {
			e.printStackTrace();
		}
		//信息不为空返回数据
		if (StringUtils.isNotBlank(content)) {
			vo.setContent(content);
			result.setData(vo);
			return result ;
		}
		ServiceResult<SettingVo> serviceResult = settingServiceImpl.getSettingByName(vo.getName());
		if (serviceResult.isOk() && serviceResult.getData() != null) {
			result.setData(serviceResult.getData());
			return result ;
		}
		result.setData(vo);
		return result ;
	}
	/**
	 * 
	* @Title: saveSetting
	* @Description: 保存设置信息
	* @param @param vo
	* @param @return    参数
	* @return JsonResult    返回类型
	* @throws
	* @author mayi
	* @date 2020年2月19日
	 */
	@RequestMapping("/save_setting")
	@ResponseBody
	public Result saveSetting(SettingVo vo) {
		Result result = new Result();
		//判断传入名称
		if (StringUtils.isBlank(vo.getName())) {
			result.setCode(HttpConstant.SETTING_RET.SETTING_NAME_NULL);;
			result.setMsg(HttpConstant.SETTING_MESSAGES.SETTING_NAME_NULL_INFO);
			return result ;
		}
		if (StringUtils.isBlank(vo.getContent())) {
			result.setCode(HttpConstant.SETTING_RET.SETTING_CONTENT_NULL);;
			result.setMsg(HttpConstant.SETTING_MESSAGES.SETTING_CONTENT_NULL_INFO);
			return result ;
		}
		vo.setCreator("51696adae5a44d65bc00b6602d6bb66a");
		ServiceResult<SettingVo> serviceResult = settingServiceImpl.saveSetting(vo);
		if (serviceResult.isOk()) {
			try {
				redisService.set("setting:"+vo.getName(), vo.getContent());
			} catch (RedisConnectException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
