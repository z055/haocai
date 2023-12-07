package com.haocai.module.system.dao;

import com.haocai.module.system.vo.Menu;
import com.haocai.module.system.vo.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    /**
     * 根据角色查询所有菜单
     * @param
     * @return
     */
    List<Permission> queryAllMenu ();

    /**
     * 添加菜单
     * @param menu 菜单对象
     * @return 影响行数
     */
      int addMenu (Menu menu);

    /**
     * 通过名字查询菜单
     * @param code
     * @return
     */
      Menu queryByCode (@Param("code") String code);

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
    int deleteMenu (@Param("menuId") long menuId);

    /**
     * 获取所有菜单
     * @return
     */
    List<Menu> getAllMenu ();

    /**
     * 根据父id查询菜单
     * @param pcode
     * @return
     */
    List<Menu> getPermission (@Param("pcode") long pcode);
}
