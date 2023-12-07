package com.haocai.module.course.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.DateUtils;
import com.haocai.module.course.param.CourseParam;
import com.haocai.module.course.result.CourseResult;
import com.haocai.module.course.service.CourseService;
import com.haocai.module.course.util.CourseListener;
import com.haocai.module.course.util.JsonResult;
import com.haocai.module.course.vo.Course;
import com.haocai.module.course.vo.ZhuanYe;
import com.haocai.module.system.vo.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 课程管理数据处理层
 * course表
 *
 * @author 小崔
 */

@Controller
@RequestMapping("/course")
public class CourseController {
    private Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private CourseService courseService;

    /**
     * 查询课程管理所有信息
     *
     * @return
     */
    @RequestMapping("/getAllCourse")
    @ResponseBody
    public JsonResult getAllCourse(CourseParam courseParam, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String role = user.getRoleName();
        if (role.indexOf("专业负责人") != -1) {
            courseParam.setUserAccount(user.getAccount());
        } else if (role.indexOf("二级学院管理员") != -1) {
            courseParam.setDepartment(user.getDeptName());
        }
        int courseCount = courseService.queryCourseCount(courseParam); //查询数据总数
        List<Course> result = courseService.getAllCourse(courseParam);
        CourseResult showDate = new CourseResult();
        showDate.setCount(courseCount);
        showDate.setList(result);
        return new JsonResult(JsonResult.SUCCESS, showDate, "查询成功");
    }

