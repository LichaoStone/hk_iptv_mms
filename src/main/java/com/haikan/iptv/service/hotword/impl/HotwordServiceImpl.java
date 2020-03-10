package com.haikan.iptv.service.hotword.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haikan.iptv.bean.hotword.HotwordBean;
import com.haikan.iptv.common.util.bean.Result;
import com.haikan.iptv.common.util.constant.HttpConstant;
import com.haikan.iptv.mapper.hotword.HotwordSqlMapper;
import com.haikan.iptv.service.hotword.HotwordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HotwordServiceImpl implements HotwordService {
    @Resource
    private HotwordSqlMapper hotwordSqlMapper;

    @Override
    public Result getHotList(HotwordBean bean) throws Exception {
        Result result=new Result();
        result.setData(hotwordSqlMapper.getHotList(bean));
        result.setRet(HttpConstant.RET.SUCCESS);
        return result;
    }

    @Override
    public Result getHotwordList(HotwordBean bean) throws Exception {
        PageHelper.startPage(bean.getOffset(), bean.getPageSize());
        List<?> list = hotwordSqlMapper.getHotwordList(bean);
        PageInfo<HotwordBean> pageInfo = new PageInfo<HotwordBean>((List<HotwordBean>) list);
        Result result = new Result(pageInfo,list) ;
        result.setRet(HttpConstant.RET.SUCCESS);
        return result;
    }

    @Override
    public Result modifyStatus(HotwordBean bean) throws Exception {
        Result result=new Result();

        if("-1".equals(bean.getStatus())){
            hotwordSqlMapper.delHotword(bean);
        }else{
            hotwordSqlMapper.modifyStatus(bean);
        }

        result.setRet(HttpConstant.RET.SUCCESS);
        return result;
    }

    @Override
    public Result bathModifyStatus(HotwordBean bean) throws Exception {
        Result result=new Result();

        if("-1".equals(bean.getStatus())){
            hotwordSqlMapper.bathDel(bean);
        }else{
            hotwordSqlMapper.bathModifyStatus(bean);
        }
        result.setRet(HttpConstant.RET.SUCCESS);
        return result;
    }

    @Override
    public Result update(HotwordBean bean) throws Exception {
        Result result=new Result();
        hotwordSqlMapper.update(bean);
        result.setRet(HttpConstant.RET.SUCCESS);
        return result;
    }

    @Override
    public Result addHotWord(HotwordBean bean) throws Exception {
        Result result=new Result();
        hotwordSqlMapper.addHotWord(bean);
        result.setRet(HttpConstant.RET.SUCCESS);
        return result;
    }

    @Override
    public Result getHotwordsCount(HotwordBean bean) throws Exception {
        Result result=new Result();
        result.setData(hotwordSqlMapper.getHotwordsCount(bean));
        result.setRet(HttpConstant.RET.SUCCESS);
        return  result;
    }
}
