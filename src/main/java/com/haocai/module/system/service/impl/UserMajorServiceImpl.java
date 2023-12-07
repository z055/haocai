package com.haocai.module.system.service.impl;

import com.haocai.module.system.dao.UserMajorMapper;
import com.haocai.module.system.service.UserMajorService;
import com.haocai.module.system.vo.UserMajor;
import com.haocai.module.system.vo.Zhuanye;
import com.haocai.module.system.vo.param.UserMajorParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserMajorServiceImpl implements UserMajorService {
    @Autowired
    private UserMajorMapper userMajorMapper;
    @Override
    public List<UserMajor> getAllUser(UserMajorParam userMajorParam) {
        userMajorParam.setPage((userMajorParam.getPage()-1)*userMajorParam.getLimit());
        return userMajorMapper.getAllUser(userMajorParam);
    }

    @Override
    public int getAllUserCount(UserMajorParam userMajorParam) {
        userMajorParam.setPage((userMajorParam.getPage()-1)*userMajorParam.getLimit());
        return userMajorMapper.getAllUserCount(userMajorParam);
    }

    @Override
    public List<String> getZhuanYe(String deptName) {
        return userMajorMapper.getZhuanYe(deptName);
    }

    @Override
    public int updateUserMajor(UserMajor userMajor) {
        return userMajorMapper.updateUserMajor(userMajor);
    }
}
