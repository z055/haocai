package com.haocai.module.inventory.vo.param;

import com.haocai.module.inventory.vo.InventoryVo;

public class InventoryParam extends InventoryVo {
        private int page;
        private int limit;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public int getPage() {
        return page;
    }
}
