package com.haocai.module.sbModule.vo;

import java.io.Serializable;
import java.util.List;

/**
 *  借用管理实体类
 */
public class SbVo implements Serializable {
    /**
     * 审核表id
     */
    private String id;

    public String getSb_time() {
        return sb_time;
    }

    public void setSb_time(String sb_time) {
        this.sb_time = sb_time;
    }

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
    private String prices;

    /**
     * 耗材数
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
    private String operator;

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
     * \入库类型
     * @param
     */
    private String sbYear;

    public String getCon_type_id() {
        return con_type_id;
    }

    public void setCon_type_id(String con_type_id) {
        this.con_type_id = con_type_id;
    }

    public SbVo(String id, String review_id, String name, String project_name, String price, String prices, String number, String attribute, String type, String audit_body, String status, String status_z, String operator, String department, String con_type, String standards, String sbYear, String status_b, String con_type_id, String sb_time, String libraryName, String consumablesId, String conType) {
        this.id = id;
        this.review_id = review_id;
        this.name = name;
        this.project_name = project_name;
        this.price = price;
        this.prices = prices;
        this.number = number;
        this.attribute = attribute;
        this.type = type;
        this.audit_body = audit_body;
        this.status = status;
        this.status_z = status_z;
        this.operator = operator;
        this.department = department;
        this.con_type = con_type;
        this.standards = standards;
        this.sbYear = sbYear;
        this.status_b = status_b;
        this.con_type_id = con_type_id;
        this.sb_time = sb_time;
        LibraryName = libraryName;
        this.consumablesId = consumablesId;
        this.conType = conType;
    }

    public String getStatus_b() {
        return status_b;
    }

    public void setStatus_b(String status_b) {
        this.status_b = status_b;
    }

    /**
     * 耗材库存表 报废状态 0未报废 1 报废
     */
    private String status_b;

    private  String con_type_id;

    private String sb_time;


    /**
     * 库管员的id
     */
    private  String LibraryName;
    //耗材id
    private  String consumablesId;
    //库类别
    private  String conType;

    public String getConsumablesId() {
        return consumablesId;
    }

    public void setConsumablesId(String consumablesId) {
        this.consumablesId = consumablesId;
    }

    public String getConType() {
        return conType;
    }

    public void setConType(String conType) {
        this.conType = conType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setLibraryName(String libraryName) {
        LibraryName = libraryName;
    }

    public String getLibraryName() {
        return LibraryName;
    }

    public void setSbYear(String outYear) {
        this.sbYear = sbYear;
    }

    public String getSbYear() {
        return sbYear;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getStandards() {
        return standards;
    }

    public SbVo() {
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

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
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


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}


