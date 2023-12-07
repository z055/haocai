package com.haocai.module.warehouse.service;

import com.haocai.module.warehouse.entity.TConsumables;

import java.util.List;

public interface TConsumablesService {
    /**
     * 获取库存
     * @return
     */
    List<TConsumables> getCon(TConsumables tConsumables);


    /**
     * 统计总行数
     * @return
     */
    int count (TConsumables tConsumables);

    /**
     * 外借申请 库存表查
     * @param tConsumables
     * @return
     */
    List<TConsumables> getCon2(TConsumables tConsumables);
    /**
     * 外借申请 库存表查 统计总行数
     * @return
     */
    int count2 (TConsumables tConsumables);
    /**
     * 查询采购以供入库
     * @param tConsumables
     * @return
     */
    List<TConsumables> queryPurchase (TConsumables tConsumables);

    /**
     * 添加入库
     * @param tConsumables
     * @return
     */
    int addInCon (TConsumables tConsumables);

    /**
     * 添加入库
     * @param tConsumables
     * @return
     */
    int addOutCon (TConsumables tConsumables);
}
