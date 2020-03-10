package com.haikan.iptv.controller.version;

import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.controller.WebBaseController;
import com.haikan.iptv.service.version.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/version")
public class VersionController extends WebBaseController {
    Logger logger = LoggerFactory.getLogger(VersionController.class);

    @Resource
    private VersionService versionService;


    /**
     * 获取版本列表页数据
     * @param request
     * @param response
     */
    @RequestMapping(value="/getVersionList",method= RequestMethod.GET)
    @ResponseBody
    private Result getHotwordList(HttpServletRequest request, HttpServletResponse response){

        logger.info("【版本管理】获取版本管理列表页数据");

        Result result=new Result();
        try{
        }catch(Exception e) {
            logger.error("【版本管理】获取热搜词数据失败:",e);
        }

        return  result;
    }
}
