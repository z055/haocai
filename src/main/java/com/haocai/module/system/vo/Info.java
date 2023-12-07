package com.haocai.module.system.vo;

public class Info extends Data{
    private String workNum;
    private String name;
    private String sex;
    private String password;
    private String department;
    private String category;
    private String status;
    private String type;
    private String attribute;
    private String attributeType;
    private String post;
    private String postGrade;

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostGrade() {
        return postGrade;
    }

    public void setPostGrade(String postGrade) {
        this.postGrade = postGrade;
    }

    @Override
    public String toString() {
        return "Info{" +
                "workNum='" + workNum + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", attribute='" + attribute + '\'' +
                ", attributeType='" + attributeType + '\'' +
                ", post='" + post + '\'' +
                ", postGrade='" + postGrade + '\'' +
                '}';
    }
}
