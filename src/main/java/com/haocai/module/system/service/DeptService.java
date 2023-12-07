package com.haocai.module.system.service;

import com.haocai.module.system.vo.Dept;
import com.haocai.module.system.vo.Role;
import com.haocai.module.system.vo.ZTreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptService {

    /**
     * 查询所有条数
     * @return 条数
     */
    int queryAllCount (String condition);

    /**
     * 查询
     * @return 部门集合
     */
    List<Dept> findDept(@Param("page") String page, @Param("limit") String limit, @Param("condition") String condition);

    /**
     * 添加角色
     * @param dept 返回用户实体
     * @return 0：删除失败，1：删除成功
     */
    int deleteDept(Dept dept);

    /**
     * 查询所有部门用于填充下拉框
     * @return
     */
    List<Dept> findDeptToSelect();

    /**
     * 查询上级部门
     * @return
     */
    List<Dept> getPDept ();

    /**
     * 查询全部部门
     * @return
     */
    List<Dept> getDept ();


    /**
     * 添加部门
     * @param dept
     * @return
     */
    int addDept (Dept dept);

    /**
     * 修改部门
     * @param dept
     * @return
     */
    int updateDept (Dept dept);

    /**
     * 查询部门填充部门树
     * @return
     */
    List<ZTreeNode> getDeptToTree();

}
