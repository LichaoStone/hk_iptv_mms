package com.haikan.iptv.bean.version;

public class VersionBean {
    /**
     * 版本主键key
     */
    private String versionKey;
    /**
     * 版本号
     */
    private String version;
    /**
     * 所属终端：1Android，2iOS，3微信小程序
     */
    private String terminal;
    /**
     * 安装包路径、更新地址
     */
    private String updateUrl;
    /**
     * 是否强制升级：1是，2否
     */
    private String isForced;
    /**
     * 更新内容
     */
    private String updateContent;
    /**
     * 状态：1待启用，2启用，3禁用
     */
    private String status;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 每页数据条数
     */
    private  Integer pageSize ;
    /**
     * 当前页数
     */
    private Integer offset ;

    private String _timestamp;
    private String _sign;
    private String v;

    public String getVersionKey() {
        return versionKey;
    }

    public void setVersionKey(String versionKey) {
        this.versionKey = versionKey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getIsForced() {
        return isForced;
    }

    public void setIsForced(String isForced) {
        this.isForced = isForced;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String get_timestamp() {
        return _timestamp;
    }

    public void set_timestamp(String _timestamp) {
        this._timestamp = _timestamp;
    }

    public String get_sign() {
        return _sign;
    }

    public void set_sign(String _sign) {
        this._sign = _sign;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
