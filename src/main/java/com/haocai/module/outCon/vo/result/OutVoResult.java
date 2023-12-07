package com.haocai.module.outCon.vo.result;

import com.haocai.module.outCon.vo.OutConVo;

import java.util.List;

public class OutVoResult {
    private int count;
    private List<OutConVo> list;

    public void setList(List<OutConVo> list) {
        this.list = list;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public List<OutConVo> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "OutVoResult{" +
                "count=" + count +
                ", list=" + list +
                '}';
    }
}
