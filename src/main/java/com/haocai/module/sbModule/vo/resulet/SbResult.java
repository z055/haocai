package com.haocai.module.sbModule.vo.resulet;

import com.haocai.module.sbModule.vo.SbVo;

import java.util.List;

public class SbResult {
    private List<SbVo> list;
    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public void setList(List<SbVo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public List<SbVo> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "SbResult{" +
                "list=" + list +
                ", count=" + count +
                '}';
    }
}
