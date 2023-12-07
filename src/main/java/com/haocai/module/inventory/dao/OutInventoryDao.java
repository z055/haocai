package com.haocai.module.inventory.dao;
import com.haocai.module.inventory.vo.InventoryVo;
import com.haocai.module.inventory.vo.param.InventoryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutInventoryDao {
    List<InventoryVo> getInfo(InventoryParam param);
    List<InventoryVo> getCount(InventoryParam param);
    List<InventoryVo> getAllPrices(InventoryParam param);
}
