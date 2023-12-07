package com.haocai.module.warehouse.entity;

import java.io.Serializable;

/**
 * 耗材库存表(TConsumables)实体类
 *
 * @author makejava
 * @since 2022-09-04 17:59:26
 */
public class TConsumables implements Serializable {
    private static final long serialVersionUID = -14686557108985605L;
    /**
     * ID
     */
    private String id;
    /**
     * 耗材编号
     */
    private String consumablesid;
    /**
     * 耗材数量
     */
    private Long number;
    /**
     * 二级学院
     */
    private String department;
    /**
     * 共享状态(1借用，2不借用)
     */
    private char status;
    /**
     * 备注
     */
    private String mark;
    /**
     * 价格
     */
    private Float price;

    /**
     * 耗材名称
     */
    private String consumableName;

    /**
     * 耗材类型
     */
    private String type;

    /**
     * 耗材属性
     */
    private String attribute;

    /**
     * 页码//起始列
     */
    private int page;

    /**
     * 条数
     */
    private int limit;

    /**
     * 出库数量
     */
    private int outNumber;

    /**
     * 入库数量
     */
    private int inNumber;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 出库年份
     */
    private String year;

    /**
     * 库类别
     */
    private String conType;

    /**
     * 出库类型
     *
     */
    private String out_type;
    /**
     * 出库类型对应名称 id
     */
    private String conTypeId;
    private String conTypeName;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getConTypeId() {
        return conTypeId;
    }

    public void setConTypeId(String conTypeId) {
        this.conTypeId = conTypeId;
    }

    public String getConTypeName() {
        return conTypeName;
    }

    public void setConTypeName(String conTypeName) {
        this.conTypeName = conTypeName;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    /**
     *
     * @param out_type
     */
    private String standards;

    public void setPage(int page) {
        this.page = page;
    }

    public String getStandards() {
        return standards;
    }

    public void setOut_type(String out_type) {
        this.out_type = out_type;
    }

    public String getOut_type() {
        return out_type;
    }

    public int getInNumber() {
        return inNumber;
    }

    public void setInNumber(int inNumber) {
        this.inNumber = inNumber;
    }

    public String getConType() {
        return conType;
    }

    public void setConType(String conType) {
        this.conType = conType;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(int outNumber) {
        this.outNumber = outNumber;
    }

    public int getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = new Integer(page);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = new Integer(limit);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsumablesid() {
        return consumablesid;
    }

    public void setConsumablesid(String consumablesid) {
        this.consumablesid = consumablesid;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getConsumableName() {
        return consumableName;
    }

    public void setConsumableName(String consumableName) {
        this.consumableName = consumableName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public TConsumables() {
    }

    public TConsumables(String id, String consumablesid, Long number, String department, char status, String mark, Float price, String consumableName, String type, String attribute) {
        this.id = id;
        this.consumablesid = consumablesid;
        this.number = number;
        this.department = department;
        this.status = status;
        this.mark = mark;
        this.price = price;
        this.consumableName = consumableName;
        this.type = type;
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "TConsumables{" +
                "id=" + id +
                ", consumablesid='" + consumablesid + '\'' +
                ", number=" + number +
                ", department='" + department + '\'' +
                ", status=" + status +
                ", mark='" + mark + '\'' +
                ", price=" + price +
                ", consumableName='" + consumableName + '\'' +
                ", type='" + type + '\'' +
                ", attribute='" + attribute + '\'' +
                ", page=" + page +
                ", limit=" + limit +
                ", outNumber=" + outNumber +
                ", inNumber=" + inNumber +
                ", applicant='" + applicant + '\'' +
                ", year='" + year + '\'' +
                ", conType='" + conType + '\'' +
                '}';
    }
}

