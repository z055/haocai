package com.haocai.module.equipmentMaintenance.service;

import com.haocai.module.equipmentMaintenance.vo.Maintenance;
import com.haocai.module.equipmentMaintenance.vo.param.MaintenanceParam;
import com.haocai.module.equipmentMaintenance.vo.result.MaintenanceResult;

public interface MaintenanceService {
    /**
     * 添加实训、比赛维护记录
     * @param maintenance
     * @return
     */
    public Boolean addMaintenance(Maintenance maintenance);

    public MaintenanceResult selectMaintenance(MaintenanceParam maintenanceParam);

    public Boolean deleteMaintenanceById(String id);

    public Boolean updateMaintenanceById(Maintenance maintenance);
}
