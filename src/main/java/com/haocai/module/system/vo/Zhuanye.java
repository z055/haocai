package com.haocai.module.system.vo;

import java.io.Serializable;

/**
 * (Zhuanye)实体类
 *
 * @author makejava
 * @since 2022-06-13 15:56:53
 */
public class Zhuanye implements Serializable {
    private static final long serialVersionUID = -16865632462199844L;
    /**
     * 专业id
     */
    private Integer zhuanyeid;
    /**
     * 专业名称
     */
    private String name;
    /**
     * 专业编号
     */
    private String bianhao;
    /**
     * 所在学院(部门)
     */
    private String department;


    public Integer getZhuanyeid() {
        return zhuanyeid;
    }

    public void setZhuanyeid(Integer zhuanyeid) {
        this.zhuanyeid = zhuanyeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}

