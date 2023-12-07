package com.haocai.module.contents.dao;

import com.haocai.module.contents.entity.TConsumableslist;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 实训耗材目录表(TConsumableslist)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-01 18:43:45
 */
public interface TConsumableslistDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TConsumableslist queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param tConsumableslist 查询条件
     * @return 对象列表
     */
    List<TConsumableslist> queryAllByLimit(TConsumableslist tConsumableslist, @Param("startPage") int startPage, @Param("limit") int limit);

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
     * @return 影响行数
     */
    int insert(TConsumableslist tConsumableslist);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TConsumableslist> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TConsumableslist> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TConsumableslist> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TConsumableslist> entities);

    /**
     * 修改数据
     *
     * @param tConsumableslist 实例对象
     * @return 影响行数
     */
    int update(TConsumableslist tConsumableslist);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

