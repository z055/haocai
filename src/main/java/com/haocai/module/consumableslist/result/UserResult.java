package com.haocai.module.consumableslist.result;

public class UserResult {
    private String role; //职位
    private String name; //姓名
    private String department; //隶属学院or部门

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
