package com.haocai.module.inCon.vo;

import java.io.Serializable;

public class InConVo implements Serializable {
    /**
     * 审核表id
     */
    private String id;
    /**
     * 审核流程id
     */
    private String review_id;
    /**
     * 耗材id
     */
    private String consumablesid;
    /**
     *  审核项目名
     */
    private String project_name;
    /**
     * 耗材名称
     */
    private String name;
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
     * 最终显示状态
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
     * 入库年份
     */
    private String yearin;
    /**
     * 入库申请时间
     */
    private String in_time;

    /**
     * 搜索起始时间
     */
    private String startTime;
    /**
     * 搜索终止时间
     */
    private String endTime;

    /**
     * 耗材规格
     * @param yearin
     */
    private String standards;

    /**
     *总价格
     * @param
     */
    private String prices;
    /**
     *审核人名
     */
    private String shenHeName;
    /**
     * 单据编号
     */
    private String receiptNo;
    /**
     * 课程/大赛/实训id
     */
    private String conTypeId;
    /**
     * 课程/大赛/实训name
     */
    private String conTypeNo;
    private String conTypeName;
    //设备保养名称
    private String maintenanceName;

    public String getMaintenanceName() {
        return maintenanceName;
    }

    public void setMaintenanceName(String maintenanceName) {
        this.maintenanceName = maintenanceName;
    }

    public String getConTypeName() {
        return conTypeName;
    }

    public void setConTypeName(String conTypeName) {
        this.conTypeName = conTypeName;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
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

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
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

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getPrices() {
        return prices;
    }

    public void setYearin(String yearin) {
        this.yearin = yearin;
    }

    public String getYearin() {
        return yearin;
    }

    public InConVo() {
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getStandards() {
        return standards;
    }

    public void setConsumablesid(String consumablesid) {
        this.consumablesid = consumablesid;
    }

    public String getConsumablesid() {
        return consumablesid;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public void setStatus_z(String status_z) {
        this.status_z = status_z;
    }

    public String getStatus_z() {
        return status_z;
    }

    public void setCon_type(String con_type) {
        this.con_type = con_type;
    }

    public String getCon_type() {
        return con_type;
    }

    public String getReview_id() {
        return review_id;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
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

    public void setPrice_z(String price_z) {
        this.price_z = price_z;
    }

    public String getPrice_z() {
        return price_z;
    }

    public String getShenHeName() {
        return shenHeName;
    }

    public void setShenHeName(String shenHeName) {
        this.shenHeName = shenHeName;
    }

    @Override
    public String toString() {
        return "InConVo{" +
                "id='" + id + '\'' +
                ", review_id='" + review_id + '\'' +
                ", consumablesid='" + consumablesid + '\'' +
                ", project_name='" + project_name + '\'' +
                ", name='" + name + '\'' +
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
                ", yearin='" + yearin + '\'' +
                ", in_time='" + in_time + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", standards='" + standards + '\'' +
                ", prices='" + prices + '\'' +
                ", shenHeName='" + shenHeName + '\'' +
                ", receiptNo='" + receiptNo + '\'' +
                ", conTypeId='" + conTypeId + '\'' +
                ", conTypeNo='" + conTypeNo + '\'' +
                ", conTypeName='" + conTypeName + '\'' +
                ", maintenanceName='" + maintenanceName + '\'' +
                '}';
    }

    public InConVo(String id, String review_id, String consumablesid, String project_name, String name, String price, String price_z, String number, String attribute, String type, String audit_body, String status, String status_z, String applicant, String department, String con_type, String yearin, String standards, String prices) {
        this.id = id;
        this.review_id = review_id;
        this.consumablesid = consumablesid;
        this.project_name = project_name;
        this.name = name;
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
        this.yearin = yearin;
        this.standards = standards;
        this.prices = prices;
    }


}
