package com.haocai.module.inventory.vo;

import java.io.Serializable;

public class InventoryVo implements Serializable {
    /**
     * 材id
     */
    private String id;
    /**
     * 耗材名称
     */
    private String name;
    /**
     * 耗材类别
     */
    private String type;
    /**
     * 属性
     */
    private String attribute;
    /**
     * 耗材状态
     */
    private String status;
    /**
     * 耗材数量
     */
    private String number;
    /**
     * 耗材价格
     */

    private String price;
    /**
     * 耗材学院
     */
    private String department;
    /**
     * 实训项目
     */
    private String training;
    /**
     * 开始年份
     */
    private String yearIn0;
    /**
     * 结束年份
     */
    private String yearIn1;
    /**
     *  盘点耗材总数
     */
    private String sum;

    /**
     * 耗材规格
     */
    private String standards;
    /**
     *总价格
     */
    private String prices;
    /**
     *计算库价值
     */
    private String all;
    /**
     * 库类别
     * @param standards
     */
    private String conType;
    /**
     * 大赛/实训/设备保养名称
     */
    private String conName;

    public InventoryVo() {
    }

    public InventoryVo(String id, String name, String type, String attribute, String status, String number, String price, String department, String training, String yearIn0, String yearIn1, String sum, String standards, String prices, String all, String conType) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.attribute = attribute;
        this.status = status;
        this.number = number;
        this.price = price;
        this.department = department;
        this.training = training;
        this.yearIn0 = yearIn0;
        this.yearIn1 = yearIn1;
        this.sum = sum;
        this.standards = standards;
        this.prices = prices;
        this.all = all;
        this.conType = conType;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getYearIn0() {
        return yearIn0;
    }

    public void setYearIn0(String yearIn0) {
        this.yearIn0 = yearIn0;
    }

    public String getYearIn1() {
        return yearIn1;
    }

    public void setYearIn1(String yearIn1) {
        this.yearIn1 = yearIn1;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getConType() {
        return conType;
    }

    public void setConType(String conType) {
        this.conType = conType;
    }

    @Override
    public String toString() {
        return "InventoryVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", attribute='" + attribute + '\'' +
                ", status='" + status + '\'' +
                ", number='" + number + '\'' +
                ", price='" + price + '\'' +
                ", department='" + department + '\'' +
                ", training='" + training + '\'' +
                ", yearIn0='" + yearIn0 + '\'' +
                ", yearIn1='" + yearIn1 + '\'' +
                ", sum='" + sum + '\'' +
                ", standards='" + standards + '\'' +
                ", prices='" + prices + '\'' +
                ", all='" + all + '\'' +
                ", conType='" + conType + '\'' +
                ", conName='" + conName + '\'' +
                '}';
    }
}

