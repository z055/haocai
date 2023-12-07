package com.haocai.module.consumableslist.result;

import com.haocai.module.consumableslist.vo.ReviewTable;

import java.util.List;

/**
 * 查询当前用户所需要审核的全部数据返回类
 */
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
