package com.haocai.module.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haocai.module.system.service.DeptService;
import com.haocai.module.system.service.RoleService;
import com.haocai.module.system.service.UserService;
import com.haocai.module.system.vo.Dept;
import com.haocai.module.system.vo.User;
import com.haocai.module.system.vo.UserData;
import com.haocai.utils.JsonTemplate;
import com.haocai.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author 王鹏、沈东平
 * @since 2022-5-6
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 用户登录,新增切换角色功能
     *
     * @param account  账号
     * @param password 密码
     * @param request
     * @return
     * @throws IOException
     * @throws ServletException
     * @author 磊
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        @RequestParam("remember") String remember,
                        HttpServletRequest request) throws IOException, ServletException {
        User login = userService.login(account, password);
        if (login != null) {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null || !((User) session.getAttribute("user")).getAccount().equals(account)) {
                updateSession(session, login, remember);
            }
            return "index";
        } else {
            return "login";
        }
    }

    /**
     * 更新 session 中的 user 属性
     * @author 磊
     */
    private void updateSession(HttpSession session, User login, String remember) {
        session.setAttribute("zhiwei", login.getRoleName());
        session.setAttribute("department", login.getDeptName());
        if ("yes".equalsIgnoreCase(remember)) {
            session.setMaxInactiveInterval(60 * 60 * 24 * 7);
        }
        String[] roleNameArr = login.getRoleName().split(",");
        String[] roleIdArr = login.getRoleId()!=null?login.getRoleId().split(","):null;
        String roleName = roleNameArr.length > 0 ? roleNameArr[0] : "";
        String roleId = (roleIdArr != null ? roleIdArr.length : 0) > 0 ? roleIdArr[0] : "";
        login.setRoleBox(login.getRoleName());
        login.setRoleName(roleName);
        login.setRoleId(roleId);
        session.setAttribute("user", login);
    }
//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String login(@RequestParam("account") String account,
//                        @RequestParam("password") String password,
//                        @RequestParam("remember") String remember,
//                        HttpServletRequest request) throws IOException, ServletException {
//        User login = userService.login(account, password);
//        System.out.println(remember);
//        System.out.println(login);
//        if (login != null) {
//            HttpSession session = request.getSession();
//            if (session.getAttribute("user")==null){
//                // 将角色和部门存进session
//                session.setAttribute("zhiwei", login.getRoleName());
//                session.setAttribute("department", login.getDeptName());
//                if ("yes".equalsIgnoreCase(remember)) {
//                    session.setMaxInactiveInterval(60 * 60 * 24 * 7);
//                }
//                login.setRoleBox(login.getRoleName());
//                String[] roleName = login.getRoleName().split(",");
//                String[] roleIds= login.getRoleId().split(",");
//                login.setRoleName(roleName[0]);
//                login.setRoleId(roleIds[0]);
//                session.setAttribute("user", login);
//                User user = (User) session.getAttribute("user");
//            }else {
//                //判断账号是否一致
//                User user = (User) session.getAttribute("user");
//                if (!user.getAccount().equals(account)){
//                    //切换账号
//                    session.setAttribute("zhiwei", login.getRoleName());
//                    session.setAttribute("department", login.getDeptName());
//                    if ("yes".equalsIgnoreCase(remember)) {
//                        session.setMaxInactiveInterval(60 * 60 * 24 * 7);
//                    }
//                    login.setRoleBox(login.getRoleName());
//                    String[] roleName = login.getRoleName().split(",");
//                    String[] roleIds= login.getRoleId().split(",");
//                    login.setRoleName(roleName[0]);
//                    login.setRoleId(roleIds[0]);
//                    session.setAttribute("user", login);
//                    session.setAttribute("user", login);
//                }
//                //切换角色进入
//            }
//            return "index";
//        }
//        return "login";
//    }

    /**
     * 修改session中用户角色
     *
     * @author 磊
     */
    @ResponseBody
    @RequestMapping("/updateSessionRole")
    public String updateSessionRole(HttpServletRequest request, String role) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setRoleName(role);
        user.setRoleId(userService.selectRoleIdByName(role));
        session.setAttribute("user", user);
        return "切换角色成功";
    }




