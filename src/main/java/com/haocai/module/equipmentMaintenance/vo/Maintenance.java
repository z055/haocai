package com.haocai.module.equipmentMaintenance.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 生产实训大赛维护类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance implements Serializable {
    String id;
    String department;//二级学院
    String name;//二级学院+设备保养名称 格式：二级学院:名称
    String principal;//责任人姓名
    String account;//责任人账号
}
