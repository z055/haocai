package com.haocai.module.consumableslist.controller;

import cn.hutool.http.server.HttpServerResponse;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.DateUtils;
import com.google.gson.Gson;
import com.haocai.module.consumableslist.dao.ConsumableslistMapper;
import com.haocai.module.consumableslist.param.ConsumableslistParam;
import com.haocai.module.consumableslist.param.MajorCourse;
import com.haocai.module.consumableslist.result.ConsumableslistResult;
import com.haocai.module.consumableslist.result.CourseDataResult;
import com.haocai.module.consumableslist.result.TimeResult;
import com.haocai.module.consumableslist.result.UserResult;
import com.haocai.module.consumableslist.service.ConsumableslistService;

import com.haocai.module.consumableslist.util.ConsumablesListListener;

import com.haocai.module.consumableslist.util.JsonResult;

import com.haocai.module.consumableslist.vo.Consumableslist;

import com.haocai.module.consumableslist.vo.Consumableslist01;
import com.haocai.module.consumableslist.vo.ErrorExcel;
import com.haocai.module.course.vo.Course;
import com.haocai.module.system.vo.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/consumableslist")
public class ConsumableslistController {

    private Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    ConsumableslistService consumableslistService;
    @Autowired
    ConsumableslistMapper consumableslistMapper;

