package com.haocai.module.equipmentMaintenance.vo.param;

import com.haocai.module.equipmentMaintenance.vo.Maintenance;

public class MaintenanceParam extends Maintenance {
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
