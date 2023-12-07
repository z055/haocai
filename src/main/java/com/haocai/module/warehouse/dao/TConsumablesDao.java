package com.haocai.module.warehouse.dao;

import com.haocai.module.warehouse.entity.TConsumables;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TConsumablesDao {
    /**
     * getCon
     * 查询指定行数据
     * @return
     */
    List<TConsumables> queryAllByLimit(TConsumables tConsumables);


    /**
     * 添加出库
     * @param tConsumables
     * @return
     */
    int addOutCon (TConsumables tConsumables);

    /**
     * 查询采购以供入库
     * @param tConsumables
     * @return
     */
    List<TConsumables> queryPurchase (TConsumables tConsumables);
    /**
     * 外借
     * @param tConsumables
     * @return
     */
    List<TConsumables> queryAllByLimit2(TConsumables tConsumables);
    /**
     * 添加入库
     * @param tConsumables
     * @return
     */
    int addInCon (TConsumables tConsumables);

    /**
     * 修改耗材状态//入库
     * @param tConsumables
     */
    void updateInStatus (TConsumables tConsumables);

    /**
     * 修改耗材状态//出库
     * @param tConsumables
     */
    void updateOutStatus (TConsumables tConsumables);

    /**
     * 统计库存总行数
     * @return
     */
    Long count (TConsumables tConsumables);
    Long count2 (TConsumables tConsumables);
}
