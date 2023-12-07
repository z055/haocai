package com.haocai.module.consumableslist.param;

import com.haocai.module.consumableslist.vo.Consumableslist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsumableslistParam extends Consumableslist {
    //用于前端分页限制每页数据
    private int page; //前端传来的第几页
    private int limit; //限制查询的数据数量（前端传）
    private String name;//耗材名称
    private String attribute;//耗材属性
    private String type;//耗材类别
    private String status;//审核状态
    private String condition; //前端查询时传来的条件
    private String ids; //用于批量删除或者导出前端传来的多个id
    private String major;// 专业
    private String courseName; //课程名
    private String review_ids;
    private String[] reviewIds;
    private String beginTime; //开始时间
    private String endTime; //结束时间
    private String department;//登录人员的部门
    private List<Object> auditStatus; //审核状态用于限制登录人员所能查看的数据
    private String showPower; //数据展示权限

    public ConsumableslistParam(String department, String major) {
        this.major = major;
        this.department = department;
    }
}
