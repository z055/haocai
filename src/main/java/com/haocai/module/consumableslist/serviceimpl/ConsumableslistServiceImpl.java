package com.haocai.module.consumableslist.serviceimpl;

import com.alibaba.excel.EasyExcel;
import com.haocai.module.consumableslist.dao.ConsumableslistMapper;
import com.haocai.module.consumableslist.param.ConsumableslistParam;

import com.haocai.module.consumableslist.param.MajorCourse;
import com.haocai.module.consumableslist.result.CourseDataResult;
import com.haocai.module.consumableslist.service.ConsumableslistService;
import com.haocai.module.consumableslist.vo.Consumableslist;
import com.haocai.module.consumableslist.vo.Consumableslist01;
import com.haocai.module.consumableslist.vo.ErrorExcel;
import com.haocai.module.course.vo.Course;
import com.haocai.module.system.vo.User;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.aspectj.weaver.ast.Literal;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ConsumableslistServiceImpl implements ConsumableslistService {

    @Autowired
    ConsumableslistMapper consumableslistMapper;

    /**
     * 查询数据数量
     *
     * @param consumableslistParam
     * @return
     * @author 磊
     */
    @Override
    public int dataCount(ConsumableslistParam consumableslistParam, HttpServletRequest request) {
        System.out.println("dataCount查询条件为");
        System.out.println(consumableslistParam);
        User user = (User) request.getSession().getAttribute("user");
        String userRole = user.getRoleName();
        if (userRole.indexOf("专业负责人") != -1) {
            //可能是个错误的决定
//            consumableslistParam.setOperatoraccount(user.getAccount());
            consumableslistParam.setDepartment(user.getDeptName());
        } else if (userRole.indexOf("二级学院管理员") != -1) {
            //二级管理员的特别限制:即使条件全空也是在本学院下查询
            consumableslistParam.setDepartment(user.getDeptName());
        } else if (userRole.indexOf("二级学院教学院长") != -1) {
            //没有目录管理界面
        } else if (userRole.indexOf("教务处管理员") != -1) {

        } else if (userRole.indexOf("督导员") != -1) {

        } else if (userRole.indexOf("超级管理员") != -1) {

        } else {
            consumableslistParam.setDepartment(user.getDeptName());
        }
        int count = consumableslistMapper.dataCount(consumableslistParam);
        System.out.println(count);
        return count;
    }

    /**
     * 查询数据
     *
     * @param consumableslistParam
     * @return
     */
    @Override
    public List<Consumableslist> getAllConsumablesList(ConsumableslistParam consumableslistParam, HttpServletRequest request) {
        consumableslistParam.setPage((consumableslistParam.getPage() - 1) * consumableslistParam.getLimit());
        System.out.println("getAllConsumablesList查询条件为");
        System.out.println(consumableslistParam);
        List<Consumableslist> result = consumableslistMapper.getAllConsumablesList(consumableslistParam);
        List<Consumableslist> endData = new ArrayList<>();
        for (Consumableslist resultData : result) {
            switch (resultData.getAttribute()) {
                case "1":
                    resultData.setAttribute("低值易耗品");
                    break;
                case "2":
                    resultData.setAttribute("低值耐用品");
                    break;
                default:
                    resultData.setAttribute("其他");
            }
            switch (resultData.getType()) {
                case "1":
                    resultData.setType("酒店食品");
                    break;
                case "2":
                    resultData.setType("金属材料");
                    break;
                case "3":
                    resultData.setType("电子电器");
                    break;
                case "4":
                    resultData.setType("建筑材料");
                    break;
                case "5":
                    resultData.setType("服装服饰");
                    break;
                case "6":
                    resultData.setType("文化绘画");
                    break;
                case "7":
                    resultData.setType("工具仪表");
                    break;
                case "8":
                    resultData.setType("化工药品");
                    break;
                case "9":
                    resultData.setType("气体类");
                    break;
                case "10":
                    resultData.setType("维修保养");
                    break;
                case "11":
                    resultData.setType("其他");
                    break;
                default:
                    resultData.setType("type数据类型异常,请联系管理员");
            }
            switch (resultData.getStatus()) {
                case "0":
                    resultData.setStatus("未提交");
                    break;
                case "1":
                    resultData.setStatus("通过");
                    break;
                case "2":
                    resultData.setStatus("被退回");
                    break;
                case "3":
                    resultData.setStatus("审核中");
                    break;
            }
            endData.add(resultData);
        }
        System.out.println("getAllConsumablesList的endData");
        System.out.println(endData);
        return endData;
    }

    /**
     * 删除数据
     *
     * @param consumableslist
     * @return
     */
    @Override
    public int deleteData(Consumableslist consumableslist) {
        int result = consumableslistMapper.deleteData(consumableslist);
        int resultReview = consumableslistMapper.deleteReviewData(consumableslist);
        if (result > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 查询课程表里的专业
     *
     * @param consumableslistParam
     * @return
     */
    public List<CourseDataResult> getCourseMajor(ConsumableslistParam consumableslistParam) {
        List<CourseDataResult> result = consumableslistMapper.getCourseMajor(consumableslistParam);
        return result;
    }

    /**
     * 根据专业去查询课程表里课程
     *
     * @param consumableslistParam
     * @return
     */
    public List<CourseDataResult> getCourseName(ConsumableslistParam consumableslistParam) {
        List<CourseDataResult> result = consumableslistMapper.getCourseName(consumableslistParam);
        return result;
    }

    /**
     * 查询课程表里实训项目
     *
     * @param consumableslistParam
     * @return
     */
    public List<CourseDataResult> getTrainingByCourse(ConsumableslistParam consumableslistParam) {
        List<CourseDataResult> result = consumableslistMapper.getTrainingByCourse(consumableslistParam);
        return result;
    }


    /**
     * 批量删除
     *
     * @param consumableslistParam
     * @return
     */
    @Override
    public int deleteSelectData(ConsumableslistParam consumableslistParam) {
        String reviewIds = consumableslistParam.getReview_ids();
        String[] strArr = reviewIds.split(",");
        consumableslistParam.setReviewIds(strArr);
        int resultConsumeList = consumableslistMapper.deleteSelectData(consumableslistParam);
        int resultReview = consumableslistMapper.deleteSelectReviewData(consumableslistParam);
        if (resultConsumeList > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 查询课程管理
     * 课程名和实训名
     * 用于添加或修改时选择
     *
     * @return
     */
    @Override
    public List<Course> selectCourseData(MajorCourse majorCourse) {
        List<Course> list = consumableslistMapper.selectCourseData(majorCourse);
        return list;
    }


    /**
     * 耗材编号查询保证数据唯一
     *
     * @param consumableslist
     * @return
     */
    @Override
    public Consumableslist selectNoData(Consumableslist consumableslist) {
        Consumableslist result = consumableslistMapper.selectNoData(consumableslist);
        return result;
    }

    /**
     * 添加数据
     *
     * @param consumableslist
     * @param request
     * @return
     */
    @Override
    public int addConsumablesList(Consumableslist consumableslist, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //查重
        List<Consumableslist> result = consumableslistMapper.addReCheckData(consumableslist);
        if (result.size() == 0) {
            //查询耗材编码
            Consumableslist noData = consumableslistMapper.selectNoData(consumableslist);
            if (noData != null) {
                consumableslist.setNo(noData.getNo());
            } else {
                consumableslist.setNo(UUID.randomUUID().toString().replace("-", ""));
            }
            consumableslist.setReview_id(UUID.randomUUID().toString().replace("-", ""));
            consumableslist.setOperator(user.getRoleName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
            String datas = sdf.format(new Date());
            consumableslist.setOperatordate(datas);
            consumableslist.setOperatoraccount(user.getAccount());
            consumableslist.setOperatorname(user.getName());
            consumableslist.setStatus("0"); //设置未提交状态
            consumableslist.setStatus_z("0");//设置审核流程状态为未提交
            //调用添加方法
            int resultNum = consumableslistMapper.addConsumablesList(consumableslist);
            return resultNum;
        }
        return 0;
    }

    /**
     * 修改数据
     *
     * @param consumableslist
     * @param request
     * @return
     */
    @Override
    public int updateConsumablesList(Consumableslist consumableslist, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //查重
//        Consumableslist result=consumableslistMapper.addRecheck(consumableslist);

        //查询耗材编码
        Consumableslist noData = consumableslistMapper.selectNoData(consumableslist);
        if (noData == null) {
            consumableslist.setNo(UUID.randomUUID().toString().replace("-", ""));
        } else if (noData != null) {
            consumableslist.setNo(noData.getNo());
        }
        consumableslist.setOperator(user.getRoleName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        String datas = sdf.format(new Date());
        consumableslist.setOperatordate(datas);
        consumableslist.setOperatorname(user.getName());
        int resultNum = consumableslistMapper.updateConsumablesList(consumableslist);
        if (resultNum > 0) {
            return resultNum;
        }
        return 0;
    }

    private void modifyReviewState(String review_id) {
        consumableslistMapper.modifyReviewState01(review_id);
        consumableslistMapper.modifyReviewState02(review_id);
        consumableslistMapper.modifyReviewState03(review_id);
    }

    /**
     * 打回数据重新提交
     *
     * @param consumableslist
     * @param request
     * @return
     * @author 磊
     */
    @Override
    public int modifyConsumablesList(Consumableslist consumableslist, HttpServletRequest request) {
        int resultNum = updateConsumablesList(consumableslist, request);
        consumableslistMapper.modifyConsumablesListState(consumableslist);
        modifyReviewState(consumableslist.getReview_id());
        if (resultNum > 0) {
            return resultNum;
        }
        return 0;
    }

    /**
     * 重新提交被打回的批量数据
     *
     * @param review_id
     * @return
     * @author 磊
     */
    @Override
    public int quantityModifyConsumablesList(String review_id) {
        int count = consumableslistMapper.modifyConsumablesListStateByReviewId(review_id);
        modifyReviewState(review_id);
        return count;
    }

    /**
     * 修改数据查询要修改的数据
     *
     * @param consumableslist
     * @return
     */
    @Override
    public Consumableslist selectUpdateData(Consumableslist consumableslist) {
        Consumableslist resultData = consumableslistMapper.selectUpdateData(consumableslist);
        switch (resultData.getAttribute()) {
            case "1":
                resultData.setAttribute("低值易耗品");
                break;
            case "2":
                resultData.setAttribute("低值耐用品");
                break;
            default:
                resultData.setAttribute("其他");
        }

        switch (resultData.getType()) {
            case "1":
                resultData.setType("酒店食品");
                break;
            case "2":
                resultData.setType("金属材料");
                break;
            case "3":
                resultData.setType("电子电器");
                break;
            case "4":
                resultData.setType("建筑材料");
                break;
            case "5":
                resultData.setType("服装服饰");
                break;
            case "6":
                resultData.setType("文化绘画");
                break;
            case "7":
                resultData.setType("工具仪表");
                break;
            case "8":
                resultData.setType("化工药品");
                break;
            case "9":
                resultData.setType("气体类");
                break;
            case "10":
                resultData.setType("维修保养");
                break;
            case "11":
                resultData.setType("其他");
                break;
            default:
                resultData.setType("其他的");

        }
        switch (resultData.getStatus()) {
            case "0":
                resultData.setStatus("未提交");
                break;
            case "1":
                resultData.setStatus("通过");
                break;
            case "2":
                resultData.setStatus("被退回");
                break;
            case "3":
                resultData.setStatus("审核中");
                break;
        }
        return resultData;
    }

    /**
     * excel 导出
     *
     * @param consumableslistParam
     * @return
     */
    @Override
    public List<Consumableslist> excelExport(ConsumableslistParam consumableslistParam) {
        List<Consumableslist> result = consumableslistMapper.excelExport(consumableslistParam);
        List<Consumableslist> endData = new ArrayList<>();
        for (Consumableslist resultData : result) {

            switch (resultData.getAttribute()) {
                case "1":
                    resultData.setAttribute("低值易耗品");
                    break;
                case "2":
                    resultData.setAttribute("低值耐用品");
                    break;
                default:
                    resultData.setAttribute("其他");
            }

            switch (resultData.getType()) {
                case "1":
                    resultData.setType("酒店食品");
                    break;
                case "2":
                    resultData.setType("金属材料");
                    break;
                case "3":
                    resultData.setType("电子电器");
                    break;
                case "4":
                    resultData.setType("建筑材料");
                    break;
                case "5":
                    resultData.setType("服装服饰");
                    break;
                case "6":
                    resultData.setType("文化绘画");
                    break;
                case "7":
                    resultData.setType("工具仪表");
                    break;
                case "8":
                    resultData.setType("化工药品");
                    break;
                case "9":
                    resultData.setType("气体类");
                    break;
                case "10":
                    resultData.setType("维修保养");
                    break;
                case "11":
                    resultData.setType("其他");
                    break;
                default:
                    resultData.setType("其他的");

            }
            switch (resultData.getStatus()) {
                case "0":
                    resultData.setStatus("未提交");
                    break;
                case "1":
                    resultData.setStatus("通过");
                    break;
                case "2":
                    resultData.setStatus("被退回");
                    break;
                case "3":
                    resultData.setStatus("审核中");
                    break;
            }

            endData.add(resultData);
        }
        return endData;
    }

    /**
     * 目录维护
     * 时间开放管理
     *
     * @param consumableslistParam
     * @return
     */
    @Override
    public int timeSetting(ConsumableslistParam consumableslistParam, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        consumableslistParam.setOperator(user.getRoleName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        String datas = sdf.format(new Date());
        consumableslistParam.setOperatordate(datas);
        consumableslistParam.setOperatorname(user.getName());
        int result = consumableslistMapper.timeSetting(consumableslistParam);
        return result;
    }

    /**
     * 时间开放管理查询
     *
     * @return
     */
    @Override
    public ConsumableslistParam selectTimeResult() {
        ConsumableslistParam result = consumableslistMapper.selectTimeResult();
        return result;
    }


    /**
     * 审核提交
     *
     * @param consumableslistParam
     * @param request
     * @return
     */
    @Override
    public int auditSubmit(ConsumableslistParam consumableslistParam, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String reviewIDs = consumableslistParam.getReview_ids();
        String[] strArr = reviewIDs.split(",");
        consumableslistParam.setReviewIds(strArr);
        //设置提交状态（审核中）
        consumableslistParam.setStatus("3");
        //设置审核状态为1（提交）
        consumableslistParam.setStatus_z("1");
        int result = consumableslistMapper.auditSubmit(consumableslistParam);
        return result;
    }


    /**
     * excel导入
     *
     * @param consumableslist
     * @return
     */
    @Override
    public int importExcel(Consumableslist consumableslist, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //查询耗材编码
        Consumableslist noData = consumableslistMapper.selectNoData(consumableslist);
        if (noData != null) {
            consumableslist.setNo(noData.getNo());
        } else {
            consumableslist.setNo(UUID.randomUUID().toString().replace("-", ""));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        String datas = sdf.format(new Date());
        consumableslist.setReview_id(UUID.randomUUID().toString().replace("-", ""));
        consumableslist.setOperatordate(datas);
        consumableslist.setOperator(user.getRoleName());
        consumableslist.setOperatoraccount(user.getAccount());
        consumableslist.setOperatorname(user.getName());
        consumableslist.setStatus("0");
        consumableslist.setStatus_z("0");
        //调用添加方法
        int resultNum = consumableslistMapper.addConsumablesList(consumableslist);
        return resultNum;

    }


    /**
     * 向数据库插入错误excel
     *
     * @param errorExcel
     * @return
     */
    @Override
    public int addErrorExcel(ErrorExcel errorExcel) {
        int result = consumableslistMapper.addErrorExcel(errorExcel);
        return result;
    }

    /**
     * 删除错误数据excel
     *
     * @param errorExcel
     * @return
     */
    @Override
    public int deleteErrorExcel(ErrorExcel errorExcel) {
        int result = consumableslistMapper.deleteErrorExcel(errorExcel);
        return result;
    }

    /**
     * 查询错误excel
     *
     * @param errorExcel
     * @return
     */
    @Override
    public List<ErrorExcel> getErrorExcel(ErrorExcel errorExcel) {
        List<ErrorExcel> result = consumableslistMapper.getErrorExcel(errorExcel);
        return result;
    }

    /**
     * excel录入根据实训名称查课程表是否存在
     *
     * @param consumableslist
     * @return
     */
    @Override
    public List<Course> selectCourseByTraining(Consumableslist consumableslist) {
        List<Course> result = consumableslistMapper.selectCourseByTraining(consumableslist);
        return result;
    }

    /**
     * 将错误数据以excel形式下载
     *
     * @param request
     * @param response
     */
    @Override
    public void downloadErrorExcel(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        ErrorExcel errorExcel = new ErrorExcel();
        errorExcel.setUserid(user.getUserId().toString());
        List<ErrorExcel> list = consumableslistMapper.getErrorExcel(errorExcel);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");//防止下载的文件名字乱码
        try {
            response.setHeader("Content-disposition", "attachment;filename=error_" + System.currentTimeMillis() + ".xlsx");
            EasyExcel.write(response.getOutputStream(), ErrorExcel.class).sheet("错误数据").doWrite(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMark(Consumableslist01 consumable) {
        consumableslistMapper.updateMark(consumable);
    }

    @Override
    public List<CourseDataResult> getUserMajor(String account) {
        return consumableslistMapper.getUserMajor(account);
    }

    @Override
    public int selectMajorByMark(ConsumableslistParam consumableslistParam) {
        return consumableslistMapper.selectMajorByMark(consumableslistParam);
    }

    @Override
    public List<CourseDataResult> initMajorSelect(ConsumableslistParam consumableslistParam) {
        return consumableslistMapper.initMajorSelect(consumableslistParam);
    }

    @Override
    public List<MajorCourse> addExistCourse(MajorCourse majorCourse) {
        return consumableslistMapper.addExistCourse(majorCourse);
    }


}
