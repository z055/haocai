package com.haocai.module.system.dao;

import com.haocai.module.system.vo.UserMajor;
import com.haocai.module.system.vo.Zhuanye;
import com.haocai.module.system.vo.param.UserMajorParam;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 杨
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface UserMajorMapper {
    List<UserMajor> getAllUser(UserMajorParam userMajorParam);
    int getAllUserCount(UserMajorParam userMajorParam);
    List<String> getZhuanYe(@RequestParam("deptName") String deptName);
    int updateUserMajor(UserMajor userMajor);
}
