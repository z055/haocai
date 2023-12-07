package com.haocai.module.system.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.List;

public class Menu {

    @JsonSerialize(using = ToStringSerializer.class)
    private long menuId;
    @JsonSerialize(using = ToStringSerializer.class)
    private long pcode;
    private String code;
    private String title;
    private String href;
    private String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    private int sonNum;
    private String icon;

    public List<Menu> getChild() {
        return child;
    }

    public void setChild(List<Menu> child) {
        this.child = child;
    }

    private List<Menu> child;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSonNum() {
        return sonNum;
    }

    public void setSonNum(int sonNum) {
        this.sonNum = sonNum;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
