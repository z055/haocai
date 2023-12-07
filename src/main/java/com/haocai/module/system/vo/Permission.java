package com.haocai.module.system.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.List;

public class Permission extends RolePermissions{

    @JsonSerialize(using = ToStringSerializer.class)
    private long menuId;
    @JsonSerialize(using = ToStringSerializer.class)
    private long pcode;
    private int sonNum;
    private String target;
    private String code;
    private String title;
    private String icon;
    private String href;
    private List<Permission> child;

    public Permission () {}

    public List<Permission> getChild() {
        return child;
    }

    public void setChild(List<Permission> child) {
        this.child = child;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getPcode() {
        return pcode;
    }

    public void setPcode(long pcode) {
        this.pcode = pcode;
    }

    public int getSonNum() {
        return sonNum;
    }

    public void setSonNum(int sonNum) {
        this.sonNum = sonNum;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "menuId=" + menuId +
                ", pcode=" + pcode +
                ", sonNum=" + sonNum +
                ", target='" + target + '\'' +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", child=" + child +
                '}';
    }
}
