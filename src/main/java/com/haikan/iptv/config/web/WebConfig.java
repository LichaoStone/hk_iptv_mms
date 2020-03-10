/**
* @Title: WebConfig.java
* @Package com.haikan.iptv.config.web
* @Description: TODO
* @author mayi
* @date 2020年1月16日
* @version V1.0
*/
package com.haikan.iptv.config.web;

import com.google.common.collect.Maps;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.Map;

/**
* @ClassName: WebConfig
* @Description: 配置过滤器
* @author mayi
* @date 2020年1月16日
*
*/
@Configuration
public class WebConfig {
	/**
	 * xss过滤拦截器
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean xssFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = Maps.newHashMap();
		initParameters.put("excludes", "/favicon.ico,/images/*,/js/*,/css/*,/colorpicker/*,/druid,/frame/*,/fileinput/*");
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}
//	@Bean
//	public FilterRegistrationBean shiroFilterRegistration() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//		//该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
//		registration.addInitParameter("targetFilterLifecycle", "true");
//		registration.setEnabled(true);
//		registration.setOrder(Integer.MAX_VALUE - 1);
//		registration.addUrlPatterns("/*");
//		return registration;
//	}
}
