package com.haikan.iptv.bean;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author bobbi
 * @since 2018-10-08
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer userId;

    private String username;

    private String password;

    private String email;

    private String salt;

    private Integer createUserId;

    private Date createTime;

    private Integer status;

    private List<Integer> roleIdList;

}
