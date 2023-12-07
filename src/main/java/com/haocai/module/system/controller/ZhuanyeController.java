package com.haocai.module.system.controller;

import com.haocai.module.system.vo.Zhuanye;
import com.haocai.module.system.service.ZhuanyeService;
import com.haocai.utils.JsonTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Zhuanye)表控制层
 *
 * @author makejava
 * @since 2022-06-13 15:56:53
 */
@Controller
@RequestMapping("/zhuanye")
public class ZhuanyeController {
    /**
     * 服务对象
     */
    @Resource
    private ZhuanyeService zhuanyeService;

    @RequestMapping("majorMgr")
    public String majorMgr () {
        return "/page/system/majorMgr";
    }

    @RequestMapping("major_add")
    public String major_add () {
        return "/page/system/major_add";
    }

    @RequestMapping("major_edit")
    public String major_edit () {
        return "/page/system/major_edit";
    }

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @ResponseBody
    @RequestMapping("queryZhuanye")
    public String queryByPage(@RequestParam("condition") String condition, @RequestParam("page") String page, @RequestParam("limit") String limit) {
        List major = this.zhuanyeService.queryByPage(condition,page,limit);
        int count = new Integer(Math.toIntExact(this.zhuanyeService.countAll()));
        return new JsonTemplate("查询成功",major,count).toString();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Zhuanye> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.zhuanyeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param zhuanye 实体
     * @return 新增结果
     */
    @ResponseBody
    @RequestMapping("/addMajor")
    public String addMajor(@RequestBody Zhuanye zhuanye) {
        int index = this.zhuanyeService.insert(zhuanye);
        if (index >= 1) {
            return new JsonTemplate("添加成功",index,1).toString();
        }
        return new JsonTemplate("添加失败", index, 0).toString();
    }

    /**
     * 编辑数据
     * @param zhuanye 实体
     * @return 编辑结果
     */
    @ResponseBody
    @RequestMapping("/editMajor")
    public String editMajor(@RequestBody Zhuanye zhuanye) {
        System.out.println(zhuanye.toString());
        int index  = this.zhuanyeService.update(zhuanye);
        if (index >= 1) {
            return new JsonTemplate("修改成功", index, 1).toString();
        }
        return new JsonTemplate("修改失败", index, 0).toString();
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ResponseBody
    @RequestMapping("/deleteMajor")
    public String deleteById(@RequestParam("id") Integer id) {
        boolean flag =  this.zhuanyeService.deleteById(id);
        if (flag) {
            return new JsonTemplate("删除成功", true, 1).toString();
        }
        return new JsonTemplate("删除失败", false, 0).toString();
    }

}

