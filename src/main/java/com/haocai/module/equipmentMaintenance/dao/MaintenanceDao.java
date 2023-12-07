package com.haocai.module.equipmentMaintenance.dao;

import com.haocai.module.equipmentMaintenance.vo.Maintenance;
import com.haocai.module.equipmentMaintenance.vo.param.MaintenanceParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceDao {

    /**
     * 添加实训、比赛维护记录
     * @param maintenance
     * @return
     */
    public int insertMaintenance(Maintenance maintenance);

    /**
     * 根据账号查姓名
     * @param account
     * @return
     */
    public String selectNameByAccount(@Param("account") String account);

    public List<Maintenance> selectMaintenance(MaintenanceParam maintenanceParam);
    public int selectMaintenanceCount(MaintenanceParam maintenanceParam);

    public int deleteMaintenanceById(@Param("id") String id);
    public int updateMaintenanceById(Maintenance maintenance);
    public int selectMaintenanceByName(@Param("name") String name);
}
