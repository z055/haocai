package com.haocai.module.system.service.impl;

import com.haocai.module.system.dao.MenuMapper;
import com.haocai.module.system.dao.RolePermissionsMapper;
import com.haocai.module.system.service.MenuService;
import com.haocai.module.system.vo.Menu;
import com.haocai.module.system.vo.Permission;
import com.haocai.module.system.vo.RolePermissions;
import com.haocai.module.system.vo.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RolePermissionsMapper rolePermissionsMapper;

    @Override
    public List<Permission> queryAllMenu() {
        return menuMapper.queryAllMenu();
    }

    @Override
    public int addMenu(Menu menu) {
        Menu menus = menuMapper.queryByCode(menu.getCode());
        if (menus != null) {
            return -1;
        }
            return menuMapper.addMenu(menu);
    }

    @Override
    public List<Menu> getMenu() {
        List<Menu> str = menuMapper.getMenu();
        return str;
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public boolean deleteMenu(String md) {
        long menuId = new Long(md);
        if (menuMapper.deleteMenu(menuId) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<ZTreeNode> getAllMenu(String roleId) {
        List<ZTreeNode> list = new ArrayList<>();
        //获取当前角色所有菜单
        List<String> listRP = rolePermissionsMapper.queryRP_menuId(new Long(roleId));
        List<Menu> menus = menuMapper.getAllMenu();
        menus.forEach(s->{
            ZTreeNode zTreeNode = new ZTreeNode();
            zTreeNode.setName(s.getTitle());
            zTreeNode.setpId(s.getPcode());
            zTreeNode.setId(s.getMenuId());
            if (s.getSonNum() > 0){
                zTreeNode.setIcon("/images/shuzhuangtu.png");
            }else{
                zTreeNode.setIcon("/images/zijiedian.png");
            }
            for (int i = 0; i < listRP.size(); i++) {
               if (listRP.get(i).equals(s.getMenuId()+"")) {
                    zTreeNode.setChecked(true);
                    break;
                }
            }
            list.add(zTreeNode);
        });
        return list;
    }


}
