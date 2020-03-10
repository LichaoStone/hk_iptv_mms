package com.haikan.iptv.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户与角色对应关系
 * </p>
 *
 * @author bobbi
 * @since 2018-10-19
 */
@Data
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    private Integer roleId;


}
