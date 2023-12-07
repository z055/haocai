package com.haocai.module.consumableslist.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

public class ErrorExcel {
    @ExcelIgnore
    private long id;
    @ExcelProperty("耗材名称")
    private String name;
    @ExcelProperty("耗材属性")
    private String attribute;
    @ExcelProperty("耗材类别")
    private String type;
    @ExcelProperty("型号规格及主要技术要求")
    private String standards;
    @ExcelProperty("单位")
    private String unit;
    @ExcelProperty("单价(元)")
    private String price;
    @ExcelProperty("生均数量")
    private String number;
    @ExcelProperty("实训项目")
    private String training;
    @ExcelProperty("二级学院")
    private String department;
    @ExcelProperty("使用学期")
    private String semester;
    @ExcelIgnore
    private String userid;
    @ExcelProperty("错误信息")
    private String errormsg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }
}
