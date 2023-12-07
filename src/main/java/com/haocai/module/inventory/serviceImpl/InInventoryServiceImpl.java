package com.haocai.module.inventory.serviceImpl;

import com.haocai.module.inventory.dao.InInventoryDao;
import com.haocai.module.inventory.service.InInventoryService;
import com.haocai.module.inventory.vo.InventoryVo;
import com.haocai.module.inventory.vo.param.InventoryParam;
import com.haocai.module.inventory.vo.result.InventoryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InInventoryServiceImpl implements InInventoryService {
    @Autowired
    InInventoryDao inInventoryDao;
    @Override
    public InventoryResult getInfo(InventoryParam param) {
            int count= inInventoryDao.getCount(param).size();
            param.setPage((param.getPage()-1)*param.getLimit());
            List<InventoryVo> list= inInventoryDao.getInfo(param);
            InventoryResult inventoryResult=new InventoryResult();
            inventoryResult.setCount(count);
            inventoryResult.setList(list);
        return inventoryResult;
    }

    @Override
    public InventoryResult getAllPrices(InventoryParam param) {
        int count= inInventoryDao.getCount(param).size();
        List<InventoryVo> list= inInventoryDao.getAllPrices(param);
        InventoryResult inventoryResult=new InventoryResult();
        inventoryResult.setList(list);
        return inventoryResult;

    }


}
