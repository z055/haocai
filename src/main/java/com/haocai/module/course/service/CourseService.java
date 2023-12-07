package com.haocai.module.course.service;

import com.haocai.module.course.param.CourseParam;
import com.haocai.module.course.vo.Course;
import com.haocai.module.course.vo.ZhuanYe;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 课程管理业务处理接口
 */

public interface CourseService {
    /**
     * 查询课程管理表所有数据
     * @return
     */
    public List<Course> getAllCourse(CourseParam courseParam);

    /**
     * 课程管理
     * 添加数据
     * @return
     * @param course
     * @param request
     */
    public int addCourse(Course course, HttpServletRequest request);

    /**
     * 添加数据专业查询
     * @param zhuanYe
     * @return
     */
    public List<ZhuanYe> addSelectMajor(ZhuanYe zhuanYe);

    /**
     * 查询全部专业
     * @return
     */
    public List<ZhuanYe> selectAllMajor();

    /**
     * 查重
     * 根据课程名和实训项目名和专业名
     * @param course
     * @return
     */
    public Course addRecheck(Course course);

    /**
     * 修改数据
     * @param course
     * @return
     */
    public int updateCourse(Course course, HttpServletRequest request);

    /**
     * 修改数据单独渲染
     * @param course
     * @return
     */
    public Course updateData(Course course);

    /**
     * 删除数据
     * @param course
     * @return
     */
    public int deleteCourse(Course course);

    /**
     * 批量删除
     * @param courseParam
     * @return
     */
    public int deleteSelectData(CourseParam courseParam);

    /**
     * 获取所有信息数量
     * @return
     */
    public int getCourseCount();

    /**
     * 模糊查询
     * @param courseParam
     * @return
     */
    public List<Course> queryCourseDate(CourseParam courseParam);

    /**
     * 模糊查询时查询数据总数
     * @param courseParam
     * @return
     */
    public int queryCourseCount(CourseParam courseParam);

    /**
     * Excel导出课程管理数据
     * @param courseParam
     * @return
     */
    public List<Course> courseExcelExport(CourseParam courseParam);

    /**
     * excel导入
     * @param course
     * @return
     */
    public int courseExcelInsert(Course course, HttpServletRequest request);

    int selectDepartmentMajor(Course course);
}
