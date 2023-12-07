package com.haocai.module.trainmegKeep.vo;

import java.io.Serializable;

/**
 * 生产实训大赛维护类
 */

public class TrainMeg implements Serializable {
    String id;
    String department;//二级学院
    String name;//实训名称或大赛名称
    String depTrainMeg;//二级学院+实训项目或大赛名称 格式：二级学院:名称
    String conType;//库类别 1技能大赛 2生产实训
    String principal;//责任人姓名
    String account;//责任人账号

    public TrainMeg() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    @Override
    public String toString() {
        return "TrainMeg{" +
                "id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", depTrainMeg='" + depTrainMeg + '\'' +
                ", conType='" + conType + '\'' +
                ", principal='" + principal + '\'' +
                ", account='" + account + '\'' +
                '}';
    }

    public TrainMeg(String department, String name, String depTrainMeg, String conType, String principal, String account) {
        this.department = department;
        this.name = name;
        this.depTrainMeg = depTrainMeg;
        this.conType = conType;
        this.principal = principal;
        this.account = account;
    }

    public TrainMeg(String id, String department, String name, String depTrainMeg, String conType, String principal, String account) {
        this.id = id;
        this.department = department;
        this.name = name;
        this.depTrainMeg = depTrainMeg;
        this.conType = conType;
        this.principal = principal;
        this.account = account;
    }

    public String getDepTrainMeg() {
        return depTrainMeg;
    }

    public void setDepTrainMeg(String depTrainMeg) {
        this.depTrainMeg = depTrainMeg;
    }

    public String getConType() {
        return conType;
    }

    public void setConType(String conType) {
        this.conType = conType;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
