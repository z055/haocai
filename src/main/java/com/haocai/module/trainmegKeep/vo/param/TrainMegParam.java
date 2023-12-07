package com.haocai.module.trainmegKeep.vo.param;

import com.haocai.module.trainmegKeep.vo.TrainMeg;

public class TrainMegParam extends TrainMeg {
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
}
