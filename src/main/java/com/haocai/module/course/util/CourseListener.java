package com.haocai.module.course.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.haocai.module.course.service.CourseService;
import com.haocai.module.course.vo.Course;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于excel的导入读取
 *
 * @author 小崔
 */
public class CourseListener extends AnalysisEventListener<Course> {

    @Autowired
    private CourseService courseService;
    @Autowired
    private HttpServletRequest request;
    private List<Course> courseList = new ArrayList<>();

    public CourseListener(CourseService courseService, HttpServletRequest request) {
        this.courseService = courseService;
        this.request = request;
    }

    // 一行行读取excel内容
    // @author 崔
//    @Override
//    public void invoke(Course course, AnalysisContext analysisContext) {
//        if (course.getName()!=null){
//            String oldTraining=course.getTraining();
//            String newTraining=oldTraining.replaceAll("，", "," );
//            course.setTraining(newTraining);
//            saveData(course,request);
//        }
//    }

    // 一行行读取excel内容
    // 新增条件判断 将判断放入外部Controller防止废弃数据导入数据库中
    // 提供对应的访问权限
    // @author 磊
    @Override
    public void invoke(Course course, AnalysisContext context) {
        // 将读取到的课程对象添加到courseList中
//        System.out.println(course.getDepartment());
//        System.out.println(course.getMajor());
//        System.out.println(course.getName());
//        System.out.println(course.getTraining());
//        System.out.println(course.getClasshour());
//        System.out.println(!(course.getDepartment() == null && course.getMajor() == null && course.getName() == null && course.getTraining() == null && course.getClasshour() == 0));
        if (!(course.getDepartment() == null && course.getMajor() == null && course.getName() == null && course.getTraining() == null && course.getClasshour() == 0)) {
            String oldMajor = course.getMajor();
            if (oldMajor != null) {
                String newMajor = oldMajor.replaceAll("\\(", "（").replaceAll("\\)", "）");
                course.setMajor(newMajor);
            }
            String oldName = course.getName();
            if (oldName != null) {
                String newName = oldName.replaceAll("\\(", "（").replaceAll("\\)", "）");
                course.setName(newName);
            }
            String oldTraining = course.getTraining();
            if (oldTraining != null) {
                String newTraining = oldTraining.replaceAll("，", ",").replaceAll("\\(", "（").replaceAll("\\)", "）");
                course.setTraining(newTraining);
            }
            courseList.add(course);
        }
    }


    //设置访问
    public List<Course> getCourseList() {
        return courseList;
    }

    private void saveData(Course course, HttpServletRequest request) {
        courseService.courseExcelInsert(course, request);
    }

    //  读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头：" + headMap);
    }

    //  读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("存储完毕");
    }
}
