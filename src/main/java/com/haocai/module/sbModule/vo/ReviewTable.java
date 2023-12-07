package com.haocai.module.sbModule.vo;

public class ReviewTable {
    private String id; //审核表id uuid
    private String review_id; //审核项目编号
    private String project_name;//审核项目名称
    private String before_re; //前一个审核人
    private String re_pe_id; //当前审核人
    private String after_re; //下一个审核人
    private String status; //审核表审核状态 0待审核 1通过 2退回 3被退回
    private String role; //审核角色
    private String audit_body; //审核意见（退回用）
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String name; //耗材名称
    private String department;//部门
    private String attribute; //耗材属性 低值易耗品1、低值耐用品2
    private String type; //耗材类别 酒店食品1、金属材料2、电子电器3、建筑材料4、服装服饰5、文化绘画6、工具仪表7、化工药品8、气体类9、维修保养10、其他11
    private String operator;//申请人
    private String opinion;


    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
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

    public String getBefore_re() {
        return before_re;
    }

    public void setBefore_re(String before_re) {
        this.before_re = before_re;
    }

    public String getRe_pe_id() {
        return re_pe_id;
    }

    public void setRe_pe_id(String re_pe_id) {
        this.re_pe_id = re_pe_id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAudit_body() {
        return audit_body;
    }

    public void setAudit_body(String audit_body) {
        this.audit_body = audit_body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "ReviewTable{" +
                "id='" + id + '\'' +
                ", review_id='" + review_id + '\'' +
                ", project_name='" + project_name + '\'' +
                ", before_re='" + before_re + '\'' +
                ", re_pe_id='" + re_pe_id + '\'' +
                ", after_re='" + after_re + '\'' +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                ", audit_body='" + audit_body + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", attribute='" + attribute + '\'' +
                ", type='" + type + '\'' +
                ", operator='" + operator + '\'' +
                ", opinion='" + opinion + '\'' +
                '}';
    }
}
