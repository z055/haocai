package com.haocai.module.contents.service;

import com.haocai.module.contents.entity.TConsumableslist;
import java.util.List;

/**
 * 实训耗材目录表(TConsumableslist)表服务接口
 *
 * @author makejava
 * @since 2022-06-01 18:43:49
 */
public interface TConsumableslistService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TConsumableslist queryById(Integer id);

    /**
     * 分页查询
     *
     * @param tConsumableslist 筛选条件
     * @return 查询结果
     */
    List<TConsumableslist> queryByPage(TConsumableslist tConsumableslist, String startPage, String limit);

    /**
     * 统计总行数
     *
     * @param tConsumableslist 查询条件
     * @return 总行数
     */
    long count(TConsumableslist tConsumableslist);

    /**
     * 新增数据
     *
     * @param tConsumableslist 实例对象
     * @return 实例对象
     */
    TConsumableslist insert(TConsumableslist tConsumableslist);

    /**
     * 修改数据
     *
     * @param tConsumableslist 实例对象
     * @return 实例对象
     */
    TConsumableslist update(TConsumableslist tConsumableslist);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
