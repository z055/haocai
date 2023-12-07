package com.haocai.module.sbModule.dao;

import com.haocai.module.sbModule.vo.ConOut;
import com.haocai.module.sbModule.vo.ReviewTable;
import com.haocai.module.sbModule.vo.SbVo;
import com.haocai.module.sbModule.vo.param.ReviewParam;
import com.haocai.module.sbModule.vo.param.SbParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDao {

    public int getAllReviewDataCount(ReviewParam reviewParam);
    //查看当前用户要审核的数据

    public List<ReviewTable> getAllReviewData(ReviewParam reviewParam);

    //查看审核详情
    public List<ReviewTable> getSbDetailData(ReviewTable reviewTable);

    //根据审核编号查
    public List<SbVo> getReviewBorrowData(ReviewParam reviewParam);
    //审核后修改自己的状态
    public int reviewUpdateMe(ReviewParam reviewParam);

    public int reviewUpdateOther(ReviewParam reviewParam);

//    审核后修改目录表的审核流程状态
    public int reviewUpdateConsumListBorrow(SbParam sbParam);
//往借用表添加审核意见
    public int addReviewOpinion(ReviewParam reviewParam);
    //往审核表添加审核意见
    public int addReviewTableOpinion(ReviewParam reviewParam);
    //查看审核意见
    public List<ReviewParam> showOpinion(ReviewParam reviewParam);

    public int updateOutStatus(ConOut conOut);
    //根据账号查用户名
    public String selectNameByAccount(@Param("operator") String operator);

    public String selectNameByUserId(@Param("re_pe_id") String re_pe_id);

    public String getStatus(ReviewParam reviewParam);
}