    /**
     * 课程管理添加数据
     *
     * @param
     * @return
     */
    @RequestMapping("/addCourse")
    @ResponseBody
    public JsonResult addCourse(Course course, HttpServletRequest request) {
        int result = courseService.addCourse(course, request);
        if (result == 1) {
            return new JsonResult(JsonResult.SUCCESS, result, "添加成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "失败，该专业的课程和实训项目已存在");
        }

    }

    /**
     * 添加数据查询专业
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/addSelectMajor")
    @ResponseBody
    public JsonResult addSelectMajor(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        ZhuanYe zhuanYe = new ZhuanYe();
        zhuanYe.setDepartment(user.getDeptName());
        List<ZhuanYe> result = courseService.addSelectMajor(zhuanYe);
        return new JsonResult(JsonResult.SUCCESS, result, "查询成功");
    }

    /**
     * 查询全部专业
     *
     * @return
     */
    @RequestMapping("/selectAllMajor")
    @ResponseBody
    public JsonResult selectAllMajor() {
        List<ZhuanYe> result = courseService.selectAllMajor();
        return new JsonResult(JsonResult.SUCCESS, result, "查询成功");
    }

    /**
     * 修改数据
     *
     * @param course
     * @return
     */
    @RequestMapping("/updateCourse")
    @ResponseBody
    public JsonResult updateCourse(Course course, HttpServletRequest request) {
        int result = courseService.updateCourse(course, request);
        if (result == 1) {
            return new JsonResult(JsonResult.SUCCESS, result, "修改成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "修改失败,记录已经存在");
        }

    }

    /**
     * 被修改数据单独查询渲染
     *
     * @param course
     * @return
     */
    @RequestMapping("/updateData")
    @ResponseBody
    public JsonResult updateData(Course course) {
        Course result = courseService.updateData(course);
        if (result != null) {
            return new JsonResult(JsonResult.SUCCESS, result, "查询成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "查询失败");
        }

    }

    /**
     * 删除数据
     *
     * @param course
     * @return
     */
    @RequestMapping("/deleteCourse")
    @ResponseBody
    public JsonResult deleteCourse(Course course) {
        int result = courseService.deleteCourse(course);
        if (result > 0) {
            return new JsonResult(JsonResult.SUCCESS, result, "删除成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "删除失败");
        }
    }

    /**
     * 批量删除
     *
     * @param courseParam
     * @return
     */
    @RequestMapping("/deleteSelectData")
    @ResponseBody
    public JsonResult deleteSelectData(CourseParam courseParam) {
        int result = courseService.deleteSelectData(courseParam);
        if (result > 0) {
            return new JsonResult(JsonResult.SUCCESS, result, "删除成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "删除失败");
        }

    }


    /**
     * 模糊查询
     *
     * @param courseParam
     * @return
     */
    @RequestMapping("/queryCourseDate")
    @ResponseBody
    public JsonResult queryCourseDate(CourseParam courseParam) {
        int count = courseService.queryCourseCount(courseParam); //模糊查询时获取数据总数
        List<Course> result = courseService.queryCourseDate(courseParam);
        if (count != 0) {
            CourseResult showDate = new CourseResult();
            showDate.setCount(count);
            showDate.setList(result);
            return new JsonResult(JsonResult.SUCCESS, showDate, "查询成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "没有查找到相关数据");
        }

    }


    /**
     * Excel导出
     * Excel导出课程管理数据
     * 可以根据所传来id选择性导出
     *
     * @param courseParam
     * @return
     */
    @RequestMapping("/courseExcelExport")
    @ResponseBody
    public void courseExcelExport(HttpServletResponse response, CourseParam courseParam) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode(String.format("%s课程管理.", DateUtils.format(new Date(), "yyyy-MM-dd")), "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ExcelTypeEnum.XLSX);
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            EasyExcel.write(response.getOutputStream(), Course.class).sheet("课程管理1").doWrite(courseService.courseExcelExport(courseParam));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Excel导入(能导入别的学院 要求专业在数据库中能查到)
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/courseExcelImport")
    @ResponseBody
    public JsonResult courseExcelImport(MultipartFile file, HttpServletRequest request) throws IOException {
        CourseListener courseListener = new CourseListener(courseService, request);
        try {
            EasyExcel.read(file.getInputStream(), Course.class, courseListener).sheet().doRead();
        } catch (Exception e) {
            // 异常处理记录日志
            System.err.println("字段类型匹配异常：" + e.getMessage());
            return new JsonResult(JsonResult.FALL, "", "课时格式异常,请重新检查");
        }
        List<Course> courses = courseListener.getCourseList();
        System.out.println(courses);
        Pattern majorPattern = Pattern.compile("[^,，]+");
        Pattern namePattern = Pattern.compile("[^,，]+");
        Pattern trainingPattern = Pattern.compile("^[^,，].*[^,，]$|.*,,+.*");

        Set<String> duplicateCheckSet = new HashSet<>(); // 用于存储已经出现过的组合字段数据
        for (Course course : courses) {
            System.out.println(course);
            String department = course.getDepartment();
            String major = course.getMajor();
            String name = course.getName();
            String training = course.getTraining();
            int classHour = course.getClasshour();
            if (department == null || department.trim().equals("")) {
                return new JsonResult(JsonResult.FALL, "", "课程名称为" + course.getName() + "的二级学院为空，请重新上传");
            } else if (major == null || major.trim().equals("") || !majorPattern.matcher(major).matches())
                return new JsonResult(JsonResult.FALL, "", "课程名称为" + course.getName() + "的专业格式错误，请重新上传");
            else if (name == null || name.trim().equals("") || !namePattern.matcher(name).matches()) {
                return new JsonResult(JsonResult.FALL, "", "课程名称为" + course.getName() + "的课程名称格式错误，请重新上传");
            } else if (training == null || training.trim().equals("") || !trainingPattern.matcher(training).matches()) {
                return new JsonResult(JsonResult.FALL, "", "课程名称为" + course.getName() + "的实训项目格式错误，请重新上传");
            } else if (classHour <= 0) {
                return new JsonResult(JsonResult.FALL, "", "课程名称为" + course.getName() + "的课时数据错误，请重新上传");
            } else if (courseService.addRecheck(course) != null) {
                return new JsonResult(JsonResult.FALL, "", "课程名称为" + course.getName() + "的数据已存在，请重新上传");
            } else if (courseService.selectDepartmentMajor(course) == 0) {
                return new JsonResult(JsonResult.FALL, "", "课程名称为" + course.getName() + "对应的专业或学院不存在，请重新上传");
            } else {
                //判断是否有重复数据,若有则返回哪一个耗材名称重复了
                String compositeKey = course.getDepartment() + "|" + course.getMajor() + "|" + course.getName() + "|" + course.getTraining() + "|" + course.getClasshour();
                if (duplicateCheckSet.contains(compositeKey)) {
                    // 存在完全相同的数据，返回重复数据信息
                    return new JsonResult(JsonResult.FALL, "", "存在完全相同的数据，重复数据：" + compositeKey);
                } else {
                    duplicateCheckSet.add(compositeKey);
                }
            }
        }
        //-----------------------
        for (Course course : courses) {
            courseService.courseExcelInsert(course, request);
        }
        return new JsonResult(JsonResult.SUCCESS, "", "导入成功");
    }


    /**
     * Excel模板下载
     *
     * @return
     */
    @RequestMapping("/courseExcelDownload")
    @ResponseBody
    public void courseExcelDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //要下载的文件地址
        String path = request.getSession().getServletContext().getRealPath("/download");
        String filename = "课程管理模板.xlsx";
        //设置response相应头
        response.reset();//设置页面不缓存
        response.setCharacterEncoding("utf-8");//设置相应编码
        response.setContentType("multipart/form-data");//二进制传输流
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "utf-8"));
        File file = new File(path, filename);
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(data)) != -1) {
            outputStream.write(data, 0, len);
            outputStream.flush();
        }
        inputStream.close();
        outputStream.close();
    }


}
