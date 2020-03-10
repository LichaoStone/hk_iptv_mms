package com.haikan.iptv.mapper.version;

import com.haikan.iptv.bean.version.VersionBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VersionSqlMapper {
    /**
     * 获取版本管理列表页数据
     * @param bean
     * @return
     * @throws Exception
     */
    List<?> getVersionList(VersionBean bean) throws Exception;

    /**
     * 修改状态
     * @param bean
     * @return
     * @throws Exception
     */
    Integer modifyStatus(VersionBean bean) throws  Exception;

    /**
     * 获取详情数据
     * @param bean
     * @return
     * @throws Exception
     */
    VersionBean getDataView(VersionBean bean) throws Exception;

    /**
     * 新增版本
     * @param bean
     * @return
     * @throws Exception
     */
    Integer addVersion(VersionBean bean) throws Exception;

    /**
     * 修改版本信息
     * @param bean
     * @return
     * @throws Exception
     */
    Integer updateVersion(VersionBean bean) throws  Exception;
}
