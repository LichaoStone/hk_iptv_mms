package com.haikan.iptv.service.hotword;

import com.haikan.iptv.bean.hotword.HotwordBean;
import com.haikan.iptv.common.util.bean.Result;



public interface HotwordService {
    /**
     * 获取热搜词列表页数据
     * @param bean
     * @return
     * @throws Exception
     */
    Result getHotList(HotwordBean bean) throws Exception;

    /**
     * 获取热搜词列表页数据
     * @param bean
     * @return
     * @throws Exception
     */
    Result getHotwordList(HotwordBean bean) throws Exception;

    /**
     * 修改状态
     * @param bean
     * @return
     * @throws Exception
     */
    Result modifyStatus(HotwordBean bean ) throws Exception;

    /**
     * 批量修改状态
     * @param bean
     * @return
     * @throws Exception
     */
    Result bathModifyStatus(HotwordBean bean) throws Exception;

    /**
     * 编辑热搜词
     * @param bean
     * @return
     * @throws Exception
     */
    Result update(HotwordBean bean) throws  Exception;

    /**
     * 新增热搜词
     * @param bean
     * @return
     * @throws Exception
     */
    Result addHotWord(HotwordBean bean) throws Exception;

    /**
     * 获取热搜词总数量
     * @param bean
     * @return
     * @throws Exception
     */
    Result getHotwordsCount(HotwordBean bean) throws Exception;
}
