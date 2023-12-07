package com.haocai.module.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haocai.module.system.service.DeptService;
import com.haocai.module.system.vo.Dept;
import com.haocai.module.system.vo.Role;
import com.haocai.utils.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("dept_add")
    public String deptAdd () {
        return "/page/system/dept_add";
    }

    @RequestMapping("dept_edit")
    public String deptEdit () {
        return "/page/system/dept_edit";
    }

    @ResponseBody
    @RequestMapping("findDept")
    public String findDept (@RequestParam("page") String page, @RequestParam("limit") String limit, @RequestParam("condition") String condition) {
        int count = deptService.queryAllCount(condition);
        List<Dept> dept = deptService.findDept(page,limit,condition);
        return new JsonTemplate("dept",dept,count).toString();
    }

    @ResponseBody
    @RequestMapping("addDept")
    public String addRole (@RequestBody Dept dept) {
        System.out.println(dept.getPid());
        int index = deptService.addDept(dept);
        if (index >= 1) {
            return new JsonTemplate("部门添加成功",index,1).toString();
        }
        return new JsonTemplate("部门添加失败",index,0).toString();
    }

    @ResponseBody
    @RequestMapping("/getPDept")
    public String getPDept () throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(deptService.getPDept());
    }

    @ResponseBody
    @RequestMapping("/getDept")
    public String getDept () throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(deptService.getDept());
    }


    @ResponseBody
    @RequestMapping("/deleteDept")
    public String deleteDept (Dept dept) {
        int count = deptService.deleteDept(dept);
        if (count >= 1) {
            return new JsonTemplate("删除成功", count,1).toString();
        }
        return new JsonTemplate("删除失败", count,0).toString();
    }

    @ResponseBody
    @RequestMapping("/editDept")
    public String updateDept (@RequestBody Dept dept) {
        int index = deptService.updateDept(dept);
        if (index >= 1) {
            return new JsonTemplate("修改成功", index, 1).toString();
        }
        return new JsonTemplate("修改失败", index, 0).toString();
    }

    @RequestMapping("/findDeptToSelect")
    @ResponseBody
    public String findDeptToSelect() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(deptService.findDeptToSelect());
    }

    /**
     * 渲染部门树
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/getDeptToTree")
    @ResponseBody
    public String getDeptToTree() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(deptService.getDeptToTree());
    }

}
