package com.haocai.module.course.serviceimpl;

import com.haocai.module.course.dao.CourseMapper;
import com.haocai.module.course.param.CourseParam;
import com.haocai.module.course.service.CourseService;
import com.haocai.module.course.vo.Course;
import com.haocai.module.course.vo.ZhuanYe;
import com.haocai.module.system.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 课程管理
 * 业务处理层实现
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询课程管理所有数据
     * @return
     */
    @Override
    public List<Course> getAllCourse(CourseParam courseParam) {
        courseParam.setPage((courseParam.getPage()-1)*courseParam.getLimit());
        List<Course> list=courseMapper.getAllCourse(courseParam);
        return list;
    }

    /**
     * 添加数据课程管理
     * @return
     */
    @Override
    public int addCourse(Course course,HttpServletRequest request) {
        User user= (User) request.getSession().getAttribute("user");
        //查重
        Course result=courseMapper.addRecheck(course);
        if (result==null){
            //把实训名取出
            //设置id
            course.setId(UUID.randomUUID().toString());
            //设置课程编号
            course.setNo(UUID.randomUUID().toString().replace("-", ""));
            //设置操作时间
            SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
            String datas=sdf.format(new Date());
            course.setOperatordate(datas);
            //设置操作员角色
            course.setOperator(user.getRoleName());
            //设置操作员姓名
            course.setOperatorname(user.getName());
            //设置操作员账号
            course.setUserAccount(user.getAccount());
            int resultNum=courseMapper.addCourse(course);
            return resultNum;
        }
        return 0;
    }

    /**
     * 添加数据专业查询
     * @param zhuanYe
     * @return
     */
    @Override
    public List<ZhuanYe> addSelectMajor(ZhuanYe zhuanYe) {
        List<ZhuanYe> resultList=courseMapper.addSelectMajor(zhuanYe);
        return resultList;
    }

    /**
     * 查询全部专业
     * @return
     */
    @Override
    public List<ZhuanYe> selectAllMajor() {
        List<ZhuanYe> resultList=courseMapper.selectAllMajor();
        return resultList;
    }

    /**
     * 添加查重
     * @param course
     * @return
     */
    @Override
    public Course addRecheck(Course course) {
        Course result=courseMapper.addRecheck(course);
        return result;
    }

    /**
     * 数据修改
     * @param course
     * @param request
     * @return
     */
    @Override
    public int updateCourse(Course course,HttpServletRequest request) {
        User user= (User) request.getSession().getAttribute("user");
        //修改查重
        Course result=courseMapper.addRecheck(course);
        if (result==null){
            //设置操作时间
            SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
            String datas=sdf.format(new Date());
            course.setOperatordate(datas);
            //设置操作员角色
            course.setOperator(user.getRoleName());
            //设置操作员姓名
            course.setOperatorname(user.getName());
            int resultNum=courseMapper.updateCourse(course);
            return resultNum;
        }
        return 0;
    }

    /**
     * 修改数据单独渲染
     * @param course
     * @return
     */
    @Override
    public Course updateData(Course course) {
        Course result=courseMapper.updateData(course);
        if (result!=null){
            return result;
        }
        return null;
    }

    /**
     * 删除数据
     * @param course
     * @return
     */
    @Override
    public int deleteCourse(Course course) {
        int result=courseMapper.deleteCourse(course);
        if (result>0){
            return result;
        }
        return 0;
    }

    /**
     * 批量删除
     * @param courseParam
     * @return
     */
    @Override
    public int deleteSelectData(CourseParam courseParam){
        String Ids=courseParam.getIds();
        String[] strArr=Ids.split(",");
        courseParam.setArrIds(strArr);
        int result=courseMapper.deleteSelectData(courseParam);
        if (result>0){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 查询数据总数
     * @return
     */
    @Override
    public int getCourseCount() {
        int result=courseMapper.getCourseCount();
        return result;
    }

    /**
     * 模糊查询
     * @param courseParam
     * @return
     */
    @Override
    public List<Course> queryCourseDate(CourseParam courseParam) {
        courseParam.setPage((courseParam.getPage()-1)*courseParam.getLimit());
        List<Course> result=courseMapper.queryCourseDate(courseParam);
        if (result!=null){
            return result;
        }
        return null;
    }

    /**
     * 模糊查询查询数据总数
     * @param courseParam
     * @return
     */
    @Override
    public int queryCourseCount(CourseParam courseParam) {
        int result=courseMapper.queryCourseCount(courseParam);
        return result;
    }

    /**
     * Excel课程管理数据导出
     * @param courseParam
     * @return
     */
    @Override
    public List<Course> courseExcelExport(CourseParam courseParam) {
        List<Course> list=courseMapper.courseExcelExport(courseParam);
        return list;
    }


    /**
     * excel导入数据
     * @param course
     * @return
     */
    @Override
    public int courseExcelInsert(Course course,HttpServletRequest request) {
        User user= (User) request.getSession().getAttribute("user");
        //查重
        Course result=courseMapper.addRecheck(course);
        System.out.println("查重五项是否全部相同");
        if (result==null){
            //设置id
            course.setId(UUID.randomUUID().toString());
            //设置课程编号
            course.setNo(UUID.randomUUID().toString().replace("-", ""));
            //设置操作员角色
            course.setOperator(user.getRoleName());
            //设置操作员姓名
            course.setOperatorname(user.getName());
            //设置操作账号
            course.setUserAccount(user.getAccount());
            //设置操作时间
            SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
            String datas=sdf.format(new Date());
            course.setOperatordate(datas);
            int resultNum=courseMapper.addCourse(course);
            return resultNum;
        }
        return 0;
    }

    @Override
    public int selectDepartmentMajor(Course course) {
        return courseMapper.selectDepartmentMajor(course);
    }
}
