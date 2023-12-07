package com.haocai.module.inventory.serviceImpl;
import com.haocai.module.inventory.dao.OutInventoryDao;
import com.haocai.module.inventory.service.OutInventoryService;
import com.haocai.module.inventory.vo.InventoryVo;
import com.haocai.module.inventory.vo.param.InventoryParam;
import com.haocai.module.inventory.vo.result.InventoryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutInventoryServiceImpl implements OutInventoryService {
    @Autowired
  private   OutInventoryDao outInventoryDao;
    @Override
    public InventoryResult getInfo(InventoryParam param) {
        param.setPage((param.getPage()-1)* param.getLimit());
          List<InventoryVo> list=  outInventoryDao.getInfo(param);
          int count=outInventoryDao.getCount(param).size();
          InventoryResult result=new InventoryResult();
          result.setList(list);
          result.setCount(count);
        return result;
    }

    @Override
    public InventoryResult getAllPrices(InventoryParam param) {
        List<InventoryVo> list=  outInventoryDao.getAllPrices(param);
        InventoryResult result=new InventoryResult();
        result.setList(list);
        return result;
    }
}
