package com.haocai.module.consumableslist.serviceimpl;

import com.haocai.module.consumableslist.dao.ReviewMapper;
import com.haocai.module.consumableslist.param.ReviewParam;
import com.haocai.module.consumableslist.result.CourseDataResult;
import com.haocai.module.consumableslist.service.ReviewService;
import com.haocai.module.consumableslist.vo.Consumableslist;
import com.haocai.module.consumableslist.vo.ReviewTable;
import com.haocai.module.system.vo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
//    /**
//     * 查询当前用户需要审核的数量
//     *
//     * @param reviewParam
//     * @param request
//     * @return
//     */
//    @Override
//    public int getAllReviewDataCount(ReviewParam reviewParam, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        System.out.println("目录审核查询的角色为");
//        System.out.println(user.getRoleName());
//        System.out.println("该查询账号为:");
//        System.out.println(user.getUserId());
//        //获取当前用户账号(工号)
////        String account = user.getAccount();
////        long userId = user.getUserId();
////        reviewParam.setRe_pe_id(String.valueOf(userId));
//        String status = reviewParam.getStatus();
//        System.out.println("status" + status);
//        String a = "";
//        if (a.equals(status)) {
//            status = null;
//            System.out.println("status" + status);
//            reviewParam.setStatus(status);
//        }
//        //定义一个全局返回变量
//        int resultCount = 0;
//        String training = reviewParam.getTraining();
//        List<ReviewTable> resultMax = new ArrayList<>();
//        if (training == null) {
//            training = "";
//        }
//        if (training.equals("")) {
//            System.out.println("------impl简单查询");
//            System.out.println("账号：" + reviewParam.getRe_pe_id());
//            System.out.println("状态：" + reviewParam.getStatus());
//            System.out.println(reviewParam.getDepartment());
//            resultCount = reviewMapper.getAllReviewDataCount(reviewParam);
//            System.out.println("数据数量：" + resultCount);
//        } else {
//            String[] strArr = training.split(",");
//            for (int i = 0; i < strArr.length; i++) {
//                reviewParam.setTraining(strArr[i]);
//                List<ReviewTable> results = reviewMapper.getAllReviewDataCountByMajor(reviewParam);
//                for (ReviewTable selectData : results) {
//                    boolean flag = true;
//                    for (ReviewTable reCheckData : resultMax) {
//                        if (reCheckData.getId().equals(selectData.getId())) {
//                            flag = false;
//                            break;
//                        }
//                    }
//                    if (flag) {
//                        resultMax.add(selectData);
//                    }
//                }
//            }
//            resultCount = resultMax.size();
//        }
//
//        return resultCount;
//    }

    /**
     * 查询当前用户需要审核的数量
     *
     * @param reviewParam
     * @param request
     * @return
     */
    @Override
    public int getAllReviewDataCount(ReviewParam reviewParam, HttpServletRequest request) {

        //定义一个全局返回变量
        int resultCount = 0;
        System.out.println("count------impl简单查询");
        System.out.println("账号：" + reviewParam.getRe_pe_id());
        resultCount = reviewMapper.getAllReviewDataCount(reviewParam);
        System.out.println("数据数量：" + resultCount);
        return resultCount;
    }

    /**
     * 查询当前用户需要审核的全部数据
     *
     * @param reviewParam
     * @param request
     * @return
     */
    @Override
    public List<ReviewTable> getAllReviewData(ReviewParam reviewParam, HttpServletRequest request) {
        reviewParam.setPage((reviewParam.getPage() - 1) * reviewParam.getLimit());//设置页数
        List<ReviewTable> resultMax = reviewMapper.getAllReviewData(reviewParam);
        List<ReviewTable> endData = new ArrayList<>();
        for (ReviewTable resultData : resultMax) {
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
                    resultData.setStatus("待审核");
                    break;
                case "1":
                    resultData.setStatus("已通过");
                    break;
                case "2":
                    resultData.setStatus("已回退");
                    break;
                case "3":
                    resultData.setStatus("被回退");
                    break;
            }

            endData.add(resultData);
        }
        return endData;
    }

