package com.haocai.module.consumableslist.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;


import java.io.Serializable;
import java.util.Objects;

/**
 * 耗材目录管理
 * @author 小崔
 * 对应t_consumableslist表
 */
public class Consumableslist implements Serializable{
    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    private Integer id; //耗材目录 id uuid

    @ExcelIgnore
    private String review_id; //审核编号uuid

    @ExcelProperty("耗材名称")
    private String name; //耗材名称

    @ExcelIgnore
    private String no; //耗材编号

    @ExcelProperty("耗材属性")
    private String attribute; //耗材属性 1:低值易耗品 2：低值耐用品

    @ExcelProperty("耗材类别")
    private String type; //耗材类别:酒店食品1、金属材料2、电子电器3、建筑材料4、服装服饰5、文化绘画6、工具仪表7、化工药品8、气体类9、维修保养10、其他11

    @ExcelProperty("型号规格及主要技术要求")
    private String standards; //型号规格及主要技术要求

    @ExcelProperty("单位")
    private String unit; //单位

    @ExcelProperty("单价(元)")
    private String price; //耗材单价（元）

    @ExcelProperty("生均数量")
    private String number; //生均数量

    @ExcelProperty("实训项目")
    private String training; //实训项目

    @ExcelProperty("使用学期")
    private String semester; //学期

    @ExcelProperty("二级学院")
    private String department; // 二级学院

    @ExcelIgnore
    private String operator; //操作员

    @ExcelIgnore
    private String operatoraccount; //录入人账号

    @ExcelIgnore
    private String auditor; //审核员

    @ExcelIgnore
    private String operatordate; //录入时间

    @ExcelIgnore
    private String auditordate; //审核时间

    @ExcelProperty("审核状态")
    private String status;  //审核最终状态（用于给提交数据的人查看）0未提交 1通过 3被退回 4审核中

    @ExcelIgnore
    private String status_z; //审核状态（用于确定是谁通过和被谁退回） 0未提交 1提交 2二级学院管理员通过 3二级学院管理员退回 4二级学院教学院长通过 5二级学院教学院长退回 6教务处管理员通过 7教务处管理员退回

    @ExcelProperty("所属专业")
    private String mark;  //所属专业
    @ExcelIgnore
    private String opinion; //审核意见

    @ExcelIgnore
    private String operatorname; //操作员姓名

    @ExcelIgnore
    private String auditorname; //审核员姓名



    public String getOperatoraccount() {
        return operatoraccount;
    }

    public void setOperatoraccount(String operatoraccount) {
        this.operatoraccount = operatoraccount;
    }

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getStatus_z() {
        return status_z;
    }

    public void setStatus_z(String status_z) {
        this.status_z = status_z;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getOperatordate() {
        return operatordate;
    }

    public void setOperatordate(String operatordate) {
        this.operatordate = operatordate;
    }

    public String getAuditordate() {
        return auditordate;
    }

    public void setAuditordate(String auditordate) {
        this.auditordate = auditordate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getOperatorname() {
        return operatorname;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }

    public String getAuditorname() {
        return auditorname;
    }

    public void setAuditorname(String auditorname) {
        this.auditorname = auditorname;
    }

    @Override
    public String toString() {
        return "Consumableslist{" +
                "id=" + id +
                ", review_id='" + review_id + '\'' +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", attribute='" + attribute + '\'' +
                ", type='" + type + '\'' +
                ", standards='" + standards + '\'' +
                ", unit='" + unit + '\'' +
                ", price='" + price + '\'' +
                ", number='" + number + '\'' +
                ", training='" + training + '\'' +
                ", semester='" + semester + '\'' +
                ", department='" + department + '\'' +
                ", operator='" + operator + '\'' +
                ", operatoraccount='" + operatoraccount + '\'' +
                ", auditor='" + auditor + '\'' +
                ", operatordate='" + operatordate + '\'' +
                ", auditordate='" + auditordate + '\'' +
                ", status='" + status + '\'' +
                ", status_z='" + status_z + '\'' +
                ", opinion='" + opinion + '\'' +
                ", mark='" + mark + '\'' +
                ", operatorname='" + operatorname + '\'' +
                ", auditorname='" + auditorname + '\'' +
                '}';
    }

}

