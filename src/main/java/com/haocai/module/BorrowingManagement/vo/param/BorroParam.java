package com.haocai.module.BorrowingManagement.vo.param;

import com.haocai.module.BorrowingManagement.vo.BorrowingManagementVo;

/**
 * 耗材分页使用的实体类
 */
public class BorroParam extends BorrowingManagementVo {
    private int page;
    private int limit;
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getLimit() {
        return limit;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getPage() {
        return page;
    }
    @Override
    public String toString() {
        return "BorroParam{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
