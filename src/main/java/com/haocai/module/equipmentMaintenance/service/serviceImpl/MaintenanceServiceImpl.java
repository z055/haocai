package com.haocai.module.equipmentMaintenance.service.serviceImpl;

import com.haocai.module.equipmentMaintenance.dao.MaintenanceDao;
import com.haocai.module.equipmentMaintenance.service.MaintenanceService;
import com.haocai.module.equipmentMaintenance.vo.Maintenance;
import com.haocai.module.equipmentMaintenance.vo.param.MaintenanceParam;
import com.haocai.module.equipmentMaintenance.vo.result.MaintenanceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    MaintenanceDao maintenanceDao;

    /**
     * 添加实训、比赛维护记录
     * @param maintenance
     * @return
     */
    @Override
    public Boolean addMaintenance(Maintenance maintenance) {
        String id = UUID.randomUUID().toString();
        maintenance.setId(id);
        String name = maintenance.getDepartment()+":"+maintenance.getName();
        maintenance.setName(name);
        if(maintenanceDao.selectMaintenanceByName(name)==0){
            String principal = maintenanceDao.selectNameByAccount(maintenance.getAccount());
            maintenance.setPrincipal(principal);
            int i = maintenanceDao.insertMaintenance(maintenance);
            return i>=1;
        }else{
            return false;
        }


    }

    /**
     * 查询实训、大赛维护数据
     * @param maintenanceParam
     * @return
     */
    @Override
    public MaintenanceResult selectMaintenance(MaintenanceParam maintenanceParam) {
        maintenanceParam.setPage((maintenanceParam.getPage() - 1) * maintenanceParam.getLimit());
        List<Maintenance> trainMegs = maintenanceDao.selectMaintenance(maintenanceParam);
        int i = maintenanceDao.selectMaintenanceCount(maintenanceParam);
        MaintenanceResult trainMegResult = new MaintenanceResult();
        trainMegResult.setResultTM(trainMegs);
        trainMegResult.setCount(i);
        return trainMegResult;
    }

    @Override
    public Boolean deleteMaintenanceById(String id) {
        int i = maintenanceDao.deleteMaintenanceById(id);
        return i>=1;
    }

    @Override
    public Boolean updateMaintenanceById(Maintenance maintenance) {
        String depTrainMeg = maintenance.getDepartment()+":"+maintenance.getName();
        maintenance.setName(depTrainMeg);
        int i = maintenanceDao.updateMaintenanceById(maintenance);
        return i>=0;
    }
}
