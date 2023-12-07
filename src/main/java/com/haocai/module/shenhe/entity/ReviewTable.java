package com.haocai.module.shenhe.entity;

import java.io.Serializable;

/**
 * 审核流程表
(ReviewTable)实体类
 *
 * @author makejava
 * @since 2022-09-02 09:47:08
 */
public class ReviewTable implements Serializable {
    private static final long serialVersionUID = -76632220039447514L;
    /**
     * 审核表id
     */
    private String id;
    /**
     * 审核编号
     */
    private String reviewId;
    /**
     * 审核项目名称
     */
    private String projectName;
    /**
     * 前一审核人
     */
    private String beforeRe;
    /**
     * 审核人编号
     */
    private String rePeId;
    /**
     * 后一审核人
     */
    private String afterRe;
    /**
     * 审核状态
0待审核
1成功
2退回
3被退回

     */
    private String status;
    /**
     * 审核角色
     */
    private String role;
    /**
     * 审核意见
     */
    private String auditBody;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBeforeRe() {
        return beforeRe;
    }

    public void setBeforeRe(String beforeRe) {
        this.beforeRe = beforeRe;
    }

    public String getRePeId() {
        return rePeId;
    }

    public void setRePeId(String rePeId) {
        this.rePeId = rePeId;
    }

    public String getAfterRe() {
        return afterRe;
    }

    public void setAfterRe(String afterRe) {
        this.afterRe = afterRe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAuditBody() {
        return auditBody;
    }

    public void setAuditBody(String auditBody) {
        this.auditBody = auditBody;
    }

}

