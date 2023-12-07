package com.haocai.module.sbModule.dao;

import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.OutReviewVo;
import com.haocai.module.sbModule.vo.ConOut;
import com.haocai.module.sbModule.vo.ReviewTable;
import com.haocai.module.sbModule.vo.SbVo;
import com.haocai.module.sbModule.vo.param.ReviewParam;
import com.haocai.module.sbModule.vo.param.SbParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SbDao {
//    查看耗材已出库数量
    List<ConOut> getConOut(ConOut conOut);
//    外借管理 查询
    List<SbVo> getBorrows(SbParam sbVo);
//    外借管理 查询
    int getCount(SbParam param);
//    插入借用表数据
    int addSb(SbVo sbVo);
//    插入库存表外借
    int addOut(ConOut conOut);
//    添加耗材借用审核
    int AddSbReview(ReviewTable reviewTable);
//    外借申请 查询

//    修改借用审核状态
    int updateSbReview(SbVo sbVo);
//    修改借用表状态
    int updateSbBorrow(SbVo sbVo);
//    根据id获取耗材借用表数据
    SbVo getSbById(@Param("review_id") String review_id);
    //获取耗材库存信息
    SbVo getConById(@Param("id") String id);

    //添加库存表数据
    int addCon(SbVo sbVo);
    int delSb(SbParam sbParam);
//    根据审核编号删除审核数据
    int deleteReviewData(SbParam sbParam);
//    修改库存表耗材数量和总价格
    int update(SbVo sbVo);

//    获取库存数量
    SbVo getNumber(SbVo sbVo);
//    修改耗材借用表最终审核状态
    int updateReview(SbVo sbVo);
//    获取审核流程表id
    ReviewParam getRevId(ReviewParam reviewParam);

}
