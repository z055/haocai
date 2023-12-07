package com.haocai.module.consumableslist.dao;

import com.haocai.module.consumableslist.param.ConsumableslistParam;

import com.haocai.module.consumableslist.param.MajorCourse;
import com.haocai.module.consumableslist.result.CourseDataResult;
import com.haocai.module.consumableslist.vo.Consumableslist;
import com.haocai.module.consumableslist.vo.Consumableslist01;
import com.haocai.module.consumableslist.vo.ErrorExcel;
import com.haocai.module.course.vo.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumableslistMapper {

    /**
     * 根据查询条件查询数据数量
     * 没有条件就是查询所有数据量
     *
     * @param consumableslistParam
     * @return
     */
    public int dataCount(ConsumableslistParam consumableslistParam);

    /**
     * 按条件查询数据
     * 无条件查询全部
     *
     * @param consumableslistParam
     * @return
     */
    public List<Consumableslist> getAllConsumablesList(ConsumableslistParam consumableslistParam);

    /**
     * 查询课程表里专业
     *
     * @param consumableslistParam
     * @return
     */
    public List<CourseDataResult> getCourseMajor(ConsumableslistParam consumableslistParam);

    /**
     * 根据专业查询课程表里课程
     *
     * @param consumableslistParam
     * @return
     */
    public List<CourseDataResult> getCourseName(ConsumableslistParam consumableslistParam);

    /**
     * 专业不为空课程为空下查询实训项目
     *
     * @param consumableslistParam
     * @return
     */
    public List<CourseDataResult> getTrainingByCourse(ConsumableslistParam consumableslistParam);

    /**
     * 查询实训项目数量
     *
     * @param consumableslistParam
     * @return
     */
    public List<Consumableslist> getTrainingByCourseCount(ConsumableslistParam consumableslistParam);

    /**
     * 删除数据
     *
     * @param consumableslist
     * @return
     */
    public int deleteData(Consumableslist consumableslist);

    /**
     * 批量删除
     *
     * @param consumableslistParam
     * @return
     */
    public int deleteSelectData(ConsumableslistParam consumableslistParam);

    /**
     * 批量删除审核表里数据
     *
     * @param consumableslistParam
     * @return
     */
    public int deleteSelectReviewData(ConsumableslistParam consumableslistParam);

    /**
     * 删除数据时同时删除审核表里的审核数据
     *
     * @param consumableslist
     * @return
     */
    public int deleteReviewData(Consumableslist consumableslist);

    /**
     * 查询t_course课程管理表
     * 查询课程名和实训项目
     *
     * @return
     */
    public List<Course> selectCourseData(MajorCourse majorCourse);

    /**
     * 查重
     *
     * @param consumableslist
     * @return
     */
    public List<Consumableslist> addReCheckData(Consumableslist consumableslist);

    /**
     * 添加数据
     *
     * @param consumableslist
     * @return
     */
    public int addConsumablesList(Consumableslist consumableslist);

    /**
     * 添加时
     * 查询耗材编号
     * 确保耗材编号唯一
     *
     * @param consumableslist
     * @return
     */
    public Consumableslist selectNoData(Consumableslist consumableslist);

    /**
     * 修改数据
     *
     * @param consumableslist
     * @return
     */
    public int updateConsumablesList(Consumableslist consumableslist);

    /**
     * 打回数据重新提交修改状态
     *
     * @param consumableslist
     * @return
     */
    int modifyConsumablesListState(Consumableslist consumableslist);

    /**
     * 修改数据时查询要修改的数据
     *
     * @param consumableslist
     * @return
     */


    public Consumableslist selectUpdateData(Consumableslist consumableslist);

    /**
     * 三角色的状态修改
     *
     * @param  rewiew_id
     * @return
     */

    int modifyReviewState01(String rewiew_id);

    int modifyReviewState02(String rewiew_id);

    int modifyReviewState03(String rewiew_id);

    /**
     * excel 导出
     *
     * @param consumableslistParam
     * @return
     */
    public List<Consumableslist> excelExport(ConsumableslistParam consumableslistParam);


    /**
     * 时间开放管理
     *
     * @param consumableslistParam
     * @return
     */
    public int timeSetting(ConsumableslistParam consumableslistParam);

    /**
     * 时间维护查询
     *
     * @return
     */
    public ConsumableslistParam selectTimeResult();

    /**
     * 审核提交
     *
     * @param consumableslistParam
     * @return
     */
    public int auditSubmit(ConsumableslistParam consumableslistParam);

    /**
     * 审核通过
     *
     * @param consumableslistParam
     * @return
     */
    public int auditSuccess(ConsumableslistParam consumableslistParam);

    /**
     * 审核退回
     *
     * @param consumableslistParam
     * @return
     */
    public int auditFail(ConsumableslistParam consumableslistParam);

    /**
     * 上传excel向数据库插入错误信息
     *
     * @param errorExcel
     * @return
     */
    public int addErrorExcel(ErrorExcel errorExcel);

    /**
     * 删除上传错误excel信息
     *
     * @param errorExcel
     * @return
     */
    public int deleteErrorExcel(ErrorExcel errorExcel);

    /**
     * 查询错误excel
     *
     * @param errorExcel
     * @return
     */
    public List<ErrorExcel> getErrorExcel(ErrorExcel errorExcel);

    /**
     * excel录入根据实训名称查课程表是否存在
     *
     * @param
     * @return
     */
    public List<Course> selectCourseByTraining(Consumableslist consumableslist);

    /**
     * 重新提交被打回的批量数据
     *
     * @author 磊
     * @return
     */
    public int modifyConsumablesListStateByReviewId(String review_id);

    void updateMark(Consumableslist01 consumable);

    List<CourseDataResult> getUserMajor(String account);

    /**
     * 上传excel时对所属专业进行判断
     *
     * @author 磊
     * @return
     */
    int selectMajorByMark(ConsumableslistParam consumableslistParam);

    List<CourseDataResult> initMajorSelect(ConsumableslistParam consumableslistParam);

    /**
     * 查询所选部门下本人的课程(目录添加页面)
     *
     * @author 磊
     * @return
     */
    List<MajorCourse> addExistCourse(MajorCourse majorCourse);
}
