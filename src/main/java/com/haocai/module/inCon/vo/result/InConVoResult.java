package com.haocai.module.inCon.vo.result;

import com.haocai.module.inCon.vo.InConVo;

import java.util.List;

public class InConVoResult {
    private List<InConVo> list;
    private int count;
    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setList(List<InConVo> list) {
        this.list = list;
    }

    public List<InConVo> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "InConVoResult{" +
                "list=" + list +
                ", count=" + count +
                '}';
    }
}
