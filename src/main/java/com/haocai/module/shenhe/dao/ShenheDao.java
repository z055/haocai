package com.haocai.module.shenhe.dao;

import com.haocai.module.shenhe.entity.ReviewTable;
import org.springframework.stereotype.Repository;

@Repository
public interface ShenheDao {

    /**
     * 插入审核
     * @param reviewTable
     * @return
     */
    int insertShenhe (ReviewTable reviewTable);

}
