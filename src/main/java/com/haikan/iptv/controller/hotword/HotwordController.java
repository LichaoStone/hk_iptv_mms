package com.haikan.iptv.controller.hotword;

import com.haikan.iptv.bean.hotword.HotwordBean;
import com.haikan.iptv.common.util.DateUtil;
import com.haikan.iptv.common.util.PkCreat;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.controller.WebBaseController;
import com.haikan.iptv.service.hotword.HotwordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Controller
@RequestMapping("/hotword")
public class HotwordController extends WebBaseController {
    Logger logger = LoggerFactory.getLogger(HotwordController.class);

    @Resource
    private HotwordService hotwordService;


    /**
     * 获取热搜词
     * @param request
     * @param response
     */
    @RequestMapping(value="/getHotwordList",method= RequestMethod.GET)
    @ResponseBody
    private Result getHotwordList(HttpServletRequest request, HttpServletResponse response){
        String hotwords=request.getParameter("hotwords");
        String status=request.getParameter("status");

        logger.info("【热搜词管理】获取热搜词列表页数据:hotwords="+hotwords+",status="+status);

        Result result=new Result();
        try{
            HotwordBean hotwordBean=new HotwordBean();
            hotwordBean.setHotWords(hotwords);
            hotwordBean.setStatus(status);

            result=hotwordService.getHotwordList(hotwordBean);
        }catch(Exception e) {
            logger.error("【热搜词管理】获取热搜词数据失败:",e);
        }

        return  result;
    }

    /**
     * 获取热搜词数量
     * @param request
     * @param response
     */
    @RequestMapping(value="/getHotwordsCount",method= RequestMethod.GET)
    @ResponseBody
    private Result getHotwordsCount(HttpServletRequest request, HttpServletResponse response){
        String status=request.getParameter("status");

        Result result=new Result();
        try{
            HotwordBean hotwordBean=new HotwordBean();
            hotwordBean.setStatus(status);
            result=hotwordService.getHotwordsCount(hotwordBean);
        }catch(Exception e) {
            logger.error("【热搜词管理】获取热搜词数量失败:",e);
        }

        return result;
    }

    /**
     * 修改状态
     * @param request
     * @param response
     */
    @RequestMapping(value="/modifyStatus",method= RequestMethod.GET)
    @ResponseBody
    private Result modifyStatus(HttpServletRequest request, HttpServletResponse response){
        String status=request.getParameter("status");
        String tHotKey=request.getParameter("tHotKey");

        logger.info("【热搜词管理】修改热搜词状态:tHotKey="+tHotKey+",status="+status);

        Result result=new Result();
        try{
            HotwordBean hotwordBean=new HotwordBean();
            hotwordBean.settHotKey(tHotKey);
            hotwordBean.setStatus(status);

            result=hotwordService.modifyStatus(hotwordBean);
        }catch(Exception e) {
            logger.error("【热搜词管理】修改热搜词状态失败:",e);
        }

        return result;
    }


    /**
     * 修改热搜词
     * @param request
     * @param response
     */
    @RequestMapping(value="/update",method= RequestMethod.GET)
    @ResponseBody
    private Result update(HttpServletRequest request, HttpServletResponse response){
        String hotwords=request.getParameter("hotwords");
        String tHotKey=request.getParameter("tHotKey");

        logger.info("【热搜词管理】修改热搜词:tHotKey="+tHotKey+",hotwords="+hotwords);

        Result result=new Result();
        try{
            HotwordBean hotwordBean=new HotwordBean();
            hotwordBean.settHotKey(tHotKey);
            hotwordBean.setHotWords(hotwords);

            result=hotwordService.update(hotwordBean);
        }catch(Exception e) {
            logger.error("【热搜词管理】修改热搜词失败:",e);
        }

        return result;
    }



    /**
     * 修改热搜词
     * @param request
     * @param response
     */
    @RequestMapping(value="/addHotword",method= RequestMethod.GET)
    @ResponseBody
    private Result addHotword(HttpServletRequest request, HttpServletResponse response){
        String hotwords=request.getParameter("hotwords");

        logger.info("【热搜词管理】修改热搜词:hotwords="+hotwords);

        Result result=new Result();
        try{
            HotwordBean hotwordBean=new HotwordBean();
            hotwordBean.settHotKey(PkCreat.getTablePk());
            hotwordBean.setHotWords(hotwords);
            hotwordBean.setStatus("2");  //默认禁用
            hotwordBean.setOrderNum("10000"); //默认10000
            hotwordBean.setCreator("111");
            String time= DateUtil.getTimeToSec();
            hotwordBean.setCreateTime(time);
            hotwordBean.setUpdateTime(time);

            result=hotwordService.addHotWord(hotwordBean);
        }catch(Exception e) {
            logger.error("【热搜词管理】修改热搜词失败:",e);
        }

        return  result;
    }


    /**
     * 批量修改热搜词
     * @param request
     * @param response
     */
    @RequestMapping(value="/bathModifyStatus",method= RequestMethod.GET)
    @ResponseBody
    private Result bathModifyStatus(HttpServletRequest request, HttpServletResponse response){
        String  keys=request.getParameter("keys");
        String  status=request.getParameter("status");

        logger.info("【热搜词管理】修改热搜词:keys="+keys+",status="+status);

        Result result=new Result();
        try{
            HotwordBean hotwordBean=new HotwordBean();
            hotwordBean.setKeyList(Arrays.asList(keys.split(",")));
            hotwordBean.setStatus(status);

            result=hotwordService.bathModifyStatus(hotwordBean);
        }catch(Exception e) {
            logger.error("【热搜词管理】修改热搜词失败:",e);
        }

        return result;
    }

}
