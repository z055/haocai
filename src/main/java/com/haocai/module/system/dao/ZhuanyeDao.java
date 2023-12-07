package com.haocai.module.system.dao;

import com.haocai.module.system.vo.Zhuanye;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Zhuanye)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-13 15:56:53
 */
public interface ZhuanyeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param zhuanyeid 主键
     * @return 实例对象
     */
    Zhuanye queryById(Integer zhuanyeid);

    /**
     * 查询指定行数
     * @return 对象列表
     */
    List<Zhuanye> queryAllByLimit(@Param("condition") String condition, @Param("startPage") int page, @Param("limit") int limit);

    /**
     * 统计总行数
     * @return 总行数
     */
    long count();

    /**
     * 新增数据
     *
     * @param zhuanye 实例对象
     * @return 影响行数
     */
    int insert(Zhuanye zhuanye);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Zhuanye> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Zhuanye> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Zhuanye> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Zhuanye> entities);

    /**
     * 修改数据
     *
     * @param zhuanye 实例对象
     * @return 影响行数
     */
    int update(Zhuanye zhuanye);

    /**
     * 通过主键删除数据
     *
     * @param zhuanyeid 主键
     * @return 影响行数
     */
    int deleteById(Integer zhuanyeid);

}