//    /**
//     * 查询当前用户需要审核的全部数据
//     *
//     * @param reviewParam
//     * @param request
//     * @return
//     */
//    @Override
//    public List<ReviewTable> getAllReviewData(ReviewParam reviewParam, HttpServletRequest request) {
//        reviewParam.setPage((reviewParam.getPage() - 1) * reviewParam.getLimit());//设置页数
//
//        System.out.println("目录审核查询的角色为");
//        User user = (User) request.getSession().getAttribute("user");
//        System.out.println(user.getRoleName());
//        String training = reviewParam.getTraining();
//        List<ReviewTable> resultMax = new ArrayList<>();
//        if (training == null) {
//            training = "";
//        }
//        if (training.equals("")) {
//            resultMax = reviewMapper.getAllReviewData(reviewParam);
//        } else {
//            String[] strArr = training.split(",");
//            for (int i = 0; i < strArr.length; i++) {
//                reviewParam.setTraining(strArr[i]);
//                List<ReviewTable> results = reviewMapper.getAllReviewData(reviewParam);
//                for (ReviewTable selectData : results) {
//                    boolean flag = true;
//                    for (ReviewTable reCheckData : resultMax) {
//                        if (reCheckData.getId().equals(selectData.getId())) {
//                            flag = false;
//                            break;
//                        }
//                    }
//                    if (flag) {
//                        resultMax.add(selectData);
//                    }
//                }
//            }
//        }
//
//        List<ReviewTable> endData = new ArrayList<>();
//        for (ReviewTable resultData : resultMax) {
//            switch (resultData.getAttribute()) {
//                case "1":
//                    resultData.setAttribute("低值易耗品");
//                    break;
//                case "2":
//                    resultData.setAttribute("低值耐用品");
//                    break;
//                default:
//                    resultData.setAttribute("其他");
//            }
//
//            switch (resultData.getType()) {
//                case "1":
//                    resultData.setType("酒店食品");
//                    break;
//                case "2":
//                    resultData.setType("金属材料");
//                    break;
//                case "3":
//                    resultData.setType("电子电器");
//                    break;
//                case "4":
//                    resultData.setType("建筑材料");
//                    break;
//                case "5":
//                    resultData.setType("服装服饰");
//                    break;
//                case "6":
//                    resultData.setType("文化绘画");
//                    break;
//                case "7":
//                    resultData.setType("工具仪表");
//                    break;
//                case "8":
//                    resultData.setType("化工药品");
//                    break;
//                case "9":
//                    resultData.setType("气体类");
//                    break;
//                case "10":
//                    resultData.setType("维修保养");
//                    break;
//                case "11":
//                    resultData.setType("其他");
//                    break;
//                default:
//                    resultData.setType("其他的");
//
//            }
//            switch (resultData.getStatus()) {
//                case "0":
//                    resultData.setStatus("待审核");
//                    break;
//                case "1":
//                    resultData.setStatus("已通过");
//                    break;
//                case "2":
//                    resultData.setStatus("已回退");
//                    break;
//                case "3":
//                    resultData.setStatus("被回退");
//                    break;
//            }
//
//            endData.add(resultData);
//        }
//        return endData;
//    }

    /**
     * 审核时查询具体要审核的数据
     *
     * @param reviewParam
     * @return
     */
    @Override
    public List<Consumableslist> getReviewData(ReviewParam reviewParam) {
        String review_ids = reviewParam.getReview_ids();
        String[] strArr = review_ids.split(",");
        reviewParam.setReviewIds(strArr);
        List<Consumableslist> list = reviewMapper.getReviewData(reviewParam);
        List<Consumableslist> endData = new ArrayList<>();
        for (Consumableslist resultData : list) {
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
            endData.add(resultData);
        }
        return endData;
    }


    /**
     * 查询学院下的具体专业
     *
     * @param reviewParam
     * @return
     */
    public List<CourseDataResult> getCourseMajorData(ReviewParam reviewParam) {
        List<CourseDataResult> result = reviewMapper.getCourseMajorData(reviewParam);
        return result;
    }

    /**
     * 查询学院下的实训项目
     *
     * @param reviewParam
     * @return
     */
    public List<CourseDataResult> getTrainingByCourseData(ReviewParam reviewParam) {
        List<CourseDataResult> results = reviewMapper.getTrainingByCourseData(reviewParam);
        return results;
    }

    /**
     * 审核通过
     *
     * @param reviewParam
     * @return
     */
    @Override
    public int reviewPass(ReviewParam reviewParam) {
        String ids = reviewParam.getIds();
        String[] strArr = ids.split(",");
        Consumableslist consumableslist = new Consumableslist();
        for (int i = 0; i < strArr.length; i++) {
            reviewParam.setId(strArr[i]);
            List<ReviewTable> ReviewTableData = reviewMapper.getReviewTableData(reviewParam);
            for (ReviewTable reviewData : ReviewTableData) {
                reviewParam.setRe_pe_id(reviewData.getAfter_re());
                reviewParam.setAfter_re(reviewData.getAfter_re());
                consumableslist.setReview_id(reviewData.getReview_id());
                reviewParam.setRole(reviewData.getRole());
                reviewParam.setReview_id(reviewData.getReview_id());
            }

            if (reviewParam.getAfter_re() == null || reviewParam.getAfter_re() == "") {
                //下一级不在有审核人（当前用户为最终审核人）
                reviewParam.setStatus("1");
                consumableslist.setStatus_z("6");
                consumableslist.setStatus("1");
                int resultMe = reviewMapper.reviewUpdateMe(reviewParam);
                int resultConsumList = reviewMapper.reviewUpdateConsumList(consumableslist);
            } else {
                //下一级还有审核员
                //为下一级设置待审核状态
                reviewParam.setStatus("0");
                //修改下一级状态
                int resultOther = reviewMapper.reviewUpdateOther(reviewParam);
                //为自己设置通过状态
                reviewParam.setStatus("1");
                //修改自己状态
                int resultMe = reviewMapper.reviewUpdateMe(reviewParam);
                //判断当前审核人的角色并修改目录状态
                if (reviewParam.getRole().equals("二级学院管理员")) {
                    consumableslist.setStatus_z("2");
                } else if (reviewParam.getRole().equals("二级学院教学院长")) {
                    consumableslist.setStatus_z("4");
                }
                int resultConsumList = reviewMapper.reviewUpdateConsumList(consumableslist);
            }

        }
        return 1;

    }

    /**
     * 审核退回原本
     * @param reviewParam
     * @return
     */
