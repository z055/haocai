package com.haocai.utils;

import java.io.Serializable;

/**
 * json数据返回格式定义
 * @author itczy
 *
 */
public class JsonResult implements Serializable {
    public static final String SUCCESS="1"; //成功状态
    public static final String FALL="0"; //失败状态
    private String status; //返回状态
    private Object data; //返回信息
    private String message; //返回提示信息
    public JsonResult(){}
    //返回格式
    public JsonResult(String status, Object data, String message) {
        this.status=status;
        this.data=data;
        this.message=message;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
