package com.haocai.module.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haocai.module.system.service.RolePermissionsService;
import com.haocai.module.system.service.RoleService;
import com.haocai.module.system.vo.Permission;
import com.haocai.module.system.vo.Role;
import com.haocai.module.system.vo.RolePermissions;
import com.haocai.module.system.vo.ZTreeNode;
import com.haocai.utils.JsonTemplate;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class  RoleController {

    @RequestMapping("/role_edit")
    public String role_add(){
        return "page/system/role_edit";
    }

    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionsService rolePermissionsService;

    @ResponseBody
    @RequestMapping("/findRole")
    public String findRole(@RequestParam("page") String page, @RequestParam("limit") String limit, @RequestParam("condition") String condition) throws JsonProcessingException {
        int count = roleService.queryAllRole(condition);
        List<Role> roles = roleService.findRole(page,limit,condition);
        return new JsonTemplate("roles",roles,count).toString();
    }

    @ResponseBody
    @RequestMapping("/addRole")
    public String addRole (@RequestBody Role role) {
        System.out.println("======"+role.getName());
        int index = roleService.addRole(role);
        if (index==1) {
            return new JsonTemplate("添加成功","",index).toString();
        }else if (index==0) {
            return new JsonTemplate("添加失败", "", index).toString();
        } else {
            return new JsonTemplate("角色已存在","",index).toString();
        }
    }

    @ResponseBody
    @RequestMapping("/deleteRole")
    public String deleteRole (@RequestParam("name") String name) {
        int index = roleService.deleteRole(name);
        if (index >= 1) {
            return new JsonTemplate("删除成功", "", index).toString();
        }
        return new JsonTemplate("删除失败", "", index).toString();

    }

    @ResponseBody
    @RequestMapping("/editRole")
    public String editRole (@RequestBody Role role) {
        int index = roleService.updateRole(role);
        if (index == 1) {
            return new JsonTemplate("修改成功", "", index).toString();
        }
        return new JsonTemplate("修改失败", "", index).toString();

    }

    @ResponseBody
    @RequestMapping("/assignRP")
    public String assignRP (@RequestBody List<Map<String,Object>> list) {
        String roleId = list.get(0).get("roleId").toString();
        list.remove(0);
        int index = rolePermissionsService.assignRP(list,roleId);
        if (index > 0) {
            return new JsonTemplate("菜单分配完成",index,1).toString();
        } else {
            return  new JsonTemplate("菜单分配失败", index, 0).toString();
        }
    }

    @ResponseBody
    @RequestMapping("/findRoleToSelect")
    public String findRoleToSelect(@RequestParam("limit") String limit, @RequestParam("page") String page) throws JsonProcessingException {
        int count = roleService.queryAllRole("");
        return new JsonTemplate("",roleService.getRole(),count).toString();
    }

}
