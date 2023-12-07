package com.haocai.module.system.service;

import com.haocai.module.system.vo.Menu;
import com.haocai.module.system.vo.Permission;
import com.haocai.module.system.vo.ZTreeNode;

import java.util.List;

public interface MenuService {

    /**
     * 根据角色查询所有菜单
     * @param
     * @return
     */
    List<Permission> queryAllMenu();

    /**
     * 添加菜单
     * @param menu 菜单对象
     * @return 影响行数
     */
    int addMenu (Menu menu);

    /**
     * 获取父级菜单
     * @return
     */
    List<Menu> getMenu ();

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    int updateMenu (Menu menu);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    boolean deleteMenu (String menuId);

    /**
     * 获取所有菜单
     * @return
     */
    List<ZTreeNode> getAllMenu (String roleId);
}
