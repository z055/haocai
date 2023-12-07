package com.haocai.module.inventory.dao;

import com.haocai.module.inventory.vo.InventoryVo;
import com.haocai.module.inventory.vo.param.InventoryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryDao {
    List<InventoryVo> getInfo(InventoryParam inventoryParam);
    int getCount(InventoryParam inventoryParam);
    List<InventoryVo> getTraining();
    List<InventoryVo> getAll(InventoryParam inventoryParam);
}
