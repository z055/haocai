package com.haocai.module.consumableslist.service;

import com.haocai.module.consumableslist.param.ReviewParam;
import com.haocai.module.consumableslist.result.CourseDataResult;
import com.haocai.module.consumableslist.vo.Consumableslist;
import com.haocai.module.consumableslist.vo.ReviewTable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 目录审核service接口层
 */
@Service
public interface ReviewService {

    /**
     * 查询当前登录人员所需要审核的全部数据总数
     * @return
     */
    public int getAllReviewDataCount(ReviewParam reviewParam, HttpServletRequest request);
    /**
     * 查询当前登录人员所需要审核的全部数据
     * @return
     */
    public List<ReviewTable> getAllReviewData(ReviewParam reviewParam,HttpServletRequest request);

    /**
     * 查询要审核的具体数据
     * @param reviewParam
     * @return
     */
    public List<Consumableslist> getReviewData(ReviewParam reviewParam);

    /**
     * 查询学院下的具体专业
     * @param reviewParam
     * @return
     */
    public List<CourseDataResult> getCourseMajorData(ReviewParam reviewParam);

    /**
     * 查询学院下的实训项目
     * @param reviewParam
     * @return
     */
    public List<CourseDataResult> getTrainingByCourseData(ReviewParam reviewParam);


    /**
     * 审核通过
     * @param reviewParam
     * @return
     */
    public int reviewPass(ReviewParam reviewParam);

    /**
     * 审核退回
     * @param reviewParam
     * @return
     */
    public int reviewBack(ReviewParam reviewParam);



    /**
     * 审核后修改自己状态
     * @param reviewParam
     * @return
     */
    public int reviewUpdateMe(ReviewParam reviewParam);

    /**
     * 审核后修改其他审核人状态
     * @param reviewParam
     * @return
     */
    public int reviewUpdateOther(ReviewParam reviewParam);

    /**
     * 通过后修改目录表状态
     * @param consumableslist
     * @return
     */
    public int reviewUpdateConsumList(Consumableslist consumableslist);




}
