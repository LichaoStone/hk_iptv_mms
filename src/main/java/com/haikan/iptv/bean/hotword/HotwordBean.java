package com.haikan.iptv.bean.hotword;

import java.util.List;

public class HotwordBean {
    private String tHotKey;
    private String hotWords;
    private List<String> keyList;
    private String creator;
    private String createTime;
    private String updateTime;
    private String status;
    private String orderNum;

    public String gettHotKey() {
        return tHotKey;
    }

    public void settHotKey(String tHotKey) {
        this.tHotKey = tHotKey;
    }

    public String getHotWords() {
        return hotWords;
    }

    public void setHotWords(String hotWords) {
        this.hotWords = hotWords;
    }

    public List<String> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<String> keyList) {
        this.keyList = keyList;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }


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

    public Integer getPageSize() {
        if (pageSize == null) {
            pageSize = 10 ;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        if (offset == null) {
            offset = 1 ;
        }
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