//    @Override
//    public int reviewBack(ReviewParam reviewParam) {
//        String ids=reviewParam.getIds();
//        String[] strArr=ids.split(",");
//        String opinion=reviewParam.getAudit_body();
//        Consumableslist consumableslist=new Consumableslist();
//
//        for (int i=0;i<strArr.length;i++){
//            reviewParam.setId(strArr[i]);
//            List<ReviewTable> ReviewTableData=reviewMapper.getReviewTableData(reviewParam);
//
//            for (ReviewTable reviewData:ReviewTableData){
//                reviewParam.setRe_pe_id(reviewData.getBefore_re());
//                reviewParam.setBefore_re(reviewData.getBefore_re());
//                consumableslist.setReview_id(reviewData.getReview_id());
//                reviewParam.setRole(reviewData.getRole());
//                reviewParam.setReview_id(reviewData.getReview_id());
//                reviewParam.setStatus(reviewData.getStatus());
//                reviewParam.setAudit_body(opinion);
//            }
//            System.out.println("上1审核人："+reviewParam.getBefore_re());
//            //审核时数据状态
//            String oldStatus=reviewParam.getStatus();
//            System.out.println("======================");
//            System.out.println("======================");
//            System.out.println("======================");
//            System.out.println("1111111111111111111111");
//            System.out.println(consumableslist.getReview_id());
//            System.out.println(oldStatus);
//            if (reviewParam.getBefore_re()==null || reviewParam.getBefore_re()==""){
//                System.out.println("上1审核人："+reviewParam.getBefore_re());
//                //上一级不在有人（当前人员未最开始的审核人也是退回的最后一级）
//                if (oldStatus.equals("0")){
//                    System.out.println("上1审核人："+reviewParam.getBefore_re());
//                    //修改自己的状态为回退状态(2)
//                    reviewParam.setStatus("2");
//                    int resultMe=reviewMapper.reviewUpdateMe(reviewParam);
//                    //设置最终回退状态（给提交者看）
//                    consumableslist.setStatus("2");
//                    //设置二级学院管理员退回状态
//                    consumableslist.setStatus_z("3");
//                    int resultConsumList=reviewMapper.reviewUpdateConsumList(consumableslist);
//                    //添加审核意见
//                    int resultOpinion=reviewMapper.addReviewOpinion(reviewParam);
//
//                }else if(oldStatus.equals("3")){
//                    //设置自己的状态由被回退（3）改为回退（2）
//                    reviewParam.setStatus("2");
//                    int resultMe=reviewMapper.reviewUpdateMe(reviewParam);
//                    //不在需要修改目录流程状态，修改目录的最终状态给提交者看
//                    consumableslist.setStatus("2");
//                    int resultConsumList=reviewMapper.reviewUpdateConsumList(consumableslist);
//
//                }
//
//            }else {
//                //上一级还有审核员情况
//                if (oldStatus.equals("0")){
//                    if (reviewParam.getRole().equals("二级学院教学院长")){
//                        //修改自己的状态由待审核变为回退
//                        reviewParam.setStatus("2");
//                        int resultMe=reviewMapper.reviewUpdateMe(reviewParam);
//                        //修改上一级的通过状态变为被回退
//                        reviewParam.setStatus("3");
//                        int resultOther=reviewMapper.reviewUpdateOther(reviewParam);
//                        //修改目录表的流程状态为二级学院教学院长退回状态
//                        consumableslist.setStatus_z("5");
//                        int resultConsumList=reviewMapper.reviewUpdateConsumList(consumableslist);
//                        //添加审核意见
//                        int resultOpinion=reviewMapper.addReviewOpinion(reviewParam);
//
//                    }else if (reviewParam.getRole().equals("教务处管理员")){
//                        //修改自己的状态由待审核变为回退
//                        reviewParam.setStatus("2");
//                        int resultMe=reviewMapper.reviewUpdateMe(reviewParam);
//                        //修改上一级的状态由通过变为被回退
//                        reviewParam.setStatus("3");
//                        int resultOther=reviewMapper.reviewUpdateOther(reviewParam);
//                        //修改目录审核流程状态为当前人员（教务处管理员退回状态）
//                        consumableslist.setStatus_z("7");
//                        int resultConsumList=reviewMapper.reviewUpdateConsumList(consumableslist);
//                        //添加审核意见
//                        int resultOpinion=reviewMapper.addReviewOpinion(reviewParam);
//
//                    }
//                }else if (oldStatus.equals("3")){
//                    System.out.println("======================");
//                    System.out.println("======================");
//                    System.out.println("======================");
//                    System.out.println("55555555555555555555555");
//                    System.out.println(consumableslist.getReview_id());
//                    //这个角色一定是教务处管理员不需要在讨论了
//                    //修改自己的状态由被回退改为回退
//                    reviewParam.setStatus("2");
//                    int resultMe=reviewMapper.reviewUpdateMe(reviewParam);
//                    //修改上一级的状态由通过变为被回退
//                    reviewParam.setStatus("3");
//                    int resultOther=reviewMapper.reviewUpdateOther(reviewParam);
//                }
//            }
//        }
//
//        return 1;
//    }

    /**
     * 审核退回副本
     *
     * @param reviewParam
     * @return
     */
    @Override
    public int reviewBack(ReviewParam reviewParam) {
        String ids = reviewParam.getIds();
        String[] strArr = ids.split(",");
        String opinion = reviewParam.getAudit_body();
        Consumableslist consumableslist = new Consumableslist();

        for (int i = 0; i < strArr.length; i++) {
            reviewParam.setId(strArr[i]);
            List<ReviewTable> ReviewTableData = reviewMapper.getReviewTableData(reviewParam);
            for (ReviewTable reviewData : ReviewTableData) {
                reviewParam.setRe_pe_id(reviewData.getBefore_re());
                reviewParam.setBefore_re(reviewData.getBefore_re());
                consumableslist.setReview_id(reviewData.getReview_id());
                reviewParam.setRole(reviewData.getRole());
                reviewParam.setReview_id(reviewData.getReview_id());
                reviewParam.setStatus(reviewData.getStatus());
                reviewParam.setAudit_body(opinion);
            }
            System.out.println("上1审核人：" + reviewParam.getBefore_re());
            //审核时数据状态
            String oldStatus = reviewParam.getStatus();
            if (reviewParam.getBefore_re() == null || reviewParam.getBefore_re() == "") {
                System.out.println("上1审核人：" + reviewParam.getBefore_re());
                //上一级不在有人（当前人员未最开始的审核人也是退回的最后一级）
                if (oldStatus.equals("0")) {
                    System.out.println("上1审核人：" + reviewParam.getBefore_re());
                    //修改自己的状态为回退状态(2)
                    reviewParam.setReview_id(consumableslist.getReview_id());
                    reviewMapper.reviewUpdateThree(reviewParam);
                    //设置最终回退状态（给提交者看）
                    consumableslist.setStatus("2");
                    //设置二级学院管理员退回状态
                    consumableslist.setStatus_z("3");
                    reviewMapper.reviewUpdateConsumList(consumableslist);
                    //添加审核意见
                    System.out.println("二级管理员意见");
                    reviewMapper.addReviewOpinion(reviewParam);
                } else if (oldStatus.equals("3")) {
                    //设置自己的状态由被回退（3）改为回退（2）
                    reviewParam.setStatus("2");
                    reviewMapper.reviewUpdateMe(reviewParam);
                    //不在需要修改目录流程状态，修改目录的最终状态给提交者看
                    consumableslist.setStatus("2");
                    reviewMapper.reviewUpdateConsumList(consumableslist);
                }
            } else {
                //上一级还有审核员情况
                if (oldStatus.equals("0")) {
                    if (reviewParam.getRole().equals("二级学院教学院长")) {
                        reviewParam.setReview_id(consumableslist.getReview_id());
                        reviewMapper.reviewUpdateThree(reviewParam);
                        consumableslist.setStatus_z("5");
                        consumableslist.setStatus("2");
                        reviewMapper.reviewUpdateConsumList(consumableslist);
                        //添加审核意见
                        System.out.println("二级学院院长意见");
                        reviewMapper.addReviewOpinion(reviewParam);

                    } else if (reviewParam.getRole().equals("教务处管理员")) {
                        //修改自己的状态由待审核变为回退
                        reviewParam.setReview_id(consumableslist.getReview_id());
                        reviewMapper.reviewUpdateThree(reviewParam);
                        consumableslist.setStatus_z("7");
                        consumableslist.setStatus("2");
                        reviewMapper.reviewUpdateConsumList(consumableslist);
                        //添加审核意见
                        System.out.println("教务处管理员意见");
                        reviewMapper.addReviewOpinion(reviewParam);
                    }
                }
            }
        }
        return 1;
    }


    /**
     * 审核后修改自己的状态
     *
     * @param reviewParam
     * @return
     */
    @Override
    public int reviewUpdateMe(ReviewParam reviewParam) {

        return 0;
    }

    /**
     * 审核后修改其他人状态
     *
     * @param reviewParam
     * @return
     */
    @Override
    public int reviewUpdateOther(ReviewParam reviewParam) {
        return 0;
    }

    /**
     * 审核后修改目录表状态
     *
     * @param consumableslist
     * @return
     */
    @Override
    public int reviewUpdateConsumList(Consumableslist consumableslist) {
        return 0;
    }


}
