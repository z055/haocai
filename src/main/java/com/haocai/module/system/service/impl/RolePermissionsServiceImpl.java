package com.haocai.module.system.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haocai.module.system.dao.MenuMapper;
import com.haocai.module.system.dao.RolePermissionsMapper;
import com.haocai.module.system.service.RolePermissionsService;
import com.haocai.module.system.vo.Menu;
import com.haocai.module.system.vo.Permission;
import com.haocai.module.system.vo.RolePermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RolePermissionsServiceImpl implements RolePermissionsService {

    @Autowired
    private RolePermissionsMapper rolePermissionsMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 递归获取目录
      * @param
     * @param pcode
     * @return
     */
    @Override
    public List<Menu> getPermission(Set<String> menuId, long pcode) {
        System.out.println("menuId++++"+menuId);
        List<Menu> list = new ArrayList<>();
        List<Menu> permission = menuMapper.getPermission(pcode);
        outer:for (int i = 0; i < permission.size(); i++){
            int finalI = i;
            Iterator<String> iterator = menuId.iterator();
            while (iterator.hasNext()){
                if (iterator.next().equals(permission.get(finalI).getMenuId()+"")) {
                    continue outer;
                  }
            }
            permission.remove(i);
            i--;
        }
        permission.forEach(t->{
            if (t.getSonNum() != 0) {
                List<Menu> permissions = getPermission(menuId,t.getMenuId());
                t.setChild(permissions);
            }
            list.add(t);
        });
        return list;
    }

    @Override
    public Set<String> menuId (long[] roleId) {
        Set<String> menuId = new HashSet<>();
        for(int i = 0; i < roleId.length; i++) {
            List<String> str = rolePermissionsMapper.queryRP_menuId(roleId[i]);
            menuId.addAll(str);
        }
        return menuId;
    }

    @Override
    public List<String> queryRP(String roleId) {
        return rolePermissionsMapper.queryRP_menuId(Long.parseLong(roleId));
    }

    @Override
    public int assignRP(List<Map<String, Object>> list, String roleId) {
        RolePermissions rolePermissions = new RolePermissions();
        rolePermissions.setRoleId(Long.parseLong(roleId));
        rolePermissionsMapper.deleteRP(rolePermissions);
        if (list.isEmpty()) {
            return 1;
        }
        return rolePermissionsMapper.addRP(list, Long.parseLong(roleId));
    }

}
