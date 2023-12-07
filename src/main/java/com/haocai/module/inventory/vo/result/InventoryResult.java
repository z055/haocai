package com.haocai.module.inventory.vo.result;

import com.haocai.module.inventory.vo.InventoryVo;

import java.util.List;

public class InventoryResult {
        private List<InventoryVo> list;
        private int count;

    public String getAllPrice() {
        return AllPrice;
    }

    public void setAllPrice(String allPrice) {
        AllPrice = allPrice;
    }

    private  String AllPrice;
    public void setCount(int count) {
        this.count = count;
    }

    public void setList(List<InventoryVo> list) {
        this.list = list;
    }

    public List<InventoryVo> getList() {
        return list;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "InventoryResult{" +
                "list=" + list +
                ", count=" + count +
                '}';
    }
}
