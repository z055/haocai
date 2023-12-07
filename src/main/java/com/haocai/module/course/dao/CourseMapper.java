package com.haocai.module.course.dao;

import com.haocai.module.course.param.CourseParam;
import com.haocai.module.course.vo.Course;
import com.haocai.module.course.vo.ZhuanYe;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 课程管理Mapper接口
 * @author 小崔
 */

@Repository
public interface CourseMapper {
    /**
     * 获取课程管理所有数据
     * @return
     */
    public List<Course> getAllCourse(CourseParam courseParam);

    /**
     * 课程管理添加数据
     * @param course
     * @return
     */
    public int addCourse(Course course);

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
     * 添加查重
     * @param course
     * @return
     */
    public Course addRecheck(Course course);

    /**
     * 修改数据
     * @param course
     * @return
     */
    public int updateCourse(Course course);

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
     * 查询数据总数
     * @return
     */
    public int getCourseCount();


    /**
     *按条件查询
     * 模糊查询
     * 按课程名或实训名
     * @param courseParam
     * @return
     */
    public List<Course> queryCourseDate(CourseParam courseParam);

    /**
     * 模糊查询查询数据总数
     * @param courseParam
     * @return
     */
    public int queryCourseCount(CourseParam courseParam);

    /**
     * Excel课程管理数据导出
     * @param courseParam
     * @return
     */
    public List<Course> courseExcelExport(CourseParam courseParam);

    int selectDepartmentMajor(Course course);



}
