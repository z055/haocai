package com.haocai.module.system.dao;

import com.haocai.module.system.vo.Dept;
import com.haocai.module.system.vo.Role;
import com.haocai.module.system.vo.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    int queryAllCount(@Param("condition") String condition);

    /**
     * 查询
     * @return 角色集合
     */
    List<Role> findRole(@Param("startPage") int startPage, @Param("limit") int limit, @Param("condition") String condition);

    /**
     * 根据名字查询
     * @param name 角色名
     * @return 角色实体
     */
    Role findName(@Param("name") String name);

    /**
     * 添加角色
     * @param role 角色实体
     * @return -1：角色已存在，0：添加失败，1：添加成功
     */
    int addRole (Role role);

    /**
     * 删除角色
     * @param name 用户名
     * @return 0：删除失败，1：删除成功
     */
    int deleteRole(@Param("name") String name);

    /**
     * 修改角色信息
     * @param role 角色实体
     * @return  0：删除失败，1：删除成功
     */
    int updateRole(Role role);

    /**
     * 取得所有角色填充角色树
     * @return
     */
    List<ZTreeNode> getRoleToTree();

    List<Role> getRole();

    /**
     * 根据id查角色
     * @param roleId
     * @return
     */
    Role selectById(@Param("roleId") Long roleId);

}
