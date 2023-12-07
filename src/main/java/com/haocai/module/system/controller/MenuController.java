package com.haocai.module.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haocai.module.system.service.MenuService;
import com.haocai.module.system.vo.Menu;
import com.haocai.module.system.vo.Permission;
import com.haocai.utils.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @RequestMapping("/menu_add")
    public String menuAdd () {
        return "/page/system/menu_add";
    }

    @RequestMapping("/menuEdit")
    public String menuEdit () {
        return "/page/system/menu_edit";
    }

    @Autowired
    private MenuService menuService;

    @RequestMapping("/queryAllMenu")
    @ResponseBody
    public String queryAllMenu (){
        List<Permission> list = menuService.queryAllMenu();
        String str = new JsonTemplate("菜单目录", list, list.size()).toString();
        return str;
    }

    @RequestMapping("/addMenu")
    @ResponseBody
    public String addMenu (@RequestBody Menu menu) {
        int index = menuService.addMenu(menu);
        if (index == 1) {
            return new JsonTemplate("添加成功","",index).toString();
        } else if (index == -1) {
            return new JsonTemplate("菜单已存在","",index).toString();
        } else {
            return new JsonTemplate("添加失败","",index).toString();
        }
    }

    @RequestMapping("/getMenu")
    @ResponseBody
    public String getMenu () throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(menuService.getMenu());
        return str;

    }

    @RequestMapping("/editMenu")
    @ResponseBody
    public String editMenu (@RequestBody Menu menu) {
        int index = menuService.updateMenu(menu);
        if (index == 1){
            return new JsonTemplate("修改成功", "", index).toString();
        }
        return new JsonTemplate("修改失败", "", index).toString();

    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public String deleteMenu (@RequestParam("menuId") String menuId) {
        boolean flag = menuService.deleteMenu(menuId);
        if (flag) {
            return new JsonTemplate("删除成功", "", 1).toString();
        }
        return new JsonTemplate("删除失败", "", 0).toString();
    }

    @RequestMapping("/getAllMenu")
    @ResponseBody
    public String getAllMenu (@RequestParam("roleId") String roleId) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(menuService.getAllMenu(roleId));
    }
}
