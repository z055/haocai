package com.haocai.module.system.service;

import com.haocai.module.system.vo.User;
import com.haocai.module.system.vo.UserData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 登录方法
     * @param account 账号
     * @param password 密码
     * @return 用户姓名
     */
    User login(String account, String password);

    /**
     * 单点登录获取信息接口
     * @param account 账号
     * @return 用户姓名
     */
    User loginInfo(String account);

    /**
     * 用于单点登录
     * @param userData
     * @return
     */
    User singleLogin(UserData userData);

    /**
     * 依条件查询用户信息
     * @param condition 账号/姓名/手机号
     * @param timeLimit 时间段
     * @param page 页数
     * @param limit 每页条数
     * @return
     */
    Map<String,Object> queryUserByCondition(String condition, String timeLimit, String deptId,String page, String limit);

    /**
     * 根据id查找用户
     * @param userId
     * @return
     */
    User findUserByUserId(Long userId);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 重置密码
     * @param userId
     * @return
     */
    int reset(Long userId);

    /**
     * 分配角色
     * @param roleIds
     * @return
     */
    int changeRole(String roleIds,Long userId);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 改变用户状态
     * @param userId
     * @return
     */
    int changeStatus(Long userId,String status);

    /**
     * 更改用户基本信息
     * @param user
     * @return
     */
    int changeUserInfo(User user);

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param userId
     * @return
     */
    int changePassword(String oldPassword,String newPassword,Long userId);

    /**
     * 获取专业填充下拉框
     * @return
     */
    List<Map<String,Object>> findMajorToSelect();

    /**
     * 新用户添加角色
     * @param roleId
     * @param majorId
     * @param userId
     * @return
     */
    int setRole(String roleId,String majorId,String userId);

    /**
     * 根据角色查询用户
     * @param roleName 角色名
     * @return
     */
    List<User> getUserByRoleName(String roleName,String department);

    HashMap<String,Object> getUserByRoleNameToTable(String page, String limit, String roleName,User user);

    String selectRoleIdByName(String name);

}
