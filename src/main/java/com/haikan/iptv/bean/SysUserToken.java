package com.haikan.iptv.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 系统用户Token
 * </p>
 *
 * @author bobbi
 * @since 2018-10-19
 */
@Data
public class SysUserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String token;



}
