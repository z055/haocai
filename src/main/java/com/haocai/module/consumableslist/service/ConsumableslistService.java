package com.haocai.module.consumableslist.service;

import com.haocai.module.consumableslist.param.ConsumableslistParam;
import com.haocai.module.consumableslist.param.MajorCourse;
import com.haocai.module.consumableslist.result.CourseDataResult;
import com.haocai.module.consumableslist.vo.Consumableslist;
import com.haocai.module.consumableslist.vo.Consumableslist01;
import com.haocai.module.consumableslist.vo.ErrorExcel;
import com.haocai.module.course.vo.Course;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public interface ConsumableslistService {

    /**
     * 查询数据数量
     * 可根据是否有条件查询数量
     *
     * @param consumableslistParam
     * @return
     */
    public int dataCount(ConsumableslistParam consumableslistParam, HttpServletRequest request);

    /**
     * 查询数据
     *
     * @param consumableslistParam
     * @return
     */
    public List<Consumableslist> getAllConsumablesList(ConsumableslistParam consumableslistParam, HttpServletRequest request);

    /**
     * 查询课程表里的专业
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
     * 查询课程表里实训项目
     *
     * @param consumableslistParam
     * @return
     */
    public List<CourseDataResult> getTrainingByCourse(ConsumableslistParam consumableslistParam);

    /**
     * 删除数据
     *
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
     * 查询课程管理表
     * 查询课程名和实训名字
     * 用于添加时渲染
     *
     * @return
     */
    public List<Course> selectCourseData(MajorCourse majorCourse);


    /**
     * 耗材编号查询
     * 保证唯一
     *
     * @param consumableslist
     * @return
     */
    public Consumableslist selectNoData(Consumableslist consumableslist);

    /**
     * 添加数据
     *
     * @param consumableslist
     * @return
     */
    public int addConsumablesList(Consumableslist consumableslist, HttpServletRequest request);

    /**
     * 修改数据
     *
     * @param consumableslist
     * @param request
     * @return
     */
    public int updateConsumablesList(Consumableslist consumableslist, HttpServletRequest request);

    /**
     * 被打回数据重新提交
     *
     * @param consumableslist
     * @param request
     * @return
     */
    int modifyConsumablesList(Consumableslist consumableslist, HttpServletRequest request);

    /**
     * 重新提交被打回的批量数据
     *
     * @param review_id
     * @return
     */
    int quantityModifyConsumablesList(String review_id);

    /**
     * 修改数据时查询要修改的数据
     *
     * @param consumableslist
     * @return
     */
    public Consumableslist selectUpdateData(Consumableslist consumableslist);

    /**
     * excel 导出
     *
     * @param consumableslistParam
     * @return
     */
    public List<Consumableslist> excelExport(ConsumableslistParam consumableslistParam);


    /**
     * 目录维护时间开放管理
     *
     * @param consumableslistParam
     * @return
     */
    public int timeSetting(ConsumableslistParam consumableslistParam, HttpServletRequest request);

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
     * @param request
     * @return
     */
    public int auditSubmit(ConsumableslistParam consumableslistParam, HttpServletRequest request);


    /**
     * EXCEL导入
     *
     * @param consumableslist
     * @return
     */
    public int importExcel(Consumableslist consumableslist, HttpServletRequest request);

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
     * @param consumableslist
     * @return
     */
    public List<Course> selectCourseByTraining(Consumableslist consumableslist);

    /**
     * 将错误数据以excel形式下载
     *
     * @param request
     * @param response
     */
    public void downloadErrorExcel(HttpServletRequest request, HttpServletResponse response);

    void updateMark(Consumableslist01 consumable);

    List<CourseDataResult> getUserMajor(String account);

    int selectMajorByMark(ConsumableslistParam consumableslistParam);

    List<CourseDataResult> initMajorSelect(ConsumableslistParam consumableslistParam);

    List<MajorCourse> addExistCourse(MajorCourse majorCourse);
}
