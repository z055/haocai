package com.haocai.module.inCon.vo.param;

import com.haocai.module.inCon.vo.InConVo;

import java.util.List;

public class InConVoParam extends InConVo {
    private int page;
    private int limit;
    private List reviewId;
    /**
     * 审核人id
     */
    private String roleId;
    /**
     *  前一个审核人
     */
    private String before_re;
    /**
     * 后一审核人
     */
    private String after_re;

    /**
     * 审核人角色
     */
    private String role;

    public void setReviewId(List reviewId) {
        this.reviewId = reviewId;
    }

    public List getReviewId() {
        return reviewId;
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getBefore_re() {
        return before_re;
    }

    public void setBefore_re(String before_re) {
        this.before_re = before_re;
    }

    public String getAfter_re() {
        return after_re;
    }

    public void setAfter_re(String after_re) {
        this.after_re = after_re;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "InConVoParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", reviewId=" + reviewId +
                ", roleId='" + roleId + '\'' +
                ", before_re='" + before_re + '\'' +
                ", after_re='" + after_re + '\'' +
                ", role='" + role + '\'' +
                '}'+super.toString();
    }
}
