package com.haocai.module.system.service;

import com.haocai.module.system.vo.Role;
import com.haocai.module.system.vo.ZTreeNode;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RoleService {

    String getRoleName(String roleIds);

    int queryAllRole(String condition);

    /**
     * 查询
     * @return 角色集合
     */
    List<Role> findRole(@RequestParam("page") String page, @RequestParam("limit") String limit, @RequestParam("condition") String condition);

    /**
     * 添加角色
     * @param role 角色实体
     * @return -1：角色已存在，0：添加失败，1：添加成功
     */
    int addRole (Role role);

    /**
     * 删除角色
     * @param name 角色名
     * @return 0；删除成功，1：删除失败
     */
    int deleteRole (String name);

    /**
     * 更新角色信息
     * @param role 角色实体
     * @return  0：删除失败，1：删除成功
     */
    int updateRole (Role role);

    /**
     * 取得所有角色填充角色树
     * @return
     */
    List<ZTreeNode> getRoleToTree(String roleIds);

    List<Role> getRole();
}
