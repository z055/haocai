package com.haocai.module.BorrowingManagement.vo;

import java.io.Serializable;
import java.util.List;

/**
 *  借用管理实体类
 */
public class BorrowingManagementVo implements Serializable {
    /**
     * ID
     */
    private String id;
    /**
     * IDS
     */
    private String[] ids;
    /**
     * 耗材编号
     */
    private String consumablesid;
    /**
     * 耗材名称
     */
    private String name;
    /**
     *耗材数量
     */
    private float number;
    /**
     *二级学院
     */
    private String department;
    /**
     *共享状态(1借用，2不借用)
     */
    private String status;
    /**
     * 耗材属性
     */
    private String attribute;
    /**
     * 耗材类别
     */
    private String type;
    private String price;
    private String con_type;
    public BorrowingManagementVo() {
    }

    public BorrowingManagementVo(String id, String[] ids, String consumablesid, String name, float number, String department, String status, String attribute, String type, String price) {
        this.id = id;
        this.ids = ids;
        this.consumablesid = consumablesid;
        this.name = name;
        this.number = number;
        this.department = department;
        this.status = status;
        this.attribute = attribute;
        this.type = type;
        this.price = price;
    }

    public void setCon_type(String con_type) {
        this.con_type = con_type;
    }

    public String getCon_type() {
        return con_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getConsumablesid() {
        return consumablesid;
    }

    public void setConsumablesid(String consumablesid) {
        this.consumablesid = consumablesid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BorrowingManagementVo{" +
                "id='" + id + '\'' +
                ", ids=" + ids +
                ", consumablesid='" + consumablesid + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", department='" + department + '\'' +
                ", status='" + status + '\'' +
                ", attribute='" + attribute + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", con_type='" + con_type + '\'' +
                '}';
    }
}
