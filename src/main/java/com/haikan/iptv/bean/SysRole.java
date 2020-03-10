package com.haikan.iptv.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author bobbi
 * @since 2018-10-19
 */
@Data
public class SysRole implements Serializable {  

    private static final long serialVersionUID = 1L;

    private Integer roleId;


    private String roleName;


    private String remark;


    private Integer createUserId;


    private Date createTime;

    private List<Integer> menuIdList;

}
