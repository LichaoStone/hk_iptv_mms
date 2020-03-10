package com.haikan.iptv.config.shiro;

import com.haikan.iptv.bean.permissions.Permission;
import com.haikan.iptv.bean.platformUser.vo.PlatformUserVo;
import com.haikan.iptv.common.util.FebsUtil;
import com.haikan.iptv.common.util.HttpContextUtil;
import com.haikan.iptv.common.util.IPUtil;
import com.haikan.iptv.common.util.bean.ServiceResult;
import com.haikan.iptv.common.util.constant.Constants;
import com.haikan.iptv.common.util.constant.RedisKeyConstants;
import com.haikan.iptv.config.redis.RedisService;
import com.haikan.iptv.config.shiro.util.JWTUtil;
import com.haikan.iptv.service.platformUser.PlatformUserServiceApi;
import com.haikan.iptv.service.role.RoleServiceApi;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义实现 ShiroRealm，包含认证和授权两大模块
 */
public class ShiroRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private RedisService redisService;
    @Resource
    private PlatformUserServiceApi platformUserServiceImpl;
    @Resource
    private RoleServiceApi roleServiceImpl;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**`
     * 授权模块，获取用户角色和权限
     *
     * @param token token
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        String username = JWTUtil.getUsername(token.toString());

        logger.info("-----------------------------获取用户权限开始--------------------------------");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //add Permission Resources
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        ServiceResult<PlatformUserVo> result = platformUserServiceImpl.getUserByLoginName(loginName);
        Integer roleKey = result.getData().getRoleId();
        List<Permission> permissionList = roleServiceImpl.getPermissionBeanVosByRoleKey(roleKey);
//        List<Permission> permissionList = permissionListRes.getData();

        List<String> urlList = new ArrayList<String>();
        if (!"admin".equals(loginName) && StringUtils.isBlank(roleKey+"")) { //默认的权限
            urlList.addAll(Constants.PERMISSION_LIST);
        }
        if ("admin".equals(loginName)) { //默认的权限
            urlList.addAll(Constants.PERMISSION_LIST);
            permissionList = roleServiceImpl.getPermissionBeanVosByRoleKey(null);
//            permissionList = permissionListRes2.getData();
        }

        if (permissionList != null && permissionList.size() > 0) {
            /**
             * 获得用户权限URL
             */
            permissionList.stream()
                    .forEach(permission -> {
                        if (StringUtils.isNotBlank(permission.getShowUrl())) {
                            urlList.add(permission.getShowUrl());
                        }
                    });
        }
        urlList.addAll(Constants.PERMISSION_LIST);

        info.addStringPermissions(urlList);
        logger.info("-----------------------------获取用户权限结束--------------------------------");

        return info;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();

        // 从 redis里获取这个 token
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);

        String encryptToken = FebsUtil.encryptToken(token);
        String encryptTokenInRedis = null;
        try {
            encryptTokenInRedis = redisService.get(RedisKeyConstants.TOKEN_CACHE_PREFIX + encryptToken + "." + ip);
        } catch (Exception ignore) {
        }
        // 如果找不到，说明已经失效
        if (StringUtils.isBlank(encryptTokenInRedis))
            throw new AuthenticationException("token已经过期");

        String username = JWTUtil.getUsername(token);

        if (StringUtils.isBlank(username))
            throw new AuthenticationException("token校验不通过");

        // 通过用户名查询用户信息
        PlatformUserVo user = (PlatformUserVo)platformUserServiceImpl.getUserByLoginName(username).getData();

        if (user == null)
            throw new AuthenticationException("用户名或密码错误");
        if (!JWTUtil.verify(token, username, user.getPassword()))
            throw new AuthenticationException("token校验不通过");
        return new SimpleAuthenticationInfo(token, token, "febs_shiro_realm");
    }
}
