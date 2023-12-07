package com.haocai.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonTemplate {

    /**
     * layui表格默认返回值
     */
    private static final int CODE = 0;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;
    /**
     * 数据条数
     */
    private int count;

    public JsonTemplate(String msg, Object data, int count) {
        this.msg = msg;
        this.data = data;
        this.count = count;
    }


    @Override
    public String toString() {
        Map<String,Object> map = new HashMap<>();
        map.put("code",CODE);
        map.put("msg",this.msg);
        map.put("data",this.data);
        map.put("count",this.count);
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
