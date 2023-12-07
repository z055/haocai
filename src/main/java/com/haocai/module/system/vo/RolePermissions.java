package com.haocai.module.system.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class RolePermissions{

    @JsonSerialize(using = ToStringSerializer.class)
    private long relation_id;
    @JsonSerialize(using = ToStringSerializer.class)
    private long roleId;
    @JsonSerialize(using = ToStringSerializer.class)
    private long menuId;
    private Permission permission;

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(long relation_id) {
        this.relation_id = relation_id;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RolePermissions{" +
                "relation_id=" + relation_id +
                ", roleId=" + roleId +
                ", menuId=" + menuId +
                ", permission=" + permission +
                '}';
    }
}
