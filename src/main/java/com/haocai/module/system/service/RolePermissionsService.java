package com.haocai.module.system.service;

import com.haocai.module.system.dao.RolePermissionsMapper;
import com.haocai.module.system.vo.Menu;
import com.haocai.module.system.vo.Permission;
import com.haocai.module.system.vo.RolePermissions;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RolePermissionsService{

    List<Menu> getPermission (Set<String> menuId, long pcode);

    /**
     * 查询角色对应的权限
     * @param roleId
     * @return
     */
    List<String> queryRP (String roleId);

    /**
     * 操作用户权限
     * @param
     * @return
     */
    int assignRP(List<Map<String, Object>> list, String roleId);

    /**
     *获取多角色对应菜单
     * @param roleId
     * @return
     */
    Set<String> menuId (long[] roleId);
}