//    /**
//     * 单点登录(已弃用)
//     *
//     * @param userData
//     * @param request
//     * @return
//     */
//    @RequestMapping("/singleLogin")
//    public String singleLogin(@ModelAttribute("userData") UserData userData, HttpServletRequest request) {
//        System.out.println(userData);
//        if (ValidateUtil.isNotEmpty(userData.getData())) {
//            request.getSession().setAttribute("userData", userData);
//        } else {
//            userData = (UserData) request.getSession().getAttribute("userData");
//        }
//        User user = userService.singleLogin(userData);
//        // 成功跳转主页面
//        if (ValidateUtil.isNotEmpty(user)) {
//            request.getSession().setAttribute("user", user);
//            return "index";
//        } else {
//            return "login";
//        }
//    }

    /**
     * 单点登录
     *
     * @param userData
     * @param request
     * @return
     * @author 磊
     */
    @RequestMapping("/singleLogin")
    public String singleLogin(@ModelAttribute("userData") UserData userData, HttpServletRequest request) {
        System.out.println(userData);//UserData{code='0', data=Data{password='null', username='991000210053', authorities='[]', accountNonExpired='true', accountNonLocked='true', credentialsNonExpired='true', enabled='false', type='1', info=Info{workNum='9120201100112', name='李玲', sex='女', password='', department='147', category='专业技术岗位', status='null', type='null', attribute='在编人员', attributeType='null', post='null', postGrade='null'}}}
        if (ValidateUtil.isNotEmpty(userData.getData())) {
            request.getSession().setAttribute("userData", userData);
        } else {
            userData = (UserData) request.getSession().getAttribute("userData");
        }
        User user = userService.singleLogin(userData);
        // 成功跳转主页面
        if (ValidateUtil.isNotEmpty(user)) {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null || !((User) session.getAttribute("user")).getAccount().equals(userData.getData().getUsername())) {
                User login = userService.loginInfo(userData.getData().getUsername());
                updateSession(session, login, "no");
            }
            return "index";
        } else {
            return "login";
        }
    }




    /**
     * 依条件查询用户信息
     *
     * @param condition 账号/姓名/手机号
     * @param timeLimit 时间段
     * @param page      页数
     * @param limit     每页条数
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryUserByCondition")
    public String queryUserByCondition(@RequestParam("condition") String condition,
                                       @RequestParam("timeLimit") String timeLimit,
                                       @RequestParam("deptId") String deptId,
                                       @RequestParam("page") String page,
                                       @RequestParam("limit") String limit) {
        Map<String, Object> map = userService.queryUserByCondition(condition, timeLimit, deptId, page, limit);
        return new JsonTemplate("条件查询", map.get("data"), (Integer) map.get("count")).toString();
    }

    /**
     * 根据id查找用户
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/findUserByUserId")
    public String findUserByUserId(@RequestParam("userId") Long userId) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(userService.findUserByUserId(userId));
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public int updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * 删除用户（逻辑删除）
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public int deleteUser(@RequestParam("userId") Long userId) {
        if (userId == 1L) {
            return -1;
        }
        return userService.changeStatus(userId, "DELETE");
    }

    @ResponseBody
    @RequestMapping("/reset")
    public int reset(@RequestParam("userId") Long userId) {
        return userService.reset(userId);
    }

    @ResponseBody
    @RequestMapping("/changeRole")
    public int changeRole(@RequestParam("roleIds") String roleIds, @RequestParam("userId") Long userId) {
        return userService.changeRole(roleIds, userId);
    }

    @ResponseBody
    @RequestMapping("/getRoleToTree")
    public String getRoleToTree(@RequestParam("roleIds") String roleIds) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(roleService.getRoleToTree(roleIds));
    }

    @ResponseBody
    @RequestMapping("/addUser")
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping("/freeze")
    public String freeze(@RequestParam("userId") Long userId, HttpServletRequest request) {
        if (userId == 1L) {
            return "no";
        }
        return userService.changeStatus(userId, "LOCKED") == 0 ? "冻结失败" : "冻结成功";
    }

    @ResponseBody
    @RequestMapping("/unFreeze")
    public String unFreeze(@RequestParam("userId") Long userId) {
        return userService.changeStatus(userId, "ENABLE") == 0 ? "解冻失败" : "解冻成功";
    }

    /**
     * 取得当前用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCurrentUser")
    public String getCurrentUser(HttpServletRequest request) throws JsonProcessingException {
        System.out.println(request.getSession().getAttribute("user"));
        return new ObjectMapper().writeValueAsString(request.getSession().getAttribute("user"));
    }


    @ResponseBody
    @RequestMapping("/getUserInfo")
    public String getUserInfo(String userId) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(userService.findUserByUserId(Long.valueOf(userId)));
    }

    @ResponseBody
    @RequestMapping("/changeUserInfo")
    public int changeUserInfo(@RequestBody User user) {
        return userService.changeUserInfo(user);
    }

    @ResponseBody
    @RequestMapping("/changePassword")
    public int changePassword(@RequestParam("oldPassword") String oldPassword,
                              @RequestParam("newPassword") String newPassword,
                              @RequestParam("userId") String userId) {
        return userService.changePassword(oldPassword, newPassword, Long.valueOf(userId));
    }

    @ResponseBody
    @RequestMapping("/findMajorToSelect")
    public String findMajorToSelect() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(userService.findMajorToSelect());
    }

    @ResponseBody
    @RequestMapping("/setRole")
    public int setRole(@RequestParam("roleId") String roleId,
                       @RequestParam("majorId") String majorId,
                       @RequestParam("userId") String userId,
                       HttpSession session) {
        session.setAttribute("user",null);
        return userService.setRole(roleId, majorId, userId);
    }

    @ResponseBody
    @RequestMapping("/getUserByRoleName")
    public String getUserByRoleName(@RequestParam("roleName") String roleName, @RequestParam("department") String department) throws JsonProcessingException {
        if ("null".equals(department)) {
            department = null;
        }
        return new ObjectMapper().writeValueAsString(userService.getUserByRoleName(roleName, department));
    }

    // 与上个方法相同用于填充表格
    @ResponseBody
    @RequestMapping("/getUserByRoleNameToTable")
    public String getUserByRoleNameToTable(@RequestParam("page") String page,
                                           @RequestParam("limit") String limit,
                                           @RequestParam("roleName") String roleName,
                                           HttpServletRequest request) throws JsonProcessingException {
        User user = (User) request.getSession().getAttribute("user");

        HashMap<String, Object> userData = userService.getUserByRoleNameToTable(page, limit, roleName, user);
        return new JsonTemplate("用户", userData.get("data"), (int) userData.get("count")).toString();
    }
}
