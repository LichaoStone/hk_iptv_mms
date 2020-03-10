package com.haikan.iptv.bean.advertisement.vo;


import com.haikan.iptv.bean.advertisement.Advertisement;

/**
 *广告表
 */
public class AdvertisementVo extends Advertisement {
    private String beginAndEnd;

    private String advBelongs;

    private String targetName;
    private Object pv;
    private Object uv;
    private String statusName;
    private String beginTimeStr;
    private String endTimeStr;


    public String getAdvBelongs() {
        return advBelongs;
    }

    public void setAdvBelongs(String advBelongs) {
        this.advBelongs = advBelongs;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getBeginAndEnd() {
        return beginAndEnd;
    }

    public void setBeginAndEnd(String beginAndEnd) {
        this.beginAndEnd = beginAndEnd;
    }

    public Object getPv() {
        return pv;
    }

    public void setPv(Object pv) {
        this.pv = pv;
    }

    public Object getUv() {
        return uv;
    }

    public void setUv(Object uv) {
        this.uv = uv;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    public String getBeginTimeStr() {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
}
