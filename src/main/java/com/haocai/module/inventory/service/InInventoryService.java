package com.haocai.module.inventory.service;

import com.haocai.module.inventory.vo.param.InventoryParam;
import com.haocai.module.inventory.vo.result.InventoryResult;

public interface InInventoryService {
    InventoryResult getInfo(InventoryParam param);
    InventoryResult getAllPrices(InventoryParam param);
}
