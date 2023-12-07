package com.haocai.module.consumableslist.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * 根据姓名填充专业
 *
 * @author 磊
 */
public class Consumableslist01 implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelProperty("学院")
    private String department; //耗材名称

    @ExcelProperty("操作人")
    private String operatorname; //耗材属性 1:低值易耗品 2：低值耐用品

    @ExcelProperty("专业")
    private String mark;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOperatorname() {
        return operatorname;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Consumableslist01{" +
                "department='" + department + '\'' +
                ", operatorname='" + operatorname + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}


