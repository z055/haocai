package com.haocai.module.outCon.vo;

public class OutReviewVo {
    private String id;
    private String review_id;
    private String project_name;
    private String re_pe_id;
    private String before_re;
    private String after_re;
    private String status;
    private String audit_body;
    private String role;

    public OutReviewVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getRe_pe_id() {
        return re_pe_id;
    }

    public void setRe_pe_id(String re_pe_id) {
        this.re_pe_id = re_pe_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAudit_body() {
        return audit_body;
    }

    public void setAudit_body(String audit_body) {
        this.audit_body = audit_body;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ReviewVo{" +
                "id='" + id + '\'' +
                ", review_id='" + review_id + '\'' +
                ", project_name='" + project_name + '\'' +
                ", re_pe_id='" + re_pe_id + '\'' +
                ", before_re='" + before_re + '\'' +
                ", after_re='" + after_re + '\'' +
                ", status='" + status + '\'' +
                ", audit_body='" + audit_body + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
