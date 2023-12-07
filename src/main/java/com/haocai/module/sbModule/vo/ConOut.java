package com.haocai.module.sbModule.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 耗材出库实体类
 * 获取出库耗材数量用
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConOut {
    private String id;
    private String review_id;//审核编号
    private String name;//耗材名称
    private String number;//耗材数量(出库数，外借数)
    private String department;//二级学院
    private String price;//单价
    private String attribute;//耗材属性
    private String type;//耗材类别
    private String status;//审核状态0审核中 1 专业负责人同意 2专业负责人驳回  3二级管理员同意4二极管理员退回 5二级学院教学院长通过 6二级学院教学院长退回
    private String status_z;//审核状态
    private String applicant;//申请人
    private String OutYear;//出库年份
    private String out_time;//申请时间
    private String con_type;//库类别
    private String out_type;//出库类别 3代表外借
    private String standards;//耗材规格
    private String prices;//总价格
    private String LibraryName;//库管员id
    private String applicantName;//申请人姓名
    private String borrow_review_id;//外借审核编号
}
