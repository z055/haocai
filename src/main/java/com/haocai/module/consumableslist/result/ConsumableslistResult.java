package com.haocai.module.consumableslist.result;

import com.haocai.module.consumableslist.vo.Consumableslist;

import java.util.List;

public class ConsumableslistResult {
    private int count; //数据总和
    private List<Consumableslist> list; //数据集合

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Consumableslist> getList() {
        return list;
    }

    public void setList(List<Consumableslist> list) {
        this.list = list;
    }
}
