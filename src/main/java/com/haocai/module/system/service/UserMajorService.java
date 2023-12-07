package com.haocai.module.system.service;

import com.haocai.module.system.vo.UserMajor;
import com.haocai.module.system.vo.Zhuanye;
import com.haocai.module.system.vo.param.UserMajorParam;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 杨
 * @version 1.0
 * @since 1.0
 */
public interface UserMajorService {
    List<UserMajor> getAllUser(UserMajorParam userMajorParam);
    int getAllUserCount(UserMajorParam userMajorParam);
    List<String> getZhuanYe(String deptName);
    int updateUserMajor(UserMajor userMajor);
}
