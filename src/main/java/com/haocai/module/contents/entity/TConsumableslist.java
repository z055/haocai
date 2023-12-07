package com.haocai.module.contents.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 实训耗材目录表(TConsumableslist)实体类
 *
 * @author makejava
 * @since 2022-06-01 18:43:48
 */
public class TConsumableslist implements Serializable {
    private static final long serialVersionUID = 809735553962147748L;
    /**
     * ID
     */
    private Integer id;
    /**
     * 耗材名称
     */
    private String name;
    /**
     * 耗材编号
     */
    private String no;
    /**
     * 耗材属性:低值易耗品1、低值耐用品2
     */
    private String attribute;
    /**
     * 耗材类别:酒店食品1、金属材料2、电子电器3、建筑材料4、服装服饰5、文化绘画6、工具仪表7、化工药品8、气体类9、维修保养10、其他11
     */
    private String type;
    /**
     * 型号规格及主要技术要求
     */
    private String standards;
    /**
     * 单位
     */
    private String unit;
    /**
     * 耗材单价（元）
     */
    private Float price;
    /**
     * 生均数量
     */
    private Float number;
    /**
     * 实训项目
     */
    private String training;
    /**
     * 学期
     */
    private String semester;
    /**
     * 二级学院
     */
    private String department;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 审核员
     */
    private String auditor;
    /**
     * 录入时间
     */
    private Date operatordate;
    /**
     * 审核时间
     */
    private Date auditordate;
    /**
     * 审核状态 0提交 1通过 2退回
     */
    private String status;
    /**
     * 审核意见
     */
    private String opinion;
    /**
     * 备注
     */
    private String mark;
    /**
     * 操作员姓名
     */
    private String operatorname;
    /**
     * 审核员姓名
     */
    private String auditorname;


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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
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

    public Date getOperatordate() {
        return operatordate;
    }

    public void setOperatordate(Date operatordate) {
        this.operatordate = operatordate;
    }

    public Date getAuditordate() {
        return auditordate;
    }

    public void setAuditordate(Date auditordate) {
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

}

