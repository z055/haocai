package com.haocai.module.consumableslist.param;

import com.haocai.module.consumableslist.vo.ReviewTable;

public class ReviewParam extends ReviewTable {
    //用于前端分页限制每页数据
    private int page; //前端传来的第几页
    private int limit; //限制查询的数据数量（前端传）
    private String department; //学院
    private String training;
    private String major;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    @Override
    public String toString() {
        return "ReviewParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", department='" + department + '\'' +
                ", training='" + training + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
