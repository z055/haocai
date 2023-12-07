package com.haocai.module.sbModule.vo.param;

import com.haocai.module.sbModule.vo.ReviewTable;

public class ReviewParam extends ReviewTable {
    private int page;
    private int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ReviewParam{" + super.toString() +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
