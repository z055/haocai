package com.haocai.module.system.dao;

import com.haocai.module.system.vo.Dept;
import com.haocai.module.system.vo.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeptMapper {

    /**
     * 查询所有条数
     * @return 条数
     */
    int queryAllCount (@Param("condition") String condition);


    /**
     * 查询
     * @return 部门集合
     */
    List<Dept> findDept(@Param("startPage") int startPage, @Param("limit") int limit, @Param("condition") String condition);

    /**
     * 删除角色
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
     * 获取上级部门
     * @return
     */
    List<Dept> getPDept ();

    /**
     * 获取全部部门
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
    List<Map<String,Object>> getDeptToTree();

    /**
     * 根据部门编号查询部门id
     * @return
     */
    String getDeptIdBySimpleName(@Param("simpleName") String simpleName);
}