    /**
     * 查询全部数据或按条件查询
     *
     * @param consumableslistParam
     * @return
     */
    @RequestMapping("/getAllConsumablesList")
    @ResponseBody
    public JsonResult getAllConsumablesList(ConsumableslistParam consumableslistParam, HttpServletRequest request) {
        int resultNum = 0;
        List<Consumableslist> result = new ArrayList<>();
        System.out.println("getAllConsumablesList的controller中consumableslistParam");
        System.out.println(consumableslistParam);
        resultNum = consumableslistService.dataCount(consumableslistParam, request);
        result = consumableslistService.getAllConsumablesList(consumableslistParam, request);
        if (resultNum != 0) {
            ConsumableslistResult resultData = new ConsumableslistResult();
            resultData.setCount(resultNum);
            resultData.setList(result);
            return new JsonResult(JsonResult.SUCCESS, resultData, "查询成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "无数据");
        }

    }

    /**
     * 查询专业表里的所有专业(目录管理页面)
     *
     * @return
     */
    @RequestMapping("/getCourseMajor")
    @ResponseBody
    public JsonResult getCourseMajor(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String userRole = user.getRoleName();
        List<CourseDataResult> result = new ArrayList<>();
        if (userRole.contains("专业负责人")) {
            List<CourseDataResult> userMajor = consumableslistService.getUserMajor(user.getAccount());
            System.out.println("专业负责人user表中的major");
            //user表中major_id字段不为空
            if (userMajor.get(0) != null) {
                String[] majors = userMajor.get(0).getMajor().split(",");
                for (String major : majors) {
                    result.add(new CourseDataResult(major));
                }
            }
        } else if (userRole.contains("二级学院管理员")) {
            ConsumableslistParam consumableslistParam = new ConsumableslistParam();
            consumableslistParam.setDepartment(user.getDeptName());
            System.out.println("getCourseMajor查询条件为");
            System.out.println(consumableslistParam);
            result = consumableslistService.getCourseMajor(consumableslistParam);
            System.out.println("ajax查询二级管理员动态专业栏");
            System.out.println(result);
        }
        if (result != null && result.size() > 0) {
            return new JsonResult(JsonResult.SUCCESS, result, "查询成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "无数据");
        }

    }

    /**
     * 查询部门里的所有专业(目录添加页面)
     *
     * @return
     */
    @RequestMapping("/initMajorSelect")
    @ResponseBody
    public JsonResult initMajorSelect(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<CourseDataResult> result = new ArrayList<>();
        ConsumableslistParam consumableslistParam = new ConsumableslistParam();
        consumableslistParam.setDepartment(user.getDeptName());
        System.out.println("initMajorSelect中的查询条件:");
        System.out.println(consumableslistParam);
        result = consumableslistService.initMajorSelect(consumableslistParam);
        if (result != null && result.size() > 0) {
            return new JsonResult(JsonResult.SUCCESS, result, "查询成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "无数据");
        }
    }

    /**
     * 查询所选部门下本人的课程(目录添加页面)
     *
     * @return
     */
    @RequestMapping("/addExistCourse")
    @ResponseBody
    public JsonResult addExistCourse(HttpServletRequest request, String major) {
        User user = (User) request.getSession().getAttribute("user");
        List<MajorCourse> result = new ArrayList<>();
        MajorCourse majorCourse = new MajorCourse(user.getDeptName(), major, user.getAccount());
        System.out.println("addExistCourse:");
        System.out.println(majorCourse);
        result = consumableslistService.addExistCourse(majorCourse);
        if (result != null && result.size() > 0) {
            return new JsonResult(JsonResult.SUCCESS, result, "查询成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "无数据");
        }
    }

    /**
     * 根据专业去查询课程表里的课程名
     *
     * @param consumableslistParam
     * @param request
     * @return
     */
    @RequestMapping("/getCourseName")
    @ResponseBody
    public JsonResult getCourseName(ConsumableslistParam consumableslistParam, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        consumableslistParam.setDepartment(user.getDeptName());
        List<CourseDataResult> result = consumableslistService.getCourseName(consumableslistParam);
        if (result != null) {
            return new JsonResult(JsonResult.SUCCESS, result, "查询成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "无数据");
        }

    }

    /**
     * 针对专业不为空课程为空下查询实训项目
     *
     * @param consumableslistParam
     * @param request
     * @return
     */
    @RequestMapping("/getTrainingByCourse")
    @ResponseBody
    public JsonResult getTrainingByCourse(ConsumableslistParam consumableslistParam, HttpServletRequest request) {
        System.out.println("getTrainingByCourse查询条件为");
        System.out.println(consumableslistParam);
        User user = (User) request.getSession().getAttribute("user");
        consumableslistParam.setDepartment(user.getDeptName());
        List<CourseDataResult> results = consumableslistService.getTrainingByCourse(consumableslistParam);
        if (results != null) {
            return new JsonResult(JsonResult.SUCCESS, results, "查询成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "无数据");
        }

    }

    /**
     * 针对专业不为空课程不为空的条件下查询实训项目
     *
     * @param consumableslist
     * @param request
     * @return
     */

    public JsonResult getTrainByCourse(Consumableslist consumableslist, HttpServletRequest request) {
        return new JsonResult(JsonResult.FALL, "", "无数据");
    }


    /**
     * 删除数据
     *
     * @param consumableslist
     * @return
     */
    @RequestMapping("/deleteData")
    @ResponseBody
    public JsonResult deleteData(Consumableslist consumableslist) {
        int result = consumableslistService.deleteData(consumableslist);
        if (result > 0) {
            return new JsonResult(JsonResult.SUCCESS, "", "删除成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "删除失败");
        }

    }

    /**
     * 批量删除
     *
     * @param consumableslistParam
     * @return
     */
    @RequestMapping("/deleteSelectData")
    @ResponseBody
    public JsonResult deleteSelectData(ConsumableslistParam consumableslistParam) {
        int result = consumableslistService.deleteSelectData(consumableslistParam);
        if (result > 0) {
            return new JsonResult(JsonResult.SUCCESS, "", "删除成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "删除失败");
        }
    }

    /**
     * 添加数据
     *
     * @param consumableslist
     * @param request
     * @return
     */
    @RequestMapping("/addConsumablesList")
    @ResponseBody
    public JsonResult addConsumablesList(Consumableslist consumableslist, HttpServletRequest request) {
        System.out.println(consumableslist);
        int result = consumableslistService.addConsumablesList(consumableslist, request);
        if (result > 0) {
            return new JsonResult(JsonResult.SUCCESS, "", "添加成功");
        }
        return new JsonResult(JsonResult.FALL, "", "添加失败,数据存在");
    }

    /**
     * 更新数据
     *
     * @param consumableslist
     * @return
     */
    @RequestMapping("/updateConsumablesList")
    @ResponseBody
    public JsonResult updateConsumablesList(Consumableslist consumableslist, HttpServletRequest request) {
        int resultNum = consumableslistService.updateConsumablesList(consumableslist, request);
        if (resultNum > 0) {
            return new JsonResult(JsonResult.SUCCESS, "", "更新成功");
        }
        return new JsonResult(JsonResult.FALL, "", "更新失败,数据存在");
    }

    /**
     * 重新提交被打回的单条数据
     *
     * @param consumableslist
     * @return
     */
    @RequestMapping("/modifyConsumablesList")
    @ResponseBody
    public JsonResult modifyConsumablesList(Consumableslist consumableslist, HttpServletRequest request) {
        int resultNum = consumableslistService.modifyConsumablesList(consumableslist, request);
        if (resultNum > 0) {
            return new JsonResult(JsonResult.SUCCESS, "", "更新成功");
        }
        return new JsonResult(JsonResult.FALL, "", "更新失败,数据已存在");
    }

    /**
     * 重新提交被打回的批量数据
     *
     * @return
     */
    @RequestMapping("/quantityModifyConsumablesList")
    @ResponseBody
    public JsonResult quantityModifyConsumablesList(@RequestParam("data") String data) {
        // 将JSON字符串转换为数组
        String[] dataArray = new Gson().fromJson(data, String[].class);
        for (String item : dataArray) {
            consumableslistService.quantityModifyConsumablesList(item);
        }
        return new JsonResult(JsonResult.SUCCESS, "", "更新成功");
    }


    /**
     * 添加时查询课程管理
     * 查询课程名和实训名
     *
     * @return
     */
    @RequestMapping("/selectCourseData")
    @ResponseBody
    public JsonResult selectCourseData(MajorCourse majorCourse, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        majorCourse.setDepartment(user.getDeptName());
        majorCourse.setOperatoraccount(user.getAccount());
        List<Course> result = consumableslistService.selectCourseData(majorCourse);
        return new JsonResult(JsonResult.SUCCESS, result, "查询成功");
    }


    /**
     * 修改数据时查询要修改的数据
     *
     * @param consumableslist
     * @return
     */
    @RequestMapping("/selectUpdateData")
    @ResponseBody
    public JsonResult selectUpdateData(Consumableslist consumableslist) {
        Consumableslist resultData = consumableslistService.selectUpdateData(consumableslist);
        if (resultData != null) {
            return new JsonResult(JsonResult.SUCCESS, resultData, "查询成功");
        } else {
            return new JsonResult(JsonResult.FALL, "", "查询失败");
        }
    }


    /**
     * excel耗材管理数据导出
     *
     * @param response
     * @param
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, ConsumableslistParam consumableslistParam) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode(String.format("%s耗材管理.", DateUtils.format(new Date(), "yyyy-MM-dd")), "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ExcelTypeEnum.XLSX);
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            EasyExcel.write(response.getOutputStream(), Consumableslist.class).sheet("测试").doWrite(consumableslistService.excelExport(consumableslistParam));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 耗材模板下载
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //要下载的文件地址
        String path = request.getSession().getServletContext().getRealPath("/download");
        String filename = "目录管理模板.xlsx";
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

    /**
     * excel导入
     *
     * @param file
     * @return
     * @throws IOException
     * @author 磊
     */
    @RequestMapping("/importExcel")
    @ResponseBody
    public Object importExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {

//        ---------------------------新增耗材重复校验,耗材属性校验,耗材类型校验,学期检验,字段重复校验功能
        List<Consumableslist> consumables = EasyExcel.read(file.getInputStream()).head(Consumableslist.class).sheet().doReadSync();
        System.out.println("excel接收的数据:");
        System.out.println(consumables);
        List<Consumableslist> filteredConsumables = consumables.stream()
                .filter(consumable ->
                        consumable.getAttribute() != null &&
                                consumable.getType() != null &&
                                consumable.getStandards() != null &&
                                consumable.getUnit() != null &&
                                consumable.getPrice() != null &&
                                consumable.getNumber() != null &&
                                consumable.getTraining() != null &&
                                consumable.getSemester() != null &&
                                consumable.getMark() != null &&
                                consumable.getDepartment() != null
                )
                .collect(Collectors.toList());
        System.out.println("修改后的数据(去除null数据)");
        System.out.println(filteredConsumables);
        Pattern attributePattern = Pattern.compile("^[12]$");
        Pattern semesterPattern = Pattern.compile("^[1-6](,[1-6]){0,4}$");
        Pattern typePattern = Pattern.compile("^[1-9]|10|11$");
        Pattern markPattern = Pattern.compile("[^()]*");
        Pattern trainingPattern = Pattern.compile("^[^,()]+(?:,[^,()]+)*$");
        Set<String> duplicateCheckSet = new HashSet<>(); // 用于存储已经出现过的组合字段数据
        for (Consumableslist consumable : filteredConsumables) {
            System.out.println(consumable);
            String attribute = consumable.getAttribute();
            String semester = consumable.getSemester();
            String type = consumable.getType();
            String mark = consumable.getMark();
            String department = consumable.getDepartment();
            String training = consumable.getTraining();
            if (semester == null || semester.trim().equals("") || !semesterPattern.matcher(semester).matches())
                return new JsonResult("2", "", "耗材名称为" + consumable.getName() + "的学期格式错误，请重新上传");
            else if (type == null || type.trim().equals("") || !typePattern.matcher(type).matches()) {
                return new JsonResult("2", "", "耗材名称为" + consumable.getName() + "的耗材类别格式错误，请重新上传");
            } else if (training == null || training.trim().equals("") || !trainingPattern.matcher(training).matches()) {
                return new JsonResult("2", "", "耗材名称为" + consumable.getName() + "的实训项目格式错误，请重新上传");
            } else if (attribute == null || attribute.trim().equals("") || !attributePattern.matcher(attribute).matches()) {
                return new JsonResult("2", "", "耗材名称为" + consumable.getName() + "的耗材属性格式错误，请重新上传");
            } else if (mark == null || mark.trim().equals("") || !markPattern.matcher(mark).matches()) {
                return new JsonResult("2", "", "所属专业格式错误或为空，请重新上传");
            } else if (consumableslistService.selectMajorByMark(new ConsumableslistParam(department, mark)) == 0) {
                return new JsonResult("2", "", "数据库中不存在'" + mark + "'专业,请重新上传");
            }
//            else if (consumableslistMapper.addReCheckData(consumable).size() != 0) {
//                return new JsonResult("2", "", "数据库中已存在'" + consumable.getName() + "'的相关数据,请重新上传");
//            }
            else {
                //判断是否有重复数据,若有则返回哪一个耗材名称重复了
                String compositeKey = consumable.getName() + "|" + consumable.getAttribute() + "|" + consumable.getType() + "|"
                        + consumable.getStandards() + "|" + consumable.getUnit() + "|" + consumable.getPrice() + "|"
                        + consumable.getNumber() + "|" + consumable.getTraining() + "|" + consumable.getSemester() + "|"
                        + consumable.getDepartment();
                if (duplicateCheckSet.contains(compositeKey)) {
                    // 存在完全相同的数据，返回重复数据信息
                    return new JsonResult("2", "", "excel表中存在完全相同的数据，重复数据：" + compositeKey);
                } else {
                    duplicateCheckSet.add(compositeKey);
                }
            }
        }
//        ---------------------------
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("session中的用户个人信息");
        System.out.println(user);
        //添加第一件事先删除原先的错误信息
        ErrorExcel errorExcel = new ErrorExcel();
        errorExcel.setUserid(user.getUserId().toString());
        consumableslistService.deleteErrorExcel(errorExcel);
        EasyExcel.read(file.getInputStream(), Consumableslist.class, new ConsumablesListListener(consumableslistService, request, consumableslistMapper)).sheet().doRead(); //sheet()默认是0
        List<ErrorExcel> resultData = consumableslistService.getErrorExcel(errorExcel);
        if (resultData.size() != 0) {
            return new JsonResult(JsonResult.FALL, "", "含有错误数据，请下载错误数据");
        }
        return new JsonResult(JsonResult.SUCCESS, "", "导入成功");
    }

    /**
     * excel导入自定义
     *
     * @param file
     * @return
     * @throws IOException
     * @author 磊
     */
    @RequestMapping("/importExcel01")
    @ResponseBody
    public Object importExcel01(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        ---------------------------
        List<Consumableslist01> consumables = EasyExcel.read(file.getInputStream()).head(Consumableslist01.class).sheet().doReadSync();
        for (Consumableslist01 consumable : consumables) {
            String mark = consumable.getMark();
            if (mark.contains("，")) {
                mark = mark.replace("，", ",");
                consumable.setMark(mark);
            }
            if (mark.contains("（")) {
                mark = mark.replace("(", "（");
                consumable.setMark(mark);
            }
            if (mark.contains(")")) {
                mark = mark.replace(")", "）");
                consumable.setMark(mark);
            }
            System.out.println(consumable);
            consumableslistService.updateMark(consumable);
        }

//        ---------------------------
        return new JsonResult("2", "", "OKOK");
    }

    /**
     * 下载错误数据excel
     *
     * @param response
     * @param request
     */
    @RequestMapping("/downloadErrorExcel")
    @ResponseBody
    public void downloadErrorExcel(HttpServletResponse response, HttpServletRequest request) {
        consumableslistService.downloadErrorExcel(request, response);
    }


    /**
     * 目录维护时间管理设置
     *
     * @param consumableslistParam
     * @return
     */
    @RequestMapping("/timeSetting")
    @ResponseBody
    public JsonResult timeSetting(ConsumableslistParam consumableslistParam, HttpServletRequest request) {
        int result = consumableslistService.timeSetting(consumableslistParam, request);
        if (result == 1) {
            return new JsonResult(JsonResult.SUCCESS, "", "设置成功");
        }
        return new JsonResult(JsonResult.FALL, "", "设置失败");
    }


    /**
     * 维护时间查询
     *
     * @return
     */
    @RequestMapping("/selectTimeResult")
    @ResponseBody
    public JsonResult selectTimeResult() {
        ConsumableslistParam result = consumableslistService.selectTimeResult();
        TimeResult timeResult = new TimeResult();
        timeResult.setBeginTime(result.getBeginTime());
        timeResult.setEndTime(result.getEndTime());
        return new JsonResult(JsonResult.SUCCESS, timeResult, "查询成功");
    }


    /**
     * 审核提交
     *
     * @param consumableslistParam
     * @return
     */
    @RequestMapping("/auditSubmit")
    @ResponseBody
    public JsonResult auditSubmit(ConsumableslistParam consumableslistParam, HttpServletRequest request) {
        int result = consumableslistService.auditSubmit(consumableslistParam, request);
        if (result > 0) {
            return new JsonResult(JsonResult.SUCCESS, "", "提交成功");
        }
        return new JsonResult(JsonResult.FALL, "", "提交失败");
    }


    /**
     * 用户权限查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/userData")
    @ResponseBody
    public JsonResult userData(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("userData中user的=============================");
        System.out.println(user);
        UserResult result = new UserResult();
        result.setRole(user.getRoleName());
        System.out.println("当前职位:" + user.getRoleName());
        result.setName(user.getName());
        System.out.println("当前用户姓名:" + user.getName());
        result.setDepartment(user.getDeptName());
        System.out.println("当前用户所在学院:" + user.getDeptName());
        return new JsonResult(JsonResult.SUCCESS, result, "success");
    }

}
