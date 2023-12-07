package com.haocai.module.course.result;

import com.haocai.module.course.vo.Course;

import java.util.List;

/**
 * 响应前端类
 */
public class CourseResult {
    private int count; //数据总数
    private List<Course> list; //查询结果集合

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }
}
