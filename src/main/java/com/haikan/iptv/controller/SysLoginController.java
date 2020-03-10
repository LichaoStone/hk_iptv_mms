package com.haikan.iptv.controller;

import com.haikan.iptv.config.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SysLoginController
 *
 * @author bobbi
 * @date 2018/10/19 18:47
 * @email 571002217@qq.com
 * @description
 */
@RestController
public class SysLoginController extends AbstractController {


    @PostMapping("/admin/sys/login")
    public Result login() {
        // 用户信息
//        SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>()
//                .lambda()
//                .eq(SysUser :: getUsername,form.getUsername()));
//        if(user ==null || !user.getPassword().equals(new Sha256Hash(form.getPassword(),user.getSalt()).toHex())){
//            // 用户名或密码错误
//            return Result.error(ErrorEnum.USERNAME_OR_PASSWORD_WRONG);
//        }
//        if(user.getStatus() ==0){
//            return Result.error("账号已被锁定，请联系管理员");
//        }

        //生成token，并保存到redis
        return null;
    }

    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public Result logout() {
       // sysUserTokenService.logout(getUserId());
        return Result.ok();
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return null;
    }
}
