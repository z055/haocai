package com.haocai.module.system.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.sql.Timestamp;

public class Dept {

    private String dept_id;
    private String pid;
    private String parent_name;
    private String simple_name;
    private String full_name;
    private Timestamp create_time;
    private Timestamp update_time;
    private long create_user;
    private long update_user;

    public String getParent_name() {
        return parent_name;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getSimple_name() {
        return simple_name;
    }

    public void setSimple_name(String simple_name) {
        this.simple_name = simple_name;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public long getCreate_user() {
        return create_user;
    }

    public void setCreate_user(long create_user) {
        this.create_user = create_user;
    }

    public long getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(long update_user) {
        this.update_user = update_user;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dept_id=" + dept_id +
                ", parent_name='" + parent_name + '\'' +
                ", simple_name='" + simple_name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", create_user=" + create_user +
                ", update_user=" + update_user +
                '}';
    }
}
