package com.haocai.module.sbModule.vo.resulet;

import com.haocai.module.sbModule.vo.ReviewTable;

import java.util.List;

public class ReviewResult {
    private int count; //数据总和
    private List<ReviewTable> list; //数据集合

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ReviewTable> getList() {
        return list;
    }

    public void setList(List<ReviewTable> list) {
        this.list = list;
    }
}
