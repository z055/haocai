package com.haocai.module.system.vo;

public class UserData {
    private  String code;
    private  Data data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
