package com.haikan.iptv.controller;

import com.haikan.iptv.bean.platformUser.vo.PlatformUserVo;
import com.haikan.iptv.config.kafka.KafkaProductor;
import com.haikan.iptv.service.platformUser.PlatformUserServiceApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/common")
public class WebBaseController {

	public static Logger logger = LoggerFactory.getLogger(WebBaseController.class);

	@Resource
	public PlatformUserServiceApi userServiceImpl;
    @Resource
    public KafkaProductor kafkaProductor;


	public PlatformUserVo getLoginUser() {
		PlatformUserVo vo = null;
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null
				&& currentUser.getPrincipal() != null
				&& currentUser.getSession() != null) {
			String loginName = currentUser.getPrincipal().toString();
			vo = userServiceImpl.getUserByLoginName(loginName).getData();
		}
		return vo;
	}

}
