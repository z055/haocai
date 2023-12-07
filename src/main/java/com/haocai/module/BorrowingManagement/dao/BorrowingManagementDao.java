package com.haocai.module.BorrowingManagement.dao;

import com.haocai.module.BorrowingManagement.vo.BorrowingManagementVo;
import com.haocai.module.BorrowingManagement.vo.param.BorroParam;
import com.haocai.module.BorrowingManagement.vo.param.UpParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingManagementDao {
    /**
     * @return
     * 获取耗材库所有耗材信息
     */
    List<BorrowingManagementVo> getAllConsumables();
    /**
     * @return
     * 搜索耗材
     */
    List<BorrowingManagementVo> searchQuery(BorroParam borroParam);
    /**
     * @return
     * 批量修改借用状态
     */
    int updateStatus(UpParam upParam);

    /**
     * 获取数据总数
     * @return
     */
    int getCount(BorroParam borroParam);
}
