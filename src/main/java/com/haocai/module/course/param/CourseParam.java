package com.haocai.module.course.param;

import com.haocai.module.course.vo.Course;

/**
 * param前端其他参数接收类
 * @author 小崔
 */
public class CourseParam extends Course {
    //用于前端分页限制每页数据
    private int page; //前端传来的第几页
    private int limit; //限制查询的数据数量（前端传）
    private String condition; //前端查询时传来的条件
    private String ids;//用以批量操作
    private String [] arrIds;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String[] getArrIds() {
        return arrIds;
    }

    public void setArrIds(String[] arrIds) {
        this.arrIds = arrIds;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
}
