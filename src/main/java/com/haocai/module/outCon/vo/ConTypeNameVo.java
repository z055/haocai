package com.haocai.module.outCon.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConTypeNameVo {
    private String id; //id使用了UUID
    private String no; //课程编号 UUID
    private String department; //部门----二级学院
    private String major; //专业
    private String name; //课程名称
    private String training; //实训名称
}
