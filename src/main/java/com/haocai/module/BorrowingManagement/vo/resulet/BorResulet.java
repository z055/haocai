package com.haocai.module.BorrowingManagement.vo.resulet;

import com.haocai.module.BorrowingManagement.vo.BorrowingManagementVo;

import java.util.List;

public class BorResulet {
    private List<BorrowingManagementVo> list;
    private int count;

    public void setList(List<BorrowingManagementVo> list) {
        this.list = list;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<BorrowingManagementVo> getList() {
        return list;
    }
    public int getCount() {
        return count;
    }
}
