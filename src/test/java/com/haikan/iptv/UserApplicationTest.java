package com.haikan.iptv;

import com.haikan.iptv.bean.role.query.RoleVoQuery;
import com.haikan.iptv.bean.role.vo.RoleVo;
import com.haikan.iptv.common.util.bean.JsonResult;
import com.haikan.iptv.common.util.bean.PageVo;
import com.haikan.iptv.common.util.bean.ServiceResult;
import com.haikan.iptv.service.role.RoleServiceApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Auther:hicon liu
 * @Date: 2020/2/17/017
 * @Description: com.haikan.iptv
 * @version: 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class UserApplicationTest {

    @Resource
    private RoleServiceApi roleServiceImpl ;


//    @Test
//    public void getRoleList() {
//        JsonResult jsonResult = new JsonResult() ;
//        RoleVoQuery query = new RoleVoQuery ();
//        query.setOffset(1);
//        query.setPageSize(10);
//        ServiceResult<PageVo<RoleVo>> serviceResult = roleServiceImpl.getRolePageByQuery(query);
//        jsonResult.setData(serviceResult.getData());
//        jsonResult.setOk(serviceResult.isOk());
//        System.out.println(jsonResult);
//    }

}
