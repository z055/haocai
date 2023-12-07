package com.haocai.module.consumableslist.result;

public class CourseDataResult {
    private String major; //专业
    private String name; //课程
    private String training; //实训项目

    public CourseDataResult(String major) {
        this.major = major;
    }

    public CourseDataResult() {
    }


    private String major_id; //user表中获取的major_id

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major = major_id;//mapper调用set方法将major_id存入到major中
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    @Override
    public String toString() {
        return "CourseDataResult{" +
                "major='" + major + '\'' +
                ", name='" + name + '\'' +
                ", training='" + training + '\'' +
                ", major_id='" + major_id + '\'' +
                '}';
    }
}
