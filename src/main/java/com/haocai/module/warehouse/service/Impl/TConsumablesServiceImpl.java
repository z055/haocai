package com.haocai.module.warehouse.service.Impl;

import com.haocai.module.warehouse.dao.TConsumablesDao;
import com.haocai.module.warehouse.entity.TConsumables;
import com.haocai.module.warehouse.service.TConsumablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.List;

@Service
public class TConsumablesServiceImpl implements TConsumablesService {

    @Autowired
    private TConsumablesDao tConsumablesDao;

    @Override
    public List<TConsumables> getCon(TConsumables tConsumables) {
        tConsumables.setPage(((tConsumables.getPage()-1)*tConsumables.getLimit())+"");
        return tConsumablesDao.queryAllByLimit(tConsumables);
    }

    @Override
    public List<TConsumables> getCon2(TConsumables tConsumables) {
        tConsumables.setPage(((tConsumables.getPage()-1)*tConsumables.getLimit())+"");
        return tConsumablesDao.queryAllByLimit2(tConsumables);
    }

    @Override
    public int count(TConsumables tConsumables) {
        return Math.toIntExact(tConsumablesDao.count(tConsumables));
    }

    @Override
    public int count2(TConsumables tConsumables) {
        return Math.toIntExact(tConsumablesDao.count2(tConsumables));
    }
    @Override
    public int addOutCon(TConsumables tConsumables) {
        Calendar calendar = Calendar.getInstance();
        String outTime = calendar.get(Calendar.YEAR)+"";
        tConsumables.setYear(outTime);
      //   tConsumablesDao.updateOutStatus(tConsumables);
        return tConsumablesDao.addOutCon(tConsumables);
    }

    @Override
    public List<TConsumables> queryPurchase(TConsumables tConsumables) {
        tConsumables.setPage(((tConsumables.getPage()-1)*tConsumables.getLimit())+"");
        return tConsumablesDao.queryPurchase(tConsumables);
    }

    @Override
    public int addInCon(TConsumables tConsumables) {
        Calendar calendar = Calendar.getInstance();
        String inTime = calendar.get(Calendar.YEAR)+"";
        tConsumables.setYear(inTime);
        System.err.println(tConsumables.toString());
    //    tConsumablesDao.updateInStatus(tConsumables);
        return tConsumablesDao.addInCon(tConsumables);
    }
}
