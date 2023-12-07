package com.haocai.module.course.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 课程实体类
 * 对应数据库haocaiguanli
 * 对应表t_course
 */
public class Course {

    @ExcelIgnore
    private String id; //id使用了UUID
    @ExcelIgnore
    private String no; //课程编号
    @ExcelProperty("二级学院")
    private String department; //部门--某个学院
    @ExcelProperty("专业")
    private String major; //专业
    @ExcelProperty("课程名")
    private String name; //课程名称
    @ExcelProperty("实训项目名")
    private String training; //实训名称
    @ExcelIgnore
    private String operator; //操作员
    @ExcelIgnore
    private String operatordate; //操作时间
    @ExcelIgnore
    private String mark; //备注
    @ExcelIgnore
    private String operatorname; //操作员姓名
    @ExcelProperty("课时")
    private int classhour; //课时

    @ExcelIgnore
    private String userAccount; //添加人用户账号

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatordate() {
        return operatordate;
    }

    public void setOperatordate(String operatordate) {
        this.operatordate = operatordate;
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

    public int getClasshour() {
        return classhour;
    }

    public void setClasshour(int classhour) {
        this.classhour = classhour;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", no='" + no + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", name='" + name + '\'' +
                ", training='" + training + '\'' +
                ", operator='" + operator + '\'' +
                ", operatordate='" + operatordate + '\'' +
                ", mark='" + mark + '\'' +
                ", operatorname='" + operatorname + '\'' +
                ", classhour=" + classhour +
                '}';
    }
}
