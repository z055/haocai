package com.haocai.module.contents.controller;

import com.haocai.module.contents.entity.TConsumableslist;
import com.haocai.module.contents.service.TConsumableslistService;
import com.haocai.utils.JsonTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实训耗材目录表(TConsumableslist)表控制层
 *
 * @author makejava
 * @since 2022-06-01 18:43:41
 */
@RestController
@RequestMapping("tConsumableslist")
public class TConsumableslistController {
    /**
     * 服务对象
     */
    @Resource
    private TConsumableslistService tConsumableslistService;

    /**
     * 分页查询
     *
     * @param tConsumableslist 筛选条件
     * @return 查询结果
     */
    @ResponseBody
    @RequestMapping("queryByPage")
    public String queryByPage(@RequestBody TConsumableslist tConsumableslist, String page, String limit) {
        List lists = this.tConsumableslistService.queryByPage(tConsumableslist, page, limit);
        long count = this.tConsumableslistService.count(tConsumableslist);
        return new JsonTemplate("查询成功", lists, (int)count).toString();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("queryById")
    public ResponseEntity<TConsumableslist> queryById(@RequestParam("id") String id) {
        return ResponseEntity.ok(this.tConsumableslistService.queryById(Integer.valueOf(id)));
    }

    /**
     * 新增数据
     *
     * @param tConsumableslist 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TConsumableslist> add(TConsumableslist tConsumableslist) {
        return ResponseEntity.ok(this.tConsumableslistService.insert(tConsumableslist));
    }

    /**
     * 编辑数据
     *
     * @param tConsumableslist 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TConsumableslist> edit(TConsumableslist tConsumableslist) {
        return ResponseEntity.ok(this.tConsumableslistService.update(tConsumableslist));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.tConsumableslistService.deleteById(id));
    }

}
