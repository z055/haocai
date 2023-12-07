package com.haocai.module.sbModule.util;

public class ReDateUtil {
    public String reType(String type) {
        if (type != null) {
            if (type.equals("酒店食品")) {
                type = "1";
            } else if (type.equals("金属材料")) {
                type = "2";
            } else if (type.equals("电子电器")) {
                type = "3";
            } else if (type.equals("建筑材料")) {
                type = "4";
            } else if (type.equals("服装服饰")) {
                type = "5";
            } else if (type.equals("文化绘画")) {
                type = "6";
            } else if (type.equals("工具仪表")) {
                type = "7";
            } else if (type.equals("化工药品")) {
                type = "8";
            } else if (type.equals("气体类")) {
                type = "9";
            } else if (type.equals("维修保养")) {
                type = "10";
            } else if (type.equals("其他")) {
                type = "11";
            }
        }
        return type;
    }
    public String reAttribute(String attribute) {
        if (attribute != null) {
            if (attribute.equals("低值易耗品")) {
                attribute = "1";
            } else if (attribute.equals("低值耐用品")) {
                attribute = "2";
            }
        }
        return attribute;
    }
    public String reConType(String conType) {
        if (conType != null) {
            if (conType.equals("技能大赛")) {
                conType = "1";
            } else if (conType.equals("生产实训")) {
                conType = "2";
            } else if (conType.equals("基本技能")) {
                conType ="3";
            }
        }
        return conType;
    }
}
