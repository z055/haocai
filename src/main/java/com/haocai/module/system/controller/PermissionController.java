package com.haocai.module.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haocai.module.system.service.RolePermissionsService;
import com.haocai.module.system.vo.Menu;
import com.haocai.module.system.vo.User;
import com.haocai.utils.JsonTemplate;
import com.haocai.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private RolePermissionsService rolePermissionsService;

    /**
     * 按照需求获取角色的权限
     * @return
     */
    @RequestMapping(value = "/getPermissionBySelect")
    @ResponseBody
    public String getPermissionBySelect(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        long[] roleId = ValidateUtil.toLongArray(user.getRoleId().split(","));
        Set<String> menuId = rolePermissionsService.menuId(roleId);
        List<Menu> permission = rolePermissionsService.getPermission(menuId, 0);
        return new JsonTemplate("菜单目录",permission,permission.size()).toString();
    }


    /**
     * 初始化菜单
     * @return
     */
    @RequestMapping(value = "/initMenu")
    @ResponseBody
    public Map<String,Object> initMenu(HttpServletRequest request) throws JsonProcessingException {
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> homeInfo = new HashMap<>();
        homeInfo.put("title", "首页");
        homeInfo.put("icon", "fa fa-home");
        homeInfo.put("href", "/page/welcome");
        map.put("homeInfo", homeInfo);
        Map<String, Object> logoInfo = new HashMap<>();
        logoInfo.put("title", "耗材管理");
        logoInfo.put("image", "/images/logo.png");
        logoInfo.put("code","logoInfo");
        logoInfo.put("href", "");
        map.put("logoInfo", logoInfo);
        Set<Object> menuInfo = new HashSet<Object>();//保证菜单的顺序
        if (user.getRoleId() == null || "".equals(user.getRoleId())) {
            return map;
        }
        long[] roleId = ValidateUtil.toLongArray(user.getRoleId().split(","));
        System.out.println("ROLEE"+roleId);
        Set<String> menuId = rolePermissionsService.menuId(roleId);
        List<Menu> permission = rolePermissionsService.getPermission(menuId, 0);
        menuInfo.addAll(permission);
        ObjectMapper objectMapper = new ObjectMapper();
        map.put("menuInfo", menuInfo);
        String str = objectMapper.writeValueAsString(map);
        System.out.println("str"+str);
        System.out.println(map);
        return map;
    }


}
