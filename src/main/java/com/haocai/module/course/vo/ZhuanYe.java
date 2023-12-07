package com.haocai.module.course.vo;

public class ZhuanYe {
    private int zhuanyeid; //专业id
    private String name; //专业名字
    private String BianHao; //专业编号
    private String department; //专业所属部门

    public int getZhuanyeid() {
        return zhuanyeid;
    }

    public void setZhuanyeid(int zhuanyeid) {
        this.zhuanyeid = zhuanyeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBianHao() {
        return BianHao;
    }

    public void setBianHao(String bianHao) {
        BianHao = bianHao;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
