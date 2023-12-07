package com.haocai.module.system.dao;

import com.haocai.module.system.vo.Permission;
import com.haocai.module.system.vo.RolePermissions;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RolePermissionsMapper {

    /**
     * 根据根据用户ID和菜单父id获取菜单，配合递归使用
     * @param
     * @param pcode
     * @return
     */
    List<RolePermissions> getListRolePermission(@Param("pcode") long pcode);

    /**
     * 查询角色对应的权限
     * @param roleId
     * @return
     */
    List<String> queryRP_menuId (@Param("roleId") long roleId);

    RolePermissions queryRP(@Param("roleId") long roleId, @Param("menuId") long menuId);

    /**
     * 添加用户权限
     * @param
     * @return
     */
    int addRP(@Param("list") List<Map<String, Object>> list, @Param("roleId") long roleId);

    /**
     * 删除用户权限
     * @param rolePermissions
     * @return
     */
    int deleteRP (RolePermissions rolePermissions);


}
