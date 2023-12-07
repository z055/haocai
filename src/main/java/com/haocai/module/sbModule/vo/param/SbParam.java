package com.haocai.module.sbModule.vo.param;

import com.haocai.module.sbModule.vo.SbVo;

public class SbParam extends SbVo {
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
