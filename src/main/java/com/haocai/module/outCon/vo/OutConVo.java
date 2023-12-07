package com.haocai.module.outCon.vo;

import java.io.Serializable;
import java.util.Objects;

public class OutConVo implements Serializable {
    /**
     * 审核表id
     */
    private String id;

    /**
     * 审核流程id
     */
    private String review_id;

    /**
     * 耗材名称
     */
    private String name;

    /**
     * 审核项目名称
     */
    private String project_name;

    /**
     * 耗材价格
     */
    private String price;

    /**
     * 总价格
     */
    private String price_z;

    /**
     * 耗材数量
     */
    private String number;

    /**
     * 耗材属性
     */
    private String attribute;

    /**
     * 耗材类别
     */
    private String type;

    /**
     * 审核意见
     */
    private String audit_body;

    /**
     * 审核状态
     */
    private String status;

    /**
     * 显示的审核状态
     */
    private String status_z;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 审核人部门
     */
    private String department;

    /**
     * 库类别
     */
    private String con_type;

    /**
     * 耗材规格
     */
    private String standards;

    /**
     * 出库年份
     * @param
     */
    private String outYear;

    /**
     * 出库类型
     *
     */
    private String out_type;

    /**
     * 库管员的id
     */
    private  String LibraryName;

    /**
     *申请人姓名
     */
    private String applicantName;
    /**
     * 审核人姓名
     */
    private String auditName;
    /**
     * 出库申请时间
     */
    private String out_time;
    /**
     * 库管员出库时间
     */
    private String out_con_time;
    /**
     * 搜索起始时间
     */
    private String startTime;
    /**
     * 搜索终止时间
     */
    private String endTime;

    /**
     *审核人名
     */
    private String shenHeName;
    /**
     *课程/大赛/实训id
     */
    private String conTypeId;
    /**
     * 课程no
     */
    private String conTypeNo;
    /**
     * 课程/大赛/实训name
     */
    private String conTypeName;

    public String getOut_con_time() {
        return out_con_time;
    }

    public void setOut_con_time(String out_con_time) {
        this.out_con_time = out_con_time;
    }

    public String getConTypeName() {
        return conTypeName;
    }

    public void setConTypeName(String conTypeName) {
        this.conTypeName = conTypeName;
    }

    public String getShenHeName() {
        return shenHeName;
    }

    public void setShenHeName(String shenHeName) {
        this.shenHeName = shenHeName;
    }

    public String getConTypeId() {
        return conTypeId;
    }

    public void setConTypeId(String conTypeId) {
        this.conTypeId = conTypeId;
    }

    public String getConTypeNo() {
        return conTypeNo;
    }

    public void setConTypeNo(String conTypeNo) {
        this.conTypeNo = conTypeNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutConVo outConVo = (OutConVo) o;
        return Objects.equals(id, outConVo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public void setLibraryName(String libraryName) {
        LibraryName = libraryName;
    }

    public String getLibraryName() {
        return LibraryName;
    }

    public void setOut_type(String out_type) {
        this.out_type = out_type;
    }

    public String getOut_type() {
        return out_type;
    }

    public void setOutYear(String outYear) {
        this.outYear = outYear;
    }

    public String getOutYear() {
        return outYear;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getStandards() {
        return standards;
    }

    public OutConVo() {
    }

    public OutConVo(String id, String name, String price, String price_z, String number, String attribute, String type, String audit_body, String status, String applicant, String department) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.price_z = price_z;
        this.number = number;
        this.attribute = attribute;
        this.type = type;
        this.audit_body = audit_body;
        this.status = status;
        this.applicant = applicant;
        this.department = department;
    }

    public void setCon_type(String con_type) {
        this.con_type = con_type;
    }

    public String getCon_type() {
        return con_type;
    }

    public void setStatus_z(String status_z) {
        this.status_z = status_z;
    }

    public String getStatus_z() {
        return status_z;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public String getReview_id() {
        return review_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_z() {
        return price_z;
    }

    public void setPrice_z(String price_z) {
        this.price_z = price_z;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getAudit_body() {
        return audit_body;
    }

    public void setAudit_body(String audit_body) {
        this.audit_body = audit_body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "OutConVo{" +
                "id='" + id + '\'' +
                ", review_id='" + review_id + '\'' +
                ", name='" + name + '\'' +
                ", project_name='" + project_name + '\'' +
                ", price='" + price + '\'' +
                ", price_z='" + price_z + '\'' +
                ", number='" + number + '\'' +
                ", attribute='" + attribute + '\'' +
                ", type='" + type + '\'' +
                ", audit_body='" + audit_body + '\'' +
                ", status='" + status + '\'' +
                ", status_z='" + status_z + '\'' +
                ", applicant='" + applicant + '\'' +
                ", department='" + department + '\'' +
                ", con_type='" + con_type + '\'' +
                ", standards='" + standards + '\'' +
                ", outYear='" + outYear + '\'' +
                ", out_type='" + out_type + '\'' +
                ", LibraryName='" + LibraryName + '\'' +
                ", applicantName='" + applicantName + '\'' +
                ", auditName='" + auditName + '\'' +
                ", out_time='" + out_time + '\'' +
                ", out_con_time='" + out_con_time + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", shenHeName='" + shenHeName + '\'' +
                ", conTypeId='" + conTypeId + '\'' +
                ", conTypeNo='" + conTypeNo + '\'' +
                ", conTypeName='" + conTypeName + '\'' +
                '}';
    }

    public OutConVo(String id, String review_id, String name, String project_name, String price, String price_z, String number, String attribute, String type, String audit_body, String status, String status_z, String applicant, String department, String con_type, String standards, String outYear, String out_type, String libraryName, String applicantName, String auditName, String out_time, String startTime, String endTime, String shenHeName) {
        this.id = id;
        this.review_id = review_id;
        this.name = name;
        this.project_name = project_name;
        this.price = price;
        this.price_z = price_z;
        this.number = number;
        this.attribute = attribute;
        this.type = type;
        this.audit_body = audit_body;
        this.status = status;
        this.status_z = status_z;
        this.applicant = applicant;
        this.department = department;
        this.con_type = con_type;
        this.standards = standards;
        this.outYear = outYear;
        this.out_type = out_type;
        LibraryName = libraryName;
        this.applicantName = applicantName;
        this.auditName = auditName;
        this.out_time = out_time;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shenHeName = shenHeName;
    }
}
