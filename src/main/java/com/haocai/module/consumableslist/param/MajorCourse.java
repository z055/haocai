package com.haocai.module.consumableslist.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MajorCourse {
    private String department;
    private String major;
    private String name;//课程名 set构造注入
    private String operatoraccount;
    private String training;


    public MajorCourse(String department,String major, String operatoraccount) {
        this.department=department;
        this.major = major;
        this.operatoraccount = operatoraccount;
    }

}
