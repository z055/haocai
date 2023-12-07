package com.haocai.module.sbModule.service;

import com.haocai.module.sbModule.vo.ReviewTable;
import com.haocai.module.sbModule.vo.SbVo;
import com.haocai.module.sbModule.vo.param.ReviewParam;
import com.haocai.module.sbModule.vo.param.SbParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface SbReviewService {

    /**
     * 查询要审核的数据数量
     * @param reviewParam
     * @param request
     * @return
     */
    public int getAllReviewDataCount(ReviewParam reviewParam, HttpServletRequest request);

    /**
     * 查询要审核的数据
     * @param reviewParam
     * @param request
     * @return
     */
    public List<ReviewTable> getAllReviewData(ReviewParam reviewParam,HttpServletRequest request);


    /**
     * 查询审核详情
     * @param reviewTable
     * @return
     */
    public List<ReviewTable> getDetailData(ReviewTable reviewTable);



    /**
     * 查询要审核的具体数据
     * @param reviewParam
     * @return
     */
    public List<SbVo> getReviewBorrowData(ReviewParam reviewParam);

    /**
     * 审核通过
     * @param reviewParam
     * @return
     */
    public int BorrowReviewPass(ReviewParam reviewParam);

    /**
     * 审核退回
     * @param reviewParam
     * @return
     */
    public int BorrowReviewBack(ReviewParam reviewParam);

    /**
     *
     */
    public List<ReviewParam> showOpinion(ReviewParam reviewParam);

}
