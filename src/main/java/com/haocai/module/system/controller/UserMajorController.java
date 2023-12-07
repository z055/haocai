package com.haocai.module.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haocai.module.system.service.UserMajorService;
import com.haocai.module.system.vo.User;
import com.haocai.module.system.vo.UserMajor;
import com.haocai.module.system.vo.Zhuanye;
import com.haocai.module.system.vo.param.UserMajorParam;
import com.haocai.utils.JsonResult;
import com.haocai.utils.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 杨
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/userMajor")
public class UserMajorController {
    @Autowired
    private UserMajorService userMajorService;

    @RequestMapping("/getAllUser")
    @ResponseBody
    public Object getAllUser(UserMajorParam userMajorParam, HttpSession session){
        User user = (User) session.getAttribute("user");
        String deptName = user.getDeptName();
        userMajorParam.setDeptName(deptName);
        List<UserMajor> allUser = userMajorService.getAllUser(userMajorParam);
        int allUserCount = userMajorService.getAllUserCount(userMajorParam);
        return new JsonTemplate("查询成功",allUser,allUserCount).toString();
    }
    @RequestMapping("/getZhuanYe")
    @ResponseBody
    public Object getZhuanYe(HttpSession session){
        User user = (User) session.getAttribute("user");
        String deptName = user.getDeptName();
        List<String> list = userMajorService.getZhuanYe(deptName);
        return new JsonResult("200",list,"查询成功");
    }
    @RequestMapping("/updateMajor")
    @ResponseBody
    public Object updateMajor(UserMajor userMajor){
        userMajorService.updateUserMajor(userMajor);
        return new JsonResult("200",null,"修改成功");
    }

}
