package com.haocai.module.system.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.haocai.module.system.vo.User;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    /**
     * 根据用户名查找
     *
     * @param account 用户名
     * @return
     */
    User getByAccount(String account);

    /**
     * 登录方法
     *
     * @param account  账号
     * @param password 密码
     * @return 用户姓名
     */
    List<User> login(@Param("account") String account, @Param("password") String password);

    /**
     * 单点登录获取信息接口
     *
     * @param account 账号
     * @return 用户姓名
     */
    List<User> loginInfo(@Param("account") String account);

    /**
     * 单点登录
     *
     * @return
     */
    List<User> singleLogin(Long userId);

    /**
     * 根据条件查询用户
     *
     * @param condition 账号/姓名/手机号
     * @param startTime 起始时间
     * @param endTime   截止时间
     * @param deptId    部门id
     * @return
     */
    int getCountByCondition(@Param("condition") String condition,
                            @Param("startTime") String startTime,
                            @Param("endTime") String endTime,
                            @Param("deptId") String deptId);

    /**
     * 根据条件查询用户
     *
     * @param condition  账号/姓名/手机号
     * @param startTime  起始时间
     * @param endTime    截止时间
     * @param deptId     部门id
     * @param startCount 起始数
     * @param endCount   终止数
     * @return
     */
    @MapKey("userId")
    Map<Long, User> queryUserByCondition(@Param("condition") String condition,
                                         @Param("startTime") String startTime,
                                         @Param("endTime") String endTime,
                                         @Param("deptId") String deptId,
                                         @Param("startCount") int startCount,
                                         @Param("endCount") int endCount);

    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    User findUserByUserId(@Param("userId") Long userId);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUser(@Param("user") User user);

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    int reset(@Param("userId") Long userId, @Param("password") String password);

    /**
     * 分配角色
     *
     * @param roleIds
     * @return
     */
    int changeRole(@Param("roleIds") String roleIds, @Param("userId") Long userId);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(@Param("user") User user);

    /**
     * 单点登录添加角色
     *
     * @param user
     * @return
     */
    int addSingleUser(@Param("user") User user);

    /**
     * 改变用户状态
     *
     * @param userId
     * @return
     */
    int changeStatus(@Param("userId") Long userId, @Param("status") String status);

    /**
     * 更改用户基本信息
     *
     * @param user
     * @return
     */
    int changeUserInfo(@Param("user") User user);

    /**
     * 修改密码
     *
     * @param password
     * @param userId
     * @return
     */
    int changePassword(@Param("password") String password, @Param("userId") Long userId);

    /**
     * 获取专业填充下拉框
     *
     * @return
     */
    List<Map<String, Object>> findMajorToSelect();

    int setRole(@Param("roleId") String roleId, @Param("majorId") String majorId, @Param("userId") String userId);

    /**
     * 根据角色查询用户
     *
     * @param roleName 角色名
     * @return
     */
    List<User> getUserByRoleName(@Param("roleName") String roleName, @Param("department") String department);

    List<User> getUserByRoleNameToTable(@Param("start") int start, @Param("end") int end, @Param("roleName") String roleName);

//采购中心
    List<User> getUserByRoleNameTodep(@Param("start") int start, @Param("end") int end, @Param("roleName") String roleName);


    // 查询上方法总数
    int getUserByRoleNameCount(@Param("roleName") String roleName);

    // 第一级审核人查询部门
    List<User> getUserByRoleNameToTableLevelOne(@Param("start") int start, @Param("end") int end, @Param("roleName") String roleName, @Param("department") String department);

    // 查询上方法总数
    int getUserByRoleNameLevelOneCount(@Param("roleName") String roleName, @Param("department") String department);

    String selectRoleIdByName(String name);


}
