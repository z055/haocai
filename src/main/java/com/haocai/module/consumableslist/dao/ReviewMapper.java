package com.haocai.module.consumableslist.dao;

import com.haocai.module.consumableslist.param.ReviewParam;
import com.haocai.module.consumableslist.result.CourseDataResult;
import com.haocai.module.consumableslist.vo.Consumableslist;
import com.haocai.module.consumableslist.vo.ReviewTable;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
public interface ReviewMapper {

    /**
     * 查询当前人员需要审核数据的总量
     *
     * @param reviewParam
     * @return
     */
    public int getAllReviewDataCount(ReviewParam reviewParam);

    /**
     * 查询当前人员需要审核的全部数据
     *
     * @param reviewParam
     * @return
     */
    public List<ReviewTable> getAllReviewData(ReviewParam reviewParam);

    /**
     * 审核时查询要审核的具体数据
     *
     * @param reviewParam
     * @return
     */
    public List<Consumableslist> getReviewData(ReviewParam reviewParam);

    /**
     * 查询学院下的专业
     *
     * @param reviewParam
     * @return
     */
    public List<CourseDataResult> getCourseMajorData(ReviewParam reviewParam);

    /**
     * 查询学院下实训项目
     *
     * @param reviewParam
     * @return
     */
    public List<CourseDataResult> getTrainingByCourseData(ReviewParam reviewParam);

    /**
     * 查询专业条件下的数据数量
     *
     * @param reviewParam
     * @return
     */
    public List<ReviewTable> getAllReviewDataCountByMajor(ReviewParam reviewParam);

    /**
     * 审核后修改自己状态
     *
     * @param reviewParam
     * @return
     */
    public int reviewUpdateMe(ReviewParam reviewParam);

    /**
     * 审核后修改其他人状态
     *
     * @param reviewParam
     * @return
     */
    public int reviewUpdateOther(ReviewParam reviewParam);

    /**
     * 审核后修改三条数据的状态为2
     *
     * @param reviewParam
     * @return
     */
    public int reviewUpdateThree(ReviewParam reviewParam);

    /**
     * 审核后修改目录表状态
     *
     * @param consumableslist
     * @return
     */
    public int reviewUpdateConsumList(Consumableslist consumableslist);


    /**
     * 向目录表添加审核意见
     *
     * @param reviewParam
     * @return
     */
    public int addReviewOpinion(ReviewParam reviewParam);

    /**
     * 审核时查询审核表数据
     *
     * @param reviewParam
     * @return
     */
    public List<ReviewTable> getReviewTableData(ReviewParam reviewParam);


}
