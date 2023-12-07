package com.haocai.module.inventory.serviceImpl;

import com.haocai.module.inventory.dao.InventoryDao;
import com.haocai.module.inventory.service.InventoryService;
import com.haocai.module.inventory.vo.InventoryVo;
import com.haocai.module.inventory.vo.param.InventoryParam;
import com.haocai.module.inventory.vo.result.InventoryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryDao inventoryDao;
    @Override
    public InventoryResult getInfo(InventoryParam inventoryParam) {
      inventoryParam.setPage((inventoryParam.getPage()-1)*inventoryParam.getLimit());
      List<InventoryVo> list=  inventoryDao.getInfo(inventoryParam);
      InventoryResult result=new InventoryResult();
      result.setList(list);
      result.setCount(inventoryDao.getCount(inventoryParam));
        return result ;
    }

    @Override
    public List<String> getTraining() {
        List<InventoryVo> list=inventoryDao.getTraining();
        List<String> trainings=new ArrayList<>();
        for (InventoryVo inventoryVo : list) {
            trainings.add(inventoryVo.getTraining());
        }
        return trainings;
    }

    @Override
    public InventoryResult getAll(InventoryParam inventoryParam) {
        List<InventoryVo> list =  inventoryDao.getAll(inventoryParam);
        InventoryResult result=new InventoryResult();
        result.setList(list);
        return result ;

    }


}
