package com.haikan.iptv.service.version;

import com.haikan.iptv.bean.version.VersionBean;
import com.haikan.iptv.common.util.bean.Result;

public interface VersionService {
    /**
     * 获取版本管理列表页数据
     * @param bean
     * @return
     * @throws Exception
     */
    Result getVersionList(VersionBean bean) throws Exception;

    /**
     * 修改状态
     * @param bean
     * @return
     * @throws Exception
     */
    Result modifyStatus(VersionBean bean) throws  Exception;

    /**
     * 获取详情数据
     * @param bean
     * @return
     * @throws Exception
     */
    Result getDataView(VersionBean bean) throws Exception;

    /**
     * 新增版本
     * @param bean
     * @return
     * @throws Exception
     */
    Result addVersion(VersionBean bean) throws Exception;

    /**
     * 修改版本信息
     * @param bean
     * @return
     * @throws Exception
     */
    Result updateVersion(VersionBean bean) throws  Exception;

    /**
     * 上传附件
     * @param bean
     * @return
     * @throws Exception
     */
    Result upload(VersionBean bean) throws Exception;
}
