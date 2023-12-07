package com.haocai.module.system.service.impl;

import com.haocai.module.system.dao.DeptMapper;
import com.haocai.module.system.dao.UserMapper;
import com.haocai.module.system.service.RoleService;
import com.haocai.module.system.service.UserService;
import com.haocai.module.system.vo.Data;
import com.haocai.module.system.vo.Info;
import com.haocai.module.system.vo.User;
import com.haocai.module.system.vo.UserData;
import com.haocai.utils.MD5Util;
import com.haocai.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String account, String password) {
        // 获取MD5密码盐
        User user = userMapper.getByAccount(account);
        if(ValidateUtil.isNotEmpty(user)){
            // 使用MD5进行加密处理
            password = MD5Util.md5(password,user.getSalt());
            User login = userMapper.login(account, password).get(0);
            // 密码正确
            if(ValidateUtil.isNotEmpty(login)){
                login.setRoleName(roleService.getRoleName(login.getRoleId()));
                return login;
            }
        }
        return null;
    }

    @Override
    public User loginInfo(String account) {
        User user = userMapper.getByAccount(account);
        if(ValidateUtil.isNotEmpty(user)){
            User login = userMapper.loginInfo(account).get(0);
            if(ValidateUtil.isNotEmpty(login)){
                login.setRoleName(roleService.getRoleName(login.getRoleId()));
                return login;
            }
        }
        return null;
    }

    @Override
    public User singleLogin(UserData userData) {
        Data data = userData.getData();
        Info info = data.getInfo();
        User byAccount = userMapper.getByAccount(data.getUsername());
        if (ValidateUtil.isNotEmpty(byAccount)){
            User singleLogin = userMapper.singleLogin(byAccount.getUserId()).get(0);
            singleLogin.setRoleName(roleService.getRoleName(singleLogin.getRoleId()));
            return singleLogin;
        }else{
            User user = new User();
            String salt = MD5Util.getSalt(8);
            // 默认密码“111111”
            String password = MD5Util.md5("111111", salt);
            user.setAccount(data.getUsername());
            user.setPassword(password);
            user.setSalt(salt);
            user.setName(info.getName());
            user.setSex("男".equals(info.getSex()) ? "M" : "F");
            user.setDeptId(deptMapper.getDeptIdBySimpleName(info.getDepartment()));
            // 插入数据并接收回传主键id
            userMapper.addSingleUser(user);
            User singleLogin = userMapper.singleLogin(user.getUserId()).get(0);
            singleLogin.setRoleName(roleService.getRoleName(singleLogin.getRoleId()));
            return singleLogin;
        }
    }

    @Override
    public Map<String,Object> queryUserByCondition(String condition, String timeLimit, String deptId, String page, String limit) {
        Map<String,Object> map = new HashMap<>();
        String startTime = "";
        String endTime = "";
        int startCount = (Integer.parseInt(page) - 1) * Integer.parseInt(limit);
        int endCount = startCount + Integer.parseInt(limit);
        if(!"".equalsIgnoreCase(timeLimit) && timeLimit != null){
            String[] time = timeLimit.split(" - ");
            startTime = time[0] + " 0:0:0";
            endTime = time[1] + " 23:59:59";
        }
        if("-1".equalsIgnoreCase(deptId)){
            deptId = "0";
        }
        // 查询所有用户
        Map<Long, User> userMap = userMapper.queryUserByCondition(condition, startTime, endTime, deptId, startCount, endCount);
        for (User user : userMap.values()){
            // 循环获取roleName
            user.setRoleName(roleService.getRoleName(user.getRoleId()));
        }
        int count = userMapper.getCountByCondition(condition, startTime, endTime, deptId);
        map.put("count",count);
        map.put("data",userMap);
        return map;
    }

    @Override
    public User findUserByUserId(Long userId) {
        return userMapper.findUserByUserId(userId);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int reset(Long userId) {
        User user = userMapper.findUserByUserId(userId);
        String password = MD5Util.md5("111111", user.getSalt());
        return userMapper.reset(userId,password);
    }

    @Override
    public int changeRole(String roleIds,Long userId) {
        return userMapper.changeRole(roleIds,userId);
    }

    @Override
    public int addUser(User user) {
        String salt = MD5Util.getSalt(8);
        String password = MD5Util.md5(user.getPassword(),salt);
        user.setSalt(salt);
        user.setPassword(password);
        return userMapper.addUser(user);
    }

    @Override
    public int changeStatus(Long userId,String status) {
        return userMapper.changeStatus(userId, status);
    }

    @Override
    public int changeUserInfo(User user) {
        return userMapper.changeUserInfo(user);
    }

    @Override
    public int changePassword(String oldPassword, String newPassword, Long userId) {
        User user = userMapper.findUserByUserId(userId);
        oldPassword = MD5Util.md5(oldPassword,user.getSalt());
        if(!user.getPassword().equals(oldPassword)){
            return -1;
        }
        String password = MD5Util.md5(newPassword, user.getSalt());
        return userMapper.changePassword(password,userId);
    }

    @Override
    public List<Map<String, Object>> findMajorToSelect() {
        return userMapper.findMajorToSelect();
    }

    @Override
    public int setRole(String roleId, String majorId, String userId) {
        return userMapper.setRole(roleId,majorId,userId);
    }

    @Override
    public List<User> getUserByRoleName(String roleName,String department) {
        System.err.println(department+"===========");
        return userMapper.getUserByRoleName(roleName,department);
    }

    @Override
    public HashMap<String,Object> getUserByRoleNameToTable(String page, String limit, String roleName,User user) {
        HashMap<String,Object> map = new HashMap<>();
        int startCount = (Integer.parseInt(page) - 1) * Integer.parseInt(limit);
        int endCount = startCount + Integer.parseInt(limit);
        List<User> users = null;
        int count = 0;
        if ("二级学院管理员".equals(roleName)){
            users = userMapper.getUserByRoleNameToTableLevelOne(startCount, endCount, roleName,user.getDeptName());
            count = userMapper.getUserByRoleNameLevelOneCount(roleName,user.getDeptName());
        }else
        if("二级学院教学院长".equals(roleName)){
            users = userMapper.getUserByRoleNameToTableLevelOne(startCount, endCount, roleName,user.getDeptName());
            count = userMapper.getUserByRoleNameLevelOneCount(roleName,user.getDeptName());
        }else if ("教务处管理员".equals(roleName)){
            users = userMapper.getUserByRoleNameToTable(startCount, endCount, roleName);
            count = userMapper.getUserByRoleNameCount(roleName);
        } else if ("采购中心".equals(roleName)){
            users = userMapper.getUserByRoleNameTodep(startCount, endCount, roleName);
            count = userMapper.getUserByRoleNameCount(roleName);
        }
        map.put("data",users);
        map.put("count",count);
        return map;
    }

    @Override
    public String selectRoleIdByName(String name) {
        return userMapper.selectRoleIdByName(name);
    }
}
