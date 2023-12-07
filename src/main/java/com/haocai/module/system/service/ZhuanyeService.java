package com.haocai.module.system.service;

import com.haocai.module.system.vo.Zhuanye;

import java.util.List;

/**
 * (Zhuanye)表服务接口
 *
 * @author makejava
 * @since 2022-06-13 15:56:59
 */
public interface ZhuanyeService {

    /**
     * 通过ID查询单条数据
     *
     * @param zhuanyeid 主键
     * @return 实例对象
     */
    Zhuanye queryById(Integer zhuanyeid);

    /**
     * 分页查询
     * @return 查询结果
     */
    List<Zhuanye> queryByPage(String condition, String page, String limit);

    /**
     * 新增数据
     *
     * @param zhuanye 实例对象
     * @return 实例对象
     */
    int insert(Zhuanye zhuanye);

    /**
     * 修改数据
     *
     * @param zhuanye 实例对象
     * @return 实例对象
     */
    int update(Zhuanye zhuanye);

    /**
     * 通过主键删除数据
     *
     * @param zhuanyeid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer zhuanyeid);

    /**
     * 条数
     * @return
     */
    Long countAll ();

}
