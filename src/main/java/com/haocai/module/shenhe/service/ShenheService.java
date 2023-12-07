package com.haocai.module.shenhe.service;


import org.apache.ibatis.annotations.Param;

public interface ShenheService {
    int insertShenhe (String [] reviewId,
                      String [] projectName,
                      String [] user,
                      String [] role);

}
