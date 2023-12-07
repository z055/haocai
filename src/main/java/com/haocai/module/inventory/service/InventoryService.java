package com.haocai.module.inventory.service;


import com.haocai.module.inventory.vo.param.InventoryParam;
import com.haocai.module.inventory.vo.result.InventoryResult;

import java.util.List;

public interface InventoryService {
    InventoryResult getInfo(InventoryParam inventoryParam);
    List<String>getTraining();
    InventoryResult getAll(InventoryParam inventoryParam);
}
