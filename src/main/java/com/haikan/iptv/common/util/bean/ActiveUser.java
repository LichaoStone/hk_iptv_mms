package com.haikan.iptv.common.util.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.haikan.iptv.common.util.DateTimeUtils;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 在线用户
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActiveUser implements Serializable {
    private static final long serialVersionUID = 2055229953429884344L;

    // 唯一编号
    private String id = RandomStringUtils.randomAlphanumeric(20);
    // 用户名
    private String username;
    // ip地址
    private String ip;
    // token(加密后)
    private String token;
    // 登录时间
    private String loginTime = DateTimeUtils.formatFullTime(LocalDateTime.now(),DateTimeUtils.YYYY_MM_DD_HH_mm_ss);
    // 登录地点
    private String loginAddress;
}
