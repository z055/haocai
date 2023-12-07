package com.haocai.module.contents.service.impl;

import com.haocai.module.contents.entity.TConsumableslist;
import com.haocai.module.contents.dao.TConsumableslistDao;
import com.haocai.module.contents.service.TConsumableslistService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实训耗材目录表(TConsumableslist)表服务实现类
 *
 * @author makejava
 * @since 2022-06-01 18:43:51
 */
@Service("tConsumableslistService")
public class TConsumableslistServiceImpl implements TConsumableslistService {
    @Resource
    private TConsumableslistDao tConsumableslistDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TConsumableslist queryById(Integer id) {
        return this.tConsumableslistDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param tConsumableslist 筛选条件
     * @return 查询结果
     */
    @Override
    public List<TConsumableslist> queryByPage(TConsumableslist tConsumableslist, String page, String limit) {
        long total = this.tConsumableslistDao.count(tConsumableslist);
        int  p = Integer.parseInt(page),
                l = Integer.parseInt(limit);
        int startPage = (p-1)*l;
        return this.tConsumableslistDao.queryAllByLimit(tConsumableslist, startPage,l);
    }

    @Override
    public long count(TConsumableslist tConsumableslist) {
        return this.tConsumableslistDao.count(tConsumableslist);
    }

    /**
     * 新增数据
     *
     * @param tConsumableslist 实例对象
     * @return 实例对象
     */
    @Override
    public TConsumableslist insert(TConsumableslist tConsumableslist) {
        this.tConsumableslistDao.insert(tConsumableslist);
        return tConsumableslist;
    }

    /**
     * 修改数据
     *
     * @param tConsumableslist 实例对象
     * @return 实例对象
     */
    @Override
    public TConsumableslist update(TConsumableslist tConsumableslist) {
        this.tConsumableslistDao.update(tConsumableslist);
        return this.queryById(tConsumableslist.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tConsumableslistDao.deleteById(id) > 0;
    }
}
