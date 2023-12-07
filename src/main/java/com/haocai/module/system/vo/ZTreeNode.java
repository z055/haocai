package com.haocai.module.system.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class ZTreeNode {
    /**
     * 节点id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Object id;

    /**
     * 父节点id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Object pId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 是否打开节点（默认打开）
     */
    private Boolean open = true;

    /**
     * 是否被选中
     */
    private Boolean checked;

    /**
     * 节点图标（路径）
     */
    private String icon;

    /**
     * 节点图标（类名）
     */
    private String iconSkin;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getpId() {
        return pId;
    }

    public void setpId(Object pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    @Override
    public String toString() {
        return "ZTreeNode{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", open=" + open +
                ", checked=" + checked +
                ", icon='" + icon + '\'' +
                ", iconSkin='" + iconSkin + '\'' +
                '}';
    }
}
