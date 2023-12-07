package com.haocai.module.system.service.impl;

import com.haocai.module.system.vo.Zhuanye;
import com.haocai.module.system.dao.ZhuanyeDao;
import com.haocai.module.system.service.ZhuanyeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * (Zhuanye)表服务实现类
 *
 * @author makejava
 * @since 2022-06-13 15:56:59
 */
@Service("zhuanyeService")
public class ZhuanyeServiceImpl implements ZhuanyeService {
    @Resource
    private ZhuanyeDao zhuanyeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param zhuanyeid 主键
     * @return 实例对象
     */
    @Override
    public Zhuanye queryById(Integer zhuanyeid) {
        return this.zhuanyeDao.queryById(zhuanyeid);
    }

    /**
     * 分页查询
     * @return 查询结果
     */
    @Override
    public List<Zhuanye> queryByPage(String condition, String page, String limit) {
        int p = new Integer(page),
                l = new Integer(limit);
        int startPage = (p-1)*l;
        return this.zhuanyeDao.queryAllByLimit(condition,startPage, l);
    }

    /**
     * 新增数据
     *
     * @param zhuanye 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Zhuanye zhuanye) {
        return this.zhuanyeDao.insert(zhuanye);
    }

    /**
     * 修改数据
     *
     * @param zhuanye 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Zhuanye zhuanye) {
        return this.zhuanyeDao.update(zhuanye);
    }

    /**
     * 通过主键删除数据
     *
     * @param zhuanyeid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer zhuanyeid) {
        return this.zhuanyeDao.deleteById(zhuanyeid) > 0;
    }

    @Override
    public Long countAll() {
        return this.zhuanyeDao.count();
    }
}
