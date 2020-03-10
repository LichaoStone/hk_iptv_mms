package com.haikan.iptv.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haikan.iptv.bean.platformUser.PlatformUser;
import com.haikan.iptv.bean.platformUser.vo.PlatformUserVo;
import com.haikan.iptv.common.util.DateTimeUtils;
import com.haikan.iptv.common.util.FebsUtil;
import com.haikan.iptv.common.util.IPUtil;
import com.haikan.iptv.common.util.StringUtils;
import com.haikan.iptv.common.util.bean.ActiveUser;
import com.haikan.iptv.common.util.bean.BaseResult;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.common.util.bean.ServiceResult;
import com.haikan.iptv.common.util.constant.Constants;
import com.haikan.iptv.common.util.constant.HttpConstant;
import com.haikan.iptv.common.util.constant.RedisKeyConstants;
import com.haikan.iptv.config.properties.IptvProperties;
import com.haikan.iptv.config.redis.RedisService;
import com.haikan.iptv.config.shiro.JWTToken;
import com.haikan.iptv.config.shiro.util.JWTUtil;
import com.haikan.iptv.service.platformUser.PlatformUserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController extends  WebBaseController{
	
	@Resource
	private PlatformUserServiceApi userServiceImpl ;
	@Autowired
	private RedisService redisService;
	@Autowired
	private IptvProperties properties;
	@Autowired
	private ObjectMapper mapper;

	
	@RequestMapping("/do_login")
	@ResponseBody
	public Result doLogin(String loginName, String password, HttpServletRequest request) {
		Result result = new Result();
		try {
			//检查用户名
			if (StringUtils.isBlank(loginName)) {
				result.setCode(HttpConstant.CODE.SUCCESS);
				result.setRet(HttpConstant.RET.LOGIN_NAME_NULL);
				result.setMsg(HttpConstant.MESSAGES.LOGIN_NAME_NULL_INFO);
				return result;
			}
			//检查密码
			if (StringUtils.isBlank(password)) {
				result.setCode(HttpConstant.CODE.SUCCESS);
				result.setRet(HttpConstant.RET.LOGIN_NAME_NULL);
				result.setMsg(HttpConstant.MESSAGES.LOGIN_NAME_NULL_INFO);
				return result;
			}
			//判定账户是否被锁定
			if (redisService.exists(Constants.USER.REDIS_PASSWORD_ERROR_KEY+loginName)) {
				if (Constants.USER.USER_LOGIN_TIMES <= Integer.parseInt(redisService.get(Constants.USER.REDIS_PASSWORD_ERROR_KEY+loginName).toString())) {
					result.setCode(HttpConstant.CODE.SUCCESS);
					result.setRet(HttpConstant.RET.LOGIN_USER_LOCK);
					result.setMsg(HttpConstant.MESSAGES.LOGIN_USER_LOCK_INFO);
					return result;
				}
			}
			PlatformUserVo userVo = new PlatformUserVo() ;
			userVo.setLoginName(loginName);
			userVo.setPassword(password);
			ServiceResult<PlatformUserVo> serviceResult = userServiceImpl.checkLoginUser(userVo);
			if (!serviceResult.isOk()) {
				//用户密码错误
				if (Constants.USER.PASSWORD_ERROR_CODE.equals(serviceResult.getMsgCode())) {
					if (redisService.exists(Constants.USER.REDIS_PASSWORD_ERROR_KEY+loginName)) {
						redisService.incrBy(Constants.USER.REDIS_PASSWORD_ERROR_KEY+loginName, 1);
					}else {
						redisService.set(Constants.USER.REDIS_PASSWORD_ERROR_KEY+loginName, "1",60*60*24L);
					}
				}
				result.setCode(HttpConstant.CODE.SUCCESS);
				result.setRet(HttpConstant.RET.LOGIN_INFO_ERROR);
				result.setMsg(HttpConstant.MESSAGES.LOGIN_INFO_ERROR_INFO);
				return result;
			}
			redisService.del(Constants.USER.REDIS_PASSWORD_ERROR_KEY+loginName);

			String token = FebsUtil.encryptToken(JWTUtil.sign(loginName, password));
			LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getShiro().getJwtTimeOut());
			String expireTimeStr = DateTimeUtils.formatFullTime(expireTime);
			JWTToken jwtToken = new JWTToken(token, expireTimeStr);

			String userId = this.saveTokenToRedis(serviceResult.getData(), jwtToken, request);
//			user.setId(userId);

			Map<String, Object> userInfo = this.generateUserInfo(jwtToken, serviceResult.getData());

			result.setCode(HttpConstant.CODE.SUCCESS);
			result.setRet(HttpConstant.RET.SUCCESS);
			result.setMsg(HttpConstant.MESSAGES.LOGIN_INFO_ERROR_INFO);
			return result;
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String saveTokenToRedis(PlatformUser user, JWTToken token, HttpServletRequest request) throws Exception {
		String ip = IPUtil.getIpAddr(request);

		// 构建在线用户
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUsername(user.getUserName());
		activeUser.setIp(ip);
		activeUser.setToken(token.getToken());
	//	activeUser.setLoginAddress(AddressUtil.getCityInfo(ip));

		// zset 存储登录用户，score 为过期时间戳
		this.redisService.zadd(RedisKeyConstants.ACTIVE_USERS_ZSET_PREFIX, Double.valueOf(token.getExipreAt()), mapper.writeValueAsString(activeUser));
		// redis 中存储这个加密 token，key = 前缀 + 加密 token + .ip
		this.redisService.set(RedisKeyConstants.TOKEN_CACHE_PREFIX + token.getToken() + "."+ ip, token.getToken(), properties.getShiro().getJwtTimeOut() * 1000);

		return activeUser.getId();
	}


	/**
	 * 生成前端需要的用户信息，包括：
	 * 1. token
	 * 2. Vue Router
	 * 3. 用户角色
	 * 4. 用户权限
	 * 5. 前端系统个性化配置信息
	 *
	 * @param token token
	 * @param user  用户信息
	 * @return UserInfo
	 */
	private Map<String, Object> generateUserInfo(JWTToken token, PlatformUser user) {
		String username = user.getUserName();
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("token", token.getToken());
		userInfo.put("exipreTime", token.getExipreAt());

//		Set<String> roles = this.userManager.getUserRoles(username);
//		userInfo.put("roles", roles);
//
//		Set<String> permissions = this.userManager.getUserPermissions(username);
//		userInfo.put("permissions", permissions);
//
//		UserConfig userConfig = this.userManager.getUserConfig(String.valueOf(user.getUserId()));
//		userInfo.put("config", userConfig);

		user.setPassword("it's a secret");
		userInfo.put("user", user);
		return userInfo;
	}

	
//	@RequestMapping("/getUserList")
//	@ResponseBody
//	public JsonResult getUserList(PlatformUserVoQuery query) {
//		JsonResult jsonResult = new JsonResult(true);
//		List<PlatformUserVo> list = userServiceImpl.getUserPage(query);
//		jsonResult.setOk(serviceResult.isOk());
//		jsonResult.setData(serviceResult.getData());
//		return jsonResult ;
//	}
//	@RequestMapping("/logout")
//	@ResponseBody
//	public JsonResult logout() {
//		shiroRedisUtils.logout(getLoginUser().getUserId());
//		return new JsonResult(true);
//	}
}