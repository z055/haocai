package com.haocai.module.consumableslist.result;

/**
 * 目录管理时间管理
 * 查询时间返回类
 */
public class TimeResult {
    private String beginTime; //开始时间
    private String endTime; //结束时间

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
