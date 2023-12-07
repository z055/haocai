package com.haocai.module.system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.sql.Timestamp;

public class Role {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long role_id;
    @JsonSerialize(using = ToStringSerializer.class)
    private long pid;
    private String name;
    private String description;
    @JsonSerialize(using = ToStringSerializer.class)
    private long create_user;
    @JsonSerialize(using = ToStringSerializer.class)
    private long update_user;

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
