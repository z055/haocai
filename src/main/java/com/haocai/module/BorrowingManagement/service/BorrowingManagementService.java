package com.haocai.module.BorrowingManagement.service;

import com.haocai.module.BorrowingManagement.vo.BorrowingManagementVo;
import com.haocai.module.BorrowingManagement.vo.param.BorroParam;
import com.haocai.module.BorrowingManagement.vo.param.UpParam;
import com.haocai.module.BorrowingManagement.vo.resulet.BorResulet;

import java.util.List;

public interface BorrowingManagementService {
    /**
     *
     * @return
     * 获取耗材库所有耗材信息
     */
    List<BorrowingManagementVo> getAllConsumables();
    /**
     * @return
     * 搜索耗材
     */
    BorResulet searchQuery(BorroParam borroParam);
    /**
     * @return
     * 批量修改借用状态
     */
    public int updateStatus(UpParam upParam);
}
